package com.rhinoceros.mall.web.controller;

import com.rhinoceros.mall.core.constant.ConstantValue;
import com.rhinoceros.mall.core.po.Address;
import com.rhinoceros.mall.core.po.Comment;
import com.rhinoceros.mall.core.po.User;
import com.rhinoceros.mall.core.query.PageQuery;
import com.rhinoceros.mall.service.service.AddressService;
import com.rhinoceros.mall.service.service.CommentService;
import com.rhinoceros.mall.service.service.UserService;
import com.rhinoceros.mall.web.support.web.annotation.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.util.List;


/**
 * 用户个人信息控制器
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    AddressService addressService;
    @Autowired
    CommentService commentService;


    @Authentication
    @RequestMapping("/info")
    public String showUserInfo() {
        return "user";
    }

    @Authentication
    @RequestMapping("/detail")
    public String showUserDetail() {
        return "include/user/myInfoPage";
    }

    @Authentication
    @RequestMapping("/modify")
    public String showModifyInfo() {
        return "include/user/modifyInfoPage";
    }

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    @Authentication
    @ResponseBody
    @RequestMapping("/update.json")
    public Integer updateUserInfo(User user, HttpSession session) {
        User newUser = userService.updateSelectionById(user);
        session.setAttribute(ConstantValue.CURRENT_USER, newUser);
        return 1;

    }

    @Authentication
    @RequestMapping("/address")
    public String showMyAddress(HttpSession session, Model model) {
        User user = (User) session.getAttribute(ConstantValue.CURRENT_USER);
        List<Address> addressList = (List<Address>) addressService.findByUserId(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("addressList", addressList);
        return "include/user/myAddressPage";
    }

    /**
     * 更新地址
     *
     * @param address
     * @param session
     * @return
     */
    @Authentication
    @ResponseBody
    @RequestMapping("/updateAddress.json")
    public Integer updateAddress(Address address, HttpSession session) {
        addressService.updateSelectionById(address);
        return 1;
    }

    /**
     * 删除地址
     *
     * @param id
     * @return
     */
    @Authentication
    @ResponseBody
    @RequestMapping("/deleteAddress")
    public Integer deleteAddress(Long id) {
        addressService.deleteById(id);
        return 1;
    }

    /**
     * 添加地址
     *
     * @param address
     * @return
     */
    @Authentication
    @ResponseBody
    @RequestMapping("/addAddress.json")
    public Address addAddress(Address address) {
        addressService.add(address);
        return address;
    }

    @Authentication
    @RequestMapping("/evaluation")
    public String showMyEvaluation(HttpSession session, Model model, @RequestParam(value = "page", required = false) Integer page) {
        User user = (User) session.getAttribute(ConstantValue.CURRENT_USER);
        if (page == null) {
            page = 1;
        }
        List<Comment> comments = commentService.findByUserId(user.getId(), new PageQuery(page, 5));
        Integer commentNum = commentService.commentNumByUserId(user.getId());
        model.addAttribute("comments", comments);
        model.addAttribute("user", user);
        model.addAttribute("nowPage", page);
        model.addAttribute("commentNum", commentNum);
        return "include/user/myEvaluationPage";
    }

    @Authentication
    @RequestMapping("/uploadAvatar")
    public String uploadAvatar(HttpSession session, @RequestPart("file") MultipartFile multipartFile)
            throws Exception {
        User currentUser = (User) session.getAttribute(ConstantValue.CURRENT_USER);
        Long userId = currentUser.getId();
        User newUser = new User();
        newUser.setId(userId);
        if (multipartFile.getSize()>0){
            //获取文件名和输入流
            String fileName = multipartFile.getOriginalFilename();
            InputStream is = multipartFile.getInputStream();
            newUser = userService.upload(is,newUser,fileName);
            session.setAttribute(ConstantValue.CURRENT_USER, newUser);
        }
        return "include/user/modifyInfoPage";
    }
}
