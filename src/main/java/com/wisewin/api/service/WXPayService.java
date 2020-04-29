package com.wisewin.api.service;

import com.wisewin.api.dao.KeyValueDAO;
import com.wisewin.api.dao.OrderDao;
import com.wisewin.api.dao.UserDao;
import com.wisewin.api.entity.bo.OrderItemBO;
import com.wisewin.api.entity.bo.UserBO;
import com.wisewin.api.util.IDBuilder;
import com.wisewin.api.util.OrderUtil;
import com.wisewin.api.util.wxUtil.WXPayRequest;
import com.wisewin.api.util.wxUtil.WXPayUtil;
import com.wisewin.api.util.wxUtil.config.WXConfig;
import com.wisewin.api.util.wxUtil.config.WXRequestConfig;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class WXPayService {
    @Resource
    OrderDao orderDao;
    @Resource
    KeyValueDAO keyValueDAO;
    @Resource
    UserDao userDao;

    //返回预支付信息  调用支付  插入预订单
    public Map<String, String> getUnifiedOrder(Integer userId, BigDecimal pyment, Integer rewardId, Integer commentId, Integer appraisalId) throws Exception {
        //获取请求参数
        Map<String, String> map = WXConfig.toMapJSAPI();
        //生成订单号
        String orderNumber = OrderUtil.getNumber();
        //传入自己生产的订单号
        map.put("out_trade_no", orderNumber);//订单号
        //金额
        map.put("total_fee", totalFee(pyment));
        //自定义请求参数
        map.put("attach", "");
        //回调地址
        map.put("notify_url", WXConfig.NOTIFY_URL_CURRENCY);
        //第一次签名
        String mapStr = WXPayUtil.generateSignedXml(map, WXConfig.KEY);
        //发送请求 获取到预支付信息
        String result = getCodeUrl(mapStr);
        //预支付订单信息
        Map<String, String> resultMap = WXPayUtil.xmlToMap(result);
        //初始化二次签名信息 用第一次请求拿到的信息中的
        Map<String, String> twoMap = new HashMap<String, String>();
        twoMap.put("appid", resultMap.get("appid"));
        twoMap.put("partnerid", resultMap.get("mch_id"));
        twoMap.put("prepayid", resultMap.get("prepay_id"));//第一次发送请求拿到的预支付id
        twoMap.put("noncestr", resultMap.get("nonce_str"));
        twoMap.put("timestamp", WXPayUtil.getCurrentTimestamp() + "");//时间戳
        twoMap.put("package", "Sign=WXPay");
        //第二次签名 把这个签名给安卓拉起支付请求
        String twoMapStr = WXPayUtil.generateSignedXml(twoMap, WXConfig.KEY);
        //给前端调用的Map
        twoMap = WXPayUtil.xmlToMap(twoMapStr);
        //存入自己的数据库
        if (twoMap != null && !twoMap.isEmpty()) {
            //插入预支付订单
            OrderItemBO orderItemBO = new OrderItemBO();
            orderItemBO.setOrderNumber(map.get("out_trade_no"));
            orderItemBO.setUserId(userId);//打赏人的id
            orderItemBO.setPayment(pyment);//打赏的实付金额
            orderItemBO.setState(0);//订单状态
            orderItemBO.setRewardId(rewardId);//被打赏人id
            //计算实际收入金额
            String value = keyValueDAO.getValueByKey("proportion");
            BigDecimal proportion=new BigDecimal(value);
            orderItemBO.setRewardAmount(proportion.multiply(pyment));

            orderItemBO.setCommentId(commentId);//评论id
            orderItemBO.setAppraisalId(appraisalId);//鉴定id
            orderItemBO.setPayPlatform(2);//支付方式
            orderItemBO.setPlatformNumber("");//支付流水号
            orderDao.addOrderDao(orderItemBO);
        }
        return twoMap;
    }

    //支付成功回调
    public Map<String, String> getOrderResult(HttpServletRequest request) throws Exception {
        //接受微信回调参数
        InputStream inStream = request.getInputStream();
        //转换为map
        Map<String, String> resultMap = inStreamToMap(inStream);
        //处理业务逻辑
        String return_code = resultMap.get("return_code");//状态
        String result_code = resultMap.get("result_code");//交易结果
        String out_trade_no = resultMap.get("out_trade_no");//商户订单号(自己生产的订单号)
        String sign = resultMap.get("sign");
        //验证签名
        if (WXPayUtil.isSignatureValid(resultMap, WXConfig.KEY)) {
            if (return_code.equals("SUCCESS") && result_code.equals("SUCCESS")) {//交易成功
                if (out_trade_no != null) {//商户订单号
                    //获取订单状态
                    OrderItemBO orderItemBO = orderDao.getOrderByOrderNumber(out_trade_no);
                    if(orderItemBO==null){
                        return null;
                    }
                    if (!"10".equals(orderItemBO.getState())) {
                        //成功的后续逻辑
                        orderItemBO.setState(10);
                        //修改订单状态 支付时间
                        orderItemBO.setCloseTime(resultMap.get("time_end"));
                        orderItemBO.setPlatformNumber(resultMap.get("transaction_id"));
                        orderDao.updOrderDao(orderItemBO);
                        //给被打赏人加余额
                        UserBO userBO=new UserBO();
                        userBO.setId(orderItemBO.getRewardId());
                        userBO.setMoney(orderItemBO.getRewardAmount());
                        userDao.updUserMoney(userBO);
                    }
                } else {
                    System.err.println("支付失败");
                }
            } else {
                System.err.println("交易标识不正确");
            }
        }
        return resultMap;
    }

    //请求统一下单
    public String getCodeUrl(String mapStr) throws Exception {
        WXRequestConfig wxPayConfig = new WXRequestConfig();
        WXPayRequest wxPayRequest = new WXPayRequest(wxPayConfig);
        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString();
        String resultXml = wxPayRequest.requestWithoutCert(WXConfig.PLACE_AN_ORDERAPI, uuidStr, mapStr, false);
        return resultXml;
    }

    //将InputStream转换为Map
    public Map<String, String> inStreamToMap(InputStream inStream) throws Exception {
        int _buffer_size = 1024;
        Map<String, String> resultMap = new HashMap<String, String>();
        if (inStream != null) {
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] tempBytes = new byte[_buffer_size];
            int count = -1;
            while ((count = inStream.read(tempBytes, 0, _buffer_size)) != -1) {
                outStream.write(tempBytes, 0, count);
            }
            tempBytes = null;
            outStream.flush();
            //将流转换成字符串
            String result = new String(outStream.toByteArray(), "UTF-8");
            //将字符串解析成MAP
            resultMap = WXPayUtil.xmlToMap(result);

        }
        return resultMap;
    }

    //转换金额 有 1.00 转为 100分
    private String totalFee(BigDecimal price) {
        BigDecimal setScale = price.setScale(2, BigDecimal.ROUND_HALF_DOWN);
        System.out.println(setScale);
        String str = setScale.multiply(new BigDecimal("100")).toString();
        BigDecimal b = new BigDecimal(str.substring(0, str.length() - 3));
        return b.toString();
    }

}


