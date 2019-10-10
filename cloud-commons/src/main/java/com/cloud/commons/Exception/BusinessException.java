package com.cloud.commons.Exception;

import com.cloud.commons.enums.ExceptionResultEnum;

import java.io.Serializable;

public class BusinessException extends RuntimeException implements Serializable {
    private Integer code;  //错误码

    public BusinessException() {
    }

    public BusinessException(ExceptionResultEnum exceptionResultEnum) {
        super(exceptionResultEnum.getMsg());
        this.code = exceptionResultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
