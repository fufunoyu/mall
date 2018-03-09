package com.rhinoceros.mall.web.controller;
/* created at 4:27 PM 3/6/2018  */

import com.rhinoceros.mall.core.constant.web.ConstantValue;
import com.rhinoceros.mall.core.enumeration.OrderStatus;
import com.rhinoceros.mall.core.pojo.Order;
import com.rhinoceros.mall.core.pojo.User;
import com.rhinoceros.mall.core.vo.OrderListVo;
import com.rhinoceros.mall.service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 订单显示页面
     *
     * @param model
     * @param session
     * @param orderStatus
     * @param page
     * @return
     */
    @RequestMapping("/list")
    public String orderList(Model model, HttpSession session,
                            @RequestParam(value = "status", required = false) OrderStatus orderStatus,
                            @RequestParam(value = "page", required = false) Integer page) {
        User user = (User) session.getAttribute(ConstantValue.CURRENT_USER);
        if (user == null) {
            return "redirect:/login";
        }
        if (page == null) {
            page = 1;
        }
        List<OrderListVo> orderListVos;
        Integer orderNum;
        Integer pageSize = 2;

        orderNum = orderService.findOrderNumByUserIdAndStatus(user.getId(), orderStatus);
        orderListVos = orderService.findOrderListVoByUserId(user.getId(), orderStatus, page, pageSize);

        model.addAttribute("orderListVos", orderListVos);
        model.addAttribute("orderNum", orderNum);
        model.addAttribute("nowPage", page);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("orderStatus", orderStatus==null?"ALL":orderStatus.name());
        return "bought";
    }

    /**
     * 改变订单状态
     *
     * @param oid
     * @param status
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping({"/status"})
    public String addToCartProduct(
            @RequestParam("oid") Long oid,
            @RequestParam("status") OrderStatus status,
            HttpSession session
    ) {
        User user = (User) session.getAttribute(ConstantValue.CURRENT_USER);
        if (user == null) {
            return "redirect:/login";
        }
        Order order = new Order();
        order.setId(oid);
        order.setStatus(status);
        orderService.updateSelectionById(order);
        return "success";
    }

    @RequestMapping({"/confirmPayPage"})
    public String confirmReceive(HttpSession session) {
        User user = (User) session.getAttribute(ConstantValue.CURRENT_USER);
        if (user == null) {
            return "redirect:/login";
        }
        Order order = new Order();

        return "orderConfirmed";
    }

}
