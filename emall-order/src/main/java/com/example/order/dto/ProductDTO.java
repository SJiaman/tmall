package com.example.order.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author tianchangqing
 * @date 2022/9/23 15:05
 * @desc
 */
@Data
public class ProductDTO {
    private Integer id;

    private Integer sid;

    private Integer categoryId;

    private String itemType;

    private String name;

    private BigDecimal price;

    private Integer num;
}
