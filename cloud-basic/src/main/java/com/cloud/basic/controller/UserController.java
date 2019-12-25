package com.cloud.basic.controller;

import com.cloud.basic.master.dao.MasterSUserRepo;
import com.cloud.basic.master.entity.SUserEntity;
import com.cloud.basic.slave.dao.SlaveSUserRepo;
import com.cloud.commons.Exception.BusinessException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;

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
@RefreshScope //config手动刷新配置，必须post调用client:端口,如:{{httpLocal}}:8111/actuator/refresh
public class UserController {
    @Autowired
    MasterSUserRepo userRepo;

    @Resource
    SlaveSUserRepo slaveSUserRepo;

    @Value("${logging.level.root}")
    private String logLevel;//config手动刷新用
//
//    @Value("${test.default}")
//    private String test;
//
//    @Value("${server.port}")
//    private int label;

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

    @ApiOperation(value = "根据手机号查找用户slave")
    @GetMapping("/findByPhoneSlave")
    public com.cloud.basic.slave.entity.SUserEntity findByPhoneSlave(@ApiParam(value = "手机号码", required = true) @RequestParam String phone) {
        return slaveSUserRepo.findByPhone(phone);
    }

    @ApiOperation(value = "根据姓名查找用户")
    @GetMapping("/findByName")
    public SUserEntity findByName(@ApiParam(value = "姓名", required = true) @RequestParam String name) {
        return userRepo.findByName(name);
    }

    @ApiOperation(value = "用户查询接口")
    @GetMapping("/select")
    public List<SUserEntity> select(@ApiParam(value = "手机号码", required = true) @RequestParam String phone) {
        return null;
    }

    @ApiOperation(value = "用户注册接口")
    @PostMapping("/register")
    @Transactional(value = "transactionManagerMaster")//多事务下要指定事务管理器名称
    public Boolean register(@ApiParam(value = "用户注册信息：国家|姓名|密码|年龄|性别|地址|QQ号|微信号|手机号码|邮箱", required = true) @RequestBody SUserEntity userEntity) throws Exception {
//        String encodedText = Base64Util.encoder(String.valueOf(userEntity.getAge()));
//        Base64Util.decoder(encodedText);
//        String encodedMD5Text = MD5Util.encoder(String.valueOf(userEntity.getAge()));
//        MD5Util.verify(String.valueOf(userEntity.getAge()), encodedMD5Text);
//        String encodedSHA1Text = Sha1Util.encoder(String.valueOf(userEntity.getAge()));
//        Sha1Util.verify(String.valueOf(userEntity.getAge()), encodedSHA1Text);
//        String encrypt = AesUtil.encoder(String.valueOf(userEntity.getAge()), userEntity.getPhone());
//        AesUtil.decoder(encrypt, userEntity.getPhone());
//        userEntity = null;
        if (Objects.isNull(userEntity)) {
            throw new BusinessException("registerException", "userEntity不能为空");
        }
        userEntity = userRepo.save(userEntity);
        return userEntity.getId() != null ? true : false;
    }

    @ApiOperation(value = "用户注册接口Slave")
    @PostMapping("/registerSlave")
    @Transactional(value = "transactionManagerSlave")//多事务下要指定事务管理器名称
    public Boolean registerSlave(@ApiParam(value = "用户注册信息：国家|姓名|密码|年龄|性别|地址|QQ号|微信号|手机号码|邮箱", required = true) @RequestBody com.cloud.basic.slave.entity.SUserEntity userEntity) throws Exception {
        if (Objects.isNull(userEntity)) {
            throw new BusinessException("registerException", "userEntity不能为空");
        }
        userEntity = slaveSUserRepo.save(userEntity);
        return userEntity.getId() != null ? true : false;
    }

    @ApiOperation(value = "用户查重接口")
    @GetMapping("/selectRepeat")
//    hystrix使用
    @HystrixCommand(fallbackMethod = "selectRepeatFallback")
    public Integer selectRepeat(@ApiParam(value = "手机号码", required = true) @RequestParam String phone) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();//获取权限信息
//        int i = 1 / 0;
        throw new BusinessException("registerException", "userEntity不能为空");//加了Hystrix后，全局异常不生效
//        return null;
    }

    @ApiOperation(value = "testRefresh")
    @GetMapping("/testRefresh")
    public void testRefresh() {
        log.debug("debug:{}", logLevel);
        log.info("info:{}", logLevel);
        log.warn("warn:{}", logLevel);
        log.trace("trace:{}", logLevel);
        log.error("error:{}", logLevel);
    }

    //    hystrix回调
    public Integer selectRepeatFallback(String phone) {
        return 0;
    }
}
