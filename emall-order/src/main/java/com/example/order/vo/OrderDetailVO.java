package com.example.order.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author tianchangqing
 * @date 2022/9/23 10:55
 * @desc
 */
@Data
public class OrderDetailVO {
    private Integer oid;

    private String recvName;

    private String recvPhone;

    private String recvAddress;

    private Long totalPrice;

    private Integer status;

    private Date orderTime;

    private Date payTime;

    private Date createdTime;
}
