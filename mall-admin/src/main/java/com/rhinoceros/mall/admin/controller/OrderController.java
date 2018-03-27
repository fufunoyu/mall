package com.rhinoceros.mall.admin.controller;

import com.rhinoceros.mall.core.enumeration.OrderStatus;
import com.rhinoceros.mall.core.po.Address;
import com.rhinoceros.mall.core.po.Order;
import com.rhinoceros.mall.core.po.Product;
import com.rhinoceros.mall.core.po.User;
import com.rhinoceros.mall.core.query.PageQuery;
import com.rhinoceros.mall.core.vo.DeliveryInfoVo;
import com.rhinoceros.mall.core.vo.DeliveryInfosVo;
import com.rhinoceros.mall.core.vo.AdminOrderListWithCountVo;
import com.rhinoceros.mall.core.vo.AdminOrderVo;
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


    /**
     * 显示退货中页面
     * @return
     */
    @RequestMapping("/returnIngPage")
    public String showReturnIngPage(){
        return "include/returnIng";
    }



    /**
     * 获取所有退货中的订单的信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/returnIngList.json")
    public AdminOrderListWithCountVo getReturnIngList(@PageDefault(required = false) PageQuery pageQuery) {
        AdminOrderListWithCountVo adminOrderListWithCountVo = getAdminOrderListWithCountVo(OrderStatus.RETURN_ING, pageQuery, true, true, false);
        return adminOrderListWithCountVo;
    }



    /**
     * 确认订单退货成功
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping("/confirmReturn.json")
    public String confirmReturn(@RequestParam("ids[]")List<String> ids){
        for(String oIdentifier: ids){
            orderService.confirmReturn(oIdentifier);
        }
        return "{\"result\":\"success\"}";
    }


    /**
     * 显示已发货页面
     * @return
     */
    @RequestMapping("/shipIngPage")
    public String showShipIngPage(){
        return "include/shipIng";
    }


    /**
     * 获取所有已发货的订单的信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/shipIngList.json")
    public AdminOrderListWithCountVo getShipIngList(@PageDefault(required = false) PageQuery pageQuery) {
        AdminOrderListWithCountVo adminOrderListWithCountVo = getAdminOrderListWithCountVo(OrderStatus.WAIT_RECEIVE, pageQuery, true, true, false);
        return adminOrderListWithCountVo;
    }


    /**
     * 显示待评价页面
     * @return
     */
    @RequestMapping("/waitCommentPage")
    public String showWaitCommentPage(){
        return "include/waitComment";
    }

    /**
     * 获取所有已发货的订单的信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/waitCommentList.json")
    public AdminOrderListWithCountVo getWaitCommentList(@PageDefault(required = false) PageQuery pageQuery) {
        AdminOrderListWithCountVo adminOrderListWithCountVo = getAdminOrderListWithCountVo(OrderStatus.WAIT_COMMENT, pageQuery, true, true, false);
        return adminOrderListWithCountVo;
    }


    /**
     * 显示待评价页面
     * @return
     */
    @RequestMapping("/completedPage")
    public String showCompletedPage(){
        return "include/completed";
    }

    /**
     * 获取所有已发货的订单的信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/completedList.json")
    public AdminOrderListWithCountVo getComletedList(@PageDefault(required = false) PageQuery pageQuery) {
        AdminOrderListWithCountVo adminOrderListWithCountVo = getAdminOrderListWithCountVo(OrderStatus.COMPLETED, pageQuery, true, true, false);
        return adminOrderListWithCountVo;
    }

    /**
     * 获取订单展示对象
     * @param orderStatus
     * @param pageQuery
     * @param bProduct
     * @param bUser
     * @param bAddress
     * @return
     */
    public AdminOrderListWithCountVo getAdminOrderListWithCountVo(OrderStatus orderStatus, PageQuery pageQuery, Boolean bProduct, Boolean bUser, Boolean bAddress){
        List<Order> orderList = orderService.findByStatus(orderStatus, pageQuery);
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
        Long count = orderService.countOrderByStatus(orderStatus);
        AdminOrderListWithCountVo adminOrderListWithCountVo = new AdminOrderListWithCountVo();
        adminOrderListWithCountVo.setAdminOrderVoList(adminOrderVoList);
        adminOrderListWithCountVo.setCount(count);
        return adminOrderListWithCountVo;
    }





    /**
     * 查找所有符合待发货状态下的订单
     * @return
     */
    @RequestMapping("/send")
    @ResponseBody
    public DeliveryInfosVo findByStatus(@PageDefault(required = false)PageQuery pageQuery) {
        List<Order> orders = new LinkedList<>();
        List<DeliveryInfoVo> deliveryInfoVos = new LinkedList<>();
        orders = orderService.findByStatus(OrderStatus.WAIT_SHIP,pageQuery);
        DeliveryInfosVo deliveryInfosVo = new DeliveryInfosVo();
        deliveryInfosVo.setCount(orderService.countByStatus(OrderStatus.WAIT_SHIP));
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
     * 根据id批量修改订单状态
     * @param ids
     */
    @ResponseBody
    @RequestMapping("/update")
    public String delete(@RequestParam("ids[]")List<Long> ids) {
            orderService.updateStatus2ShipByIds(ids);
        return "{\"result\":\"success\"}";
    }

    /**
     * 显示待发货页面
     * @return
     */
    @RequestMapping("/waitShip")
    public String showProduct() {
        return "include/sendGoods";
    }

}
