package com.lzz.reggie.controller;


import com.lzz.reggie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户信息 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2023-02-22
 */
@RestController
@RequestMapping("/reggie/user")
public class UserController {

    @Autowired
    private UserService userService;



}

