package com.wisewin.api.web.controller;


import com.wisewin.api.entity.bo.AppraisalClassify;
import com.wisewin.api.entity.bo.UserBO;
import com.wisewin.api.entity.bo.common.constants.Result;
import com.wisewin.api.entity.dto.ResultDTOBuilder;
import com.wisewin.api.entity.param.AppraisalTypeParam;
import com.wisewin.api.service.AppraisalTypeService;
import com.wisewin.api.util.JsonUtils;
import com.wisewin.api.web.controller.base.BaseCotroller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
/**
 * @Author: Wang bin
 * @date: Created in 16:32 2019/8/27
 */
@Controller
public class AppraisalTypeController extends BaseCotroller {

    @Autowired
    private AppraisalTypeService appraisalTypeService;

    final static Logger log = LoggerFactory.getLogger(AppraisalController.class);


    /**
     * 获取鉴定类型列表
     * @param request
     * @param response
     */
    @RequestMapping("/queryAppraisalType")
    public void queryAppraisalType(HttpServletRequest request, HttpServletResponse response){
        log.info("获取鉴定类型列表");
        Long startTime = System.currentTimeMillis();
        UserBO user = super.getLoginUser(request);
        log.info("user : {}", user.getId());
        if(user == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000008"));
            super.safeJsonPrint(response, json);
            return ;
        }
        Result<AppraisalClassify> appraisalTypeBos = appraisalTypeService.queryAppraisalType();
        String json = JsonUtils.getJsonString4JavaPOJO(appraisalTypeBos);
        super.safeJsonPrint(response, json);
        log.info("return : {}", json);
        log.info("执行用时:{}",System.currentTimeMillis() - startTime);
        return;
    }

}
