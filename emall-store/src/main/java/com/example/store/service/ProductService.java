package com.example.store.service;

import com.example.store.dto.ProductDTO;
import com.example.store.dto.ProductPageDTO;


/**
 * @author example
 */
public interface ProductService {
    ProductPageDTO findProductList(Integer pageNum, Integer pageSize);

    /**
     * 查询出某商品详细信息
     */
    ProductDTO findProduct(Integer id);

    /**
     * 添加商品
     */
    Boolean insertProduct(ProductDTO productVO);

    /**
     * 删除商品
     */
    Boolean deleteProduct(Integer id);

    /**
     * 这是仓库管理员修改商品信息的接口，可以修改商品的全部信息
     */
    Boolean updateProduct(ProductDTO product);

    /**
     * 店铺需要调用的扣减与增加接口
     */
    Boolean updateQuantity(Integer id, Integer num, Integer flag);

}
