package com.rhinoceros.mall.web.controller;
/* created at 4:27 PM 3/6/2018  */

import com.rhinoceros.mall.core.pojo.User;
import com.rhinoceros.mall.core.vo.OrderListVo;
import com.rhinoceros.mall.service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/order/list")
    public String orderList(Model model, HttpSession session) {
        User user = (User) session.getAttribute(LoginController.USERNAME);
        if (user == null) {
            return "redirect:/login";
        }

        List<OrderListVo> orderListVos= orderService.findOrderListVoByUserId(user.getId());
        model.addAttribute("orderListVos",orderListVos);

        return "bought";
    }
}
