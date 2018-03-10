package com.rhinoceros.mall.web.controller;
/* created at 4:27 PM 3/6/2018  */

import com.rhinoceros.mall.core.constant.web.ConstantValue;
import com.rhinoceros.mall.core.dto.OrderDto;
import com.rhinoceros.mall.core.pojo.OrderProduct;
import com.rhinoceros.mall.core.pojo.Product;
import com.rhinoceros.mall.core.pojo.User;
import com.rhinoceros.mall.core.vo.OrderListVo;
import com.rhinoceros.mall.core.vo.OrderProductVo;
import com.rhinoceros.mall.core.vo.ProductVo;
import com.rhinoceros.mall.service.service.OrderService;
import com.rhinoceros.mall.service.service.ProductService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @RequestMapping("/add")
    public String showOrderConfirm(OrderDto orderDto , HttpServletRequest request, Model model){
        //获取商品的id
        Long pid = orderDto.getProductId();
        //根据商品id获取商品信息
        OrderProductVo orderProductVo = new OrderProductVo();
        ProductVo product = productService.findProductVoById(pid);
        orderProductVo.setProductVo(product);
        orderProductVo.setNum(orderDto.getProductNum());
        orderProductVo.setTotal(calculate(orderDto));
       // orderProductVo.setTotal();
        model.addAttribute("orderProducts", Collections.singleton(orderProductVo));
        return "buy";
    }

    @RequestMapping("/list")
    public String orderList(Model model, HttpSession session,
                            @RequestParam(value = "status", required = false) String status,
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
        if (status == null || status.equals("ALL") ) {
            orderNum = orderService.findOrderNumByUserIdAndStatus(user.getId());
            orderListVos = orderService.findOrderListVoByUserId(user.getId(), page, pageSize);
            status = "ALL";
        } else {
            orderNum = orderService.findOrderNumByUserIdAndStatus(user.getId(), status);
            orderListVos = orderService.findOrderListVoByUserId(user.getId(), status, page, pageSize);
        }

        model.addAttribute("orderListVos", orderListVos);
        model.addAttribute("orderNum", orderNum);
        model.addAttribute("nowPage", page);
        model.addAttribute("pageSize",pageSize);
        model.addAttribute("orderStatus", status);

        return "bought";
    }

    private BigDecimal calculate(OrderDto orderDto){
        int num = orderDto.getProductNum();
        OrderProductVo orderProductVo = new OrderProductVo();
        ProductVo product = productService.findProductVoById(orderDto.getProductId());
        BigDecimal totalPrice;
        if(product.getProduct().getDiscount()==null){
            totalPrice = product.getProduct().getPrice().multiply(BigDecimal.valueOf(num));
        }else{
            totalPrice = product.getProduct().getPrice().multiply(BigDecimal.valueOf(num)).multiply(product.getProduct().getDiscount());
        }
       return  totalPrice;
    }
}
