package com.example.shop.feign;

import com.example.common.utils.JsonResult;
import com.example.shop.dto.ProductDTO;
import com.example.shop.feign.fallbacks.StoreFeignClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author tianchangqing
 * @date 2022/8/31 11:18
 */
@FeignClient(name = "emall-store", fallbackFactory = StoreFeignClientFallbackFactory.class)
public interface StoreFeignClient {
    @RequestMapping(value = "/store/findlist", method = RequestMethod.GET)
    JsonResult<List<ProductDTO>> findProductList();


    @RequestMapping(value = "/store/details", method = RequestMethod.GET)
    JsonResult<ProductDTO> findProduct(@RequestParam Integer id);


    @RequestMapping(value = "/store/update/num", method = RequestMethod.POST)
    JsonResult updateNum(@RequestParam Integer id, @RequestParam Integer num, @RequestParam Integer flag);
}
