package com.open.web.service;

import com.open.web.bean.User;
import com.open.web.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> getAllUser(){
        return userMapper.getAllUser();
    }


    public User getUserByUsername(String username){
        return userMapper.getUserByUsername(username);
    }
}
