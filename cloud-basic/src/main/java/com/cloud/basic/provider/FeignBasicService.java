package com.cloud.basic.provider;

import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description: 远程过程调用，用netty也可以，feign更加方便
 * @Param:
 * @return:
 * @Author: wangliang
 * @Date: 2019/9/27
 */
@FeignClient("BASIC")
@Service
public interface FeignBasicService {

    @RequestMapping(value = "/basic/user/login", method = RequestMethod.GET, produces = "application/json")
    public Boolean login(
//            @RequestHeader("Authorization") String authorization,
            @ApiParam(value = "手机号码", required = true) @RequestParam("phone") String phone, @ApiParam(value = "密码", required = true) @RequestParam("password") String password);
}
