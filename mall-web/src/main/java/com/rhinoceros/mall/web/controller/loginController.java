package com.rhinoceros.mall.web.controller;
import com.rhinoceros.mall.core.dto.UserDto;
import com.rhinoceros.mall.core.pojo.User;
import com.rhinoceros.mall.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class loginController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(){
        return"login";
    }
    @RequestMapping("/loginSubmit")
    public String login(UserDto userDto, HttpSession httpSession) throws Exception{
        /**
         * 检查输入的用户是否存在，存在保存用户登录信息，不存在抛出异常信息
         */

        User user = userService.login(userDto);
        if(user!=null){
            httpSession.setAttribute("user",user);
            return "redirect:/index";
        }
        return "400, 用户名或密码不正确";
    }
}
