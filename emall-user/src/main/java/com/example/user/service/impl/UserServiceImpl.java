package com.example.user.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.constants.Constants;
import com.example.common.enums.ErrorMsgEnum;
import com.example.common.exception.BusinessRuntimeException;
import com.example.common.utils.RegexUtils;
import com.example.common.utils.Result;
import com.example.user.dto.LoginFormDTO;
import com.example.user.entity.User;
import com.example.user.mapper.UserMapper;
import com.example.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;

/**
 * @author Tian Changqing
 * @date 2022/11/10 16:12
 * @desc
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService  {
    @Resource
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 发送验证码
     *
     * @param phone
     * @param session
     * @return
     */
    @Override
    public String sendCode(String phone, HttpSession session) {
        // 1.验证手机格式
        if (RegexUtils.isPhoneInvalid(phone)) {
            throw new BusinessRuntimeException(ErrorMsgEnum.UserError.PHONE_WRONG_ERROR.getErrorCode(),
                    ErrorMsgEnum.UserError.PHONE_WRONG_ERROR.getErrorMsg());
        }
        // 2. 生成验证码
        String code = RandomUtil.randomNumbers(6);

        // 3.保存验证码到 session
        stringRedisTemplate.opsForValue().set(Constants.LOGIN_CODE_KEY + phone, code, Constants.LOGIN_CODE_TTL, TimeUnit.MINUTES);

        // 4.发送验证码
        log.info("code:{}", code);

        return code;
    }


}
