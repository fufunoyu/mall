package com.rhinoceros.mall.web.controller;
/* created at 4:27 PM 3/6/2018  */

import com.rhinoceros.mall.core.pojo.User;
import com.rhinoceros.mall.core.vo.OrderListVo;
import com.rhinoceros.mall.service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    /*@RequestMapping("/order/list")
    public String orderList(Model model, HttpSession session) {
        User user = (User) session.getAttribute(LoginController.USERNAME);
        if (user == null) {
            return "redirect:/login";
        }

        List<OrderListVo> orderListVos= orderService.findOrderListVoByUserId(user.getId());
        model.addAttribute("orderListVos",orderListVos);

        return "bought";
    }*/

    @RequestMapping("/order/list")
    public String orderList(Model model, HttpSession session,
                            @RequestParam(value = "status",required = false) String status,
                            @RequestParam(value = "page",required = false) Integer page) {
        User user = (User) session.getAttribute(LoginController.USERNAME);
        if (user == null) {
            return "redirect:/login";
        }
        if (page == null) {
            page = 1;
        }
        List<OrderListVo> orderListVos;
        if(status == null){
            orderListVos= orderService.findOrderListVoByUserId(user.getId());
        }
        else{
            orderListVos= orderService.findOrderListVoByUserId(user.getId(),status);
        }
        model.addAttribute("orderListVos",orderListVos);

        return "bought";
    }
}
