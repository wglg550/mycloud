package com.cloud.auth.controller;

import com.cloud.auth.entity.SUserEntity;
import com.cloud.auth.feign.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/restIndex")
public class indexRestController {

    @Autowired
    UserService userService;

    @RequestMapping("/first")
    public void first() {
        log.debug("welcome index");
    }

    @RequestMapping("/goIndexHtml")
//    @HystrixCommand(fallbackMethod = "goIndexHtmlFallback")
    public String goIndexHtml() {
        log.debug("goIndexHtml");
        return "/html/index.html";
    }

    @RequestMapping("/findByPhone")
    public SUserEntity findByPhone(String phone) {
        return userService.findByPhone(phone);
    }

    //hystrix回调
//    public String goIndexHtmlFallback() {
//        return null;
//    }
}
