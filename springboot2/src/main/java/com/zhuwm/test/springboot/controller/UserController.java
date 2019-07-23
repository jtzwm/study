package com.zhuwm.test.springboot.controller;

import com.zhuwm.test.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//@RestController注解能够使项目支持Rest
@RestController

//表示该controller类下所有的方法都公用的一级上下文根
@RequestMapping(value = "/zhuwm")
public class UserController {

    @Autowired
    private UserService userService;

    //这里使用@RequestMapping注解表示该方法对应的二级上下文路径
    @RequestMapping("getUser/{id}")
    String getUserByGet(@PathVariable int id){
        return userService.Sel(id).toString();
    }


}
