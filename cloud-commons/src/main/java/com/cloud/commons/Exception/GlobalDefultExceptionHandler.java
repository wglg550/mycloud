package com.cloud.commons.Exception;

import com.cloud.commons.enums.ExceptionResultEnum;
import com.cloud.commons.utils.Result;
import com.cloud.commons.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

/**
 * @Description: 全局异常处理
 * @Param:
 * @return:
 * @Author: wangliang
 * @Date: 2019/10/11
 */
@ControllerAdvice
@Slf4j
public class GlobalDefultExceptionHandler {

    /**
     * excetion异常处理
     *
     * @param request
     * @param e
     * @param <T>
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public <T> Result defultExcepitonHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        log.error("Exception异常：{}", e, this.getClass());
        if (e instanceof BusinessException) {
            BusinessException businessException = (BusinessException) e;
            return ResultUtil.error(businessException.getCode(), businessException.getMessage());
        }
        //Exception错误
        return ResultUtil.error(ExceptionResultEnum.EXCEPTION_ERROR.getCode(), ExceptionResultEnum.EXCEPTION_ERROR.getMsg() + e);
    }

    /**
     * sql异常处理
     *
     * @param e
     * @param <T>
     * @return
     */
    @ExceptionHandler(SQLException.class)
    @ResponseBody
    public <T> Result sqlExceptionHandler(SQLException e) {
        log.error("sqlException异常：{}", e, this.getClass());
        return ResultUtil.error(ExceptionResultEnum.SQL_ERROR.getCode(), ExceptionResultEnum.SQL_ERROR.name() + e);
    }

    /**
     * 反射异常处理
     *
     * @param e
     * @param <T>
     * @return
     */
    @ExceptionHandler(InvocationTargetException.class)
    @ResponseBody
    public <T> Result invocationTargetExceptionHandler(InvocationTargetException e) {
        log.error("invocationTarget异常：{}", e.getTargetException(), this.getClass());
        return ResultUtil.error(ExceptionResultEnum.INVOCATIONTARGET_ERROR.getCode(), ExceptionResultEnum.INVOCATIONTARGET_ERROR.name() + e.getTargetException());
    }
}
