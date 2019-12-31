package com.cloud.auth.service;

import com.cloud.auth.entity.SUserEntity;
import com.cloud.auth.feign.UserFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
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
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");//这里只能以ROLE_ADMIN和ROLE_USER进行角色配置
        authorities.add(authority);
//        authority = new SimpleGrantedAuthority("ROLE_USER");//这里只能以ROLE_ADMIN和ROLE_USER进行角色配置
//        authorities.add(authority);
        userEntity.setAuthorities(authorities);
//        // 返回带有用户权限信息的User
//        User user = new User(userEntity.getUsername(),
//                userEntity.getPassword(), true, true, true, true, authorities);
        return userEntity;
    }
}

