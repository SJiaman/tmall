package com.example.store.converter;

import com.example.store.dto.ProductDTO;
import com.example.store.dto.ProductPageDTO;
import com.example.store.entity.Product;
import com.example.store.vo.ProductPageVO;
import com.example.store.vo.ProductVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author tianchangqing
 * @date 2022/9/22 19:09
 * @desc
 */
@Mapper
public interface ProductConverter {
    ProductConverter INSTANCE = Mappers.getMapper(ProductConverter.class);

    ProductDTO vo2dto(ProductVO productVO);

    ProductVO dto2vo(ProductDTO productDTO);

    Product dto2do(ProductDTO productDTO);

    ProductDTO do2dto(Product product);

    List<ProductDTO> doList2dtoList(List<Product> products);

    List<ProductVO> dtoList2dvoList(List<ProductDTO> products);

    ProductPageVO dtoPage2voPage(ProductPageDTO productListDTO);
}
