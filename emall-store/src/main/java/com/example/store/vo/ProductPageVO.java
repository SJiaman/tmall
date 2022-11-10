package com.example.store.vo;

import lombok.Data;

import java.util.List;

/**
 * @author tianchangqing
 * @date 2022/9/5 14:53
 */
@Data
public class ProductPageVO {
    List<ProductVO> products;

    private Integer pageNum;

    private Integer pageSize;

    private Long total;
}
