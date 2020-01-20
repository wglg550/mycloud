package com.cloud.basic.master.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @Description: s_user entity
 * @Param:
 * @return:
 * @Author: wangliang
 * @Date: 2019/10/11
 */
@Entity
@Table(name = "s_user", schema = "spring_cloud_demo", catalog = "")
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
public class SUserEntity
//        implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ApiModelProperty(value = "国家")
    private String national;
    @NotNull
    @ApiModelProperty(value = "姓名")
    private String name;
    @NotNull
    @ApiModelProperty(value = "密码")
    private String password;
    @NotNull
    @ApiModelProperty(value = "年龄", example = "18")
    private Integer age;
    @NotNull
//    @Enumerated(value = EnumType.ORDINAL)//可以通过注解枚举的形式，也可以不通过注解
    @ApiModelProperty(value = "性别")
    private Boolean sex;//0：男，1：女
    @ApiModelProperty(value = "地址")
    private String address;
    @ApiModelProperty(value = "QQ号", example = "100000")
    private Integer qq;
    @ApiModelProperty(value = "微信号")
    private String wechat;
    @NotNull
    @ApiModelProperty(value = "手机号码")
    private String phone;
    @NotNull
    @ApiModelProperty(value = "邮箱")
    private String email;

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return name;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
}
