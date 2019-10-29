package com.cloud.commons.aspect;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cloud.commons.Exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Description: logging切面(主要用来打印方式耗时)
 * @Param:
 * @return:
 * @Author: wangliang
 * @Date: 2019/10/11
 */
@Aspect
@Component
@Slf4j
public class LogMethodAspect {

    private final String TOKEN = "token",
            MARK_USER = "markUser",//登录用户
            PARAM_ARGS = "args";//方法参数
    private Map<String, Object> map;
    //            PARAM_ARG_NAMES = "argNames"//方法参数名

    //Controller层切点
    @Pointcut("@annotation(com.cloud.commons.annotation.LogMethod)")
    public void logAspect() {

    }

    /**
     * 日志环绕通知
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("logAspect()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        log.info("path method log:{} 开始执行方法", className + "." + methodName);
        Object o = joinPoint.proceed();
        // 获取执行的方法名
        long end = System.currentTimeMillis();
        // 打印耗时的信息
        log.info("path method log 执行方法:{},耗时{}", methodName, (end - start) / 1000 + "s");
        return o;
    }

//    //切入该类下的所有方法
//    @Pointcut("execution(* com.cloud.basic.controller.UserController.*(..))")
//    public void UserAspect() {
//
//    }

    /**
     * 切面公用方法
     *
     * @param joinPoint
     * @return
     * @throws Exception
     */
    public Map getAspectCommon(JoinPoint joinPoint) throws Exception {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        log.info("{} aspect path:{}", this.getClass().getMethods()[0], className + "." + methodName);
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpSession session = request.getSession();
        String token = (String) session.getAttribute(TOKEN);
        if (Objects.isNull(token)) {
            throw new BusinessException("loginException", "请先登录");
        }
//        MarkUser markUser = (MarkUser) session.getAttribute(MARK_USER);
//        log.info("markUser:{}", JSONObject.toJSONString(markUser));
        Object[] args = joinPoint.getArgs(); // 参数值

//        String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();//参数名
        JSONArray jsonArgsArray = JSONObject.parseArray(JSONObject.toJSONString(args));
//        JSONArray jsonArgNamesArray = JSONObject.parseArray(JSONObject.toJSONString(argNames));
        log.info("jsonArgsArray:{},jsonArgNamesArray:{}", jsonArgsArray);
//        LOGGER.info("jsonArgNamesArray:{}", jsonArgNamesArray);
        Map<String, Object> map = new HashMap<>();
        map.put(PARAM_ARGS, jsonArgsArray);
//        map.put(PARAM_ARG_NAMES, jsonArgNamesArray);
//        map.put(MARK_USER, markUser);
        return map;
    }
}