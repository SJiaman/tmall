package com.example.store.service;

import com.example.store.dto.CategoryDTO;

import java.util.List;

/**
 * @author tianchangqing
 * @date 2022/8/31 14:40
 */
public interface CategoryService {
    List<CategoryDTO> getCategory();
}
