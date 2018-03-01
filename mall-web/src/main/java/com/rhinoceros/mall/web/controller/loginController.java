package com.rhinoceros.mall.web.controller;
import com.rhinoceros.mall.core.dto.LoginUserDto;
import com.rhinoceros.mall.core.pojo.User;
import com.rhinoceros.mall.service.impl.exception.UserException;
import com.rhinoceros.mall.service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class loginController {
    @Autowired
    private UserService userService;
    //进入登录页面
    @RequestMapping("/login")
    public String login(){
        return"login";
    }
    @RequestMapping("/loginSubmit")
    public String login(@Validated @ModelAttribute("user") LoginUserDto userDto, BindingResult br, HttpSession httpSession, Model model) {
        /**
         * 检查用户输入是否规范，不规范则返回到登录页面
         */
        if (br.hasErrors()) {
            model.addAttribute("error", br.getFieldError().getDefaultMessage());
            return "login";
        }
        /**
         * 检查输入的用户是否存在，存在则跳转到到主页面，不存在则返回到登录页面
         */
        try {
            User user = userService.login(userDto);
            return "redirect:/index";
        } catch (UserException e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }
}
