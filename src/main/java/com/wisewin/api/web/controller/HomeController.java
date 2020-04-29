package com.wisewin.api.web.controller;

import com.wisewin.api.entity.bo.AppraisalBo;
import com.wisewin.api.entity.bo.BannerBO;
import com.wisewin.api.entity.bo.UserBO;
import com.wisewin.api.entity.dto.ResultDTOBuilder;
import com.wisewin.api.query.QueryInfo;
import com.wisewin.api.service.HomeService;
import com.wisewin.api.service.UserService;
import com.wisewin.api.util.JsonUtils;
import com.wisewin.api.web.controller.base.BaseCotroller;
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
 * 首页相关接口
 * */
@Controller
@RequestMapping("/home")
public class HomeController extends BaseCotroller {
    static final Logger log = LoggerFactory.getLogger(HomeController.class);
    @Resource
    private HomeService homeService;

    /**
     * 获取首页所有的信息
     */
    @RequestMapping("/getHomeInfo")
    public void getHomeInfo(HttpServletResponse response, HttpServletRequest request,Integer pageNo,Integer pageSize) {
        Integer id = this.getId(request);
        if(id == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000008"));
            super.safeJsonPrint(response, json);
            return ;
        }
        Map<String,Object> resultMap=new HashMap<String, Object>();

        List<BannerBO> bannerBOS=homeService.getBanner();
        resultMap.put("bannerBOS",bannerBOS);
        Integer appraisalBoCount=homeService.getAppraisalBoCount(id);
        Integer commentBoCount=homeService.getCommentBoCount(id);
        resultMap.put("appraisalBoCount",appraisalBoCount);
        resultMap.put("commentBoCount",commentBoCount);
        QueryInfo queryInfo=getQueryInfo(pageNo,pageSize);
        List<AppraisalBo> appraisalBoList=homeService.getAppraisalBoList(queryInfo.getPageOffset(),queryInfo.getPageSize());
        for (AppraisalBo appraisal:appraisalBoList) {
            System.out.println("==="+appraisal.getApImages());
            String urlAll=appraisal.getApImages();
            if(urlAll!=null){
                List<String> strings = Arrays.asList(urlAll.split(","));
                if (strings!=null&&strings.size()!=0){
                    appraisal.setHeadUrl(strings.get(0));
                }
            }
        }
        resultMap.put("appraisalBoList",appraisalBoList);
        String json= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(resultMap));
        super.safeJsonPrint(response,json);
    }
    /**
     * 获取首页鉴定数量信息
     */
    @RequestMapping("/getAppraisalCount")
    public void getAppraisalCount(HttpServletResponse response, HttpServletRequest request) {
        Integer id = this.getId(request);
        if(id == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000008"));
            super.safeJsonPrint(response, json);
            return ;
        }
        Integer appraisalBoCount=homeService.getAppraisalBoCount(id);
        Integer commentBoCount=homeService.getCommentBoCount(id);

        if(appraisalBoCount==null){
            appraisalBoCount=0;
        }
        if(commentBoCount==null){
            commentBoCount=0;
        }
        Map<String,Object> resultMap=new HashMap<String, Object>();
        resultMap.put("appraisalBoCount",appraisalBoCount);
        resultMap.put("commentBoCount",commentBoCount);
        String json= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(resultMap));
        super.safeJsonPrint(response,json);

    }

    /**
     * 获取首页鉴定案例
     */
    @RequestMapping("/getAppraisalBoList")
    public void getAppraisalBoList(Integer pageNo,Integer pageSize,HttpServletResponse response, HttpServletRequest request) {
        Integer id = this.getId(request);
        if(id == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000008"));
            super.safeJsonPrint(response, json);
            return ;
        }
        QueryInfo queryInfo=getQueryInfo(pageNo,pageSize);
        //List<AppraisalBo> appraisalBoList=homeService.getAppraisalBoList();
        String json= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(null));
        super.safeJsonPrint(response,json);
    }
}
