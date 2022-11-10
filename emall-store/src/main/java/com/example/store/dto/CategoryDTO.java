package com.example.store.dto;

import lombok.Data;

import java.util.List;

/**
 * @author tianchangqing
 * @date 2022/8/31 14:44
 */
@Data
public class CategoryDTO {
    private Long id;

    private String name;

    private Long parentId;

    private Integer isParent;

    private Integer sortOrder;

    private List<CategoryDTO> children;
}
