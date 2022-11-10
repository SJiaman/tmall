package com.example.order.vo;

import lombok.Data;

/**
 * @author tianchangqing
 * @date 2022/9/23 9:25
 * @desc
 */
@Data
public class OrderVO {
    private Integer id;
    private String name;
    private String phone;
    private String address;
    private Long totalPrice;
    private Integer status;
}
