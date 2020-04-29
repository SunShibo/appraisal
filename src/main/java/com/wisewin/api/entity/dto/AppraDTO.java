package com.wisewin.api.entity.dto;

/**
 * @Author: Wang bin
 * @date: Created in 11:10 2019/9/4
 */
public class AppraDTO {
    private Integer id;
    private Integer appraisalTypeId;
    private String title;
    private String describc;
    private String apImages;
    private String appraisalState;
    private String createTime;

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

    public String getApImages() {
        return apImages;
    }

    public void setApImages(String apImages) {
        this.apImages = apImages;
    }

    public String getAppraisalState() {
        return appraisalState;
    }

    public void setAppraisalState(String appraisalState) {
        this.appraisalState = appraisalState;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAppraisalTypeId() {
        return appraisalTypeId;
    }

    public void setAppraisalTypeId(Integer appraisalTypeId) {
        this.appraisalTypeId = appraisalTypeId;
    }
}
