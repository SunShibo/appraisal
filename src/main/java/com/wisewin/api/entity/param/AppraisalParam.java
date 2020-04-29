package com.wisewin.api.entity.param;

import java.util.Date;

/**
 * @Author: Wang bin
 * @date: Created in 12:33 2019/8/27
 */
public class AppraisalParam {

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

    private String apImages;


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

    @Override
    public String toString() {
        return "AppraisalParam{" +
                "id=" + id +
                ", userId=" + userId +
                ", appraisalTypeId=" + appraisalTypeId +
                ", title='" + title + '\'' +
                ", appraisalState='" + appraisalState + '\'' +
                ", browse=" + browse +
                ", apCase='" + apCase + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", describc='" + describc + '\'' +
                ", apImages='" + apImages + '\'' +
                '}';
    }
}
