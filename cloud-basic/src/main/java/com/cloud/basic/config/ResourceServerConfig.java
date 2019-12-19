package com.cloud.basic.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 不创建会话 - 即通过前端传token到后台过滤器中验证是否存在访问权限
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http
//                .authorizeRequests();
//        // 标识访问 `/home` 这个接口，需要具备`ADMIN`角色
//        registry.antMatchers("/home").hasRole("ADMIN");
//        // 标识只能在 服务器本地ip[127.0.0.1或localhost] 访问 `/home` 这个接口，其他ip地址无法访问
//        registry.antMatchers("/home").hasIpAddress("127.0.0.1");
//        // 允许匿名的url - 可理解为放行接口 - 多个接口使用,分割
//        registry.antMatchers("/login", "/index").permitAll();
////        registry.antMatchers("/**").access("hasAuthority('admin')");
//        // OPTIONS(选项)：查找适用于一个特定网址资源的通讯选择。 在不需执行具体的涉及数据传输的动作情况下， 允许客户端来确定与资源相关的选项以及 / 或者要求， 或是一个服务器的性能
//        registry.antMatchers(HttpMethod.OPTIONS, "/**").denyAll();
//        // 自动登录 - cookie储存方式
//        registry.and().rememberMe();
//        // 其余所有请求都需要认证
//        registry.anyRequest().authenticated();
//        // 防止iframe 造成跨域
//        registry.and().headers().frameOptions().disable();

//        http
//                .csrf().disable()
//                .exceptionHandling()
//                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
//                .and()
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic();
        http
                // Since we want the protected resources to be accessible in the UI as well we need
                // session creation to be allowed (it's disabled by default in 2.0.6)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .requestMatchers().anyRequest()
                .and()
                .anonymous()
                .and()
                .authorizeRequests()
//                    .antMatchers("/product/**").access("#oauth2.hasScope('select') and hasRole('ROLE_USER')")
                .antMatchers("/actuator/**").permitAll()//config手动刷新用
                .antMatchers("/user/**").permitAll()
                .antMatchers("/index/**").permitAll()
                .antMatchers("/druid/*").permitAll()
                .antMatchers("/api/v1/login").permitAll()
                .antMatchers("/swagger*//**").permitAll()
                .antMatchers("/v2/api-docs",//swagger api json
                        "/swagger-resources/configuration/ui",//用来获取支持的动作
                        "/swagger-resources",//用来获取api-docs的URL
                        "/swagger-resources/configuration/security",//安全选择
                        "/swagger-ui.html"
                ).permitAll()
                .anyRequest().authenticated();//配置order访问控制，必须认证过后才可以访问
    }
}

