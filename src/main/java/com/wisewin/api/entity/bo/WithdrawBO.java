package com.wisewin.api.entity.bo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author: Wang bin
 * @date: Created in 11:54 2019/11/1
 */
public class WithdrawBO {
    private Integer id;
    private Integer userId;
    private String accountType;
    private String type;
    private String money;
    private String apiMsg;
    private Date createTime;
    private Date predictTime;

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

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getApiMsg() {
        return apiMsg;
    }

    public void setApiMsg(String apiMsg) {
        this.apiMsg = apiMsg;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPredictTime() {
        return predictTime = ThreeTime(this.createTime);
    }

    public void setPredictTime(Date predictTime) {
        this.predictTime = predictTime;
    }

    private static Date ThreeTime(Date da){
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(da.getTime());
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        calendar2.add(Calendar.DATE, 3);
        return calendar2.getTime();
    }

    public static void main(String[] args) {
        System.out.println(WithdrawBO.ThreeTime(new Date()));
    }

}
