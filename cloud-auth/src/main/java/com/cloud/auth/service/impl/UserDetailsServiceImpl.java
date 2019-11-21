package com.cloud.auth.service.impl;

import com.cloud.basic.dao.SUserRepo;
import com.cloud.basic.entity.SUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author Pan Weilong
 * @date 2019/7/9 15:57
 * @description: 接口.
 */

//@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SUserEntity userEntity = null;
//        SUserEntity userEntity = sUserRepo.findByName(username);
        if (Objects.isNull(userEntity)) {
            throw new UsernameNotFoundException("用户不存在或密码错误");
        }
        return userEntity;
    }
}
