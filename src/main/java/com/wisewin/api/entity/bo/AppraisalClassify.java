package com.wisewin.api.entity.bo;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Wang bin
 * @date: Created in 14:00 2019/10/30
 */
public class AppraisalClassify implements Serializable {
    private Integer id;
    private String typeName;
    private List<AppraisalClassifyInfo> appraisalClassifyInfos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<AppraisalClassifyInfo> getAppraisalClassifyInfos() {
        return appraisalClassifyInfos;
    }

    public void setAppraisalClassifyInfos(List<AppraisalClassifyInfo> appraisalClassifyInfos) {
        this.appraisalClassifyInfos = appraisalClassifyInfos;
    }

    @Override
    public String toString() {
        return "AppraisalClassifyDao{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                ", appraisalClassifyInfos=" + appraisalClassifyInfos +
                '}';
    }
}
