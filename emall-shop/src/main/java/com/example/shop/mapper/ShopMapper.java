package com.example.shop.mapper;

import com.example.shop.entity.Shop;
import com.example.shop.entity.ShopExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopMapper {
    long countByExample(ShopExample example);

    int deleteByExample(ShopExample example);

    int deleteByPrimaryKey(Long sid);

    int insert(Shop row);

    int insertSelective(Shop row);

    List<Shop> selectByExample(ShopExample example);

    Shop selectByPrimaryKey(Long sid);

    int updateByExampleSelective(@Param("row") Shop row, @Param("example") ShopExample example);

    int updateByExample(@Param("row") Shop row, @Param("example") ShopExample example);

    int updateByPrimaryKeySelective(Shop row);

    int updateByPrimaryKey(Shop row);
}