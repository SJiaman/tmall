package com.example.store.service.impl;

import com.example.common.constants.Constants;
import com.example.common.enums.ErrorMsgEnum;
import com.example.common.exception.BusinessRuntimeException;
import com.example.store.converter.ProductConverter;
import com.example.store.dto.ProductDTO;
import com.example.store.dto.ProductPageDTO;
import com.example.store.entity.Product;
import com.example.store.mapper.ProductMapper;
import com.example.store.service.ProductService;
import com.github.pagehelper.Page;
import com.github.pagehelper.page.PageMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
 * @author qiaoyali
 */
@Service
@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductMapper productMapper;


    @Override
    public ProductPageDTO findProductList(Integer pageNum, Integer pageSize) {
        Page<Object> page = PageMethod.startPage(pageNum, pageSize);
        List<Product> list = productMapper.findProductList();
        List<ProductDTO> productDTOS = ProductConverter.INSTANCE.doList2dtoList(list);
        ProductPageDTO productListDTO = new ProductPageDTO();
        productListDTO.setProducts(productDTOS);
        productListDTO.setPageNum(page.getPageNum());
        productListDTO.setPageSize(page.getPageSize());
        productListDTO.setTotal(page.getTotal());
        return productListDTO;
    }

    /**
     * 查询商品详细信息，仓库管理员删除的也可以查询到
     */
    @Override
    public ProductDTO findProduct(Integer id) {
        Product product = productMapper.findProduct(id);
        if (product == null) {
            throw new BusinessRuntimeException(ErrorMsgEnum.StoreError.NOT_FOUND_PRODUCT_ERROR.getErrorCode(),
                    ErrorMsgEnum.StoreError.NOT_FOUND_PRODUCT_ERROR.getErrorMsg());
        }
        //ProductDTO productDTO = new ProductDTO();
//        productDTO.setId(product.getId());
//        productDTO.setCategoryId(product.getCategoryId());
//        productDTO.setName(product.getName());
//        productDTO.setItemType(product.getItemType());
//        productDTO.setPrice(product.getPrice());
//        productDTO.setNum(product.getNum());

        return ProductConverter.INSTANCE.do2dto(product);
    }

    /**
     * 添加商品,这里应该创建一个增加商品库存的操作流程
     */
    @Override
    public Boolean insertProduct(ProductDTO productVO) {
        // 首先确定是否是表中有的id商品
        Product result = productMapper.findProduct(productVO.getId());
        Product product = new Product();
        Date now = new Date();
        if (result != null) {
            // 表中有的商品，修改其num值，若原status=0修改为1
            product.setNum(product.getNum() + productVO.getNum());
        }
        // 如果是没有的商品就添加进去
        product.setName(productVO.getName());
        product.setCategoryId(productVO.getCategoryId());
        product.setItemType(productVO.getItemType());
        product.setPrice(productVO.getPrice());
        product.setNum(productVO.getNum());
        product.setPriority(productVO.getPriority());

        Integer rows = productMapper.insertProduct(product);
        if (rows != 1) {
            throw new BusinessRuntimeException(ErrorMsgEnum.StoreError.INSERT_PRODUCT_ERROR.getErrorCode(),
                    ErrorMsgEnum.StoreError.INSERT_PRODUCT_ERROR.getErrorMsg());
        }
        return true;
    }

    /**
     * 删除商品，即将其is_delete=1
     */
    @Override
    public Boolean deleteProduct(Integer id) {
        Product del = productMapper.findProduct(id);
        if (del == null) {
            throw new BusinessRuntimeException(ErrorMsgEnum.StoreError.NOT_FOUND_PRODUCT_ERROR.getErrorCode(),
                    ErrorMsgEnum.StoreError.NOT_FOUND_PRODUCT_ERROR.getErrorMsg());
        }
        del.setIsDelete(1);

        Integer rows = productMapper.updateProduct(del);
        if (rows != 1) {
            throw new BusinessRuntimeException(ErrorMsgEnum.StoreError.DELETE_PRODUCT_ERROR.getErrorCode(),
                    ErrorMsgEnum.StoreError.DELETE_PRODUCT_ERROR.getErrorMsg());
        }
        return true;
    }

    /**
     * 更新商品信息----这里是仓库管理员修改商品信息的方法
     */
    @Override
    public Boolean updateProduct(ProductDTO product) {
        Product result = productMapper.findProduct(product.getId());
        if (result == null) {
            throw new BusinessRuntimeException(ErrorMsgEnum.StoreError.NOT_FOUND_PRODUCT_ERROR.getErrorCode(),
                    ErrorMsgEnum.StoreError.NOT_FOUND_PRODUCT_ERROR.getErrorMsg());
        }
        // 商品没有库存后，需要修改其状态为下架
        if (result.getNum() <= 0) {
            result.setStatus(0);
        }

        result = ProductConverter.INSTANCE.dto2do(product);
        Integer rows = productMapper.updateProduct(result);
        if (rows != 1) {
            throw new BusinessRuntimeException(ErrorMsgEnum.StoreError.UPDATE_PRODUCT_ERROR.getErrorCode(),
                    ErrorMsgEnum.StoreError.UPDATE_PRODUCT_ERROR.getErrorMsg());
        }
        return true;
    }


    /**
     * 店铺需要调用的扣减接口,并传入修改店铺的名字
     * 采用乐观锁进行多个访问处理
     */

    private void updateNum(Integer id, Integer num, Boolean flag) {
        int updateCount = 0;
        while (true) {
            updateCount++;
            Product result = productMapper.findProduct(id);
            if (result == null) {
                throw new BusinessRuntimeException(ErrorMsgEnum.StoreError.NOT_FOUND_PRODUCT_ERROR.getErrorCode(),
                        ErrorMsgEnum.StoreError.NOT_FOUND_PRODUCT_ERROR.getErrorMsg());
            }

            // 商品没有库存后，需要修改其状态为下架,即修改商品状态这里只修改num
            if (result.getNum() == null) {
                throw new BusinessRuntimeException(ErrorMsgEnum.StoreError.UPDATE_PRODUCT_ERROR.getErrorCode(),
                        ErrorMsgEnum.StoreError.UPDATE_PRODUCT_ERROR.getErrorMsg());
            }


            if (flag.equals(1)) {
                Integer newNum = result.getNum() - num;
                if (newNum < 0) {
                    throw new BusinessRuntimeException(ErrorMsgEnum.StoreError.UPDATE_PRODUCT_ERROR.getErrorCode(),
                            ErrorMsgEnum.StoreError.UPDATE_PRODUCT_ERROR.getErrorMsg());
                } else if (newNum == 0) {
                    result.setStatus(0);
                }
                result.setNum(newNum);
            } else {
                Integer newNum = result.getNum() + num;
                if (result.getNum() == 0 && newNum > 0) {
                    result.setStatus(1);
                }
                result.setNum(newNum);
            }

            /**与此同时更新修改人与修改时间*/
            Date now = new Date();
            result.setModifiedTime(now);

            Integer rows = productMapper.updateProduct(result);
            if (rows == 1 || updateCount > 100) {
                return;
            }
        }
    }

    @Override
    public Boolean updateQuantity(Integer id, Integer num, Integer flag) {
        // 修改仓库商品数量 1.减少 2. 添加
        // 1.先查询商品
        Product product = productMapper.findProduct(id);
        if (product == null) {
            throw new BusinessRuntimeException(ErrorMsgEnum.StoreError.NOT_FOUND_PRODUCT_ERROR.getErrorCode(),
                    ErrorMsgEnum.StoreError.NOT_FOUND_PRODUCT_ERROR.getErrorMsg());
        }
        synchronized (product) {
            if (flag.equals(Constants.PURCHASE)) {
                // 商品数量减少
                int newNum = product.getNum() - num;
                if (newNum < 0) {
                    throw new BusinessRuntimeException(ErrorMsgEnum.StoreError.UPDATE_PRODUCT_ERROR.getErrorCode(),
                            ErrorMsgEnum.StoreError.UPDATE_PRODUCT_ERROR.getErrorMsg());
                }
                product.setModifiedTime(new Date());
                product.setNum(newNum);
                if (newNum == 0) {
                    product.setStatus(Constants.PRODUCT_SOLD_OUT);
                }
            } else if (flag.equals(Constants.RETURN_PRODUCT)) {
                if (product.getStatus().equals(Constants.PRODUCT_SOLD_OUT)) {
                    product.setStatus(Constants.PRODUCT_ON_SALE);
                }
                product.setNum(num);
                product.setModifiedTime(new Date());
            }
            Integer row = productMapper.updateProduct(product);
            if (row == 0) {
                throw new BusinessRuntimeException(ErrorMsgEnum.StoreError.UPDATE_PRODUCT_ERROR.getErrorCode(),
                        ErrorMsgEnum.StoreError.UPDATE_PRODUCT_ERROR.getErrorMsg());
            }
        }
        return true;
    }
}
