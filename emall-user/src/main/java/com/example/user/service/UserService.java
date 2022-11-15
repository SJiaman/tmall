package com.example.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.user.dto.LoginFormDTO;
import com.example.user.entity.User;

import javax.servlet.http.HttpServletRequest;


/**
 * @author Tian Changqing
 * @date 2022/11/10 16:08
 * @desc
 */
public interface UserService extends IService<User> {
    /**
     * 发送验证码
     * @param phone
     * @return
     */
    String sendCode(String phone);

    /**
     * 用户登录
     * @param form
     * @return
     */
    String login(LoginFormDTO form);

    /**
     * 用户退出
     */
    void logout(HttpServletRequest request);
}
