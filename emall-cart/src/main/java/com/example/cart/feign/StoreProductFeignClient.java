package com.example.cart.feign;

import com.example.cart.dto.ProductDto;
import com.example.common.utils.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * @author tianchangqing
 * @date
 */
@Component
@FeignClient("store-service")
public interface StoreProductFeignClient {

//    /**
//     * 通过id 查询商品信息
//     * @param id
//     * @return
//     */
//    @RequestMapping("/shop/product")
//    JsonResult<ProductDto> getProductById(@RequestParam Integer id);
//
//    /**
//     * 通过ids查询价格列表
//     * @param ids
//     * @return
//     */
//    @RequestMapping("/shop/product/list")
//    JsonResult<List<ProductDto>>  getProductsByIds(@RequestBody Integer[] ids);

    /**
     * 单个商品信息查询(查询商铺上架的商品详细信息)
     *
     * @param id
     * @return
     */
    @RequestMapping("/stores/getSingleStoreProduct/")
    JsonResult<ProductDto> getStoreProductById(@RequestParam Integer id);


    /**
     * 多个商品信息查询(查询商铺上架的商品详细信息)
     *
     * @param ids
     * @return
     */
    @RequestMapping("/stores/getStoreProducts/")
    JsonResult<List<ProductDto>> getStoreProductsByIds(@RequestBody Integer[] ids);
}
