package com.example.cart.entity;

import lombok.Data;

import java.util.List;

/**
 * @author tianchangqing
 * @date
 */
@Data
public class CartEntity {
    private Integer id;

    private Integer userId;

    private List<CartItemEntity> cartItem;

}
