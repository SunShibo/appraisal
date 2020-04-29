package com.wisewin.api.web.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.wisewin.api.entity.bo.OrderItemBO;
import com.wisewin.api.entity.bo.OrderItemBO2;
import com.wisewin.api.entity.bo.UserBO;
import com.wisewin.api.entity.dto.ResultDTOBuilder;
import com.wisewin.api.service.AlipayService;
import com.wisewin.api.service.CommentService;
import com.wisewin.api.service.UserService;
import com.wisewin.api.util.*;
import com.wisewin.api.util.date.DateUtil;
import com.wisewin.api.web.controller.base.BaseCotroller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author: Wang bin
 * @date: Created in 14:31 2019/9/11
 *  支付宝app支付
 */
@Controller
@RequestMapping("/pay")
public class AlipayController extends BaseCotroller {

   // final static Logger log = LoggerFactory.getLogger(AlipayController.class);

    static final Logger log = LoggerFactory.getLogger(AlipayController.class);

    @Autowired
    private AlipayService alipayService;

    @Autowired
    private UserService userService;

    /**
     * 支付宝打赏
     * @param request
     * @param response
     */
    @RequestMapping("/alipay")
    public void alipay(HttpServletRequest request, HttpServletResponse response,
                       BigDecimal money, Integer rewardId, Integer commentId, Integer appraisalId){
        log.info("支付宝打赏");
        log.info("money:{}",money);
        log.info("rewardId:{}",rewardId);
        log.info("commentId:{}",commentId);
        log.info("appraisalId:{}",appraisalId);
        //获取当前登陆对象
        UserBO user = super.getLoginUser(request);
        log.info("user:{]",user);

        //log.info("微信打赏|用户:{}|时间:{}|内容:{}",user.getId(), DateUtils.getDateByType(new Date(),"yyyy-MM-dd HH:mm:ss"),"");
        if(user == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000008"));
            super.safeJsonPrint(response, json);
            log.info("user == null  return ");
            return ;
        }
        if(money == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            log.info("money == null return ");
            return;
        }
        if(rewardId == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            log.info("rewardId == null return ");
            return;
        }
        /*if(commentId == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            return;
        }*/
        if(appraisalId == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            log.info("appraisalId == null return");
            return;
        }
        String alipay = alipayService.alipay(user.getId(), money, rewardId, commentId, appraisalId);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("alipay",alipay);
        log.info("return:{}",alipay);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(map));
        super.safeJsonPrint(response, json);
        return ;
    }


    /**
     * 支付宝异步回调
     * @param request
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/notify")
    public String notify(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
            log.info("支付宝回调");

        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        String jsonString4JavaPOJO = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(requestParams));
        log.info("支付宝验签:{}",jsonString4JavaPOJO);
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

             String out_trade_no = request.getParameter("out_trade_no");            // 商户订单号
             String trade_no = request.getParameter("trade_no");            // 流水号
            // String orderType = request.getParameter("body");                    // 订单内容
             String tradeStatus = request.getParameter("trade_status");
             String total_amount = request.getParameter("total_amount");  //订单金额

           log.info("商户订单号:{}",out_trade_no);
           log.info("流水号:{}",trade_no);
           log.info("tradeStatus:{}",tradeStatus);
           log.info("订单金额:{}",total_amount);


             //签名验证(对支付宝返回的数据验证，确定是支付宝返回的)
             boolean signVerified = false;
             try {
                     //3.1调用SDK验证签名
                     signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.SIGN_TYPE);
                          } catch (AlipayApiException e) {
                     e.printStackTrace();
                 }

                 //4.对验签进行处理
             if (signVerified) {    //验签通过
                     if(tradeStatus.equals("TRADE_SUCCESS")) {    //只处理支付成功的订单: 修改交易表状态,支付成功
                         OrderItemBO2 orderItemBO = alipayService.selectOrderItemBy(out_trade_no);
                         log.info("获取预支付订单:{}",orderItemBO);
                        /* orderItemBO.setState(10); //支付完成
                         orderItemBO.setPlatformNumber(trade_no);
                         orderItemBO.setRewardAmount(new BigDecimal(total_amount));*/
                         //为被打赏人增加余额
                         log.info("增加余额");
                         int i = alipayService.updateUserMoney(total_amount, orderItemBO.getRewardId(),trade_no,orderItemBO.getId());
//                         log.info("为被打赏人增加余额:{}",i);
//                         log.info("更新状态");
//                         int returnResult = alipayService.updateOrderItem(orderItemBO) ;   //更新交易表中状态*/
//                         log.info("更新交易表状态:{}",returnResult);
                             if(i>0){
                                 log.info("return:success");
                                      return "success";
                                 }else{
                                 log.info("return:fail");
                                      return "fail";
                                 }
                         }else{
                         log.info("return:fail");
                             return "fail";
                         }
                 } else {  //验签不通过

                     System.err.println("验签失败");
                 log.info("return:fail");
                     return "fail";
                 }
    }


    /**
     * 余额打赏
     * @param request
     * @param response
     * @param money
     * @param rewardId
     * @param commentId
     * @param appraisalId
     */
    @RequestMapping("/balance")
    public void balanceController(HttpServletRequest request, HttpServletResponse response,
                                  BigDecimal money, Integer rewardId, Integer commentId, Integer appraisalId){
        log.info("余额打赏");
        Long startTime = System.currentTimeMillis();
        log.info("money:{}",money);
        log.info("rewardId:{}",rewardId);
        log.info("commentId:{}",commentId);
        log.info("appraisalId:{}",appraisalId);
        UserBO user = super.getLoginUser(request);
        log.info("user:{}",user);
        if(user == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000008"));
            super.safeJsonPrint(response, json);
            return ;
        }
        if(money == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            return;
        }
        if(rewardId == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            return;
        }
        /*if(commentId == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            return;
        }*/
        if(appraisalId == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("1000001"));
            super.safeJsonPrint(response, json);
            return;
        }
        BigDecimal userMoney = userService.queryUserById(user.getId()).getMoney();
        log.info("获取用户金额:{}",userMoney);

        if(userMoney.compareTo(money) == -1 ){
            log.info("usermoney:{}",userMoney);
            log.info("money:{}",money);
            log.info("{}",user.getMoney().compareTo(money));
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("1000011"));
            super.safeJsonPrint(response, json);
            return;
        }


        alipayService.balancePay(money, rewardId, commentId, appraisalId, user.getId());
        log.info("为被打赏人增加余额");
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("0000000"));
        super.safeJsonPrint(response, json);
        return;
    }


    /**
     * 支付宝提现
     * @param request
     * @param response
     */
    @RequestMapping("/aliCashTest")
    public void transfer(HttpServletRequest request, HttpServletResponse response){
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL,
                AlipayConfig.APP_ID,AlipayConfig.APP_PRIVATE_KEY,AlipayConfig.FORMAT,
                AlipayConfig.CHARSET,AlipayConfig.ALIPAY_PUBLIC_KEY,AlipayConfig.SIGN_TYPE);
        AlipayFundTransToaccountTransferRequest rq = new AlipayFundTransToaccountTransferRequest();
        rq.setBizContent("{" +
                "    \"out_biz_no\":\"123456789\"," +
                "    \"payee_type\":\"ALIPAY_USERID\"," +
                "    \"payee_account\":\"2088902332461053\"," +
                "    \"amount\":\"0.1\"," +
                "    \"remark\":\"余额提现\"," +
                "  }");
        AlipayFundTransToaccountTransferResponse rp = null;
        try {
            rp = alipayClient.execute(rq);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if(rp.isSuccess()){
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
    }

    @RequestMapping("/aliCash")
    public void aliCash(HttpServletRequest request, HttpServletResponse response){

    }


    @RequestMapping("/alipayUser")
    public void alipayUser(HttpServletRequest request, HttpServletResponse response) throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL,AlipayConfig.APP_ID,AlipayConfig.APP_PRIVATE_KEY,
                AlipayConfig.FORMAT,AlipayConfig.CHARSET,
                AlipayConfig.ALIPAY_PUBLIC_KEY,AlipayConfig.SIGN_TYPE);
        AlipaySystemOauthTokenRequest requ = new AlipaySystemOauthTokenRequest();
        requ.setGrantType("authorization_code");
        //requ.setCode("4b203fe6c11548bcabd8da5bb087a83b");
        //requ.setRefreshToken("201208134b203fe6c11548bcabd8da5bb087a83b");
        AlipaySystemOauthTokenResponse respon = alipayClient.execute(requ);
        if(respon.isSuccess()){
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
    }

    public static void main(String[] args) {
        File originalFile = new File("https://appraisal.oss-cn-beijing.aliyuncs.com/1571302474248.jpg");
    }
}
