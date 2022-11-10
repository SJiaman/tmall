package com.example.cart.feign;

import com.example.cart.dto.Product;
import com.example.common.utils.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author tianchangqing
 * @date 2022/8/18 14:31
 */
@Component
//@FeignClient(name="store-product",url="http://10.19.182.91:8082/")
@FeignClient(name = "store-product")
public interface ProductClient {

    @RequestMapping(value = "products/findlist", method = RequestMethod.POST)
    public JsonResult<List<Product>> findProductList();


}