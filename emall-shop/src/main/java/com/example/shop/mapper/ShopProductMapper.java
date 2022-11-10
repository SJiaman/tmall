package com.example.shop.mapper;

import com.example.shop.entity.ShopProduct;
import com.example.shop.entity.ShopProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopProductMapper {
    long countByExample(ShopProductExample example);

    int deleteByExample(ShopProductExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopProduct row);

    int insertSelective(ShopProduct row);

    List<ShopProduct> selectByExample(ShopProductExample example);

    ShopProduct selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") ShopProduct row, @Param("example") ShopProductExample example);

    int updateByExample(@Param("row") ShopProduct row, @Param("example") ShopProductExample example);

    int updateByPrimaryKeySelective(ShopProduct row);

    int updateByPrimaryKey(ShopProduct row);
}