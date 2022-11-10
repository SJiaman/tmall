package com.example.shop.feign.fallbacks;

import com.example.common.utils.JsonResult;
import com.example.shop.dto.ProductDTO;
import com.example.shop.feign.StoreFeignClient;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author tianchangqing
 * @date 2022/9/9 17:18
 * @desc
 */
@Component
public class StoreFeignClientFallbackFactory implements FallbackFactory<StoreFeignClient> {
    @Override
    public StoreFeignClient create(Throwable cause) {
        return new StoreFeignClient() {
            @Override
            public JsonResult<List<ProductDTO>> findProductList() {
                return JsonResult.fail();
            }

            @Override
            public JsonResult<ProductDTO> findProduct(Integer id) {
                return JsonResult.fail();
            }

            @Override
            public JsonResult updateNum(Integer id, Integer num, Integer flag) {
                return JsonResult.fail();
            }
        };
    }
}
