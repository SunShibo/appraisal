package com.wisewin.api.entity.dto;

/**
 * @Author: Wang bin
 * @date: Created in 12:18 2019/9/19
 */
public class IAppraisalDTO {
    private int id;
    private int userId;
    private String title;
    private String describc;
    private String appraisalState;
    private String status;
    private String apImages;
    private String createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApImages() {
        return apImages;
    }

    public void setApImages(String apImages) {
        this.apImages = apImages;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
