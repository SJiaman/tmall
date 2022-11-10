package com.example.shop.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author tianchangqing
 * @date 2022/8/31 11:22
 */
@Data
public class ProductDTO {
    private Integer id;

    private Integer categoryId;

    private String itemType;

    private String title;

    private BigDecimal price;

    private Integer num;
}
