package com.example.user.controller;

import com.example.common.utils.Result;
import com.example.user.dto.LoginFormDTO;
import com.example.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

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
    public Result sendCode(@RequestBody String phone, HttpSession session) {
        String code = userService.sendCode(phone, session);
        return Result.success(code);
    }
}
