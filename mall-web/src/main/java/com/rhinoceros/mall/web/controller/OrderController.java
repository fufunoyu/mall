package com.rhinoceros.mall.web.controller;
/* created at 4:27 PM 3/6/2018  */

import com.rhinoceros.mall.core.constant.web.ConstantValue;
import com.rhinoceros.mall.core.dto.OrderDto;
import com.rhinoceros.mall.core.enumeration.OrderStatus;
import com.rhinoceros.mall.core.po.*;
import com.rhinoceros.mall.core.query.PageQuery;
import com.rhinoceros.mall.core.dto.OrderDto;
import com.rhinoceros.mall.core.vo.OrderListVo;
import com.rhinoceros.mall.core.vo.OrderProductVo;
import com.rhinoceros.mall.core.vo.ProductVo;
import com.rhinoceros.mall.service.service.CartProductService;
import com.rhinoceros.mall.service.service.AddressService;
import com.rhinoceros.mall.service.service.OrderService;
import com.rhinoceros.mall.service.service.ProductService;
import com.rhinoceros.mall.service.service.ProductService;
import com.rhinoceros.mall.web.support.web.annotation.Authentication;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
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
    @Autowired
    private AddressService addressService;

    @Autowired
    private CartProductService cartProductService;


    /**
     * 订单显示页面
     * @param orderDto
     * @param model
     * @return
     */
    @Authentication
    @RequestMapping("/add")
    public String showOrderConfirm(OrderDto orderDto, Model model) {
        //获取商品的id
        Long pid = orderDto.getProductId();
        //根据商品id获取商品信息
        OrderProductVo orderProductVo = new OrderProductVo();
        Product product = productService.findById(pid);
        if (product != null) {
            ProductVo productVo = new ProductVo(product);
            orderProductVo.setProductVo(productVo);
            orderProductVo.setNum(orderDto.getProductNum());
            Product product1 = productService.findById(orderDto.getProductId());
            model.addAttribute("orderProducts", Collections.singleton(orderProductVo));
            model.addAttribute("total", calculate(product1.getPrice(), product1.getDiscount(), orderDto.getProductNum()));
            return "buy";
        }
        return "product";
    }

    /**
     * 购物车结算到订单确认页面
     *
     * @param ids
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("cartAdd")
    public String showCartOrderConfirm(@RequestParam("id") List<Long> ids, HttpSession session, Model model) {
        User user = (User) session.getAttribute(ConstantValue.CURRENT_USER);
        List<CartProduct> cartProducts = cartProductService.findCartProducts(ids, user.getId());
        //根据商品id获取商品信息
        List<OrderProductVo> orderProductVos = new LinkedList<>();
        BigDecimal total = BigDecimal.ZERO;
        for (CartProduct cartProduct : cartProducts) {
            OrderProductVo vo = new OrderProductVo();
            Product product = productService.findById(cartProduct.getProductId());
            ProductVo productVo = new ProductVo(product);
            total = total.add(calculate(product.getPrice(),product.getDiscount(),cartProduct.getProductNum()));
            vo.setProductVo(productVo);
            vo.setNum(cartProduct.getProductNum());
            orderProductVos.add(vo);
        }

        model.addAttribute("orderProducts", orderProductVos);
        model.addAttribute("total",total);
        return "buy";

    }

    /**
     * 订单显示页面
     *
     * @param model
     * @param session
     * @param orderStatus
     * @param page
     * @return
     */
    @Authentication
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
        Integer orderNum;
        Integer pageSize = 2;

        orderNum = orderService.countByUserIdAndStatus(user.getId(), orderStatus);
        //选择排序方式
        com.rhinoceros.mall.core.query.Order queryOrder = new com.rhinoceros.mall.core.query.Order("createAt", com.rhinoceros.mall.core.query.Order.Direction.DESC);
        //选择分页方式
        PageQuery pageQuery = new PageQuery(page, pageSize, queryOrder);

        List<Order> orders = orderService.findByUserIdAndStatus(user.getId(), orderStatus, pageQuery);
        List<OrderListVo> orderListVos = new LinkedList<OrderListVo>();
        for (Order order : orders) {
            OrderListVo orderListVo = new OrderListVo();
            orderListVo.setOrder(order);
            List<OrderProduct> orderProducts = orderService.findProductIdByOrderId(order.getId());
            List<OrderProductVo> orderProductVos = new LinkedList<OrderProductVo>();

            setOrderProductVos(orderProducts, orderProductVos);
            orderListVo.setOrderProductVos(orderProductVos);
            orderListVos.add(orderListVo);
        }

        model.addAttribute("orderListVos", orderListVos);
        model.addAttribute("orderNum", orderNum);
        model.addAttribute("nowPage", page);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("orderStatus", orderStatus == null ? "ALL" : orderStatus.name());
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
    @Authentication
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

    /**
     * 跳转到确认收货页面
     *
     * @param session
     * @param oid
     * @param model
     * @return
     */
    @Authentication
    @RequestMapping({"/confirmPayPage"})
    public String confirmReceiveButton(HttpSession session,
                                 @RequestParam("oid") Long oid,
                                 Model model) {
        User user = (User) session.getAttribute(ConstantValue.CURRENT_USER);
        if (user == null) {
            return "redirect:/login";
        }
        Order order = orderService.findById(oid);
        model.addAttribute("order", order);

        OrderListVo orderListVo = new OrderListVo();
        orderListVo.setOrder(order);
        List<OrderProduct> orderProducts = orderService.findProductIdByOrderId(order.getId());
        List<OrderProductVo> orderProductVos = new LinkedList<OrderProductVo>();

        setOrderProductVos(orderProducts, orderProductVos);
        orderListVo.setOrderProductVos(orderProductVos);
        model.addAttribute("orderListVo", orderListVo);
        Address orderAddress = addressService.findById(orderListVo.getOrder().getAddressId());
        model.addAttribute("orderAddress", orderAddress);
        return "confirmPay";
    }

    @RequestMapping({"/confiredPage"})
    public String confirmReceive(HttpSession session,
                                 Model model,
                                 @RequestParam("oid") Long oid,
                                 @RequestParam("status")OrderStatus status) {
        User user = (User) session.getAttribute(ConstantValue.CURRENT_USER);
        if (user == null) {
            return "redirect:/login";
        }
        Order order = new Order();
        order.setId(oid);
        order.setStatus(status);
        orderService.updateSelectionById(order);
        return "orderConfirmed";
    }

    @RequestMapping({"/comment"})
    public String OrderComment(HttpSession session,
                               Model model,
                               @RequestParam("oid") Long oid) {
        User user = (User) session.getAttribute(ConstantValue.CURRENT_USER);
        if (user == null) {
            return "redirect:/login";
        }

        return "review";
    }

    private void setOrderProductVos(List<OrderProduct> orderProducts, List<OrderProductVo> orderProductVos) {
        for (OrderProduct orderProduct : orderProducts) {
            Product product = productService.findById(orderProduct.getProductId());
            ProductVo productVo = new ProductVo(product);
            productVo.setProduct(product);
            //创建OrderProductVo对象以便填充
            OrderProductVo orderProductVo = new OrderProductVo();
            orderProductVo.setNum(orderProduct.getProductNum());
            orderProductVo.setProductVo(productVo);
            orderProductVos.add(orderProductVo);
        }
    }

    /**
     * 计算商品总额
     *
     * @param price
     * @param discount
     * @param num
     * @return
     */
    private BigDecimal calculate(BigDecimal price, BigDecimal discount, Integer num) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        if (discount == null) {
            totalPrice = price.multiply(BigDecimal.valueOf(num));
        } else {
            totalPrice = discount.multiply(BigDecimal.valueOf(num));
        }
        return totalPrice;
    }
}
