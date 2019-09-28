package com.cloud.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/restIndex")
public class indexRestController {

    @RequestMapping("/first")
    public void first() {
        log.debug("welcome index");
    }

    @RequestMapping("/goIndexHtml")
    public String goIndexHtml() {
        log.debug("goIndexHtml");
        return "/html/index.html";
    }
}
