package com.wisewin.api.entity.bo;

import java.util.Date;

public class AppraisalBo {
    private Integer id;

    private Integer userId;

    private Integer appraisalTypeId;

    private String title;

    private String appraisalState;

    private Integer browse;

    private String apCase;

    private String createTime;

    private String updateTime;

    private String describc;
    private String typeName;//类型名称
    private Integer enshrineId;//收藏表id
    private String status;
    private String headUrl;
    private String apImages;
    private String userName;
    private String reisedState;

    private String appraisalTypeName;

    public Integer getEnshrineId() {
        return enshrineId;
    }

    public void setEnshrineId(Integer enshrineId) {
        this.enshrineId = enshrineId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

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

    public String getAppraisalState() {
        return appraisalState;
    }

    public void setAppraisalState(String appraisalState) {
        this.appraisalState = appraisalState;
    }

    public Integer getBrowse() {
        return browse;
    }

    public void setBrowse(Integer browse) {
        this.browse = browse;
    }

    public String getApCase() {
        return apCase;
    }

    public void setApCase(String apCase) {
        this.apCase = apCase;
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

    public String getDescribc() {
        return describc;
    }

    public void setDescribc(String describc) {
        this.describc = describc;
    }

    public String getApImages() {
        return apImages;
    }

    public void setApImages(String apImages) {
        this.apImages = apImages;
    }

    public String getAppraisalTypeName() {
        return appraisalTypeName;
    }

    public void setAppraisalTypeName(String appraisalTypeName) {
        this.appraisalTypeName = appraisalTypeName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReisedState() {
        return reisedState;
    }

    public void setReisedState(String reisedState) {
        this.reisedState = reisedState;
    }
}