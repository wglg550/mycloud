package com.cloud.auth.feign;

import com.cloud.auth.entity.SUserEntity;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description: feign provider,开启后不能和全局异常共用
 * @Param:
 * @return:
 * @Author: wangliang
 * @Date: 2019/11/22
 */
@FeignClient(value = "cloud-basic"
//        ,
//        fallback = UserServiceImpl.class
//        fallbackFactory = UserServiceFeignFactory.class
)
public interface UserService {

    /**
     * @param phone
     * @return
     */
    @RequestMapping(value = "/user/findByPhone", method = RequestMethod.GET, produces = "application/json")
    SUserEntity findByPhone(@ApiParam(value = "手机号码", required = true) @RequestParam("phone") String phone);
}

