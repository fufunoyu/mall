package com.rhinoceros.mall.core.query;

import com.rhinoceros.mall.core.utils.StringUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

/**
 * 分账分页查询条件
 */
@NoArgsConstructor
public class PageQuery {

    /**
     * 第几页，从1开始
     */
    @Getter
    @Setter
    private Integer page = 1;

    /**
     * 每页显示数
     */
    @Getter
    @Setter
    private Integer size = 10;

    /**
     * 排序
     */
    @Getter
    private List<Order> orders = new LinkedList<Order>();

    public PageQuery(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }

    public PageQuery(Integer page, Integer size, List<Order> orders) {
        this(page, size);
        this.orders = orders;
    }

    public PageQuery(Integer page, Integer size, Order order) {
        this(page, size);
        this.orders.add(order);
    }

    /**
     * 偏移量
     */
    public Integer getOffset() {
        return (this.page - 1) * this.size;
    }

    /**
     * 获取排序转化成的字符串,用于数据库
     * 驼峰自动转为下划线
     *
     * @return
     */
    public String getQueryString() {
        StringBuffer sb = new StringBuffer("");

        if (orders.size() > 0) {
            sb.append(" ORDER BY ");
            for (Order order : orders) {
                // 驼峰转下划线
                String field = StringUtils.camel2Underline(order.getField());
                sb.append(field).append(" ").append(order.getDirection().name()).append(',');
            }
            //去除最后的逗号
            sb.deleteCharAt(sb.length() - 1);
        }

        if (this.getOffset() > 0 && this.getSize() > 0) {
            sb.append(" LIMIT ").append(this.getOffset()).append(",").append(this.size);
        }

        return sb.toString();
    }
}
