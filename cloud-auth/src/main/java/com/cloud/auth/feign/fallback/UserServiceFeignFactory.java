package com.cloud.auth.feign.fallback;

import com.cloud.auth.feign.UserService;
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
public class UserServiceFeignFactory implements FallbackFactory<UserService> {

    private final UserServiceImpl userServiceImpl;

    public UserServiceFeignFactory(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    public UserService create(Throwable throwable) {
        throwable.printStackTrace();
        return userServiceImpl;
    }
}
