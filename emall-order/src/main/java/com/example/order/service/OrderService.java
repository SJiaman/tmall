package com.example.order.service;

import com.example.order.dto.OrderDTO;
import com.example.order.dto.ProductDTO;

import java.util.List;

/**
 * 处理订单和订单数据的业务层接口
 * @author tiancq
 */
public interface OrderService {

    /**
     * 创建订单
     * @param productDTO
     * @return
     */
    boolean createFromShop(ProductDTO productDTO);

    /**
     * 用户订单详情
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<OrderDTO> orderList(Integer pageNum, Integer pageSize);

    /**
     * 修改订单信息
     * @param orderDTO
     * @return
     */
    boolean edit(OrderDTO orderDTO);

    /**
     * 取消订单
     * @param orderDTO
     * @return
     */
    boolean cancel(OrderDTO orderDTO);
}
