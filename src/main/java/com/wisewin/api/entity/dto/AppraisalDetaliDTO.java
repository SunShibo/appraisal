package com.wisewin.api.entity.dto;

/**
 * @Author: Wang bin
 * @date: Created in 12:23 2019/9/10
 */
public class AppraisalDetaliDTO {
  private Integer id;
  private Integer userId;
  private Integer appraisalTypeId;
  private String title;
  private String describc;
  private String appraisalState;
  private String appraisalTypeName;
  private String createTime;
  private String updateTime;
  private String userName;
  private String headUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAppraisalTypeId() {
        return appraisalTypeId;
    }

    public void setAppraisalTypeId(Integer appraisalTypeId) {
        this.appraisalTypeId = appraisalTypeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribc() {
        return describc;
    }

    public void setDescribc(String describc) {
        this.describc = describc;
    }

    public String getAppraisalState() {
        return appraisalState;
    }

    public void setAppraisalState(String appraisalState) {
        this.appraisalState = appraisalState;
    }

    public String getAppraisalTypeName() {
        return appraisalTypeName;
    }

    public void setAppraisalTypeName(String appraisalTypeName) {
        this.appraisalTypeName = appraisalTypeName;
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

    @Override
    public String toString() {
        return "AppraisalDetaliDTO{" +
                "id=" + id +
                ", userId=" + userId +
                ", appraisalTypeId=" + appraisalTypeId +
                ", title='" + title + '\'' +
                ", describc='" + describc + '\'' +
                ", appraisalState='" + appraisalState + '\'' +
                ", appraisalTypeName='" + appraisalTypeName + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", userName='" + userName + '\'' +
                ", headUrl='" + headUrl + '\'' +
                '}';
    }
}
