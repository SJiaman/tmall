package com.example.store.controller;

import com.example.common.utils.JsonResult;
import com.example.store.dto.CategoryDTO;
import com.example.store.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tianchangqing
 * @date 2022/8/31 15:18
 */
@Api(tags = "分类管理")
@RestController
public class CategoryController {
    @Resource
    CategoryService categoryService;

    @ApiOperation("所有分类")
    @GetMapping("/category")
    public JsonResult<List<CategoryDTO>> getCategory() {
        List<CategoryDTO> category = categoryService.getCategory();
        return JsonResult.success(category);
    }
}
