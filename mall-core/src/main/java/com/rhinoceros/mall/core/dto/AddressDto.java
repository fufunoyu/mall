package com.rhinoceros.mall.core.dto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AddressDto {
    //详细地址
    @NotBlank(message = "地址不能为空")
    private String detailAddress ;
    //邮编
    @NotBlank(message = "邮编不能为空")
    private String postcode;
    //收货人
    @NotBlank(message = "收货人不能为空")
    private String consignee;
    //联系方式
    @NotBlank(message = "联系方式不能为空")
    private String phoneNumber;


}
