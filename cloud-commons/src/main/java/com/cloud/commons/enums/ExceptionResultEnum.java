package com.cloud.commons.enums;

public enum ExceptionResultEnum {
    UNKONW_ERROR(-1, "未知错误"),

    SUCCESS(0, "成功"),

    ERROR(1, "失败"),

    USERNAME_ILLEGALITY(100, "用户名非法"),

    PASSWORD_ILLEGALITY(101, "密码非法");

    private Integer code;
    private String msg;

    ExceptionResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}