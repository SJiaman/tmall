package com.example.user.convert;

import com.example.user.dto.UserDTO;
import com.example.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Tian Changqing
 * @date 2022/11/14 17:45
 * @desc
 */
@Mapper
public interface UserConverter {
    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    UserDTO po2dto(User user);
}
