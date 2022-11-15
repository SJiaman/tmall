package com.example.user.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Tian Changqing
 * @date 2022/11/14 17:45
 * @desc
 */
@Data
public class UserDTO {
    private Long id;

    /**
     * 手机号码
     */
    private String phone;


    /**
     * 昵称，默认是随机字符
     */
    private String nickName;

    /**
     * 用户头像
     */
    private String icon = "";
}
