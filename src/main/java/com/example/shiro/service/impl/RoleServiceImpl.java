package com.example.shiro.service.impl;

import com.example.shiro.mapper.RoleMapper;
import com.example.shiro.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 吴成卓
 * @version V1.0
 * @Project: shiro
 * @Package com.example.shiro.service.impl
 * @Description:
 * @date 2020/3/9 星期一 11:21
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<String> getRolesByUserName(String username) {
        return roleMapper.getRolesByUserName(username);
    }
}
