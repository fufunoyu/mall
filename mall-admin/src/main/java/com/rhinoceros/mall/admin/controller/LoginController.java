package com.rhinoceros.mall.admin.controller;

import com.rhinoceros.mall.core.constant.ConstantValue;
import com.rhinoceros.mall.core.dto.LoginUserDto;
import com.rhinoceros.mall.core.po.Admin;
import com.rhinoceros.mall.service.impl.exception.user.UserException;
import com.rhinoceros.mall.service.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private AdminService adminService;

    /**
     * 登录页面显示
     *
     * @param session
     * @return
     */
    @RequestMapping("/login")
    public String login(HttpSession session) {
        // 管理员已登录，直接返回管理员首页
        if (session.getAttribute(ConstantValue.COOKIE_USERNAME) != null) {
            return "redirect:/index";
        }
        return "login";
    }

    /**
     * 提交表单数据
     *
     * @param userDto
     * @param br
     * @param session
     * @param model
     * @param response
     * @param request
     * @return
     */
    @RequestMapping("/loginSubmit")
    public String loginSubmit(@Validated @ModelAttribute("loginUser") LoginUserDto userDto, BindingResult br, HttpSession session, Model model, HttpServletResponse response, HttpServletRequest request) {

        // 管理员已登录，直接返回管理员首页
        if (session.getAttribute(ConstantValue.COOKIE_USERNAME) != null) {
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
            //创建Cookie
            Cookie nameCookie = new Cookie(ConstantValue.COOKIE_USERNAME, admin.getUsername());
            Cookie pswCookie = new Cookie(ConstantValue.COOKIE_PASSWORD, admin.getPassword());
            //设置Cookie的父路径
            nameCookie.setPath(request.getContextPath() + "/");
            pswCookie.setPath(request.getContextPath() + "/");

            //获取是否保存Cookie
            if (!userDto.getRememberMe()) {//不保存Cookie
                nameCookie.setMaxAge(0);
                pswCookie.setMaxAge(0);
            } else {//保存Cookie的时间长度，单位为秒
                nameCookie.setMaxAge(7 * 24 * 60 * 60);
                pswCookie.setMaxAge(7 * 24 * 60 * 60);
            }
            //加入Cookie到响应头
            response.addCookie(nameCookie);
            response.addCookie(pswCookie);

            //将管理员信息放入session
            session.setAttribute(ConstantValue.CURRENT_USER, admin);
            return "redirect:/index";
        } catch (UserException e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }

    /**
     * 退出登录
     *
     * @param session
     * @param response
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session, HttpServletResponse response) {
        session.removeAttribute(ConstantValue.CURRENT_USER);
        //删除cookie
        Cookie username = new Cookie(ConstantValue.COOKIE_USERNAME, "");
        username.setMaxAge(-1);
        Cookie password = new Cookie(ConstantValue.COOKIE_PASSWORD, "");
        password.setMaxAge(-1);
        response.addCookie(username);
        response.addCookie(password);
        return "redirect:/login";
    }


}
