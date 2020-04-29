package com.wisewin.api.entity.dto;

/**
 * @Author: Wang bin
 * @date: Created in 11:15 2019/9/17
 */
public class CommentDTO {
    private Integer commentId;
    private String userName;
    private String headUrl;
    private String gradeName;
    private String judge;
    private Integer userId;
    private String cnComment;
    private String goodsState;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getJudge() {
        return judge;
    }

    public void setJudge(String judge) {
        this.judge = judge;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCnComment() {
        return cnComment;
    }

    public void setCnComment(String cnComment) {
        this.cnComment = cnComment;
    }

    public String getGoodsState() {
        return goodsState;
    }

    public void setGoodsState(String goodsState) {
        this.goodsState = goodsState;
    }
}
