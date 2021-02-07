package com.xxcw.allowance.service;

import com.xxcw.allowance.bean.QueryInfo;
import com.xxcw.allowance.bean.User;
import com.xxcw.allowance.common.base.BaseApiService;
import com.xxcw.allowance.common.base.BaseResponse;
import com.xxcw.allowance.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@Slf4j
public class UserService extends BaseApiService {
    @Autowired
    private UserMapper userMapper;

    //获取所有用户
    @GetMapping("/getAllUser")
    public BaseResponse getAllUser(QueryInfo queryInfo) {
        log.info("QueryInfo:"+queryInfo);
        int total = userMapper.getUserCounts("%" + queryInfo.getQuery() + "%");
        int pageStart = (queryInfo.getPageNum() - 1) * queryInfo.getPageSize();
        List<User> users = userMapper.getAllUser("%" + queryInfo.getQuery() + "%", pageStart, queryInfo.getPageSize());
        HashMap<String, Object> res = new HashMap<>();
        res.put("total", total);
        res.put("data", users);
        return setResultSuccess(res);
    }

    //根据id获取用户
    @GetMapping("/getUpdateUser")
    public BaseResponse getUpdateUser(@RequestParam("id") int id) {
        log.info("id============="+userMapper.getUpdateUser(id));
        return setResultSuccess(userMapper.getUpdateUser(id));
    }

    //更改用户状态
    @PutMapping("/updateState")
    public BaseResponse updateState(@RequestParam("id") int id,@RequestParam("state") Boolean state){
        userMapper.updateState(id,state);
        return setResultSuccess();
    }

    //增加用户
    @PostMapping("/addUser")
    public BaseResponse addUser(@RequestBody User user){
        user.setRole("普通用户");
        user.setState(false);
        userMapper.addUser(user);
        return setResultSuccess();
    }

    //修改用户
    @PostMapping("/editUser")
    public BaseResponse editUser(@RequestBody User user){
        userMapper.editUser(user);
        return setResultSuccess();
    }

    //删除用户
    @DeleteMapping("/deleteUser")
    public BaseResponse deleteUser(@RequestParam("id") int id){
        userMapper.deleteUser(id);
        return setResultSuccess();
    }
}
