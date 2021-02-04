package com.xxcw.allowance.controller;

import com.alibaba.fastjson.JSON;
import com.xxcw.allowance.bean.User;
import com.xxcw.allowance.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private UserMapper userMapper;

    @CrossOrigin
    @RequestMapping("/login")
    public String userLogin(@RequestBody User user) {
        System.out.println("User : " + user);
        String flag = "error";
        User loginUser = userMapper.getUserByMassage(user.getUsername(), user.getPassword());
        System.out.println("loginUser="+loginUser);
        if (loginUser !=null ) {
            flag = "ok";
        }
        Map<String,Object> map = new HashMap<>();
        map.put("flag",flag);
        map.put("user",loginUser);
        String json = JSON.toJSONString(map);
        return json;
    }
}
