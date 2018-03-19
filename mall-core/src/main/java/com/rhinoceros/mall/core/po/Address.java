package com.rhinoceros.mall.core.po;
/* created at 1:39 PM 3/6/2018  */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
 public class Address {

    /**
     * 多级地址隔开符号
     */
    public static final String ADDRESS_SEPARATION = "/";

    /**
     * 地址id
     */
    private Long id;

    /**
     * 邮政编码
     */
    private String postalCode;

    /**
     * 收货地址,多级地址则以{@link ADDRESS_SEPARATION}隔开
     */
    private String deliveryAddress;
   /**
    * 收货详细地址,
    */
   private String detailAddress;
    /**
     * 收货人姓名
     */
    private String deliveryName;

    /**
     * 收货人电话
     */
    private String phone;

    /**
     * 地址所属用户id
     */
    private Long userId;


}
