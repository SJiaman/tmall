package com.example.shop.feign.fallbacks;

import com.example.common.utils.Result;
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
            public Result<List<ProductDTO>> findProductList() {
                return Result.fail();
            }

            @Override
            public Result<ProductDTO> findProduct(Integer id) {
                return Result.fail();
            }

            @Override
            public Result updateNum(Integer id, Integer num, Integer flag) {
                return Result.fail();
            }
        };
    }
}
