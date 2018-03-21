package com.rhinoceros.mall.admin.controller;

import com.rhinoceros.mall.core.enumeration.OrderStatus;
import com.rhinoceros.mall.core.po.Address;
import com.rhinoceros.mall.core.po.Order;
import com.rhinoceros.mall.core.po.Product;
import com.rhinoceros.mall.core.po.User;
import com.rhinoceros.mall.core.query.PageQuery;
import com.rhinoceros.mall.core.vo.DeliveryInfoVo;
import com.rhinoceros.mall.core.vo.DeliveryInfosVo;
import com.rhinoceros.mall.service.service.AddressService;
import com.rhinoceros.mall.service.service.OrderService;
import com.rhinoceros.mall.service.service.ProductService;
import com.rhinoceros.mall.service.service.UserService;
import com.rhinoceros.mall.web.support.web.annotation.PageDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedList;
import java.util.List;
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;

    /**
     * 查找所有符合待发货状态下的订单
     * @return
     */
    @RequestMapping("/send")
    @ResponseBody
    public DeliveryInfosVo findByStatus(@PageDefault(required = false)PageQuery pageQuery) {
        List<Order> orders = new LinkedList<>();
        List<DeliveryInfoVo> deliveryInfoVos = new LinkedList<>();
        orders = orderService.findByStatus(OrderStatus.WAIT_PAY,pageQuery);
        DeliveryInfosVo deliveryInfosVo = new DeliveryInfosVo();
        deliveryInfosVo.setCount(orderService.countByStatus(OrderStatus.WAIT_PAY));
        for (Order order : orders) {
            Order order1 = new Order();
            DeliveryInfoVo deliveryInfoVo = new DeliveryInfoVo();
            deliveryInfoVo.setOrder(order);
            User user = userService.findById(order.getUserId());
            deliveryInfoVo.setUser(user);
            Address addresses = addressService.findById(order.getAddressId());
            deliveryInfoVo.setAddress(addresses);
            Product product = productService.findById(order.getProductId());
            deliveryInfoVo.setProduct(product);
            deliveryInfoVos.add(deliveryInfoVo);
        }
        deliveryInfosVo.setDeliveryInfoVos(deliveryInfoVos);
        return deliveryInfosVo;
    }

    /**
     * 根据订单号删除订单
     * @param identifiers
     */
    @ResponseBody
    @RequestMapping("/delete")
    public String delete(@RequestParam("identifiers[]")List<String> identifiers) {
        for(String identifier:identifiers){
            orderService.deleteByIdentifier(identifier);
        }
        return "{\"result\":\"success\"}";
    }
    @RequestMapping()
    public String showProduct() {
        return "include/sendGoods";
    }

}
