package com.cloud.commons.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result {

    private Integer code;
    private String msg;
    private Object data;
}
