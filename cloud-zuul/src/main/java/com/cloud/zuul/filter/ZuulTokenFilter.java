package com.cloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @Description: zuul网关 对token验证
 * @Param:
 * @return:
 * @Author: wangliang
 * @Date: 2019/9/27
 */
@Slf4j
public class ZuulTokenFilter extends ZuulFilter {

    //排除过滤的 uri 地址
    private static final String LOGIN_URI = "/basic/basic/user/login";
    private static final String REGISTER_URI = "/basic/basic/user/register";

    @Override
    public String filterType() {
        return "pre"; // 可以在请求被路由之前调用
    }

    @Override
    public int filterOrder() {
        return 0; // filter执行顺序，通过数字指定 ,优先级为0，数字越大，优先级越低
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
//        HttpServletResponse response = requestContext.getResponse();
        String requestURI = request.getRequestURI();
        //登录和注册放行
        if (LOGIN_URI.equals(requestURI) || REGISTER_URI.equals(requestURI)) {
//            String uuidToken = UUID.randomUUID().toString();
//            redisUtils.set(uuidToken, uuidToken);
//            response.setHeader("Access-Control-Expose-Headers",
//                    "Cache-Control,Content-Type,Expires,Pragma,Content-Language,Last-Modified,token");
//            response.setHeader("token", uuidToken); // 设置响应头
            return false;
        }
        return true;// 是否执行该过滤器，此处为true，说明需要过滤
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info("ZuulTokenFilter {},{}", request.getMethod(), request.getRequestURL().toString());
        String token = request.getHeader("token");// 获取请求的参数
        if (Objects.nonNull(token)) {
            ctx.setSendZuulResponse(true); //对请求进行路由
            ctx.setResponseStatusCode(200);
            ctx.set("isSuccess", true);
            return null;
        } else {
            ctx.setSendZuulResponse(false); //不对其进行路由
            ctx.setResponseStatusCode(400);
            ctx.setResponseBody("token is empty");
            ctx.set("isSuccess", false);
            return null;
        }
    }
}