package com.cloud.commons.bean;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Description: 自定义异常处理消息
 * @Param:
 * @return:
 * @Author: wangliang
 * @Date: 2019/10/11
 */
@Getter
@Setter
public class Result implements Serializable {
    private String code;
    private String msg;
    private Object data;

    public Result() {

    }

    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
