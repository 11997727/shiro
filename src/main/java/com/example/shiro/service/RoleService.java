package com.example.shiro.service;

import java.util.List;

/**
 * @author 吴成卓
 * @version V1.0
 * @Project: shiro
 * @Package com.example.shiro.service
 * @Description:
 * @date 2020/3/9 星期一 11:21
 */
public interface RoleService {

    List<String> getRolesByUserName(String username);
}
