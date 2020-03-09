package com.example.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author 吴成卓
 * @version V1.0
 * @Project: shiro
 * @Package com.example.shiro.controller
 * @Description:
 * @date 2020/3/5 星期四 18:25
 */
@Controller
public class TestController {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, RedirectAttributes ra, Model model) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            ra.addFlashAttribute("username",username);
            return "redirect:index";
        }catch (DisabledAccountException e){
            model.addAttribute("msg","账户被锁定，请联系超级管理员");
            token.clear();
            return "login";
        }catch (AuthenticationException e){
            model.addAttribute("msg","账号或密码错误");
            token.clear();
            return "login";
        }
    }

    /**
     * 退出
     * @return
     */
    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }

    /**
     * 登录成功初始化内容
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        System.out.println("初始化");
        return "index";
    }

    /**
     * 删除权限
     * @return
     */
    @RequestMapping("/del/delById")
    public String del(){
       return "del" ;
    }

    /**
     * 修改权限
     * @return
     */
    @RequestMapping("/update/updateById")
    public String update(){
        return "update" ;
    }

    @RequestMapping("/400")
    public String error(){
        return "400";
    }
}
