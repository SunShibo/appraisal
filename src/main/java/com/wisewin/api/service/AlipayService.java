package com.wisewin.api.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.sun.prism.impl.paint.PaintUtil;
import com.wisewin.api.dao.KeyValueDAO;
import com.wisewin.api.dao.OrderItemDAO;
import com.wisewin.api.entity.bo.OrderItemBO;
import com.wisewin.api.entity.bo.OrderItemBO2;
import com.wisewin.api.util.AlipayConfig;
import com.wisewin.api.util.OrderUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Wang bin
 * @date: Created in 15:10 2019/9/11
 */
@Service
public class AlipayService {

    @Autowired
    private OrderItemDAO orderItemDAO;

    @Resource
    KeyValueDAO keyValueDAO;


    public String alipay(Integer userId, BigDecimal money, Integer rewardId, Integer commentId, Integer appraisalId){
        Map<String, Object> mp = new HashMap<String, Object>();

        //实例化客户端
        AlipayClient   alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APP_ID,
                AlipayConfig.APP_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET,
                AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGN_TYPE);
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("alipay");
        model.setSubject("appraisal");
        //生成订单号
        String orderNumber = OrderUtil.getNumber();
        model.setOutTradeNo(orderNumber);
        //model.setTimeoutExpress("30m"); 订单最晚支付时间，若为空，则默认为15d
        model.setTotalAmount(money+""); //订单金额
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl(AlipayConfig.fy_url);//回调地址
        System.err.println(orderNumber);
        mp.put("orderNumber",orderNumber);
        mp.put("userId", userId);
        mp.put("payment", money);
        mp.put("state", 0);
        mp.put("rewardId", rewardId);
        mp.put("commentId", commentId);
        mp.put("appraisalId", appraisalId);
        mp.put("appraisalId", appraisalId);
        mp.put("payPlatform", 1);


        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            if (response.isSuccess()){

                //插入预支付订单
                orderItemDAO.insetOrderItem(mp);
                System.err.println(response.getBody());
                return response.getBody();
            }
                System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }




    /**
     * 获取订单信息   根据订单号
     * @param orderNumber
     * @return
     */
    public OrderItemBO2 selectOrderItemBy(String  orderNumber){
        return orderItemDAO.selectOrderItemBy(orderNumber);
    }

    /**
     * 修改订单信息
     * @param orderItemBO
     * @return
     */
    public int updateOrderItem(OrderItemBO2 orderItemBO){
        return orderItemDAO.updateOrderItem(orderItemBO);
    }


    /**
     * 为被打赏人增加余额，并更新订单状态
     * @param money
     * @param userId
     * @return
     */
    public int updateUserMoney(String money, Integer userId, String platformNumber, Integer orderItemId){
        String value = keyValueDAO.getValueByKey("proportion");
        BigDecimal proportion=new BigDecimal(value);
        BigDecimal total_amoun = new BigDecimal(money);
        //被打赏人收到金额
        BigDecimal multiply = total_amoun.multiply(proportion);
        //增加用户
        int i = orderItemDAO.updateUserMoney(multiply + "", userId);
        //并更新交易状态
       i =  orderItemDAO.updateOrderItemStatus(multiply,platformNumber,orderItemId);
        return i ;
    }


    /**
     * 余额打赏
     * @param money
     * @param rewardId
     * @param commentId
     * @param appraisalId
     * @return
     */
    public void  balancePay(BigDecimal money, Integer rewardId, Integer commentId, Integer appraisalId, Integer userId){
        //扣除user余额
        int re  = orderItemDAO.saveUserMoney(userId,money);


        String value = keyValueDAO.getValueByKey("proportion");
        BigDecimal proportion=new BigDecimal(value);
        BigDecimal multiply = money.multiply(proportion);

        //生成订单
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orderNumber",OrderUtil.getNumber());
        map.put("userId",userId);
        map.put("payment",money);
        map.put("state",10);
        map.put("rewardId",rewardId);
        map.put("rewardAmount",multiply);
        map.put("commentId",commentId);
        map.put("appraisalId",appraisalId);
        map.put("payPlatform",4);
        orderItemDAO.insertBalanceOrder(map);

        //增加rewardId余额
        int ra = orderItemDAO.updateUserMoney(multiply+"", rewardId);
        return;
    }



}
