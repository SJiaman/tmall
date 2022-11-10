package com.example.order.feign.fallbacks;

import com.example.order.feign.ShopFeignClient;
import org.springframework.cloud.openfeign.FallbackFactory;

/**
 * @author tianchangqing
 * @date 2022/9/23 14:59
 * @desc
 */
public class ShopFeignClientFallbackFactory implements FallbackFactory<ShopFeignClient> {
    @Override
    public ShopFeignClient create(Throwable cause) {
        return null;
    }
}
