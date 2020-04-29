
package com.wisewin.api.dao;

import com.wisewin.api.entity.bo.CommentBO;
import com.wisewin.api.entity.param.CommentParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: Wang bin
 * @date: Created in 10:52 2019/8/29
 */
public interface CommentDao {

    /**
     * 添加用户评论
     * @param commentParam
     * @return
     */
    int inertComment(CommentParam commentParam);

    /**
     * 根据鉴定id和评论id获取该条评论
     * @param appraisalId
     * @param commentId
     * @return
     */
    CommentBO queryComment(@Param("appraisalId")Integer appraisalId,
                           @Param("commentId")Integer commentId
                           );

    /**
     * 判断改鉴定下是否有已被采纳的评论
     * @param appraisalId
     * @return
     */
    CommentBO queryCommentByAppraisalId(@Param("appraisalId")Integer appraisalId);

    /**
     * 将评论列为采纳
     * @param commentId
     * @return
     */
    int updateComment(@Param("commentId") Integer commentId);

    List<CommentBO>  getCommentByAppraisalId(@Param("id") Integer id,@Param("userId") Integer userId,@Param("pageOffset")Integer pageOffset,@Param("pageSize")Integer pageSize);

    /**
     * 根据id获取该条评论
     * @param id
     * @return
     */
    CommentBO queryCommentById(@Param("id")Integer id);


    /**
     * 根据id删除评论
     * @param commentId
     * @return
     */
    int deleteComment(@Param("commentId")Integer commentId);
}
