package com.example.shop.controller;

import com.example.common.utils.Result;
import com.example.shop.service.ShopService;
import com.example.shop.vo.ProductVO;
import com.example.shop.vo.ShopProductVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 控制层对应的UserController类,依赖于业务层的接口
 * @author yuefei
 */
@Slf4j
@RestController
@Api(tags = "商品管理")
@RequestMapping("/shop")
public class ShopController {

    @Resource
    private ShopService shopService;

    @ApiOperation(value = "商铺商品列表")
    @GetMapping("/getAllProduct")
    public Result<ShopProductVO> getProductBySid(@RequestParam Integer sid, @RequestParam Integer pageNum,
                                                     @RequestParam Integer pageSize) {
        //调用业务层接口方法
        ShopProductVO list = shopService.getProductBySid(sid, pageNum, pageSize);
        //创建响应结果对象即Result对象
        return Result.success(list);
    }

    @ApiOperation(value = "商铺商品")
    @GetMapping("/getSingleProduct")
    public Result<ProductVO> getProductByPid(@RequestParam Integer sid, @RequestParam Integer pid) {
        ProductVO data = shopService.getProductByPid(sid, pid);
        return Result.success(data);
    }

    @ApiOperation(value = "更新商品商品数量")
    @PutMapping("/updateProductNum")
    public Result modifiedProductNum(@RequestParam Integer pid, @RequestParam Integer count) {
        shopService.modifiedProductNum(pid, count);
        return Result.success();
    }

    @ApiOperation(value = "商铺进货")
    @PostMapping("/purchase")
    public Result<Boolean> purchase(@RequestParam Integer shopId, @RequestParam Integer pid, @RequestParam Integer num) {
        Boolean flag = shopService.purchase(shopId, pid, num);
        if (!flag) {
            return Result.fail();
        }
        return Result.success(true);
    }


    @ApiOperation(value = "商铺退货")
    @PostMapping("/salesReturn")
    public Result<Boolean> salesReturn(@RequestParam Integer shopId, @RequestParam Integer pid, @RequestParam Integer num) {
        return Result.success(true);
    }


    @ApiOperation(value = "商铺商品上架")
    @PostMapping("/putaway")
    public Result<Boolean> putaway(@RequestParam Integer pid, @RequestParam Integer num) {
        return Result.success(true);
    }


    @ApiOperation(value = "商铺商品下架")
    @PostMapping("/soldOut")
    public Result<Boolean> soldOut(@RequestParam Integer pid, @RequestParam Integer num) {
        return Result.success(true);
    }

}

