package com.xxcw.allowance.controller;

import com.xxcw.allowance.bean.User;
import com.xxcw.allowance.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private UserMapper userMapper;

    @CrossOrigin
    @RequestMapping("/login")
    public String userLogin(@RequestBody User user) {
        System.out.println("User : " + user);
        String str = "error";
        int count = userMapper.getUserByMassage(user.getUsername(), user.getPassword());
        if (count > 0) {
            str = "ok";
        }
        return str;
    }
}
