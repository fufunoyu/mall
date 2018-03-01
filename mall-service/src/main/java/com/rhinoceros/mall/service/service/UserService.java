package com.rhinoceros.mall.service.service;

import com.rhinoceros.mall.core.dto.UserDto;
import com.rhinoceros.mall.core.pojo.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

   User login(UserDto userDto);
}
