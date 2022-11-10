package com.example.order.dto;

import lombok.Data;
import java.util.Date;

/**
 * @author tianchangqing
 * @date 2022/9/23 9:24
 * @desc
 */
@Data
public class OrderDTO {
    private Integer id;
    private String name;
    private String phone;
    private String address;
    private Long totalPrice;
    private Integer status;
}
