package com.example.store.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author tianchangqing
 * @date 2022/8/31 15:59
 */
@Data
public class ProductVO {
    private Integer id;

    private Integer categoryId;

    private String itemType;

    private String name;

    private BigDecimal price;

    private Integer num;

    private Integer priority;
}
