package com.zhuwm.test.springboot.service;

import com.zhuwm.test.springboot.entity.User;
import com.zhuwm.test.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public User Sel(int id) {
        return userMapper.Sel(id);
    }
}
