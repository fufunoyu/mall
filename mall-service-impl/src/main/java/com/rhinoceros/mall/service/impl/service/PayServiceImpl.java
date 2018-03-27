package com.rhinoceros.mall.service.impl.service;
/* created at 10:02 PM 3/17/2018  */

import com.egzosn.pay.common.bean.PayOrder;
import com.rhinoceros.mall.core.enumeration.OrderStatus;
import com.rhinoceros.mall.core.enumeration.PayType;
import com.rhinoceros.mall.core.po.AliPay;
import com.rhinoceros.mall.core.po.Order;
import com.rhinoceros.mall.core.po.Product;
import com.rhinoceros.mall.dao.dao.OrderDao;
import com.rhinoceros.mall.manager.impl.exception.pay.PayException;
import com.rhinoceros.mall.manager.manager.PayManager;
import com.rhinoceros.mall.manager.manager.ProductManager;
import com.rhinoceros.mall.service.impl.exception.common.EntityNotExistException;
import com.rhinoceros.mall.service.impl.exception.common.ParameterIsNullException;
import com.rhinoceros.mall.service.impl.exception.order.OrderStatusException;
import com.rhinoceros.mall.service.impl.po.PayOrderPo;
import com.rhinoceros.mall.service.service.PayService;
import com.rhinoceros.mall.service.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Slf4j
@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private ProductService productService;

    @Autowired
    private PayManager payManager;

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductManager productManager;


    @Transactional
    @Override
    public String toPayByOrderList(List<Order> orderIdList) {

        PayOrderPo payOrderPo = getPayOrderPoByOrderList(orderIdList);
        PayOrder payOrder = new PayOrder(payOrderPo.getTitle(), payOrderPo.getBody(), payOrderPo.getTotalPrice(), payOrderPo.getIdentifierAll());
        String string = payManager.aliToPay(payOrder);
        return string;
    }

    /**
     * 获得PayOrder对象初始化所需要的参数
     *
     * @param orderList
     * @return
     */
    private PayOrderPo getPayOrderPoByOrderList(List<Order> orderList) {
        PayOrderPo payOrderPo = new PayOrderPo();
        BigDecimal totalPrice = new BigDecimal(0);
        payOrderPo.setBody("摘要");
        String totalId = randNumber();
        for (int i = 0; i < orderList.size(); i++) {
            Order order = orderList.get(i);
            //订单支付状态设为支付宝支付
            order.setPayType(PayType.aliPay);
            orderDao.updateSelectionByIdentifier(order);
            //小订单放在总订单下
            if (orderDao.findAliPayByOrderIdentifier(order.getIdentifier()) == null) {
                AliPay aliPay = new AliPay();
                aliPay.setOrderIdentifier(order.getIdentifier());
                aliPay.setOrderTotalId(totalId);
                orderDao.addAliPay(aliPay);
            }else{
                totalId=orderDao.findAliPayByOrderIdentifier(order.getIdentifier()).getOrderTotalId();
            }
            Product product = productManager.findById(order.getProductId());
            if (i == 0) {
                payOrderPo.setTitle("购买" + product.getName());
//                payOrderPo.setIdentifierAll(order.getIdentifier());
            } else {
                payOrderPo.setTitle(payOrderPo.getTitle() + "，" + product.getName());
//                payOrderPo.setIdentifierAll(payOrderPo.getIdentifierAll()+";"+order.getIdentifier());
            }
            totalPrice = totalPrice.add(order.getTotalPrice());
        }
        payOrderPo.setTotalPrice(totalPrice);
        payOrderPo.setIdentifierAll(totalId);
        return payOrderPo;
    }


    @Transactional
    @Override
    public List<String> payBack(Map<String, String[]> parameter, InputStream inputStream) {
        List<String> OrderIdentifierList;
        try {
            OrderIdentifierList = payManager.aliPayBack(parameter, inputStream);
        } catch (PayException e) {
            throw e;
        }
        for (String orderIdentifier : OrderIdentifierList) {
            if (orderIdentifier == null) {
                log.info("订单id不能为空");
                throw new ParameterIsNullException("订单id不能为空");
            }
            Order order = orderDao.findByIdentifier(orderIdentifier);
            if (order == null) {
                log.info("订单不存在");
                throw new EntityNotExistException("订单不存在");
            }
            if (order.getStatus() != OrderStatus.WAIT_PAY) {
                log.info("订单不是待付款状态");
                throw new OrderStatusException("订单不是待付款状态");
            }
            order.setStatus(OrderStatus.WAIT_SHIP);
            order.setPayAt(new Date());
            orderDao.updateSelectionById(order);
        }
        return OrderIdentifierList;
    }


    /**
     * 生成订单号
     *
     * @return
     */
    private String randNumber() {
        Random rand = new Random();
        int max = 9999, min = 1000;
        int shu = rand.nextInt(max) % (max - min + 1) + min;
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        String randomNumber = sdf.format(date) + shu;
        return randomNumber;
    }
}
