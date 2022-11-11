package com.example.store.controller;

import com.example.common.utils.Result;
import com.example.store.converter.ProductConverter;
import com.example.store.dto.ProductDTO;
import com.example.store.dto.ProductPageDTO;
import com.example.store.service.ProductService;
import com.example.store.vo.ProductPageVO;
import com.example.store.vo.ProductVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;


/**
 * @author qiaoyali
 */
@Slf4j
@RestController
@Api(tags = "商品仓库")
@RequestMapping("/store")
public class ProductController {
    @Resource
    private ProductService productService;


    @ApiOperation(value = "商品列表")
    @RequestMapping(value = "/findlist", method = RequestMethod.GET)
    public Result<ProductPageVO> findProductList(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        ProductPageDTO data = productService.findProductList(pageNum, pageSize);
        return Result.success(ProductConverter.INSTANCE.dtoPage2voPage(data));
    }

    @ApiOperation(value = "商品详情")
    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public Result<ProductVO> findProduct(@RequestParam Integer id) {
        // 调用业务对象执行获取数据
        ProductDTO data = productService.findProduct(id);
        // 返回成功和数据
        return Result.success(ProductConverter.INSTANCE.dto2vo(data));
    }


    @ApiOperation(value = "添加商品")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result insertProduct(@RequestBody ProductVO productVO) {
        productService.insertProduct(ProductConverter.INSTANCE.vo2dto(productVO));
        // 响应成功
        return Result.success();
    }


    @ApiOperation(value = "删除商品")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result deleteProduct(@RequestParam Integer id) {

        productService.deleteProduct(id);
        // 响应成功
        return Result.success();
    }


    @ApiOperation(value = "更新商品数量")
    @RequestMapping(value = "/update/num", method = RequestMethod.POST)
    public Result<Boolean> updateNum(@RequestParam Integer id, @RequestParam Integer num, @RequestParam Integer flag) {
        Boolean b = productService.updateQuantity(id, num, flag);
        if (!b) {
            return Result.fail(false);
        }
        return Result.success(true);
    }

    @ApiOperation(value = "商铺退货")
    @RequestMapping(value = "/productReturn", method = RequestMethod.POST)
    public Result<Boolean> productReturn(@RequestParam Integer id, @RequestParam Integer num) {
        return Result.success(true);
    }


    @ApiOperation(value = "更新商品")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result updateProduct(@RequestBody ProductVO product) {
        ProductDTO productDTO = ProductConverter.INSTANCE.vo2dto(product);
        productService.updateProduct(productDTO);
        // 响应成功
        return Result.success();
    }


    //    /**在更新之前需要设计一个一打开网页就发送用户当前信息的查询*/
//    @RequestMapping("getProMessage")
//    public Result<Product> getProMessage(HttpSession session){
//        Product pro= productService.findProduct(getUidFromSession(session));
//        return Result.success(data);
//    }
}
