package com.example.shiro.mapper;

import com.example.shiro.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 吴成卓
 * @version V1.0
 * @Project: shiro
 * @Package com.example.shiro.mapper
 * @Description:
 * @date 2020/3/9 星期一 10:43
 */
@Mapper
public interface UserMapper {
    User selectUserByUserName(@Param("username") String username);
}
