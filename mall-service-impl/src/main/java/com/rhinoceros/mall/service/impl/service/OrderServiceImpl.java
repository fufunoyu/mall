package com.rhinoceros.mall.service.impl.service;
/* created at 8:11 PM 3/6/2018  */

import com.rhinoceros.mall.core.dto.OrderDto;
import com.rhinoceros.mall.core.dto.OrderListDto;
import com.rhinoceros.mall.core.enumeration.OrderStatus;
import com.rhinoceros.mall.core.po.*;
import com.rhinoceros.mall.core.query.PageQuery;
import com.rhinoceros.mall.dao.dao.*;
import com.rhinoceros.mall.service.impl.exception.common.EntityNotExistException;
import com.rhinoceros.mall.service.impl.exception.common.ParameterIsNullException;
import com.rhinoceros.mall.service.impl.exception.order.OrderProductNumException;
import com.rhinoceros.mall.service.impl.exception.order.OrderProductStoreNumException;
import com.rhinoceros.mall.service.impl.exception.order.OrderStatusException;
import com.rhinoceros.mall.service.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;
    @Autowired
    private AddressDao addressDao;
    @Autowired
    private CartProductDao cartProductDao;
    /**
     * 根据userId和订单状态找出所有符合条件的分页订单
     *
     * @param userId
     * @param status
     * @param pageQuery
     * @return
     */
    @Override
    public List<Order> findByUserIdAndStatus(Long userId, OrderStatus status, PageQuery pageQuery) {
        return orderDao.findByUserIdAndStatus(userId, status, pageQuery);
    }


    /**
     * 根据用户id和状态找出符合条件的订单数量
     *
     * @param userId
     * @param status
     * @return 订单数量
     */
    @Override
    public Integer countByUserIdAndStatus(Long userId, OrderStatus status) {
        return orderDao.countByUserIdAndStatus(userId, status);
    }

    /**
     * 根据订单id更新订单信息
     *
     * @param order
     */
    @Transactional
    @Override
    public void updateSelectionById(Order order) {
        if (order.getId() == null) {
            log.info("订单id不能为空");
            throw new ParameterIsNullException("订单id不能为空");
        }
        orderDao.updateSelectionById(order);
    }

    /**
     * 根据订单id查找订单
     * @param id
     * @return
     */
    @Override
    public Order findById(Long id) {
        return orderDao.findById(id);
    }


    /**
     * 确认收货
     * @param oid
     */
    @Transactional
    @Override
    public void confirmedReceive(Long oid) {
        if (oid == null) {
            log.info("订单id不能为空");
            throw new ParameterIsNullException("订单id不能为空");
        }
        Order order = orderDao.findById(oid);
        if (order == null) {
            log.info("订单不存在");
            throw new EntityNotExistException("订单不存在");
        }

        if(order.getStatus() != OrderStatus.WAIT_RECEIVE){
            log.info("订单不是待收货状态");
            throw new OrderStatusException("订单不是待收货状态");
        }
        //更改订单状态
        order.setStatus(OrderStatus.WAIT_COMMENT);
        orderDao.updateSelectionById(order);
        //增加商品销量
        Long pid = order.getProductId();
        Integer num = order.getProductNum();
        Product product = new Product();
        Integer saleNum = productDao.findById(pid).getSaleNum()+num;
        product.setId(pid);
        product.setSaleNum(saleNum);
        productDao.updateSelectionById(product);
    }

    @Transactional
    @Override
    public void cancelOrder(Long oid) {
        if (oid == null) {
            log.info("订单id不能为空");
            throw new ParameterIsNullException("订单id不能为空");
        }
        Order order = orderDao.findById(oid);
        if (order == null) {
            log.info("订单不存在");
            throw new EntityNotExistException("订单不存在");
        }
        if(order.getStatus() != OrderStatus.WAIT_PAY){
            log.info("订单不是未支付状态");
            throw new OrderStatusException("订单不是未支付状态");
        }
        //更改订单状态;
        order.setStatus(OrderStatus.CANCEL);
        orderDao.updateSelectionById(order);
        //增加库存
        Product product = new Product();
        Integer storeNum = productDao.findById(order.getProductId()).getStoreNum()+order.getProductNum();
        product.setStoreNum(storeNum);
        product.setId(order.getProductId());
        productDao.updateSelectionById(product);
    }

    /**
     * 添加订单
     * @param dtos
     * @param userId
     * @param addressId
     * @return
     */
    @Transactional
    @Override
    public List<Order> add(OrderListDto dtos, Long userId, Long addressId) {
        if(userId == null){
            log.info("用户id不能为空");
            throw new ParameterIsNullException("用户id不能为空");
        }
        User user = userDao.findById(userId);
        if(user == null){
            log.info("用户不存在");
            throw new EntityNotExistException("用户不存在");
        }
        if(addressId==null){
            log.info("地址Id不能为空");
            throw new ParameterIsNullException("地址Id不能为空");
        }
        Address address = addressDao.findById(addressId);
        if(address==null){
            log.info("地址不存在");
            throw new EntityNotExistException("地址不存在");
        }

        List<Order> orders = new LinkedList<>();
        //设置订单号
        List<OrderDto> orderDtos = dtos.getOrders();

        for(OrderDto orderDto:orderDtos){
            Order order = new Order();
            Integer productNum = orderDto.getProductNum();
            if(productNum==null||productNum<=0){
                log.info("商品数量需要大于0");
                throw new OrderProductNumException("商品数量需要大于0");
            }
            Long productId =  orderDto.getProductId();
            if(productId==null){
                log.info("商品ID不能为空");
                throw  new ParameterIsNullException("商品ID不能为空");
            }
            Product product = productDao.findById(productId);
            if(product==null){
                log.info("商品不存在");
                throw new EntityNotExistException("商品不存在");
            }
            if(product.getStoreNum()-productNum<0){
                log.info("库存不足");
                throw new OrderProductStoreNumException("库存不足");
            }
            BigDecimal price = product.getPrice();
            BigDecimal discount = product.getDiscount();
            BigDecimal totalPrice = calculate(price,discount,productNum);
            product.setStoreNum(product.getStoreNum()-productNum);
            productDao.updateSelectionById(product);
            String identifier = randNumber();
            order.setIdentifier(identifier);
            order.setStatus(OrderStatus.WAIT_PAY);
            order.setCreateAt(new Date());
            order.setAddressId(addressId);
            order.setProductId(productId);
            order.setProductNum(productNum);
            order.setTotalPrice(totalPrice);
            order.setUserId(userId);
            orders.add(order);
            if(dtos.getCartSubmit().equals("success")){
                CartProduct cartProduct = cartProductDao.findByUserIdAndProductId(userId,productId);
                cartProductDao.deleteById(cartProduct.getProductId());
            }
        }
        orderDao.addAll(orders);

        return orders;
    }
    private BigDecimal calculate(BigDecimal price, BigDecimal discount, Integer num) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        if (discount == null) {
            totalPrice = price.multiply(BigDecimal.valueOf(num));
        } else {
            totalPrice = discount.multiply(BigDecimal.valueOf(num));
        }
        return totalPrice;
    }

    private String randNumber(){
        Random rand = new Random();
        int max=9999,min=1000;
        int shu = rand.nextInt(max)%(max-min+1) + min;
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        String randomNumber = sdf.format(date)+shu;
        return randomNumber;
    }
}
