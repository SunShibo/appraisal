package com.wisewin.api.entity.bo;

import com.wisewin.api.entity.bo.common.base.BaseModel;

import java.util.Date;

public class ReisedBO extends BaseModel {
    private Integer id;//纠错
    private Integer appraisalId;//物品id
    private Integer commentId;//评论id
    private String content;//纠错内容
    private Integer userId;
    private String status;//状态
    private Date createTime;
    private Date updateTime;
    private String apShow;
    private Integer apRead;
    private String judge;//真假
    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getJudge() {
        return judge;
    }

    public void setJudge(String judge) {
        this.judge = judge;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAppraisalId() {
        return appraisalId;
    }

    public void setAppraisalId(Integer appraisalId) {
        this.appraisalId = appraisalId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getApShow() {
        return apShow;
    }

    public void setApShow(String apShow) {
        this.apShow = apShow;
    }

    public Integer getApRead() {
        return apRead;
    }

    public void setApRead(Integer apRead) {
        this.apRead = apRead;
    }
}
