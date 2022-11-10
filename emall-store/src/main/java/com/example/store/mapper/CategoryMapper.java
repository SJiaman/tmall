package com.example.store.mapper;

import com.example.store.entity.category;
import com.example.store.entity.categoryExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CategoryMapper {
    long countByExample(categoryExample example);

    int deleteByExample(categoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(category row);

    int insertSelective(category row);

    List<category> selectByExample(categoryExample example);

    category selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") category row, @Param("example") categoryExample example);

    int updateByExample(@Param("row") category row, @Param("example") categoryExample example);

    int updateByPrimaryKeySelective(category row);

    int updateByPrimaryKey(category row);
}