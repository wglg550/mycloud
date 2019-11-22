package com.cloud.auth.feign.fallback;

import com.cloud.auth.entity.SUserEntity;
import com.cloud.auth.feign.UserService;
import org.springframework.stereotype.Service;

/**
 * @Description: feign fallback处理类
 * @Param:
 * @return:
 * @Author: wangliang
 * @Date: 2019/11/22
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public SUserEntity findByPhone(String phone) {
        return null;
    }
}