package com.cloud.commons.Exception;

import com.cloud.commons.enums.ExceptionResultEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @Description: 自定义异常处理
 * @Param:
 * @return:
 * @Author: wangliang
 * @Date: 2019/10/11
 */
@Getter
@Setter
@Slf4j
public class BusinessException extends RuntimeException implements Serializable {
    private String code;  //错误码
    private String msg;

    public BusinessException() {

    }

    public BusinessException(ExceptionResultEnum exceptionResultEnum) {
        super(exceptionResultEnum.getMsg());
        this.code = exceptionResultEnum.getCode();
    }

    public BusinessException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
