package com.wisewin.api.entity.bo;

import java.util.Date;

/**
 * @Author: Wang bin
 * @date: Created in 10:26 2019/10/8
 */
public class FeedBackBO {
    private Integer id;
    private Integer userId;
    private String describc;
    private Integer apStatus;
    private Date createTime;
    private Date updateTime;

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

    public String getDescribc() {
        return describc;
    }

    public void setDescribc(String describc) {
        this.describc = describc;
    }

    public Integer getApStatus() {
        return apStatus;
    }

    public void setApStatus(Integer apStatus) {
        this.apStatus = apStatus;
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
}
