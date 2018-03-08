package com.rhinoceros.mall.service.impl.service;
/* created at 8:11 PM 3/6/2018  */

import com.rhinoceros.mall.core.pojo.Order;
import com.rhinoceros.mall.core.pojo.OrderProduct;
import com.rhinoceros.mall.core.pojo.Product;
import com.rhinoceros.mall.core.query.PageQuery;
import com.rhinoceros.mall.core.vo.OrderListVo;
import com.rhinoceros.mall.core.vo.OrderProductVo;
import com.rhinoceros.mall.core.vo.ProductVo;
import com.rhinoceros.mall.dao.dao.OrderDao;
import com.rhinoceros.mall.dao.dao.OrderProductDao;
import com.rhinoceros.mall.dao.dao.ProductDao;
import com.rhinoceros.mall.service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderProductDao orderProductDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    /**
     * 根据userId和订单状态找出所有符合条件的订单
     * @param userId
     * @return
     */
    @Override
    public List<OrderListVo> findOrderListVoByUserId(Long userId, String status ) {
        //选择排序方式
        com.rhinoceros.mall.core.query.Order queryOrder = new com.rhinoceros.mall.core.query.Order("createAt", com.rhinoceros.mall.core.query.Order.Direction.DESC);
        //选择分页方式
        PageQuery pageQuery = new PageQuery(1,1,queryOrder);

        List<Order> orders = orderDao.findByUserIdAndStatus(userId, status, pageQuery);
        List<OrderListVo> orderListVos = new LinkedList<OrderListVo>();
        for(Order order:orders){
            OrderListVo orderListVo = new OrderListVo();
            orderListVo.setOrder(order);
            List<OrderProduct> orderProducts = orderProductDao.findByOrderId(order.getId());
            List<OrderProductVo> orderProductVos = new LinkedList<OrderProductVo>();
            for (OrderProduct orderProduct : orderProducts) {
                Product product = productDao.findById(orderProduct.getProductId());
                ProductVo productVo = new ProductVo();
                //获取商品图片url数组
                productVo.setImagesUrls(product.getImageUrls().split(Product.IMAGE_SEPARATION));
                productVo.setFirstImageUrl(productVo.getImagesUrls()[0]);
                productVo.setProduct(product);
                //创建OrderProductVo对象以便填充
                OrderProductVo orderProductVo = new OrderProductVo();
                orderProductVo.setNum(orderProduct.getProductNum());
                orderProductVo.setProductVo(productVo);
                orderProductVos.add(orderProductVo);
            }
            orderListVo.setOrderProductVos(orderProductVos);
            orderListVos.add(orderListVo);
        }

        return orderListVos;
    }
    @Override
    public List<OrderListVo> findOrderListVoByUserId(Long userId){
        String status = null;
        return findOrderListVoByUserId(userId, status);
    }


}
