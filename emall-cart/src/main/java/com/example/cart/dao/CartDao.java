package com.example.cart.dao;

import com.example.cart.entity.CartItemEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author tianchangqing
 * @date
 */
@Mapper
public interface CartDao {

    int selectCartByUserId(Integer userId);

    boolean deleteByIds(Integer[] ids);

    boolean insert(CartItemEntity cartItemEntity);

    boolean update(CartItemEntity cartItemEntity);

    CartItemEntity selectCartItem(Integer cartId, Integer productId);

    CartItemEntity selectCartItemById(Integer id);

    List<CartItemEntity> selectCartItemByIds(Integer[] ids);

    boolean clearCart(Integer userId);

    List<CartItemEntity> selectUserCart(Integer userId);
}
