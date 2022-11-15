package com.example.user.controller;

import com.example.common.utils.Result;
import com.example.user.dto.LoginFormDTO;
import com.example.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * @author Tian Changqing
 * @date 2022/11/11 18:01
 * @desc
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @ApiOperation("发送验证码")
    @PostMapping("/sendCode")
    public Result sendCode(@RequestBody String phone) {
        String code = userService.sendCode(phone);
        return Result.success(code);
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public Result login(@RequestBody LoginFormDTO form) {
        String token = userService.login(form);
        return Result.success(token);
    }

    @ApiOperation("退出")
    @GetMapping("/logout")
    public Result logout(HttpServletRequest request) {
        userService.logout(request);
        return Result.success();
    }
}
