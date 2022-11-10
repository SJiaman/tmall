package com.example.shop.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name = "cart-service",
        url = "http://10.19.181.243:8080"
)
public interface CartFeignClient {

//    @RequestMapping("/cart/list")
//    JsonResult<CartItem>  getCartList(Integer userId);

}
