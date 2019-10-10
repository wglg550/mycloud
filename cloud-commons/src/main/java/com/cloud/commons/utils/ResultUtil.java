package com.cloud.commons.utils;

import com.cloud.commons.enums.ExceptionResultEnum;

public class ResultUtil {
    public static Result success(Object object) {
        return new Result(ExceptionResultEnum.SUCCESS.getCode(), ExceptionResultEnum.SUCCESS.getMsg(), object);
    }

    public static Result success() {
        return success(null);
    }

    public static Result error(String code, String msg) {
        return new Result(ExceptionResultEnum.SUCCESS.getCode(), ExceptionResultEnum.SUCCESS.getMsg());
    }
}
