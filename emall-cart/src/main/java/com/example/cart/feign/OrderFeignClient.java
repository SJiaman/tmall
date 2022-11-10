package com.example.cart.feign;


import com.example.cart.dto.OrderItem;
import com.example.common.utils.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author tianchangqing
 * @date
 */
@Component
@FeignClient(name = "store-order")
public interface OrderFeignClient {


    /**
     * 生成订单
     *
     * @param productId
     * @param quantity
     * @return
     */
    @PostMapping("/order/")
    Boolean createOrderByCart(@RequestParam Integer productId, @RequestParam Integer quantity);

    @RequestMapping("/orders/getOrderItemList/")
    JsonResult<List<OrderItem>> getOrderItemList(@RequestParam Integer oid);
}
