package com.xxcw.allowance.service;


import com.alibaba.fastjson.JSON;
import com.xxcw.allowance.bean.User;
import com.xxcw.allowance.common.aop.login.NoLogin;
import com.xxcw.allowance.common.base.BaseApiService;
import com.xxcw.allowance.common.base.BaseResponse;
import com.xxcw.allowance.common.redis.LoginRedisService;
import com.xxcw.allowance.mapper.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuhaiyang
 * @date 2021-02-04 8:38
 * Description：TODO
 */
@Api("用户登陆接口")
@RestController
@Slf4j
public class LoginService extends BaseApiService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LoginRedisService loginRedisService;

    //登录接口，不被aop拦截
    @NoLogin
    @PostMapping("/login")
    @ApiOperation(value = "登陆接口", notes = "用户登陆")
    @ApiImplicitParams({@ApiImplicitParam(name = "user", value = "用户登录表单", required = true, dataType = "User")})
    public BaseResponse userLogin(@RequestBody User user) {
        User loginUser = userMapper.getUserByMassage(user.getUsername(), user.getPassword());
        System.out.println("loginUser="+loginUser);
        if (loginUser == null ) {
            return setResultError("登录失败");
        }
        //用户名和密码都验证通过，判断是否在其他地方已经登录。
        Map otherLoginMessage = loginRedisService.getOthersLoginMessage(loginUser.getUsername());
        if (otherLoginMessage != null) {
            //如果有强制其下线，删除redis中的信息
            log.info("用户已经登录"+ JSON.toJSONString(otherLoginMessage)+",强制其下线");
            loginRedisService.setOthersSignOff(loginUser.getUsername());
        }
        //将登陆信息存储到redis中
        Map qxry = new HashMap();
        qxry.put("id",loginUser.getId());
        qxry.put("role",loginUser.getRole());
        qxry.put("gh",loginUser.getUsername());
        Map map = loginRedisService.login(user.getUsername(), qxry);
        return setResultSuccess(map);
    }

}
