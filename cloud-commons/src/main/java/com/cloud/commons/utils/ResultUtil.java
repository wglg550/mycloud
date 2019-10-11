package com.cloud.commons.utils;

import com.cloud.commons.enums.ExceptionResultEnum;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 自定义异常消息工具
 * @Param:
 * @return:
 * @Author: wangliang
 * @Date: 2019/10/11
 */
@Slf4j
public class ResultUtil {
    public static Result success(Object object) {
        return new Result(ExceptionResultEnum.SUCCESS.getCode(), ExceptionResultEnum.SUCCESS.getMsg(), object);
    }

    public static Result success() {
        return success(null);
    }

    public static Result error(String code, String msg) {
        log.error("error code:{},msg:{}", code, msg);
        return new Result(code, msg);
    }
}
