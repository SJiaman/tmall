package com.example.shop.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author tianchangqing
 * @date 2022/9/6 14:15
 */
@Data
public class ProductVO {
    private Integer id;
    private Integer sid;
    private Integer pid;
    private Integer quantity;
    private String name;
    private String sellPoint;
    private BigDecimal price;
}
