package com.wisewin.api.entity.bo;

import com.wisewin.api.entity.bo.common.base.BaseModel;
import com.wisewin.api.util.TimeUtil;
import com.wisewin.api.util.date.DateUtil;

import java.math.BigDecimal;
import java.util.Date;

public class UserBO  extends BaseModel{

    private Integer id;
    private String name="";
    private String phone="";
    private String headUrl="";
    private int integral=0;//经验值
    private BigDecimal money=new BigDecimal(0);
    private String qqOpenid="";
    private String wxOpenid="";
    private String status="";
    private String registeredChannels="";//注册渠道
    private String idNumber="";
    private String birthday="";
    private String email="";
    private Date createTime;
    private Date updateTime;
    private String sex="";
    private String payPassword="";
    private String zfbUserId="";
    private String watermark="";
    private String watermarkState="";

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getQqOpenid() {
        return qqOpenid;
    }

    public void setQqOpenid(String qqOpenid) {
        this.qqOpenid = qqOpenid;
    }

    public String getWxOpenid() {
        return wxOpenid;
    }

    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRegisteredChannels() {
        return registeredChannels;
    }

    public void setRegisteredChannels(String registeredChannels) {
        this.registeredChannels = registeredChannels;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public String getZfbUserId() {
        return zfbUserId;
    }

    public void setZfbUserId(String zfbUserId) {
        this.zfbUserId = zfbUserId;
    }

    public String getWatermark() {
        return watermark;
    }

    public void setWatermark(String watermark) {
        this.watermark = watermark;
    }

    public String getWatermarkState() {
        return watermarkState;
    }

    public void setWatermarkState(String watermarkState) {
        this.watermarkState = watermarkState;
    }
}
