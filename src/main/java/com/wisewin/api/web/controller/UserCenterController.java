package com.wisewin.api.web.controller;

import com.wisewin.api.entity.bo.AppraisalBo;
import com.wisewin.api.entity.bo.BannerBO;
import com.wisewin.api.entity.dto.ResultDTOBuilder;
import com.wisewin.api.query.QueryInfo;
import com.wisewin.api.service.HomeService;
import com.wisewin.api.service.UserCenterService;
import com.wisewin.api.util.JsonUtils;
import com.wisewin.api.web.controller.base.BaseCotroller;
import org.omg.CORBA.INTERNAL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 个人中心相关接口
 * */
@Controller
@RequestMapping("/userCenter")
public class UserCenterController extends BaseCotroller {
    static final Logger log = LoggerFactory.getLogger(UserCenterController.class);
    @Resource
    private UserCenterService userCenterService;

    /**
     * 获取我的收藏
     */
    @RequestMapping("/getEnshrineAppraisal")
    public void getEnshrineAppraisal(HttpServletResponse response, HttpServletRequest request,Integer pageNo,Integer pageSize) {
        Integer id = this.getId(request);
        if(id == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000008"));
            super.safeJsonPrint(response, json);
            return ;
        }
        QueryInfo queryInfo=getQueryInfo(pageNo,pageSize);
        List<AppraisalBo> appraisalBoList=userCenterService.getEnshrineAppraisal(id,queryInfo.getPageOffset(),queryInfo.getPageSize());
        for (AppraisalBo appraisal:appraisalBoList) {
            String urlAll=appraisal.getApImages();
            List<String> strings = Arrays.asList(urlAll.split(","));
            if (strings!=null&&strings.size()!=0){
                appraisal.setHeadUrl(strings.get(0));
            }
        }

        String json= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(appraisalBoList));
        super.safeJsonPrint(response,json);
    }
    /**
     * 取消收藏
     */
    @RequestMapping("/delEnshrine")
    public void delEnshrine(Integer appraisalId,HttpServletResponse response, HttpServletRequest request) {
        Integer id = this.getId(request);
        if(id == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000008"));
            super.safeJsonPrint(response, json);
            return ;
        }
        userCenterService.delEnshrine(appraisalId,id);
        String json= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(null));
        super.safeJsonPrint(response,json);
    }
    /**
     * 获取我的账户
     */
    @RequestMapping("/getMyAccount")
    public void getMyAccount(HttpServletResponse response, HttpServletRequest request,Integer pageNo,Integer pageSize) {
        Integer id = this.getId(request);
        if(id == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000008"));
            super.safeJsonPrint(response, json);
            return ;
        }
        QueryInfo queryInfo=getQueryInfo(pageNo,pageSize);
        Map<String,Object> result=userCenterService.getMyAccount(id,queryInfo.getPageOffset(),queryInfo.getPageSize());
        String json= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(result));
        super.safeJsonPrint(response,json);
    }

}
