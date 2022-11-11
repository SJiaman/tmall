package com.example.order.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.enums.ResultCode;
import com.example.common.utils.Result;
import com.example.order.converter.OrderConverter;
import com.example.order.dto.OrderDTO;
import com.example.order.dto.ProductDTO;
import com.example.order.entity.Order;
import com.example.order.feign.ShopFeignClient;
import com.example.order.mapper.OrderMapper;
import com.example.order.service.OrderService;
import com.example.order.vo.ProductVO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * 处理订单和订单数据的业务层实现类
 *
 * */
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;

    @Resource
    private ShopFeignClient shopFeignClient;

    /**
     * 从店铺创建订单都是一个商品直接下单
     * @param productDTO
     * @return
     */
    @Override
    public boolean createFromShop(ProductDTO productDTO) {
        // 从商铺获取商品信息
        Result<ProductVO> result = shopFeignClient.getProductByPid(productDTO.getSid(), productDTO.getId());
        ProductVO productVO = new ProductVO();
        if (result.getState().equals(ResultCode.SUCCESS.getCode())) {
            productVO = result.getData();
        }
        // 计算价格
        BigDecimal totalPrice = productVO.getPrice().multiply(new BigDecimal(productDTO.getNum()));
        Order order = new Order();

        int row = orderMapper.insert(order);
        if (row != 1) {
            return false;
        }
        return true;
    }

    @Override
    public  List<OrderDTO> orderList(Integer pageNum, Integer pageSize) {
        IPage<Order> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Order> wrapper = Wrappers.lambdaQuery(Order.class);
        IPage<Order> result = orderMapper.selectPage(page, wrapper);
        List<Order> records = result.getRecords();
        return OrderConverter.INSTANCE.doList2dtoList(records);
    }

    @Override
    public boolean edit(OrderDTO orderDTO) {
        Order order = OrderConverter.INSTANCE.dto2do(orderDTO);
        int row = orderMapper.updateById(order);
        if (row != 1) {
            return false;
        }
        return true;
    }

    @Override
    public boolean cancel(OrderDTO orderDTO) {
        Order order = orderMapper.selectById(orderDTO.getId());
        order.setIsDelete(1);
        int row = orderMapper.updateById(order);
        if (row != 1) {
            return false;
        }
        return true;
    }



//    @Transactional
//    @Override
//    public Order create(Integer aid, Integer[] cids, Integer uid, String username) {
//        // 创建当前时间对象
//        Date now = new Date();
//
//        // 根据cids查询所勾选的购物车列表中的数据
//        List<CartVO> carts = cartService.getVOByCids(uid, cids);
//
//        // 计算这些商品的总价
//        long totalPrice = 0;
//        for (CartVO cart : carts) {
//            totalPrice += cart.getRealPrice() * cart.getNum();
//        }
//
//        // 创建订单数据对象
//        Order order = new Order();
//        // 补全数据：uid
//        order.setUid(uid);
//        // 查询收货地址数据
//        Address address = addressService.getByAid(aid, uid);
//        // 补全数据：收货地址相关的6项
//        order.setRecvName(address.getName());
//        order.setRecvPhone(address.getPhone());
//        order.setRecvProvince(address.getProvinceName());
//        order.setRecvCity(address.getCityName());
//        order.setRecvArea(address.getAreaName());
//        order.setRecvAddress(address.getAddress());
//        // 补全数据：totalPrice
//        order.setTotalPrice(totalPrice);
//        // 补全数据：status
//        order.setStatus(0);
//        // 补全数据：下单时间
//        order.setOrderTime(now);
//        // 补全数据：日志
//        order.setCreatedUser(username);
//        order.setCreatedTime(now);
//        order.setModifiedUser(username);
//        order.setModifiedTime(now);
//        // 插入订单数据
//        Integer rows1 = orderMapper.insertOrder(order);
//        if (rows1 != 1) {
//            throw new InsertException("插入订单数据时出现未知错误，请联系系统管理员");
//        }
//
//        // 遍历carts，循环插入订单商品数据
//        for (CartVO cart : carts) {//批量插入，性能
//            // 创建订单商品数据
//            OrderItem item = new OrderItem();
//            // 补全数据：setOid(order.getOid())
//            item.setOid(order.getOid());
//            // 补全数据：pid, title, image, price, num
//            item.setPid(cart.getPid());
//            item.setTitle(cart.getTitle());
//            item.setImage(cart.getImage());
//            item.setPrice(cart.getRealPrice());
//            item.setNum(cart.getNum());
//            // 补全数据：4项日志
//            item.setCreatedUser(username);
//            item.setCreatedTime(now);
//            item.setModifiedUser(username);
//            item.setModifiedTime(now);
//            // 插入订单商品数据
//            Integer rows2 = orderMapper.insertOrderItem(item);
//            if (rows2 != 1) {
//                throw new InsertException("插入订单商品数据时出现未知错误，请联系系统管理员");
//            }
//        }
//
//        // 返回
//        return order;
//    }




}
