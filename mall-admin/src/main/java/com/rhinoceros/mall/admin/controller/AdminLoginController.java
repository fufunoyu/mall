package com.rhinoceros.mall.admin.controller;

import com.rhinoceros.mall.core.dto.LoginUserDto;
import com.rhinoceros.mall.core.pojo.Admin;
import com.rhinoceros.mall.service.impl.exception.UserException;
import com.rhinoceros.mall.service.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AdminLoginController {

    private static final String USERNAME = "admin";
    @Autowired
    private AdminService adminService;

    /**
     * 登录页面展示
     *
     * @return
     */
    @RequestMapping("/login")
    public String login(HttpSession session) {
        // 管理员已登录，直接返回管理员首页
        if (session.getAttribute(USERNAME) != null) {
            return "redirect:/index";
        }
        return "login";
    }

    /**
     * 登录表单提交
     *
     * @param userDto
     * @param br
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/loginSubmit")
    public String loginSubmit(@Validated @ModelAttribute("loginUser") LoginUserDto userDto, BindingResult br, HttpSession session, Model model) {
        // 管理员已登录，直接返回管理员首页
        if (session.getAttribute(USERNAME) != null) {
            return "redirect:/index";
        }
        // 检查用户输入是否规范，不规范则返回到管理员登录页面
        if (br.hasErrors()) {
            model.addAttribute("error", br.getFieldError().getDefaultMessage());
            return "login";
        }

        //检查输入的管理员是否存在，存在则跳转到到管理员主页面，不存在则返回到管理员登录页面
        try {
            Admin admin = adminService.login(userDto);
            //将管理员信息放入session
            session.setAttribute(USERNAME, admin);
            return "redirect:/index";
        } catch (UserException e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }

    /**
     * 退出登录
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute(USERNAME);
        return "redirect:/login";
    }

}
