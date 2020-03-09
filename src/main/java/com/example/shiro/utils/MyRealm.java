package com.example.shiro.utils;

import com.example.shiro.entity.User;
import com.example.shiro.service.RoleService;
import com.example.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 吴成卓
 * @version V1.0
 * @Project: shiro
 * @Package com.example.shiro.utils
 * @Description:
 * @date 2020/3/5 星期四 17:47
 */
public class MyRealm extends AuthorizingRealm {
    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("进入授权操作");
        String username=(String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        User user = userService.getUser(username);
        List<String>rolesList= roleService.getRolesByUserName(username);
        Set<String> roles=new HashSet<>();
        for(String string:rolesList){
            System.out.println(username+">>>"+string);
            roles.add(string);
        }
         info.setRoles(roles);
        return  info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户输入的账号
        String username = (String) authenticationToken.getPrincipal();
        //根据用户名查询数据库的用户信息
        User user = userService.getUser(username);
        if (user==null||!user.getUsername().equals(username)) {
            System.out.println("账户不存在");
            throw new UnknownAccountException("账户不存在!");
        }
        if(user.getStatus().toString().equals("1")){
            System.out.println("账户被锁定");
           throw new DisabledAccountException("账户被锁定");
        }
        return new SimpleAuthenticationInfo(username, user.getPassword(), getName());
    }


}
