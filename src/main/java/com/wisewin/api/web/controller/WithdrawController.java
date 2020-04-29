package com.wisewin.api.web.controller;


import com.wisewin.api.entity.bo.UserBO;
import com.wisewin.api.entity.bo.common.constants.Result;

import com.wisewin.api.service.WithdrawService;
import com.wisewin.api.util.JsonUtils;
import com.wisewin.api.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: Wang bin
 * @date: Created in 11:56 2019/10/30
 */
@Controller
@RequestMapping("/withdraw")
public class WithdrawController extends BaseCotroller {

    @Resource
    private WithdrawService withdrawService;


    /**
     * 支付宝提现
     * @param request
     * @param response
     */
    @RequestMapping("/aliPayWithdraw")
    public void aliPayWithdraw(HttpServletRequest request, HttpServletResponse response, Double money, String payPassword,
                               String phoneType){
        UserBO loginUser = super.getLoginUser(request);
        Result result = withdrawService.aliPayWithdraw(loginUser.getId(), money, payPassword, phoneType);
        String jsonString4JavaPOJO = JsonUtils.getJsonString4JavaPOJO(result);
        super.safeJsonPrint(response, jsonString4JavaPOJO);
        return;
    }


    /**
     * 获取用户提现记录
     * @param request
     * @param response
     */
    @RequestMapping("/withdrawalRecord")
    public void withdrawalRecord(HttpServletRequest request, HttpServletResponse response){
        UserBO loginUser = super.getLoginUser(request);
        Result result = withdrawService.selectWithdraw(loginUser.getId());
        String jsonString4JavaPOJO = JsonUtils.getJsonString4JavaPOJO(result);
        super.safeJsonPrint(response, jsonString4JavaPOJO);
        return;
    }





}
