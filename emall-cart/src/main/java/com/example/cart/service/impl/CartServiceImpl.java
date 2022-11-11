package com.example.cart.service.impl;

import cn.hutool.core.date.DateUtil;
import com.example.cart.dao.CartDao;
import com.example.cart.dto.CartDto;
import com.example.cart.dto.ProductDto;
import com.example.cart.entity.CartItemEntity;
import com.example.cart.feign.StoreProductFeignClient;
import com.example.cart.service.CartService;
import com.example.common.enums.ResultCode;
import com.example.common.utils.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author tianchangqing
 * @date
 */
@Slf4j
@Service
public class CartServiceImpl implements CartService {
    @Resource
    private CartDao cartDao;

    @Resource
    private StoreProductFeignClient storeProductFeignClient;

    @Override
    public PageInfo list(Integer userId, Integer pageNum, Integer pageSize) {
        // 分页设置
        PageHelper.startPage(pageNum, pageSize);
        // 查询购物车商品
        List<CartItemEntity> userCart = cartDao.selectUserCart(userId);


        log.info("商品：{}", userCart.get(0));
        Integer[] ids = userCart.stream().map(x -> x.getProductId()).toArray(Integer[]::new);

        // 查询商品详情信息
        Result<List<ProductDto>> productsByIds = storeProductFeignClient.getStoreProductsByIds(ids);
        log.info("products:{}", productsByIds.getState());
        if (productsByIds.getState().equals(ResultCode.SUCCESS.getCode())) {
            List<ProductDto> products = productsByIds.getData();
            Map<Integer, Integer> map = userCart.stream()
                    .collect(Collectors.toMap(CartItemEntity::getProductId, CartItemEntity::getQuantity));
            products.forEach(p -> {
                if (map.containsKey(p.getId())) {
                    Integer quantity = map.get(p.getId());
                    p.setQuantity(quantity);
                }
            });
            PageInfo<ProductDto> pageInfo = new PageInfo<>(products);
            return pageInfo;
        }
        return null;
    }

    @Override
    public boolean deleteByIds(Integer[] ids) {
        return cartDao.deleteByIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateQuantity(Integer id, Integer quantity) {
        // 数量不能为小于零
        if (quantity <= 0) {
            return false;
        }
        // 先查询商品库存，库存不足不准修改
        CartItemEntity cartItemEntity = cartDao.selectCartItemById(id);
        Result<ProductDto> productResult = storeProductFeignClient.getStoreProductById(cartItemEntity.getProductId());
        ProductDto product = productResult.getData();
        log.info("店铺商品数为：{}", product.getQuantity());
        if (product.getQuantity() < quantity) {
            return false;
        }
        cartItemEntity.setQuantity(quantity);
        cartItemEntity.setModifyTime(DateUtil.parse(DateUtil.now()));

        return cartDao.update(cartItemEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addItem(Integer userId, Integer productId, Integer quantity) {
        // 第一步，调取店铺商品接口
        Result<ProductDto> productResult = storeProductFeignClient.getStoreProductById(productId);
        ProductDto product = productResult.getData();

        log.info("商品信息：{}", product);
        // 如果商品数小于加入购物车数量，不能加入购物车
        if (product.getQuantity() < quantity) {
            return false;
        }
        int userCartId = cartDao.selectCartByUserId(userId);

        // 判断购物车是否已有商品，如果有，则数量添加，没有添加新的记录
        CartItemEntity existCartItemEntity = cartDao.selectCartItem(userCartId, productId);
        // 判断是否有记录
        if (Optional.ofNullable(existCartItemEntity).isPresent()) {
            existCartItemEntity.setQuantity(existCartItemEntity.getQuantity() + quantity);
            existCartItemEntity.setModifyTime(new Date());
            return cartDao.update(existCartItemEntity);
        }
        CartItemEntity cartItemEntity = new CartItemEntity();
        cartItemEntity.setCartId(userCartId);
        cartItemEntity.setShopId(product.getShopId());
        cartItemEntity.setProductId(productId);
        cartItemEntity.setPrice(new BigDecimal(product.getPrice()));
        cartItemEntity.setCreateTime(DateUtil.parse(DateUtil.now()));
        cartItemEntity.setQuantity(quantity);

        log.info("cartItem:{}", cartItemEntity);
        return cartDao.insert(cartItemEntity);
    }


    @Override
    public CartDto getSelectItem(Integer[] ids) {
        // 购物车的商品数据
        List<CartItemEntity> userCart = cartDao.selectCartItemByIds(ids);
        Integer[] productIds = userCart.stream().map(x -> x.getProductId()).toArray(Integer[]::new);

        // 从店铺获取商品信息
        Result<List<ProductDto>> productResult = storeProductFeignClient.getStoreProductsByIds(productIds);
        if (!productResult.getState().equals(ResultCode.SUCCESS.getCode())) {
            return null;
        }
        List<ProductDto> products = productResult.getData();
        Map<Integer, Integer> map = userCart.stream()
                .collect(Collectors.toMap(CartItemEntity::getProductId, CartItemEntity::getQuantity));
        int itemCount = 0;
        Long totalAmount = 0L;
        for (ProductDto product : products) {
            if (map.containsKey(product.getId())) {
                Integer quantity = map.get(product.getId());
                product.setQuantity(quantity);
            }
            itemCount += product.getQuantity();
            totalAmount += product.getPrice() * product.getQuantity();
        }

        log.info("商品信息：{}", products);
        CartDto cartDto = new CartDto();
        cartDto.setCartItem(products);
        cartDto.setItemCount(itemCount);
        cartDto.setTotalAmount(totalAmount);
        cartDto.setCreateTime(DateUtil.parse(DateUtil.now()));
        return cartDto;
    }


    @Override
    public boolean clearCart(Integer userId) {
        return cartDao.clearCart(userId);
    }
}





