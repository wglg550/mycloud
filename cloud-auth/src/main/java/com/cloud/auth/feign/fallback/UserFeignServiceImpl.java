package com.cloud.auth.feign.fallback;

import com.cloud.auth.entity.SUserEntity;
import com.cloud.auth.feign.UserFeignService;
import org.springframework.stereotype.Service;

/**
 * @Description: feign fallback处理类
 * @Param:
 * @return:
 * @Author: wangliang
 * @Date: 2019/11/22
 */
@Service
public class UserFeignServiceImpl implements UserFeignService {

    @Override
    public SUserEntity findByPhone(String phone) {
        return null;
    }
}