package com.example.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Tian Changqing
 * @date 2022/11/10 16:13
 * @desc
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
