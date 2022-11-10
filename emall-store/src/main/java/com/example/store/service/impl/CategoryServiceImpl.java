package com.example.store.service.impl;

import com.example.store.dto.CategoryDTO;
import com.example.store.entity.category;
import com.example.store.mapper.CategoryMapper;
import com.example.store.service.CategoryService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author tianchangqing
 * @date 2022/8/31 14:40
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    CategoryMapper categoryMapper;

    @Override
    public List<CategoryDTO> getCategory() {
        List<category> categories = categoryMapper.selectByExample(null);
        List<CategoryDTO> categoryDTO = categories.stream()
                .map(o -> {
                    CategoryDTO category = new CategoryDTO();
                    category.setId(o.getId());
                    category.setName(o.getName());
                    category.setIsParent(o.getIsParent());
                    category.setParentId(o.getParentId());
                    category.setSortOrder(o.getSortOrder());
                    return category;
                }).collect(Collectors.toList());

        List<CategoryDTO> collect = categoryDTO.stream()
                .filter(o -> o.getIsParent() == 1)
                .peek(o -> o.setChildren(getChildren(o, categoryDTO)))
                .sorted(Comparator.comparingInt(CategoryDTO::getSortOrder))
                .collect(Collectors.toList());
        return collect;
    }

    private List<CategoryDTO> getChildren(CategoryDTO category, List<CategoryDTO> categoryDTOList) {
        List<CategoryDTO> categoryDTOS = categoryDTOList.stream()
                .filter(o -> o.getParentId().equals(category.getId()))
                .peek(o -> o.setChildren(getChildren(o, categoryDTOList)))
                .sorted(Comparator.comparingInt(CategoryDTO::getSortOrder))
                .collect(Collectors.toList());
        return categoryDTOS;
    }
}
