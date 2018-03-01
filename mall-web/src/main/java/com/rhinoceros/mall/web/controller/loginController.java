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
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class loginController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(){
        return"login";
    }
    @RequestMapping("/loginSubmit")
    public String login(@Validated LoginUserDto userDto, BindingResult br, HttpSession httpSession, Model model) {
        /**
         * 检查输入的用户是否存在，存在保存用户登录信息，不存在抛出异常信息
         */
        if (br.hasErrors()) {
            model.addAttribute("user", userDto);
            model.addAttribute("errors", br.getAllErrors());
            return "login";
        }

        try {
            User user = userService.login(userDto);
            httpSession.setAttribute("user", user);
            return "redirect:/index";
        } catch (UserException e) {
            model.addAttribute("user", userDto);
            model.addAttribute("msg", e.getMessage());
            return "login";
        }
    }
}
