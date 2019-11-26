package com.cloud.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableZuulProxy
//@EnableEurekaServer
@SpringBootApplication
@EnableHystrixDashboard
@EnableFeignClients
public class CloudZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudZuulApplication.class, args);
    }

//    @Bean
//    public ZuulTokenFilter tokenFilter() {
//        return new ZuulTokenFilter();
//    }
}
