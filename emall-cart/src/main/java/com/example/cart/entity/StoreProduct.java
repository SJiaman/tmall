package com.example.cart.entity;

import lombok.Data;

/**
 * @author example
 */
@Data
public class StoreProduct extends BaseEntity {
    private Integer id;
    private Integer sid;
    private Integer pid;
    private String title;
    private String storename;
    private Integer num;
    private Long price;
    private String sellPoint;
    private Integer status;
    private Integer priority;
    private boolean isDelete;
}
