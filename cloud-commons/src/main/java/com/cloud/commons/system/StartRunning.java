package com.cloud.commons.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
* @Description: 服务启动时初始化运行，哪个微服务模块需要则拿此模版去用
* @Param:
* @return:
* @Author: wangliang
* @Date: 2019/10/11
*/
//@Component
public class StartRunning implements CommandLineRunner
{
    @Override
    public void run(String... args) throws Exception {

    }
//    @Autowired
//    private IDeptService deptService;
//
//    @Override
//    public void run(String... args) throws Exception
//    {
//        System.out.println("============样本：服务器启动时执行================");
//        for (String arg:args){
//            //args参数数组是启动传入进来的
//            System.out.println("========样本：执行的参数====="+arg);
//        }
//        Dept dept = deptService.selectById(25);
//        System.out.println("=======样本：dept:======="+dept.toString());
//    }
}
