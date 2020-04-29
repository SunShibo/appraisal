package com.wisewin.api.web.controller;

import com.wisewin.api.entity.bo.UserBO;
import com.wisewin.api.entity.dto.ResultDTOBuilder;
import com.wisewin.api.service.FeedBackService;
import com.wisewin.api.util.JsonUtils;
import com.wisewin.api.util.StringUtils;
import com.wisewin.api.web.controller.base.BaseCotroller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: Wang bin
 * @date: Created in 10:42 2019/10/8
 */
@Controller
@RequestMapping("/feedBack")
public class FeedBackController extends BaseCotroller {

    @Autowired
    private FeedBackService feedBackService;



    @RequestMapping("/insertFeedBack")
    public void feedBack(HttpServletRequest request, HttpServletResponse response,
                         String describc){
        UserBO user = super.getLoginUser(request);
        if(user == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000008"));
            super.safeJsonPrint(response, json);
            return ;
        }
        if(!StringUtils.isNotBlank(describc)){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            return ;
        }
        int i = feedBackService.insertFeedBack(user.getId(), describc);
        if(i > 0){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("评论成功"));
            super.safeJsonPrint(response, json);
            return ;
        }

        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000010"));
        super.safeJsonPrint(response, json);
        return ;
    }
}
