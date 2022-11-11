package com.example.order.controller;

import com.example.common.utils.Result;
import com.example.order.converter.OrderConverter;
import com.example.order.dto.OrderDTO;
import com.example.order.service.OrderService;
import com.example.order.vo.CartProductVO;
import com.example.order.vo.OrderVO;
import com.example.order.vo.ProductVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author tiancq
 */
@Slf4j
@RestController
@Api(tags = "订单服务")
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;

    @ApiOperation("从店铺直接创建订单")
    @PostMapping("/create")
    public Result<Boolean> createOrderFromShop(@RequestBody ProductVO productVO) {
//        return Result.success(orderService.create(OrderConverter.INSTANCE.productVO2DTO(productVO)));
        return Result.success();
    }

    @ApiOperation("从购物车创建订单")
    public Result<Boolean> createOrderFromCart(@RequestBody CartProductVO cartProductVO) {
        return Result.success(true);
    }

    @ApiOperation("用户订单列表")
    @GetMapping("/list")
    public Result<List<OrderVO>> getOrderList(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        List<OrderDTO> orderDTOS = orderService.orderList(pageNum, pageSize);
        return Result.success(OrderConverter.INSTANCE.dtoList2voList(orderDTOS));
    }

    @ApiOperation("取消订单")
    @PostMapping("/cancel")
    public Result<Boolean> cancelOrder(@RequestBody OrderVO orderVO) {
        return Result.success(true);
    }

    @ApiOperation("修改订单信息")
    @PostMapping("/edit")
    public Result<Boolean> editOrder(@RequestBody OrderVO orderVO) {
        return Result.success(true);
    }

}
