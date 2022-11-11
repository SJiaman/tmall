package com.example.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.user.entity.User;

import javax.servlet.http.HttpSession;

/**
 * @author Tian Changqing
 * @date 2022/11/10 16:08
 * @desc
 */
public interface UserService extends IService<User> {
    /**
     * 发送验证码
     * @param phone
     * @param session
     * @return
     */
    String sendCode(String phone, HttpSession session);
}
