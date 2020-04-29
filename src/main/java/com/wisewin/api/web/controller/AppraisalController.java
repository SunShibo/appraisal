package com.wisewin.api.web.controller;
import com.wisewin.api.entity.bo.UserBO;
import com.wisewin.api.entity.dto.*;
import com.wisewin.api.entity.param.AppraisalParam;
import com.wisewin.api.query.QueryInfo;
import com.wisewin.api.service.AppraisalService;
import com.wisewin.api.util.DateUtils;
import com.wisewin.api.util.JsonUtils;
import com.wisewin.api.util.OSSClientUtil;
import com.wisewin.api.util.Sensitive.SensitiveWordsUtil;
import com.wisewin.api.util.WithdrawUtil;
import com.wisewin.api.web.controller.base.BaseCotroller;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;


/**
 * @Author: Wang bin
 * @date: Created in 12:30 2019/8/27
 */
@Controller
@RequestMapping("/appraisal")
public class AppraisalController extends BaseCotroller {

    @Autowired
    private AppraisalService appraisalService;

    final static Logger log =  LoggerFactory.getLogger(AppraisalController.class);

   /* private String isUpload(MultipartFile[] files){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            OSSClientUtil ossClientUtil=new OSSClientUtil();
            //上传
            String name="";
            try {
                name = ossClientUtil.uploadImg2Oss(file);
                sb.append(name);
                sb.append(",");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return sb.toString();
    }*/


    /**
     * 发布鉴定信息
     * @param request
     * @param response
     * @param appraisalParam
     * @param
     */

    @RequestMapping(value = "/insetAppraisal",headers = "content-type=multipart/form-data" , method = RequestMethod.POST)
    public void insetAppraisal(HttpServletRequest request, HttpServletResponse response,
                               AppraisalParam appraisalParam,  @RequestParam List<MultipartFile>  file) throws IOException {
        log.info("发布鉴定信息");
        Long startTime = System.currentTimeMillis();
        UserBO user = super.getLoginUser(request);

        appraisalParam.setTitle(SensitiveWordsUtil.replaceBadWord( appraisalParam.getTitle(), 2, "*"));
        appraisalParam.setDescribc(SensitiveWordsUtil.replaceBadWord( appraisalParam.getDescribc(), 2, "*"));



        if(user == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000008"));
            super.safeJsonPrint(response, json);
            return ;
        }
        //图片地址以","分割

        log.info("上传图片");
        StringBuffer sb = new StringBuffer();

        OSSClientUtil ossClientUtil=new OSSClientUtil();

        if(file!=null&&file.size()>0){
           // Collections.reverse(file);
            for (int i = 0; i < file.size(); i++) {

                MultipartFile fil = file.get(i);
                //MultipartFile fi =   WithdrawUtil.addWorkMarkToMutipartFile(fil,"京睿天成科技有限公司");
            log.info("上传图片:{}",file.get(i));
                //上传
                String name="";
                try {
                    name = ossClientUtil.uploadImg2Oss(fil);
                    log.info("name:{}",name);
                    sb.append(name);
                    sb.append(",");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        appraisalParam.setUserId(user.getId());
        appraisalParam.setApImages(sb.toString());
        appraisalService.insertAppraisal(appraisalParam);

        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("0000000"));
        super.safeJsonPrint(response, json);
        Long endTime = System.currentTimeMillis();
        log.info("return :{}",json);
        log.info("执行用时:{}",endTime - startTime);
        return;
    }

    /**
     * 鉴定列表页
     * @param request
     * @param response
     */
    @RequestMapping("/queryAppraisalList")
    public void queryAppraisal(HttpServletRequest request, HttpServletResponse response,
                               String title, String appraisalState, String appraisalTypeId,
                               Integer pageOffset, Integer pageSize){
        log.info("鉴定列表页");
        Long startTime = System.currentTimeMillis();
        log.info("title:{}",title);
        log.info("appraisalState:{}",appraisalState);
        log.info("appraisalTypeId:{}",appraisalTypeId);
        log.info("pageOffset:{}",pageOffset);
        log.info("pageSize:{}",pageSize);
        UserBO user = super.getLoginUser(request);
        log.info("userId:{}", user.getId());
        if(user == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000008"));
            super.safeJsonPrint(response, json);
            return ;
        }
        QueryInfo queryInfo = getQueryInfo(pageOffset, pageSize);
        //创建一个用于封装sql条件的map集合
        Map<String, Object> condition = new HashMap<String, Object>();
        if (queryInfo != null) {
            //把pageOffset 页数,pageSize每页的条数放入map集合中
            condition.put("pageOffset", queryInfo.getPageOffset());
            condition.put("pageSize", queryInfo.getPageSize());
        }
        condition.put("title", title);
        condition.put("appraisalState", appraisalState);
        condition.put("appraisalTypeId", appraisalTypeId);
        List<AppraDTO> appraDTOS = appraisalService.queryListAppraisal(condition);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(appraDTOS));
        super.safeJsonPrint(response, json);
        log.info("return :{}",json);
        log.info("执行用时:{}",System.currentTimeMillis()-startTime);
        return;
    }

    /**
     * 鉴定详情
     * @param request
     * @param response
     * @param appraisalId
     */
    @RequestMapping("/queryAppraisalById")
    public void queryAppraisalById(HttpServletRequest request, HttpServletResponse response, Integer appraisalId){
        log.info("鉴定详情页");
        Long startTime = System.currentTimeMillis();
        log.info("appraisalId:{}",appraisalId);
        UserBO user = super.getLoginUser(request);
        log.info("userid:{}", user.getId());
        if(user == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000008"));
            super.safeJsonPrint(response, json);
            log.info("user == null return");
            return ;
        }
        if(appraisalId == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            log.info("appraisalId == null return ");
            return;
        }
        Map<String, Object> stringObjectMap = appraisalService.queryAppraisalById(appraisalId,user.getId());
        if(stringObjectMap == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("1000010"));
            super.safeJsonPrint(response, json);
            return;
        }
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(stringObjectMap));
        super.safeJsonPrint(response, json);
        log.info("return :{}",json);
        log.info("执行用时:{}",System.currentTimeMillis() - startTime);
        return;
    }


    /**
     * 获取我的宝贝列表
     * @param response
     * @param request
     */
    @RequestMapping("/userAppraisalList")
    public void queryAppraisal(HttpServletResponse response, HttpServletRequest request){
        log.info("获取我的宝贝列表");
        long startTime = System.currentTimeMillis();
        UserBO user = super.getLoginUser(request);
        log.info("userid:{}",user.getId());
        if(user == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000008"));
            super.safeJsonPrint(response, json);
            log.info("user == null return");
            return ;
        }



        if(user == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000008"));
            super.safeJsonPrint(response, json);
            return ;
        }
        List<UserAppraisalDTO> userAppraisalDTOS = appraisalService.queryAppraisal(user.getId());
        log.info("userAppraisalDTOS:{}",userAppraisalDTOS);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(userAppraisalDTOS));
        super.safeJsonPrint(response, json);
        long endTime = System.currentTimeMillis();
        log.info("end******执行用时:{}",startTime-endTime);
        return;
    }


    /**
     * 我的鉴定
     * @param request
     * @param response
     */
    @RequestMapping("/selectIAppraisal")
    public void selectIAppraisal(HttpServletRequest request, HttpServletResponse response){
        log.info("获取我的鉴定");
        long startTime = System.currentTimeMillis();
        UserBO user = super.getLoginUser(request);
        log.info("user:{}", user.getId());
        if(user == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000008"));
            super.safeJsonPrint(response, json);
            log.info("user == null return");
            return ;
        }
            List<IAppraisalDTO> iAppraisalDTOS = appraisalService.queryIAppraisal(user.getId());
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(iAppraisalDTOS));
            super.safeJsonPrint(response, json);
            log.info("return :{}",json);
            log.info("执行用时:{}",System.currentTimeMillis() - startTime);
            return;
    }

    /**
     * 分享接口
     * @param request
     * @param response
     */
    @RequestMapping("/share")
    public void share(HttpServletRequest request, HttpServletResponse response, Integer appraisalId, String state){
        Map<String, Object> share = appraisalService.share(appraisalId, state);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(share));
        super.safeJsonPrint(response, json);
        return;
    }
}
