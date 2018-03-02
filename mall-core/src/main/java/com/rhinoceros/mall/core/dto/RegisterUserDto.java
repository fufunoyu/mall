package com.rhinoceros.mall.core.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 注册用户实体类
 */
@Getter
@Setter
public class RegisterUserDto {
    //用户名
    @NotBlank(message = "用户名不能为空")
    @Size(min=6,max=20,message = "用户名长度不少于6位且不大于20位")
    private String username;
    //登陆密码
    @NotBlank(message = "密码不能为空")
    @Size(min=6 ,max= 20 ,message = "密码长度不少于6位且不大于20位")
    private String password;
    //确认登陆密码

    private String rePassword;

}
