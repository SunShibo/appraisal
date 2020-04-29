
package com.wisewin.api.service;

import com.wisewin.api.dao.AppraisalDao;
import com.wisewin.api.dao.CommentDao;
import com.wisewin.api.dao.GradeDAO;
import com.wisewin.api.dao.UserDao;
import com.wisewin.api.entity.bo.AppraisalBo;
import com.wisewin.api.entity.bo.CommentBO;
import com.wisewin.api.entity.bo.GradeBO;
import com.wisewin.api.entity.dto.CommentDTO;
import com.wisewin.api.entity.param.CommentParam;
import com.wisewin.api.util.Sensitive.SensitiveWordsUtil;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Wang bin
 * @date: Created in 11:03 2019/8/29
 */
@Service
public class CommentService {

    static final Logger log = LoggerFactory.getLogger(CommentService.class);

    @Autowired
    private GradeDAO gradeDAO;

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private AppraisalDao appraisalDao;

    @Autowired
    private UserDao userDao;

    /**
     * 添加评论
     * @param commentParam
     * @return
     */
    public int insertComment(CommentParam commentParam){
        String s = SensitiveWordsUtil.replaceBadWord(commentParam.getCnComment(), 2, "*");
        commentParam.setCnComment(s);
        return commentDao.inertComment(commentParam);
    }

    /**
     * 根据鉴定id 和 评论id获取该评论
     * @param appraisalId
     * @param commentId
     * @return
     */
    public CommentBO queryComment(Integer appraisalId, Integer commentId){
        CommentBO commentBO = commentDao.queryComment(appraisalId, commentId);
        if(commentBO == null){
            return null ;
        }
        return commentBO ;
    }

    /**
     * 判断改鉴定页下面是否有被采纳的评论
     * @param appraisalId
     * @return
     */
    public CommentBO queryCommentByappraisalId(Integer appraisalId){
        CommentBO commentBO = commentDao.queryCommentByAppraisalId(appraisalId);

        if(commentBO == null){
            return null;
        }
        return commentBO;
    }

    /**
     * 将该评论列为采纳
     * @param commentId
     */
    public void updateComment(Integer commentId, Integer appraisalId, Integer userId){
        commentDao.updateComment(commentId);
        //看被采纳评论真假
        CommentBO commentBO = commentDao.queryCommentById(commentId);
        String judge = commentBO.getJudge();
        if(judge.equals("genuine")){
            appraisalDao.updateAppraisalState(appraisalId,"adopt");
        } else if (judge.equals("counterfeit")) {
            appraisalDao.updateAppraisalState(appraisalId,"failedpass");
        }
        //增加评论人经验
        userDao.updateUserIntegral(userId);

    }

    /**
     * 判断是否为鉴定发布者
     * @param id
     * @param userId
     * @return
     */
    private boolean isAppraisal(Integer userId, Integer id){
        AppraisalBo appraisal = appraisalDao.isAppraisal(userId, id);
        if(appraisal != null){
            return true;
        }
        return false;
    }

    /**
     * 获取评论
     * @param id
     * @param userId
     * @param pageOffset
     * @param pageSize
     * @return
     */
    public List<CommentDTO> getCommentByAppraisalId(Integer id, Integer userId, Integer pageOffset, Integer pageSize){
        Map<String, Object> map = new HashMap<String, Object>();
        List<CommentDTO> list = new ArrayList<CommentDTO>();
        map.put("pageOffset",pageOffset);
        map.put("pageSize",pageSize);
        map.put("appraisalId", id);

        List<GradeBO> gradeBOS = gradeDAO.queryGradeList();

        //判断是否为鉴定发布者
        if(isAppraisal(userId, id)){
        log.info("用户为鉴定发布者");
        //是鉴定发布者获取所有评论
            List<CommentBO> commentBOS = appraisalDao.queryComment(map);
            log.info("commentBOS:{}",commentBOS);
            if(!CollectionUtils.isEmpty(commentBOS)){
                for (CommentBO commentBO : commentBOS) {
                    CommentDTO dto = new CommentDTO();
                    dto.setCnComment(commentBO.getCnComment());
                    dto.setCommentId(commentBO.getId());
                    dto.setGradeName(isGrade(gradeBOS,commentBO.getIntegral()));
                    dto.setHeadUrl(commentBO.getHeadUrl());
                    dto.setJudge(commentBO.getJudge());
                    dto.setUserId(commentBO.getUserId());
                    dto.setUserName(commentBO.getName());
                    dto.setGoodsState(commentBO.getGoodsState());
                    if(commentBO.getGoodsState().equals("yes")){
                        list.add(0,dto);
                    } else {
                        list.add(dto);
                    }

                }
            }
        } else {
            log.info("不是鉴定发布者");
          //如果不是鉴定发布者
          //返回自己的评论和被采纳的评论
            map.put("userId", userId);
            //获取用户自己发布的评论
            List<CommentBO> commentBOS = appraisalDao.queryComment(map);
            log.info("获取自己发布的评论:{}",commentBOS);
            CommentBO comment = appraisalDao.goodsStateComment(map);
            log.info("获取被采纳的评论:{}",comment);
            if(comment != null){
                //判断这条被采纳评论是否在自己发布的评论中
                //是自己发布的
                if(isGoodState(commentBOS, comment)){

                } else {
                    //不是自己发布的
                    log.info("标记1");
                    commentBOS.add(comment);
                }
            }

            if(!CollectionUtils.isEmpty(commentBOS)){
                log.info("标记2");
                for (CommentBO commentBO : commentBOS) {
                    CommentDTO dto = new CommentDTO();
                    dto.setCnComment(commentBO.getCnComment());
                    dto.setCommentId(commentBO.getId());
                    dto.setGradeName(isGrade(gradeBOS,commentBO.getIntegral()));
                    dto.setHeadUrl(commentBO.getHeadUrl());
                    dto.setJudge(commentBO.getJudge());
                    dto.setUserId(commentBO.getUserId());
                    dto.setUserName(commentBO.getName());
                    dto.setGoodsState(commentBO.getGoodsState());
                    if(commentBO.getGoodsState().equals("yes")){
                        list.add(0,dto);
                    } else {
                        list.add(dto);
                    }
                }
            }
        }
        log.info("return:{}",list);
        return list;
    }

    /**
     * 获取用户等级
     * @param
     * @return
     */
    private String isGrade(List<GradeBO> gradeBOS,Integer integral){
        for (GradeBO gradeBO : gradeBOS) {
            if(integral <= gradeBO.getEmpirical()){
                return gradeBO.getGradeName();
            }
        }
        return "未知等级";
    }

    /**
     * 判断被采纳的评论是否在用户发布的评论中
     * @return
     */
    private boolean isGoodState(List<CommentBO> commentBOS,CommentBO comment){

        if(!CollectionUtils.isEmpty(commentBOS) && comment != null){
            for (CommentBO commentBO : commentBOS) {
                //如果存在返回ture
                if(commentBO.getUserId().equals(comment.getUserId())){
                    log.info("return true");
                    return true;
                }
            }
        }
        log.info("return false");
        return false;
    }

    /**
     * 删除评论
     * @param commentId
     * @return
     */
    public int deleteComment(@Param("commentId")Integer commentId){
        return commentDao.deleteComment(commentId);
    }

}

