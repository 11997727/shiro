package com.example.shiro;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiroApplicationTests {

    //Demo1
    IniRealm iniRealm=new IniRealm("classpath:user.ini");
    @Test
    public void testIni(){
        DefaultSecurityManager securityManager=new DefaultSecurityManager();
        securityManager.setRealm(iniRealm);

        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token=new UsernamePasswordToken("zhuchengbin","123");
        subject.login(token);

        System.out.println("result>>>"+subject.isAuthenticated());
        subject.checkRole("zuzhang");
        subject.checkRole("zuyuan");
        subject.checkPermission("user:delete");
    }

    //Demo2
    DruidDataSource dataSource=new DruidDataSource();
    {
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://122.51.34.215:3308/aviation?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT");
        dataSource.setUsername("root");
        dataSource.setPassword("654321");
    }
    @Test
    public void testJDBC(){
        JdbcRealm jdbcRealm=new JdbcRealm();
        jdbcRealm.setDataSource(dataSource);
        jdbcRealm.setPermissionsLookupEnabled(true);

        String sql="select user_password from user where user_phone=?";
        jdbcRealm.setAuthenticationQuery(sql);

        DefaultSecurityManager securityManager=new DefaultSecurityManager();
        securityManager.setRealm(jdbcRealm);

        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token=new UsernamePasswordToken("19919990911","123456");
        subject.login(token);
        System.out.println("result>>>"+subject.isAuthenticated());
    }
}
