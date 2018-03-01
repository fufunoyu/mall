package com.rhinoceros.mall.core.dto;
import lombok.Getter;
import lombok.Setter;
/**
 * 登录进行交互的实体
 */
@Getter
@Setter
public class UserDto {

    private String username;
    private String password;
}
