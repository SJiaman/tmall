package com.example.cart.controller;


import com.example.cart.dto.CartDto;
import com.example.cart.dto.OrderItem;
import com.example.cart.feign.OrderFeignClient;
import com.example.cart.service.CartService;
import com.example.common.utils.JsonResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tianchangqing
 * @date
 */
@Slf4j
@Api(tags = "购物车接口")
@RequestMapping("/cart")
@RestController
public class CartController {

    @Resource
    CartService cartService;


    @Resource
    OrderFeignClient orderFeignClient;


    @GetMapping("/order/test")
    public JsonResult<List<OrderItem>> getOrderItemList(@RequestParam Integer oid) {
        JsonResult<List<OrderItem>> orderItemList = orderFeignClient.getOrderItemList(oid);
        return orderItemList;
    }


    /**
     * 查询用户购物车商品列表 分页查询
     *
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "用户购物车商品列表")
    @GetMapping("/list")
    public JsonResult<PageInfo> cartList(@RequestParam Integer userId,
                                         @RequestParam Integer pageNum,
                                         @RequestParam Integer pageSize) {
        PageInfo cartList = cartService.list(userId, pageNum, pageSize);
        return JsonResult.success(cartList);
    }

    /**
     * 购物车添加商品
     *
     * @param productId
     * @return
     */
    @ApiOperation(value = "添加商品到购物车")
    @PostMapping("/add")
    public JsonResult<Void> addItemToCart(@RequestParam Integer userId,
                                          @RequestParam Integer productId,
                                          @RequestParam Integer quantity) {
        if (!cartService.addItem(userId, productId, quantity)) {
            return JsonResult.fail();
        }
        return JsonResult.success();
    }

    /**
     * 修改购物车商品数量
     *
     * @param id
     * @param quantity
     * @return
     */

    @PostMapping("/update")
    public JsonResult<Void> updateItemQuantity(@RequestParam Integer id, @RequestParam Integer quantity) {
        if (!cartService.updateQuantity(id, quantity)) {
            return JsonResult.fail();
        }
        return JsonResult.success();
    }


    /**
     * 删除购物车商品
     *
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    public JsonResult<Void> deleteItem(@RequestBody Integer[] ids) {
        if (!cartService.deleteByIds(ids)) {
            return JsonResult.fail();
        }
        return JsonResult.success();
    }



    @GetMapping("/clear")
    public JsonResult<Void> clearCart(@RequestParam Integer userId) {
        if (!cartService.clearCart(userId)) {
            return JsonResult.fail();
        }
        return JsonResult.success();
    }


    /**
     * 获取选择商品信息
     *
     * @param ids
     * @return
     */

    @PostMapping("/select/info")
    public JsonResult<CartDto> getSelectItem(@RequestBody Integer[] ids) {
        log.info("ids:{}", ids);
        CartDto selectItem = cartService.getSelectItem(ids);
        //return new JsonResult<>(ResultCode.SUCCESS.getCode(), selectItem);
        return JsonResult.success(selectItem);
    }


//    @ApiOperation(value = "购物车生成订单")
//    @GetMapping("/create/order")
//    public JsonResult<Boolean> createOrderFromCart(@RequestParam Integer[] ids) {
//        return null;
//    }
}
