package com.example.cart.dto;

import lombok.Data;

/**
 * @author tianchangqing
 * @date
 */
@Data
public class ProductDto {
    /**
     * id
     */
    private Integer id;

    /**
     * 商品名
     */
    private String title;

    /**
     * 店铺id
     */
    private Integer shopId;

    /**
     * 店铺名
     */
    private String shopName;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 价格
     */
    private Long price;

    /**
     * 卖点
     */
    private String sellPoint;
}
