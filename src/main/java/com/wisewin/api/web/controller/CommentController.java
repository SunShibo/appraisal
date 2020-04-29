
package com.wisewin.api.web.controller;

import com.alipay.api.domain.UserIdentity;
import com.wisewin.api.entity.bo.CommentBO;
import com.wisewin.api.entity.bo.UserBO;
import com.wisewin.api.entity.dto.CommentDTO;
import com.wisewin.api.entity.dto.ResultDTOBuilder;
import com.wisewin.api.entity.param.CommentParam;
import com.wisewin.api.query.QueryInfo;
import com.wisewin.api.service.CommentService;
import com.wisewin.api.util.JsonUtils;
import com.wisewin.api.web.controller.base.BaseCotroller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author: Wang bin
 * @date: Created in 10:59 2019/8/29
 */
@Controller
@RequestMapping("/comment")
public class CommentController extends BaseCotroller {

    static final Logger log = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;

    /**
     * 用户添加评论
     * @param request
     * @param response
     * @param commentParam
     */
    @RequestMapping(value = "/insertComment",method = RequestMethod.POST)
    public void insertComment(HttpServletRequest request, HttpServletResponse response,
                               CommentParam commentParam){
        log.info("用户添加评论");
        Long startTime = System.currentTimeMillis();
        log.info("commentParam : {}", commentParam);
        UserBO loginUser = super.getLoginUser(request);
        log.info("userid : {}", loginUser.getId());
        if(loginUser == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000008"));
            super.safeJsonPrint(response, json);
            return ;
        }
        commentParam.setUserId(loginUser.getId());
        int i = commentService.insertComment(commentParam);
        if( i > 0){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("成功"));
            super.safeJsonPrint(response, json);
            return;
        }
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("1000003"));
        super.safeJsonPrint(response, json);
        log.info("return:{}",json);
        log.info("执行用时:{}",System.currentTimeMillis() - startTime);
        return;

    }

    /**
     * 采纳评论
     * @param request
     * @param response
     */
    @RequestMapping("/acceptComment")
    public void acceptComment(HttpServletRequest request, HttpServletResponse response, Integer commentId, Integer appraisalId){
        log.info("采纳评论");
        Long startTime = System.currentTimeMillis();

        //判断用户是否登录
        UserBO loginUser = super.getLoginUser(request);
        log.info("userid : {}", loginUser.getId());
        if(loginUser == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000008"));
            super.safeJsonPrint(response, json);
            return ;
        }
        //判断该评论是否在用户发布的鉴定下的评论
        CommentBO commentBO = commentService.queryComment(appraisalId, commentId);
        log.info("commentBO:{}",commentBO);
        if(commentBO == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("1000004"));
            super.safeJsonPrint(response, json);
            return ;
        }
        CommentBO commentBO1 = commentService.queryCommentByappraisalId(appraisalId);
        if(commentBO1 != null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("1000005"));
            super.safeJsonPrint(response, json);
            return ;
        }
        commentService.updateComment(commentId, appraisalId, commentBO.getUserId());
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("采纳成功"));
        super.safeJsonPrint(response, json);
        log.info("return : {}", json);
        log.info("执行用时:{}",System.currentTimeMillis() - startTime);
        return ;
    }

    /**
     * 获取评论
     */
    @RequestMapping("/getCommentByAppraisalId")
    public void getCommentByAppraisalId(Integer appraisalId,Integer pageNo,Integer pageSize,HttpServletResponse response, HttpServletRequest request) {
        log.info("获取评论");
        Long startTime = System.currentTimeMillis();
        Integer id = this.getId(request);
        log.info("userid : {}",id);
        if(id == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000008"));
            super.safeJsonPrint(response, json);
            return ;
        }
        log.info("appraisalId:{}", appraisalId);
        log.info("pageNo:{}", pageNo);
        log.info("pageSize:{}", pageSize);
        log.info("userId:{}", id);
        QueryInfo queryInfo=getQueryInfo(pageNo,pageSize);
        List<CommentDTO> commentBOS = commentService.getCommentByAppraisalId(appraisalId,id,queryInfo.getPageOffset(),queryInfo.getPageSize());
        String json= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(commentBOS));
        super.safeJsonPrint(response,json);
        log.info("return : {}", json);
        log.info("执行用时:{}", System.currentTimeMillis() - startTime);
        return;
    }

    /**
     * 删除评论
     * 用户可以删除自己发表的并且还未被采纳的评论
     *
     * @param request
     * @param response
     */
    @RequestMapping("/deleteCommentByUserId")
    public void deleteCommentByUserId(HttpServletRequest request, HttpServletResponse response, Integer commentId){
        log.info("删除评论");
        long startTime = System.currentTimeMillis();
        UserBO loginUser = super.getLoginUser(request);
        log.info("userid : {}", loginUser.getId());
        if(loginUser == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000008"));
            super.safeJsonPrint(response, json);
            return ;
        }
        if(commentId == null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            return ;
        }
        int i = commentService.deleteComment(commentId);
        log.info("i:{}",i);
        if(i > 0){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000000"));
            super.safeJsonPrint(response, json);
            return ;
        }
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000010"));
        super.safeJsonPrint(response, json);
        log.info("return : {}",json);
        log.info("执行用时:{}", System.currentTimeMillis() -startTime);
        return ;
    }
}



