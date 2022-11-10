package com.example.order.feign;

import com.example.common.utils.JsonResult;
import com.example.order.feign.fallbacks.ShopFeignClientFallbackFactory;
import com.example.order.vo.ProductVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author tianchangqing
 * @date 2022/9/23 14:58
 * @desc 商铺服务
 */
@FeignClient(name = "shop-service", fallbackFactory = ShopFeignClientFallbackFactory.class)
public interface ShopFeignClient {
    /**
     * 商铺单个商品信息
     * @param sid
     * @param pid
     * @return
     */
    @GetMapping("/getSingleProduct")
    JsonResult<ProductVO> getProductByPid(@RequestParam Integer sid, @RequestParam Integer pid);
}
