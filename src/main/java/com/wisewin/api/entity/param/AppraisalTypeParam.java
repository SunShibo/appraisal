package com.wisewin.api.entity.param;

import com.wisewin.api.entity.bo.common.base.BaseModel;

import java.util.Date;

public class AppraisalTypeParam extends BaseModel {
    private Integer id;

    private String typeName;

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
}