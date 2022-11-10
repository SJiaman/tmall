package com.example.order.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author tianchangqing
 * @date 2022/9/23 14:55
 * @desc
 */
@Data
public class ProductVO {
    private Integer id;

    private Integer sid;

    private Integer categoryId;

    private String itemType;

    private String name;

    private BigDecimal price;

    private Integer num;
}
