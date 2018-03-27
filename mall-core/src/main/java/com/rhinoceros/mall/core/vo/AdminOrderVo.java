package com.rhinoceros.mall.core.vo;
/* created at 2:05 PM 3/20/2018  */

import com.rhinoceros.mall.core.po.Address;
import com.rhinoceros.mall.core.po.Order;
import com.rhinoceros.mall.core.po.Product;
import com.rhinoceros.mall.core.po.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminOrderVo {

    public Order order;

    public ProductVo productVo;

    public User user;

    public Address address;

}
