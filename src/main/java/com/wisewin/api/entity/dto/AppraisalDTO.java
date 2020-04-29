package com.wisewin.api.entity.dto;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: Wang bin
 * @date: Created in 10:49 2019/8/28
 */
public class AppraisalDTO {
    private int id;
    private int userId;
    private int appraisalTypeId;
    private String appraisalTypeName;
    private String title;
    private String describc;
    private String appraisalState;
    private String apCase;
    private String createTime;
    private String status;
    private String apImages;

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

    public int getAppraisalTypeId() {
        return appraisalTypeId;
    }

    public void setAppraisalTypeId(int appraisalTypeId) {
        this.appraisalTypeId = appraisalTypeId;
    }

    public String getAppraisalTypeName() {
        return appraisalTypeName;
    }

    public void setAppraisalTypeName(String appraisalTypeName) {
        this.appraisalTypeName = appraisalTypeName;
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
        List<String> strings = Arrays.asList(apImages.split(","));
        this.apImages = strings.get(0);
    }
}
