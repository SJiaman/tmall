package com.example.shop.service.impl;

import com.example.common.enums.ErrorMsgEnum;
import com.example.common.exception.BusinessRuntimeException;
import com.example.common.utils.JsonResult;
import com.example.shop.dto.ProductDTO;
import com.example.shop.entity.ShopProduct;
import com.example.shop.entity.ShopProductExample;
import com.example.shop.feign.StoreFeignClient;
import com.example.shop.mapper.ShopProductMapper;
import com.example.shop.service.ShopService;
import com.example.shop.vo.ProductVO;
import com.example.shop.vo.ShopProductVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.page.PageMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tianchangqing
 * @date 2022/8/31 16:09
 */
@Service
public class ShopServiceImpl implements ShopService {
    @Resource
    ShopProductMapper shopProductMapper;

    @Resource
    StoreFeignClient storeFeignClient;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean purchase(Integer shopId, Integer productId, Integer num) {
        // 商品库存查询商品状态
        JsonResult<ProductDTO> result = storeFeignClient.findProduct(productId);
        ProductDTO productDTO = result.getData();

        if (productDTO.getNum() < num) {
            return false;
        }
        // 状态为真入商铺商品库
        ShopProduct row = new ShopProduct();
        row.setPid(productDTO.getId());
        row.setNum(num);
        row.setSid(shopId);
        row.setName(productDTO.getItemType());
        row.setPrice(productDTO.getPrice().multiply(new BigDecimal("1.5")));
        row.setSellPoint(productDTO.getTitle());
        row.setStatus(1);
        row.setPriority(1);
        row.setIsDelete(Byte.parseByte("0"));
        int insert = shopProductMapper.insert(row);

        if (insert != 1) {
            return false;
        }
        // 更新商品仓库数量
        storeFeignClient.updateNum(productId, num, 1);
        return true;
    }

    @Override
    public Boolean salesReturn(Integer shopId, Integer id, Integer num) {

        storeFeignClient.updateNum(id, num, 0);
        return false;
    }

    /**
     * 通过商铺id显示商品列表
     */
    @Override
    public ShopProductVO getProductBySid(Integer sid,Integer pageNum, Integer pageSize) {
        Page<Object> page = PageMethod.startPage(pageNum, pageSize);
        ShopProductExample example = new ShopProductExample();
        example.createCriteria().andSidEqualTo(sid);
        List<ShopProduct> list = shopProductMapper.selectByExample(example);
        if (list.isEmpty()) {
            throw new BusinessRuntimeException(ErrorMsgEnum.ShopError.NOT_FOUND_PRODUCT_ERROR.getErrorCode(),
                    ErrorMsgEnum.ShopError.DELETE_PRODUCT_ERROR.getErrorMsg());
        }

        List<ProductVO> products = list.stream().map(p -> {
            ProductVO productVO = new ProductVO();
            productVO.setId(p.getId());
            productVO.setSid(p.getSid());
            productVO.setPid(p.getPid());
            productVO.setPrice(p.getPrice());
            productVO.setName(p.getName());
            productVO.setQuantity(p.getNum());
            productVO.setSellPoint(p.getSellPoint());
            return productVO;
        }).collect(Collectors.toList());

        ShopProductVO shopProducts = new ShopProductVO();
        shopProducts.setProducts(products);
        shopProducts.setPageNum(page.getPageNum());
        shopProducts.setPageSize(page.getPageSize());
        shopProducts.setTotal(page.getTotal());
        return shopProducts;
    }

    /**
     * 通过商品id显示商品信息,
     * 返回给购物车和订单title，num，price，sell_point
     */
    @Override
    public ProductVO getProductByPid(Integer sid, Integer pid) {
        ShopProductExample example = new ShopProductExample();
        example.createCriteria().andSidEqualTo(sid).andPidEqualTo(pid);
        List<ShopProduct> productList = shopProductMapper.selectByExample(example);
        ShopProduct result = productList.get(0);
        ProductVO productVO = new ProductVO();
        productVO.setId(result.getId());
        productVO.setPid(result.getPid());
        productVO.setSid(result.getSid());
        productVO.setName(result.getName());
        productVO.setQuantity(result.getNum());
        productVO.setPrice(result.getPrice());
        productVO.setSellPoint(result.getSellPoint());
        return productVO;
    }

    /**
     * 通过商品id先去查询商品数量，判断库存够不够,
     * 不够返回信息“商品数量不够”
     * 否则执行库存修改操作
     */
    @Override
    public void modifiedProductNum(Integer id, Integer count) {
        ShopProduct row = shopProductMapper.selectByPrimaryKey(id);
//        if (row.getNum() - count < 0) {
//            throw new BusinessRuntimeException();
//        }
        ShopProductExample example = new ShopProductExample();
        example.createCriteria().andIdEqualTo(id).andNumEqualTo(row.getNum() - count);
        int i = shopProductMapper.updateByExample(row, example);
//        if (i != 1) {
//            throw new BusinessRuntimeException();
//        }
    }
}
