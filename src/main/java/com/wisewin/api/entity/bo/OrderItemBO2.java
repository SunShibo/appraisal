package com.wisewin.api.entity.bo;

import com.wisewin.api.entity.bo.common.base.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

public class OrderItemBO2 extends BaseModel {
    private Integer id;//订单信息表
    private Integer orderId;//订单id
    private Integer userId;//用户id
    private BigDecimal payment=new BigDecimal(0);//支付金额
    private Integer state;//订单状态 0-已取消 10-未付款 20-已付款
    private Integer rewardId;//被打赏人id
    private BigDecimal rewardAmount=new BigDecimal(0);//被打赏人实际收到金额
    private Integer commentId;//评论id
    private Integer appraisalId;//鉴定id
    private Date endTime;//交易完成时间
    private String paymentTime;//支付时间
    private String closeTime;//交易关闭时间
    private Date createTime;
    private Date updateTime;
    private String platformNumber; //支付流水号
    private String payPlatform;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getRewardId() {
        return rewardId;
    }

    public void setRewardId(Integer rewardId) {
        this.rewardId = rewardId;
    }

    public BigDecimal getRewardAmount() {
        return rewardAmount;
    }

    public void setRewardAmount(BigDecimal rewardAmount) {
        this.rewardAmount = rewardAmount;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getAppraisalId() {
        return appraisalId;
    }

    public void setAppraisalId(Integer appraisalId) {
        this.appraisalId = appraisalId;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
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

    public String getPlatformNumber() {
        return platformNumber;
    }

    public void setPlatformNumber(String platformNumber) {
        this.platformNumber = platformNumber;
    }

    public String getPayPlatform() {
        return payPlatform;
    }

    public void setPayPlatform(String payPlatform) {
        this.payPlatform = payPlatform;
    }

    @Override
    public String toString() {
        return "OrderItemBO2{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", userId=" + userId +
                ", payment=" + payment +
                ", state=" + state +
                ", rewardId=" + rewardId +
                ", rewardAmount=" + rewardAmount +
                ", commentId=" + commentId +
                ", appraisalId=" + appraisalId +
                ", endTime=" + endTime +
                ", paymentTime='" + paymentTime + '\'' +
                ", closeTime='" + closeTime + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", platformNumber='" + platformNumber + '\'' +
                ", payPlatform='" + payPlatform + '\'' +
                '}';
    }
}
