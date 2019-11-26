package com.cloud.auth.service;

import com.cloud.auth.entity.SUserEntity;
import com.cloud.auth.feign.UserFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Description: 自定义用户认证Service
 * @Param:
 * @return:
 * @Author: wangliang
 * @Date: 2019/11/21
 */
@Component
public class BaseUserDetailService implements UserDetailsService {

    @Autowired
    UserFeignService userFeignService;

    @Override
    public SUserEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        SUserEntity userEntity = userFeignService.findByPhone(username);
        if (Objects.isNull(userEntity)) {
            throw new UsernameNotFoundException("用户不存在或密码错误");
        }
        return userEntity;
    }
}

