package com.cloud.commons.Exception;

import com.cloud.commons.enums.ExceptionResultEnum;
import com.cloud.commons.utils.Result;
import com.cloud.commons.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@ControllerAdvice
@Slf4j
@EnableWebMvc
public class GlobalDefultExceptionHandler {

    //声明要捕获的异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public <T> Result defultExcepitonHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        if (e instanceof BusinessException) {
            log.error("业务异常：" + e.getMessage(), this.getClass());
            BusinessException businessException = (BusinessException) e;
            return ResultUtil.error(businessException.getCode(), businessException.getMessage());
        }
        //未知错误
        return ResultUtil.error(ExceptionResultEnum.UNKONW_ERROR.getCode(), ExceptionResultEnum.UNKONW_ERROR.name() + ":" + e);
    }

    @ExceptionHandler(SQLException.class)
    @ResponseBody
    public <T> Result sqlExceptionHandler(SQLException ex) {
        return ResultUtil.error(ExceptionResultEnum.SQL_ERROR.getCode(), ExceptionResultEnum.SQL_ERROR.name() + ":" + ex);
    }
}
