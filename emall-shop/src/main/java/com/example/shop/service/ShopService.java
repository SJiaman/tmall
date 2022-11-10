package com.example.shop.service;

import com.example.shop.vo.ProductVO;
import com.example.shop.vo.ShopProductVO;

/**
 * @author tianchangqing
 * @date 2022/8/31 16:09
 */
public interface ShopService {
   /**
    * 进货
    * @param shopId
    * @param id
    * @param num
    * @return
    */
   Boolean purchase(Integer shopId, Integer id, Integer num);

   /**
    * 退货
    * @param shopId
    * @param id
    * @param num
    * @return
    */
   Boolean salesReturn(Integer shopId, Integer id, Integer num);


   /**
    * 查询店铺商品
    * @param sid
    * @return
    */
   ShopProductVO getProductBySid(Integer sid, Integer pageNum, Integer pageSize);


   /**
    * 查询店铺单个商品
    * @param sid
    * @param pid
    * @return
    */
   ProductVO getProductByPid(Integer sid, Integer pid);


   /**
    * 更改商品数量
    * @param pid
    * @param count
    */
   void modifiedProductNum(Integer id, Integer count);
}
