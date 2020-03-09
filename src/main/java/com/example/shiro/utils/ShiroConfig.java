package com.example.shiro.utils;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 吴成卓
 * @version V1.0
 * @Project: shiro
 * @Package com.example.shiro.utils
 * @Description:
 * @date 2020/3/5 星期四 17:52
 */
@Configuration
public class ShiroConfig {
    /**
     * 自定义验证规则
     * @return
     */
    @Bean
    MyRealm myRealm() {
        return new MyRealm();
    }

    /**
     * 初始化securityManager，加入自定义验证规则
     * @return
     */
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm());
        return securityManager;
    }

    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager());
        //指定登录页面
        bean.setLoginUrl("/login");
        //路径拦截规则
        Map<String, String> map = new LinkedHashMap<>();
        //无需认证即可访问
        map.put("/login", "anon");
        //配置不会被拦截的链接,静态资源
        map.put("*/static/**","anon");
        //roles和prems如果括号中写多个角色或权限，那么得都满足才可以访问
        map.put("/del/**","roles[超级管理员,普通管理员]");
        map.put("/update/**","roles[普通管理员]");
        //需要认证才可访问
        map.put("/**", "authc");
        //退出
        map.put("/logout","logout");
        //设置未授权的页面
        bean.setUnauthorizedUrl("/400");
        bean.setFilterChainDefinitionMap(map);
        return bean;
    }
}
