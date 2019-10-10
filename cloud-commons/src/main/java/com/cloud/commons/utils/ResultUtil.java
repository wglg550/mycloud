package com.cloud.commons.utils;

import com.cloud.commons.enums.ExceptionResultEnum;

public class ResultUtil {
    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(ExceptionResultEnum.SUCCESS.getCode());
        result.setMsg(ExceptionResultEnum.SUCCESS.getMsg());
        result.setData(object);
        return result;
    }

    public static Result success() {
        return success(null);
    }

    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
