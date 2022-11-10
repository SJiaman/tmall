package com.example.order.cart;


import com.example.cart.dao.CartDao;
import com.example.cart.entity.CartItemEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartTest {
    @Resource
    CartDao cartDao;

    @Test
    public void test01() {
        CartItemEntity cartItemEntity = new CartItemEntity();
        cartItemEntity = null;
        System.out.println(Optional.ofNullable(cartItemEntity).orElse(new CartItemEntity()));
    }


    @Test
    public void test02() {
        CartItemEntity cartItemEntity = cartDao.selectCartItem(1, 1005);

        if (Optional.ofNullable(cartItemEntity).isPresent()) {
            System.out.println("对象不为空");
        } else {

            System.out.println("对象为空，新创建一个对象为：" + new CartItemEntity());
        }


        //System.out.println(Optional.ofNullable(cartDao.selectCartItem(1, 1005)).orElse(new CartItemEntity()));
    }


    @Test
    public void test03() {
        // 1.不需要转换value的数据类型，直接可以相加
        Map<String, BigDecimal> map1 = new HashMap<>();
        map1.put("a", new BigDecimal("1"));
        map1.put("b", new BigDecimal("2"));
        Map<String, BigDecimal> map2 = new HashMap<>();
        map2.put("a", new BigDecimal("1"));
        map2.put("b", new BigDecimal("2"));
        // 将map1加到map2中
        map1.forEach((key, value) -> map2.merge(key, value, BigDecimal::add));

        System.out.println("map2：" + map2);

        // 2.需要转换value的数据类型，转换之后再相加
        Map<String, BigDecimal> map3 = new HashMap<>();
        map3.put("a", new BigDecimal("1"));
        map3.put("b", new BigDecimal("2"));

        Map<String, Integer> map4 = new HashMap<>();
        map4.put("a", new Integer(1));
        map4.put("b", new Integer(2));
        // 将map3加到map4中
//        map4.forEach((key, value) -> map3
//                .merge(key,value, (v1, v2) -> new BigDecimal(Integer.parseInt(v1.toString()))
//                        .add(new BigDecimal(Integer.parseInt(v2.toString()) ))));

        for (String key : map4.keySet()) {
            if (map3.containsKey(key)) {
                map3.put(key, map3.get(key).multiply(new BigDecimal(map4.get(key).toString())));
            }
        }
        System.out.println("map3：" + map3);

        BigDecimal amount = BigDecimal.ZERO;
        for (BigDecimal value : map3.values()) {
            amount = amount.add(value);
        }
        System.out.println("value:" + amount);
    }


}
