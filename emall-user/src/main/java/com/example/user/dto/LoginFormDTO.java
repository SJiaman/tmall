package com.example.user.dto;

import lombok.Data;

/**
 * @author Tian Changqing
 * @date 2022/11/10 16:10
 * @desc
 */
@Data
public class LoginFormDTO {
    private String phone;
    private String code;
    private String password;
}