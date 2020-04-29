package com.wisewin.api.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.wisewin.api.entity.bo.UserBO;
import com.wisewin.api.entity.dto.ResultDTOBuilder;
import com.wisewin.api.service.OrderItemService;
import com.wisewin.api.util.AgentUserKit;
import com.wisewin.api.util.IDBuilder;
import com.wisewin.api.util.IosVerifyUtil;
import com.wisewin.api.util.JsonUtils;
import com.wisewin.api.web.controller.base.BaseCotroller;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author: Wang bin
 * @date: Created in 14:54 2019/10/29
 */
@RequestMapping("/ios")
@Controller
public class IOSPayController extends BaseCotroller {

    static final Logger log = LoggerFactory.getLogger(IOSPayController.class);

    @Resource
    private OrderItemService orderItemService;

    /**
     * 苹果内购校验
     *
     * @param price         充值价格
     * @param transactionId 苹果内购交易ID
     * @param payload       校验体（base64字符串）
     *        rewardId      被打赏人Id
     * @return
     */
    @RequestMapping("/setIapCertificate")
    public void  iosPay(HttpServletRequest request, HttpServletResponse response,
                        BigDecimal price, String transactionId, String payload, Integer rewardId,
                        Integer commentId, Integer appraisalId) {
        log.info("start============================================iosPay======================================================");
        log.info("苹果内购校验开始，交易ID：" + transactionId + " base64校验体：" + payload);

        UserBO user = super.getLoginUser(request);
        log.info("获取当前登陆对象{}",user);
        if(user == null){
            log.info("user null return");
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("用户未登录"));
            super.safeJsonPrint(response, json);
            return;
        }

        //获取手机系统
        // String model= AgentUserKit.getDeviceInfo(request);
        // log.info("获取手机系统{}",model);
        //线上环境验证
        log.info("线上传经验证");
        String verifyResult = IosVerifyUtil.buyAppVerify(payload, 1);
        if (verifyResult == null) {
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("苹果验证失败,返回数据为空"));
            super.safeJsonPrint(response, json);
            return;
        } else {
            log.info("线上，苹果平台返回JSON:" + verifyResult);
            JSONObject appleReturn = JSONObject.parseObject(verifyResult);
            log.info("appleReturn{}",appleReturn);
            String states = appleReturn.getString("status");
            log.info("states{}",states);
            //无数据则沙箱环境验证
            if ("21007".equals(states)) {
                verifyResult = IosVerifyUtil.buyAppVerify(payload, 0);
                log.info("沙盒环境，苹果平台返回JSON:" + verifyResult);
                appleReturn = JSONObject.parseObject(verifyResult);
                states = appleReturn.getString("status");
            }
            log.info("苹果平台返回值：appleReturn" + appleReturn);
            // 前端所提供的收据是有效的    验证成功
            if (states.equals("0")) {
                String receipt = appleReturn.getString("receipt");
                log.info("receipt{}",receipt);
                JSONObject returnJson = JSONObject.parseObject(receipt);
                log.info("returnJson{}",returnJson);
                String inApp = returnJson.getString("in_app");
                log.info("inApp{}",inApp);
                List<HashMap> inApps = JSONObject.parseArray(inApp, HashMap.class);
                log.info("inApps{}",inApps);
                if (!CollectionUtils.isEmpty(inApps)) {
                    log.info("进入判断");
                    ArrayList<String> transactionIds = new ArrayList<String>();
                    for (HashMap app : inApps) {
                        log.info("进入增强for循环");
                        transactionIds.add((String) app.get("transaction_id"));
                    }
                    //交易列表包含当前交易，则认为交易成功
                    log.info("transactionId{}",transactionId);
                    if (transactionIds.contains(transactionId)) {
                        log.info("交易成功");
                        //处理逻辑代码
                        int i = orderItemService.iosPay(user.getId(), rewardId, commentId, appraisalId, "", price);

                        if(i > 0){
                            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("支付成功"));
                            super.safeJsonPrint(response, result);
                            return ;
                        } else {
                            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000012"));
                            super.safeJsonPrint(response, result);
                            return ;
                        }

                        //处理业务逻辑
                 /*       VipOrder vipOrder = vipOrderService.saveVipOrder(shipper, priceId, EnumPayType.APPLE_IN_APP_PURCHASES.getValue(), transactionId);
                        vipOrderService.paySuccess(vipOrder.getOrderCode(), null);
                        log.info("交易成功，新增并处理订单：{}", vipOrder.getOrderCode());
                        return success("充值成功");*/
                    }
                    String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("当前交易不在交易列表中"));
                    super.safeJsonPrint(response, json);
                    return;
                }
                String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("未能获取到交易列表"));
                super.safeJsonPrint(response, json);
                return;
            } else {
                String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("支付失败，错误码：" + states));
                super.safeJsonPrint(response, json);
                return;
            }
        }
    }
}
