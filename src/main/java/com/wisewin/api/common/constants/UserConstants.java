package com.wisewin.api.common.constants;

public enum UserConstants {

    /*  验证码标识` */  VERIFY("verify"),
    /*  手机发送验证码记录  */RECORD("record"),
    /*  验证码失效标识` */ VERIFY_LOSE("verify_lose"),
    /*  发送验证码次数` */ DEGREE("degree"),

    /*  Yes  */  Yes("yes"),
    /*  No  */  No("no"),

    /*注册*/ registration("registration"),
    /*活跃 */ active("active");


    private UserConstants(String value) {
        this.value = value;
    }

    private UserConstants(Integer num) {
        this.num = num;
    }

    private String value;
    private Integer num;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}