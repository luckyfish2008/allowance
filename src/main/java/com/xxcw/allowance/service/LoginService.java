package com.xxcw.allowance.service;


import com.xxcw.allowance.bean.User;
import com.xxcw.allowance.common.base.BaseApiService;
import com.xxcw.allowance.common.base.BaseResponse;
import com.xxcw.allowance.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuhaiyang
 * @date 2021-02-04 8:38
 * Description：TODO
 */
@RestController
public class LoginService extends BaseApiService {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/login")
    public BaseResponse userLogin(@RequestBody User user) {
        User loginUser = userMapper.getUserByMassage(user.getUsername(), user.getPassword());
        System.out.println("loginUser="+loginUser);
        if (loginUser == null ) {
            return setResultError("登录失败");
        }
        return setResultSuccess(loginUser);
    }

}
