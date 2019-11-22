package com.cloud.auth.feign.fallback;

import com.cloud.auth.feign.UserFeignService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @Description: feign fallbackFactory处理类，可处理http异常
 * @Param:
 * @return:
 * @Author: wangliang
 * @Date: 2019/11/22
 */
@Component
public class UserFeignServiceFactory implements FallbackFactory<UserFeignService> {

    private final UserFeignServiceImpl userFeignService;

    public UserFeignServiceFactory(UserFeignServiceImpl userFeignService) {
        this.userFeignService = userFeignService;
    }

    @Override
    public UserFeignService create(Throwable throwable) {
        throwable.printStackTrace();
        return userFeignService;
    }
}
