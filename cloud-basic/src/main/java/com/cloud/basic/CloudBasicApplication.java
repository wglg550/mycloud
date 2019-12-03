package com.cloud.basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@ComponentScan(basePackages = {"com.cloud.basic"
        , "com.cloud.commons"
})//这里要扫commons的全局异常处理包
//主要就是定义扫描的路径从中找出标识了需要装配的类自动装配到spring的bean容器中,做过web开发的同学一定都有用过@Controller，@Service，@Repository注解，查看其源码你会发现，他们中有一个共同的注解@Component，没错@ComponentScan注解默认就会装配标识了@Controller，@Service，@Repository，@Component注解的类到spring容器中
@EntityScan(basePackages = {"com.cloud.basic"})//用来扫描和发现指定包及其子包中的Entity定义
@EnableJpaRepositories(basePackages = {"com.cloud.basic"})//注解用于Srping JPA的代码配置
//@EnableJpaAuditing //Spring Data JPA 审计,开启后自动注入createTime和ceateUser
@SpringBootApplication
@EnableEurekaClient   //只有eureka为注册中心时可以发现服务
//@EnableDiscoveryClient //引用其他注册中心也可以发现服务
//@EnableCaching //开启缓存功能
@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})//
@EnableTransactionManagement //spring开启事务支持
@EnableFeignClients//fegin支持
@EnableCircuitBreaker//hystrix服务降级
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CloudBasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudBasicApplication.class, args);
    }

    @EnableWebSecurity
    public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/swagger-ui.html")
                    .antMatchers("/webjars/**")
                    .antMatchers("v2/**")
                    .antMatchers("/swagger-resources/**")
                    .antMatchers("/druid/**")
                    .antMatchers("/index.html");
        }
    }
}
