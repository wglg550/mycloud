package com.cloud.commons.annotation;

import java.lang.annotation.*;

/**
 * logging注解(主要用来打印方式耗时)
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogMethodAnnotation {
}
