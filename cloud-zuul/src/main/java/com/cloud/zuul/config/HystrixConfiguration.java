package com.cloud.zuul.config;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
* @Description: hystrix 非图形化界面，访问地址：http://localhost:8113/hystrix.stream
* @Param:
* @return:
* @Author: wangliang
* @Date: 2019/9/27
*/
//@Configuration
public class HystrixConfiguration {

//    @Bean(name = "hystrixRegistrationBean")
//    public ServletRegistrationBean servletRegistrationBean() {
//        ServletRegistrationBean registration = new ServletRegistrationBean(
//                new HystrixMetricsStreamServlet(), "/hystrix.stream");
//        registration.setName("hystrixServlet");
//        registration.setLoadOnStartup(1);
//        return registration;
//    }
//
//    @Bean(name = "hystrixForTurbineRegistrationBean")
//    public ServletRegistrationBean servletTurbineRegistrationBean() {
//        ServletRegistrationBean registration = new ServletRegistrationBean(
//                new HystrixMetricsStreamServlet(), "/actuator/hystrix.stream");
//        registration.setName("hystrixForTurbineServlet");
//        registration.setLoadOnStartup(1);
//        return registration;
//    }
}