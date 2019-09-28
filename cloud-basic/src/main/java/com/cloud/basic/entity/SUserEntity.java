package com.cloud.basic.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "s_user", schema = "spring_cloud_demo", catalog = "")
@Getter
@Setter
public class SUserEntity {
    @Id
    @GeneratedValue
    private Long id;
    @ApiModelProperty(value = "国家")
    private String national;
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "密码")
    private String passwrod;
    @ApiModelProperty(value = "年龄", example = "18")
    private Long age;
    @ApiModelProperty(value = "性别")
    private Boolean sex;
    @ApiModelProperty(value = "地址")
    private String address;
    @ApiModelProperty(value = "QQ号", example = "100000")
    private Long qq;
    @ApiModelProperty(value = "微信号")
    private String wechat;
    @ApiModelProperty(value = "手机号码")
    private String phone;
    @ApiModelProperty(value = "邮箱")
    private String email;
}
