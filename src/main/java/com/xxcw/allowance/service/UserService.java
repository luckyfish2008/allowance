package com.xxcw.allowance.service;

import com.xxcw.allowance.bean.QueryInfo;
import com.xxcw.allowance.bean.User;
import com.xxcw.allowance.common.base.BaseApiService;
import com.xxcw.allowance.common.base.BaseResponse;
import com.xxcw.allowance.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@Slf4j
public class UserService extends BaseApiService {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/getAllUser")
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
}
