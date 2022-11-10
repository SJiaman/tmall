package com.example.shop.dto;

import lombok.Data;

import java.util.List;

/**
 * @author tianchangqing
 * @date 2022/9/5 14:53
 */
@Data
public class ProductListDTO {
    List<ProductDTO> products;

    private Integer pageNum;

    private Integer pageSize;

    private Long total;

}
