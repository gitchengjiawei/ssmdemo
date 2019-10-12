package com.open.web.controller;

import com.open.web.bean.User;
import com.open.web.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v2/user/")
@Log4j2
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("showAllUser")
    public List<User> showAllUser(){
        return userService.getAllUser();
    }
}
