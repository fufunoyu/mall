package com.rhinoceros.mall.admin.controller;
/* created at 10:34 AM 3/20/2018  */

import com.rhinoceros.mall.core.enumeration.OrderStatus;
import com.rhinoceros.mall.core.po.Address;
import com.rhinoceros.mall.core.po.Order;
import com.rhinoceros.mall.core.po.Product;
import com.rhinoceros.mall.core.po.User;
import com.rhinoceros.mall.core.query.PageQuery;
import com.rhinoceros.mall.core.vo.AdminOrderListWithCountVo;
import com.rhinoceros.mall.core.vo.AdminOrderVo;
import com.rhinoceros.mall.core.vo.OrderVo;
import com.rhinoceros.mall.core.vo.ProductVo;
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
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @RequestMapping("/waitReturnPage")
    public String showWaitReturn() {
        return "include/waitReturn";
    }

    /**
     * 获取所有等待退货的订单信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/waitReturnList.json")
    public AdminOrderListWithCountVo getSlideshowList(@PageDefault(required = false) PageQuery pageQuery) {
        List<Order> orders = orderService.findByStatus(OrderStatus.WAIT_RETURN, pageQuery);
        List<AdminOrderVo> adminOrderVoList = getAdminOrderList(orders, true, true, false);
        Long count = orderService.countOrderByStatus(OrderStatus.WAIT_RETURN);
        AdminOrderListWithCountVo adminOrderListWithCountVo = new AdminOrderListWithCountVo();
        adminOrderListWithCountVo.setAdminOrderVoList(adminOrderVoList);
        adminOrderListWithCountVo.setCount(count);
        return adminOrderListWithCountVo;
    }


    /**
     * 根据订单列表生成订单展示列表
     * @param orderList
     * @param bProduct 是否传入商品信息
     * @param bUser 是否传入用户信息
     * @param bAddress 是否传入地址信息
     * @return
     */
    private List<AdminOrderVo> getAdminOrderList(List<Order> orderList, Boolean bProduct, Boolean bUser, Boolean bAddress) {
        List<AdminOrderVo> adminOrderVoList = new LinkedList<>();
        for (Order order : orderList) {
            AdminOrderVo adminOrderVo = new AdminOrderVo();
            adminOrderVo.setOrder(order);
            if(bProduct){
                Product product = productService.findById(order.getProductId());
                adminOrderVo.setProductVo(new ProductVo(product));
            }
            if (bUser) {
                User user = userService.findById(order.getUserId());
                adminOrderVo.setUser(user);
            }
            if (bAddress) {
                Address address = addressService.findById(order.getAddressId());
                adminOrderVo.setAddress(address);
            }
            adminOrderVoList.add(adminOrderVo);
        }
        return adminOrderVoList;
    }

    /**
     * 更改订单状态
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping("/goToReturn.json")
    public String goToReturn(@RequestParam("ids[]") List<String> ids){
        for (String id : ids){
            orderService.goToReturn(id);
        }
        return "{\"result\":\"success\"}";
    }

}
