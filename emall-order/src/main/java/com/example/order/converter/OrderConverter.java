package com.example.order.converter;

import com.example.order.dto.OrderDTO;
import com.example.order.dto.ProductDTO;
import com.example.order.entity.Order;
import com.example.order.vo.OrderVO;
import com.example.order.vo.ProductVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author tianchangqing
 * @date 2022/9/23 9:27
 * @desc
 */
@Mapper
public interface OrderConverter {
    OrderConverter INSTANCE = Mappers.getMapper(OrderConverter.class);

    Order dto2do(OrderDTO orderDTO);

    OrderDTO do2dto(Order order);

    List<OrderDTO> doList2dtoList(List<Order> orders);

    List<OrderVO> dtoList2voList(List<OrderDTO> orders);

    OrderDTO vo2dto(OrderVO orderVO);

    ProductVO productDTO2VO(ProductDTO productDTO);

    ProductDTO productVO2DTO(ProductVO productVO);
}
