package com.cloud.basic.controller;

import com.cloud.basic.dao.SUserRepo;
import com.cloud.basic.entity.SUserEntity;
import com.cloud.commons.Exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @Description: user controller
 * @Param:
 * @return:
 * @Author: wangliang
 * @Date: 2019/10/11
 */
@Api(tags = "用户接口controller")
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    SUserRepo userRepo;

    @ApiOperation(value = "用户登录接口")
    @GetMapping("/login")
//    @LogMethod
    public SUserEntity login(@ApiParam(value = "手机号码", required = true) @RequestParam String phone, @ApiParam(value = "密码", required = true) @RequestParam String password) {
        return userRepo.findByPhoneAndPassword(phone, password);
    }

    @ApiOperation(value = "根据手机号查找用户")
    @GetMapping("/findByPhone")
    public SUserEntity findByPhone(@ApiParam(value = "手机号码", required = true) @RequestParam String phone) {
        return userRepo.findByPhone(phone);
    }

    @ApiOperation(value = "用户查询接口")
    @GetMapping("/select")
    public List<SUserEntity> select(@ApiParam(value = "手机号码", required = true) @RequestParam String phone) {
        return null;
    }

    @ApiOperation(value = "用户注册接口")
    @PostMapping("/register")
    public Boolean register(@ApiParam(value = "用户注册信息：国家|姓名|密码|年龄|性别|地址|QQ号|微信号|手机号码|邮箱", required = true) @RequestBody SUserEntity userEntity) throws Exception {
//        String encodedText = Base64Util.encoder(String.valueOf(userEntity.getAge()));
//        Base64Util.decoder(encodedText);
//        String encodedMD5Text = MD5Util.encoder(String.valueOf(userEntity.getAge()));
//        MD5Util.verify(String.valueOf(userEntity.getAge()), encodedMD5Text);
//        String encodedSHA1Text = Sha1Util.encoder(String.valueOf(userEntity.getAge()));
//        Sha1Util.verify(String.valueOf(userEntity.getAge()), encodedSHA1Text);
//        String encrypt = AesUtil.encoder(String.valueOf(userEntity.getAge()), userEntity.getPhone());
//        AesUtil.decoder(encrypt, userEntity.getPhone());
        if (Objects.isNull(userEntity)) {
            throw new BusinessException("registerException", "userEntity不能为空");
        }
        userEntity = userRepo.save(userEntity);
        return userEntity.getId() != null ? true : false;
    }

    @ApiOperation(value = "用户查重接口")
    @GetMapping("/selectRepeat")
    public Integer selectRepeat(@ApiParam(value = "手机号码", required = true) @RequestParam String phone) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return null;
    }
}
