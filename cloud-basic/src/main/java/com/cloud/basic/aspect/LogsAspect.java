package com.cloud.basic.aspect;

import com.alibaba.fastjson.JSONObject;
import com.cloud.commons.Exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Objects;

/**
 * @Description: logs切面
 * @Param:
 * @return:
 * @Author: wangliang
 * @Date: 2019/10/11
 */
//@Aspect
//@Component
@Slf4j
public class LogsAspect {

    //Controller层切点，基于注解
//    @Pointcut("@annotation(cn.com.qmth.examcloud.core.k12.mall.api.annotation.LogsProdCreate)")
//    public void logsProdCreateAspect() {
//
//    }

    //切入该类下的所有方法
    @Pointcut("execution(* com.cloud.basic.controller.UserController.*(..))")
    public void UserAspect() {

    }


    @AfterReturning(value = "UserAspect()", returning = "result")
    public void afterReturnUserPoint(JoinPoint joinPoint, Object result) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        log.debug("login aspect afterReturnUserPoint path:{}", className + "." + methodName);
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Object[] args = joinPoint.getArgs(); // 参数值
        if (Objects.isNull(args)) {
            throw new BusinessException("afterReturnUserPointException", "args不能为空");
        }
        String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        if (Objects.isNull(argNames)) {
            throw new BusinessException("afterReturnUserPointException", "argNames不能为空");
        }
        log.debug("args:{},argNames:{}", JSONObject.toJSONString(Arrays.asList(args)), JSONObject.toJSONString(Arrays.asList(argNames)));
    }

//    /**
//     * 后置通知，正常返回，产品/创建和修改日志
//     *
//     * @param joinPoint
//     * @param result
//     */
//    @AfterReturning(value = "logsProdCreateAspect()", returning = "result")
//    public void afterReturnProdCreate(JoinPoint joinPoint, Object result) {
//
//    }
//
//    /**
//     * 后置通知，正常返回，产品修改日志
//     *
//     * @param joinPoint
//     * @param result
//     */
//    @AfterReturning(value = "logsProdUpdateAspect()", returning = "result")
//    public void afterReturnProdUpdate(JoinPoint joinPoint, Object result) {
//
//    }
//
//    /**
//     * 后置通知，正常返回，订单创建日志
//     *
//     * @param joinPoint
//     * @param result
//     */
//    @AfterReturning(value = "logsOrderCreateAspect()", returning = "result")
//    public void afterReturnOrderCreate(JoinPoint joinPoint, Object result) {
//
//    }
//
//    /**
//     * 后置通知，正常返回，订单修改日志
//     *
//     * @param joinPoint
//     * @param result
//     */
//    @AfterReturning(value = "logsOrderUpdateAspect()", returning = "result")
//    public void afterReturnOrderUpdate(JoinPoint joinPoint, Object result) {
//
//    }
}