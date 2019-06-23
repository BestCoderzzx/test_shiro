package com.myshiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lenovo on 2019/6/5.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 添加用户
     * @return
     */
    @RequestMapping("/add")
    public String addUser(){
        return "/user/add";
    }

    /**
     * 查询用户
     * @return
     */
    @RequestMapping("/query")
    public String queryUser(){
        return "/user/query";
    }


}
