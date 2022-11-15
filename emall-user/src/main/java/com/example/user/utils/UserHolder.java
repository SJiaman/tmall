package com.example.user.utils;

import com.example.user.dto.UserDTO;

/**
 * @author Tian Changqing
 * @date 2022/11/15 9:47
 * @desc 本地线程类工具， 保存每个线程的用户信息，线程隔离
 */
public class UserHolder {
    private static final ThreadLocal<UserDTO> USER_THREAD_LOCAL = new ThreadLocal<>();

    public static void setUser(UserDTO user) {
        USER_THREAD_LOCAL.set(user);
    }

    public static UserDTO getUser() {
       return USER_THREAD_LOCAL.get();
    }

    public static void removeUser() {
        USER_THREAD_LOCAL.remove();
    }
}
