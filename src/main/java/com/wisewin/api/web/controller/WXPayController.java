package com.wisewin.api.web.controller;
import com.wisewin.api.entity.bo.UserBO;
import com.wisewin.api.entity.dto.ResultDTOBuilder;
import com.wisewin.api.service.WXPayService;
import com.wisewin.api.util.AgentUserKit;
import com.wisewin.api.util.JsonUtils;
import com.wisewin.api.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.Map;

/**
 * wy log
 * */
@Controller
@RequestMapping("/WXPay")
public class WXPayController extends BaseCotroller {

    @Resource
    WXPayService wxPayService;


    /***
     * 获取预订单信息
     * @param money 打赏金额
     * @param rewardId 被打赏人id
     * @param commentId 评论id
     * @param appraisalId 物品id
     */
    @RequestMapping("/wxPay")
    public void unifiedOrder(BigDecimal money, Integer rewardId, Integer commentId, Integer appraisalId,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取当前登陆用户
        UserBO loginUser = super.getLoginUser(request);
        Integer id = loginUser.getId();
        //判断参数
        if (money==null||rewardId==null||appraisalId==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            return;
        }
        Map<String,String> resultMap=wxPayService.getUnifiedOrder(id,money,rewardId,commentId,appraisalId);

        //统一下单结果
        if (resultMap!=null&&!resultMap.isEmpty()) {
            System.out.println(resultMap);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(resultMap));
            super.safeJsonPrint(response, json);
            return;
        }
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000010"));
        super.safeJsonPrint(response, json);
        return;

    }

    //打赏回调
    @RequestMapping("/orderResult")
    public void courseOrderResult(HttpServletRequest request,HttpServletResponse response) throws Exception {
        Map<String, String> resultMap = wxPayService.getOrderResult(request);
        String return_code = resultMap.get("return_code");//状态
        String result_code = resultMap.get("result_code");//交易结果
        if (return_code.equals("SUCCESS") && result_code.equals("SUCCESS")) {//交易成功
            Writer writer = response.getWriter();
            writer.write("<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
            writer.flush();
            writer.close();
        }
    }
}
