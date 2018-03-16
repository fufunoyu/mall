package com.rhinoceros.mall.web.controller;
/* created at 4:27 PM 3/6/2018  */

import com.rhinoceros.mall.core.constant.ConstantValue;
import com.rhinoceros.mall.core.dto.OrderDto;
import com.rhinoceros.mall.core.enumeration.OrderStatus;
import com.rhinoceros.mall.core.po.*;
import com.rhinoceros.mall.core.query.PageQuery;
import com.rhinoceros.mall.core.vo.CommentVo;
import com.rhinoceros.mall.core.vo.OrderProductVo;
import com.rhinoceros.mall.core.vo.OrderVo;
import com.rhinoceros.mall.core.vo.ProductVo;
import com.rhinoceros.mall.service.service.*;
import com.rhinoceros.mall.web.support.web.annotation.Authentication;
import com.rhinoceros.mall.web.support.web.annotation.PageDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
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
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;


    /**
     * 提交订单页面的商品展示
     *
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
            model.addAttribute("orderProducts", Collections.singleton(orderProductVo));
            model.addAttribute("total", calculate(product.getPrice(), product.getDiscount(), orderDto.getProductNum()));
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
    @Authentication
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
            total = total.add(calculate(product.getPrice(), product.getDiscount(), cartProduct.getProductNum()));
            vo.setProductVo(productVo);
            vo.setNum(cartProduct.getProductNum());
            orderProductVos.add(vo);
        }

        model.addAttribute("orderProducts", orderProductVos);
        model.addAttribute("total", total);
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
        List<OrderVo> orderVos = new LinkedList<OrderVo>();
        for (Order order : orders) {
            OrderVo orderVo = new OrderVo();
            orderVo.setOrder(order);
            //设置商品
            ProductVo productVo = new ProductVo(productService.findById(order.getProductId()));
            orderVo.setProductVo(productVo);
            orderVos.add(orderVo);
        }

        model.addAttribute("orderVos", orderVos);
        model.addAttribute("orderNum", orderNum);
        model.addAttribute("nowPage", page);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("orderStatus", orderStatus == null ? "ALL" : orderStatus.name());
        return "bought";
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
        OrderVo orderVo = new OrderVo();
        //设置订单
        Order order = orderService.findById(oid);
        orderVo.setOrder(order);
        //设置商品
        ProductVo productVo = new ProductVo(productService.findById(order.getProductId()));
        orderVo.setProductVo(productVo);

        model.addAttribute("orderVo", orderVo);
        Address orderAddress = addressService.findById(orderVo.getOrder().getAddressId());
        model.addAttribute("orderAddress", orderAddress);
        return "confirmPay";
    }

    /**
     * (还没抛异常)
     * 真正确认收货
     *
     * @param session
     * @param model
     * @param oid
     * @return
     */
    @Authentication
    @RequestMapping({"/confiredPage"})
    public String confirmReceive(HttpSession session,
                                 Model model,
                                 @RequestParam("oid") Long oid) {
        User user = (User) session.getAttribute(ConstantValue.CURRENT_USER);
        if (user == null) {
            return "redirect:/login";
        }
        OrderStatus status = OrderStatus.WAIT_COMMENT;
        Order order = new Order();
        order.setId(oid);
        order.setStatus(status);
        orderService.updateSelectionById(order);
        return "orderConfirmed";
    }

    /**
     * 订单评价
     *
     * @param session
     * @param model
     * @param oid
     * @return
     */
    @Authentication
    @RequestMapping({"/comment"})
    public String orderComment(HttpSession session,
                               Model model,
                               @RequestParam("oid") Long oid,
                               @PageDefault(required = false) PageQuery pageQuery1) {
        PageQuery pageQuery = new PageQuery(pageQuery1.getPage(), pageQuery1.getSize(), new com.rhinoceros.mall.core.query.Order("createAt", com.rhinoceros.mall.core.query.Order.Direction.DESC));
        Order order = orderService.findById(oid);
        OrderVo orderVo = new OrderVo();
        orderVo.setOrder(order);
        ProductVo productVo = new ProductVo(productService.findById(order.getProductId()));
        orderVo.setProductVo(productVo);
        model.addAttribute("orderVo", orderVo);
        //评论
        List<Comment> comments = commentService.findByProductId(productVo.getProduct().getId(), pageQuery);
        List<CommentVo> commentVos = new LinkedList<>();
        for (Comment comment : comments) {
            CommentVo vo = new CommentVo();
            vo.setComment(comment);
            vo.setUser(userService.findById(comment.getUserId()));
            commentVos.add(vo);
        }
        model.addAttribute("comments", commentVos);
        model.addAttribute("nowPage", pageQuery.getPage());

        return "review";
    }

    /**
     * (还没抛异常)
     * 按了提交评论后
     *
     * @param
     * @param oid
     * @return
     */
    @Authentication
    @RequestMapping({"/completeComment"})
    public String completeComment(@RequestParam("oid") Long oid,
                                  @RequestParam("content") String content) {
        Order order = new Order();
        order.setId(oid);
        //更改订单状态
        order.setStatus(OrderStatus.COMPLETED);
        orderService.updateSelectionById(order);
        //增加评论
        Order order1 = orderService.findById(oid);
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setOrderId(oid);
        comment.setProductId(order1.getProductId());
        comment.setUserId(order1.getUserId());
        commentService.add(comment);
        return "redirect:/order/comment?oid=" + oid;
    }

    /**
     * (未处理异常)
     * 取消订单
     *
     * @param oid
     * @return
     */
    @Authentication
    @RequestMapping({"/cancleOrder"})
    public String cancleOrder(@RequestParam("oid") Long oid) {
        Order order = new Order();
        order.setId(oid);
        //更改订单状态
        order.setStatus(OrderStatus.CANCEL);
        orderService.updateSelectionById(order);
        return "redirect:/order/list?status=WAIT_PAY";
    }

    /**
     * 申请退货
     *
     * @param oid
     * @return
     */
    @Authentication
    @RequestMapping({"/returnOrder"})
    public String returnOrder(@RequestParam("oid") Long oid) {
        Order order = new Order();
        order.setId(oid);
        //更改订单状态
        order.setStatus(OrderStatus.WAIT_RETURN);
        orderService.updateSelectionById(order);
        return "redirect:/order/list?status=WAIT_RETURN";
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
