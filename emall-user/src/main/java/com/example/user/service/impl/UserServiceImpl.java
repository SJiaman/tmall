package com.example.user.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.constants.Constants;
import com.example.common.enums.ErrorMsgEnum;
import com.example.common.exception.BusinessRuntimeException;
import com.example.common.utils.RegexUtils;
import com.example.user.convert.UserConverter;
import com.example.user.dto.LoginFormDTO;
import com.example.user.dto.UserDTO;
import com.example.user.entity.User;
import com.example.user.mapper.UserMapper;
import com.example.user.service.UserService;
import com.example.user.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
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
     * @return
     */
    @Override
    public String sendCode(String phone) {
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

    /**
     * 用户登录
     *
     * @param form
     * @return
     */
    @Override
    public String login(LoginFormDTO form) {
        // 校验格式
        String phone = form.getPhone();
        // 1.验证手机格式
        if (RegexUtils.isPhoneInvalid(phone)) {
            throw new BusinessRuntimeException(ErrorMsgEnum.UserError.PHONE_WRONG_ERROR.getErrorCode(),
                    ErrorMsgEnum.UserError.PHONE_WRONG_ERROR.getErrorMsg());
        }
        
        // 2.验证验证码
        String code = form.getCode();
        String cacheCode = stringRedisTemplate.opsForValue().get(Constants.LOGIN_CODE_KEY + phone);
        if (cacheCode == null || !code.equals(cacheCode)) {
            throw new BusinessRuntimeException("3002", "验证码错误");
        }

        // 一致 查询手机号
        User user = lambdaQuery().eq(User::getPhone, phone).one();
        if (user == null) {
             user = createUserByPhone(phone);
        }

        // 保存用户信息到redis
        String token = UUID.randomUUID().toString(true);
        UserDTO userDTO = UserConverter.INSTANCE.po2dto(user);

        // 转化为map存储
        Map<String, Object> userMap = BeanUtil.beanToMap(userDTO, new HashMap<>(), CopyOptions.create().setIgnoreNullValue(true)
                .setFieldValueEditor((fieldName, fieldValue) -> fieldValue.toString()));

        // 创建key
        String tokenKey = Constants.LOGIN_USER_KEY + token;
        // 保存
        stringRedisTemplate.opsForHash().putAll(tokenKey, userMap);
        stringRedisTemplate.expire(tokenKey, Constants.LOGIN_USER_TTL, TimeUnit.MINUTES);
        return token;
    }

    /**
     * 用户退出
     */
    @Override
    public void logout(HttpServletRequest request) {
        String token = request.getHeader("authorization");
        stringRedisTemplate.delete(Constants.LOGIN_USER_KEY + token);
    }

    private User createUserByPhone(String phone) {
        User user = new User();
        user.setPhone(phone);
        user.setNickName(Constants.USER_NICK_NAME_PREFIX + RandomUtil.randomString(6));
        save(user);
        return user;
    }


}
