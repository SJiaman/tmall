package com.example.cart.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author tianchangqing
 * @date
 */
@Data
public class CartItemEntity {
    private Integer id;

    private Integer productId;

    private Integer cartId;

    private Integer shopId;

    private Integer quantity;

    private BigDecimal price;

    private Integer isDelete;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private Date modifyTime;
}
