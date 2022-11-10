package com.example.cart.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author tianchangqing
 * @date
 */
@Data
public class CartDto {

    private List<ProductDto> cartItem;

    private Integer itemCount;

    private Long totalAmount;

    private Date createTime;
}
