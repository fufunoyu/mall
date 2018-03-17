package com.rhinoceros.mall.core.dto;
/* created at 2:34 PM 3/16/2018  */

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCommentDto {
    private Long oid;
    private Long pid;
    private Boolean complete;
}
