package com.example.shiro.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Project: shiro
 * @Package com.example.shiro.mapper
 * @Description: ${todo}
 * @author 吴成卓
 * @date 2020/3/9 星期一 10:41
 * @version V1.0 
 *
 */
@Mapper
public interface RoleMapper {

    List<String> getRolesByUserName(@Param("username") String username);
}