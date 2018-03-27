package com.rhinoceros.mall.core.vo;
/* created at 10:11 AM 3/21/2018  */

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AdminOrderListWithCountVo {
    private List<AdminOrderVo> adminOrderVoList;
    /**
     * 总条数
     */
    private Long count;
}
