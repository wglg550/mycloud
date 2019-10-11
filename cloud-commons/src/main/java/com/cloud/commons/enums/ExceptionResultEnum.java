package com.cloud.commons.enums;

import lombok.Getter;

/**
 * @Description: 自定义异常消息枚举
 * @Param:
 * @return:
 * @Author: wangliang
 * @Date: 2019/10/11
 */
@Getter
public enum ExceptionResultEnum {
    UNKONW_ERROR("-2", "未知错误:"),

    EXCEPTION_ERROR("-1", "系统异常:"),

    SUCCESS("0", "成功"),

    ERROR("1", "失败"),

    SQL_ERROR("502", "sql异常:"),

    INVOCATIONTARGET_ERROR("503", "反射异常:"),

    USERNAME_ILLEGALITY("100", "用户名非法"),

    PARAMS_ILLEGALITY("102", "参数不能为空"),

    PASSWORD_ILLEGALITY("101", "密码非法");

    private String code;
    private String msg;

    ExceptionResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}