package com.rhinoceros.mall.admin.controller;

import com.rhinoceros.mall.core.po.Address;
import com.rhinoceros.mall.core.po.Order;
import com.rhinoceros.mall.core.po.User;
import com.rhinoceros.mall.core.vo.DeliveryInfoVo;
import com.rhinoceros.mall.service.service.AddressService;
import com.rhinoceros.mall.service.service.OrderService;
import com.rhinoceros.mall.service.service.UserService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.WebParam;
import java.util.LinkedList;
import java.util.List;

import static com.rhinoceros.mall.core.enumeration.OrderStatus.*;

@Controller
public class GoodsManageController {
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @RequestMapping("/sendGoods")
    public String findByStatus(Model model){
        List<Order>orders = new LinkedList<>();
        List<DeliveryInfoVo> deliveryInfoVos = new LinkedList<>();
        orders = orderService.findByStatus(WAIT_SHIP);
        for (Order order:orders){
            Order order1 = new Order();
            DeliveryInfoVo deliveryInfoVo = new DeliveryInfoVo();
            deliveryInfoVo.setOrder(order);
            User user = userService.findById(order.getUserId());
            deliveryInfoVo.setUser(user);
            Address addresses = addressService.findById( order.getAddressId());
            deliveryInfoVo.setAddress(addresses);
            deliveryInfoVos.add(deliveryInfoVo);
        }
        model.addAttribute("deliveryInfo",deliveryInfoVos);
        return "success";
    }

}
