package com.myshiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by lenovo on 2019/6/5.
 */
@Controller
public class TestController {

    @RequestMapping("/logOut")
    public String logOut(){
        SecurityUtils.getSubject().logout();
        return "toLogin";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    /**
     * 没有权限
     * @return
     */
    @RequestMapping("/noAuth")
    public String noAuth(){
        return "noAuth";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "toLogin";
    }

    @RequestMapping(value = "/submitLogin",method = RequestMethod.POST)
    public String submitLogin(String username, String password, Model model){
        System.out.println("userName:"+username+" password:"+password);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try{
            subject.login(token);
            model.addAttribute("msg","");
            return "index";
        }catch (Exception e){
            throw new AuthenticationException();
//            e.printStackTrace();
//            model.addAttribute("msg","用户名或密码错误");
//            return "toLogin";
        }
    }
}
