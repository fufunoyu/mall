package com.rhinoceros.mall.web.controller;

import com.rhinoceros.mall.core.dto.RegisterUserDto;
import com.rhinoceros.mall.service.impl.exception.UserExistException;
import com.rhinoceros.mall.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *注册控制层
 */
@Controller
public class RegisterController {

    //创建用户业务逻辑对象
    @Autowired
    private UserService userService;

    /**
     * 当点击注册时，跳转至注册界面
     * @return
     */
    @RequestMapping("/register")
    public String register() {

        return "register";
    }

    /**
     * 当点击提交时判断是否允许注册
     * @param userDto
     * @return
     */
    @RequestMapping("/foreregister")
    public String registerSubmit(RegisterUserDto userDto, Model model) {
        //判断用户名是否不为空，且规定用户名长度不小于6位
        if(userDto.getUsername() == null || userDto.getUsername().length() < 6){
            model.addAttribute("msg","用户名为空或长度小于6位");
               return "register";
        }

        //密码不为空，长度不小于6位并且密码与再次确认密码必须相同
        if (userDto.getPassword() == null || userDto.getPassword().length() < 6 || !userDto.getPassword().equals(userDto.getRePassword())) {
            model.addAttribute("msg","密码为空，长度小于6位或两次输入密码不同");
            return "register";
        }
        //若正确操作则录入该用户信息，并跳转至注册成功界面，否则抛出异常跳转至当前界面
        try {
            userService.register(userDto);
            return "registerSuccess";
        } catch (UserExistException e) {
            return "register";
        }
    }
}


