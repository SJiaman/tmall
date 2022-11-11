package com.example.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.utils.Result;
import com.example.user.dto.LoginFormDTO;
import com.example.user.entity.User;
import com.example.user.mapper.UserMapper;
import com.example.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author Tian Changqing
 * @date 2022/11/10 16:12
 * @desc
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService  {
    @Resource
    private


    /**
     * 发送验证码
     *
     * @param phone
     * @param session
     * @return
     */
    @Override
    public String sendCode(String phone, HttpSession session) {
        return null;
    }


}
