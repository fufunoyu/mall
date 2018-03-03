package com.rhinoceros.mall.web.controller;

import com.rhinoceros.mall.core.dto.LoginUserDto;
import com.rhinoceros.mall.core.dto.ResetPasswordDto;
import com.rhinoceros.mall.core.dto.RetrievePasswordDto;
import com.rhinoceros.mall.core.pojo.User;
import com.rhinoceros.mall.core.utils.SecurityUtils;
import com.rhinoceros.mall.service.impl.exception.EmailHasFoundException;
import com.rhinoceros.mall.service.impl.exception.UserException;
import com.rhinoceros.mall.service.impl.exception.UserHasFoundException;
import com.rhinoceros.mall.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {


    public static final String SECRET = "secret";

    public static final String USERNAME = "user";
    @Autowired
    private UserService userService;

    /**
     * 登录页面展示
     *
     * @return
     */
    @RequestMapping("/login")
    public String login(HttpSession session) {
        // 用户已登录，直接返回首页
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
    public String login(@Validated @ModelAttribute("loginUser") LoginUserDto userDto, BindingResult br, HttpSession session, Model model) {
        // 用户已登录，直接返回首页
        if (session.getAttribute(USERNAME) != null) {
            return "redirect:/index";
        }
        // 检查用户输入是否规范，不规范则返回到登录页面
        if (br.hasErrors()) {
            model.addAttribute("error", br.getFieldError().getDefaultMessage());
            return "login";
        }

        //检查输入的用户是否存在，存在则跳转到到主页面，不存在则返回到登录页面
        try {
            User user = userService.login(userDto);
            //将用户信息放入session
            session.setAttribute(USERNAME, user);
            return "redirect:/index";
        } catch (UserException e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }

    /**
     * 点击忘记密码时，跳转至密码找回界面
     *
     * @return
     */
    @RequestMapping("/retrievePassword")
    public String retrievePassword() {

        return "retrievePassword";
    }

    /**
     * 点击确定时验证邮箱和验证码校对
     *
     * @param retrievePasswordDto
     * @param br
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/verifyMail")
    public String verifyMail(@Validated @ModelAttribute("retrievePassword") RetrievePasswordDto retrievePasswordDto, BindingResult br, HttpServletRequest request, Model model) {

        //如果点击提交时有不规范操作会出现提示并返回当前界面
        if (br.hasErrors()) {
            model.addAttribute("msg", br.getFieldError().getDefaultMessage());
            return "retrievePassword";
        }

        String validateCode = (String) request.getSession().getAttribute("validateCode");

        //验证码不正确
        if (validateCode != null && !(validateCode.equals((retrievePasswordDto.getCode())))) {
            model.addAttribute("msg", "验证码错误");
            return "retrievePassword";
        }
        //查询数据库，确定邮箱对应的用户是否存在，
        try {
            userService.retrievePassword(retrievePasswordDto);
            String encodeStr = SecurityUtils.encode(retrievePasswordDto.getEmail());
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
            //TODO 发送邮件
            System.out.println(basePath + "/resetPassword?" + SECRET + "=" + encodeStr);
            return "sendEmailSuccess";
        } catch (UserHasFoundException | EmailHasFoundException e) {
            model.addAttribute("msg", e.getMessage());
            return "retrievePassword";
        }
//        if(validateCode != null && validateCode.equals(resetPasswordDto.getCode())){
//
//            //如果存在，发送激活链接到对应的邮箱
//
//            //用户在一定时间内点击对应的激活链接，
//
//            //如果链接有效，校验链接中的加密字符串，获取用户信息（不能基于session），跳转到重置密码界面
//            //链接中加密用户id，可以使用jwt，这样检验的controller就可以获取到用户id
//
//            //如果链接失效，提示链接失效
//            return "resetPassword";
//        }else {
//            //如果用户不存在，提示用户未注册
//            return "validateCode incorrect";
//        }

    }

    @RequestMapping("/resetPassword")
    public String resetPassword(Model model, @RequestParam(SECRET) String secret) {
        model.addAttribute("secret", secret);
        return "resetPassword";
    }

    /**
     * 点击确定重置密码
     *
     * @return
     */
    @RequestMapping("/resetPasswordSubmit")
    public String resetPasswordSubmit(@Validated @ModelAttribute("resetPassword") ResetPasswordDto resetPasswordDto, BindingResult br, HttpSession session, Model model) {
        String email = SecurityUtils.decode(resetPasswordDto.getSecret());

        if (br.hasErrors()) {
            model.addAttribute("msg", br.getFieldError().getDefaultMessage());
            return "resetPassword";
        }
        String validateCode = (String) session.getAttribute("validateCode");
        if (!(resetPasswordDto.getPassword().equals(resetPasswordDto.getRePassword()))) {
            model.addAttribute("msg", "两次密码不一致");
            return "resetPassword";
        }
        if (validateCode != null && !(validateCode.equals((resetPasswordDto.getCode())))) {
            model.addAttribute("msg", "验证码错误");
            return "resetPassword";
        }
        return "resetPasswordSuccess";
    }
}
