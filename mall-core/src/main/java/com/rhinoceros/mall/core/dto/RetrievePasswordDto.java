package com.rhinoceros.mall.core.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 找回密码实体
 */
@Getter
@Setter
public class RetrievePasswordDto {
    //用户邮箱
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "请输入正确的邮箱格式")
    private String email;

    //验证码
    @NotBlank(message = "验证码不能为空")
    private String code;

}
