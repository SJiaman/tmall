package com.example.cart.service;

import com.example.cart.dto.CartDto;
import com.github.pagehelper.PageInfo;

/**
 * @author tianchangqing
 * @date
 */
public interface CartService {
    /**
     * 购物车列表
     *
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo list(Integer userId, Integer pageNum, Integer pageSize);

    /**
     * 删除购物车商品
     *
     * @param ids
     * @return
     */
    boolean deleteByIds(Integer[] ids);

    /**
     * 修改商品数量
     *
     * @param id
     * @param quantity
     * @return
     */
    boolean updateQuantity(Integer id, Integer quantity);

    /**
     * 添加商品到购物车
     *
     * @param userId
     * @param productId
     * @param productNum
     * @return
     */
    boolean addItem(Integer userId, Integer productId, Integer productNum);


    /**
     * 获取选择的商品信息
     *
     * @param ids
     * @return
     */
    CartDto getSelectItem(Integer[] ids);

    /**
     * 清空购物车
     *
     * @param userId
     * @return
     */
    boolean clearCart(Integer userId);
}
