package com.rhinoceros.mall.service.service;

import com.rhinoceros.mall.core.dto.LoginUserDto;
import com.rhinoceros.mall.core.pojo.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

   User login(LoginUserDto userDto);
}
