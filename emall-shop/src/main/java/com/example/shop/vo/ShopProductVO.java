package com.example.shop.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author example
 */
@Data
public class ShopProductVO implements Serializable {
    private List<ProductVO> products;

    private Integer pageNum;

    private Integer pageSize;

    private Long total;
}
