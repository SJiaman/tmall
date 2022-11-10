package com.example.store.mapper;

import com.example.store.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    /**
     * 查询出所有上架商品列表
     */
    List<Product> findProductList();

    /**
     * 查询出某商品详细信息
     */
    Product findProduct(Integer id);

    /**
     * 添加商品
     */
    Integer insertProduct(Product product);

    /**
     * 删除商品
     */
    Integer deleteProduct(Integer id);


    /**
     * 修改商品信息
     * 要想更新，首先先找到对应id的商品对象，即findProduct
     */
    Integer updateProduct(Product product);

}
