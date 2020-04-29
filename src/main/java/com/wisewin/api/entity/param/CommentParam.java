package com.wisewin.api.entity.param;

/**
 * @Author: Wang bin
 * @date: Created in 10:52 2019/8/29
 */
public class CommentParam {

    private Integer id;//评论表
    private Integer appraisalId;//鉴定id
    private Integer userId;//用户id
    private String cnComment;//内容
    private String goodsState;//状态
    private String createTime;
    private String updateTime;
    private String judge;

    public Integer getId() {
        return id;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getJudge() {
        return judge;
    }

    public void setJudge(String judge) {
        this.judge = judge;
    }


    @Override
    public String toString() {
        return "CommentParam{" +
                "id=" + id +
                ", appraisalId=" + appraisalId +
                ", userId=" + userId +
                ", cnComment='" + cnComment + '\'' +
                ", goodsState='" + goodsState + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", judge='" + judge + '\'' +
                '}';
    }
}
