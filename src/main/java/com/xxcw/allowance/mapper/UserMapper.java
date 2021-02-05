package com.xxcw.allowance.mapper;

import com.xxcw.allowance.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    User getUserByMassage(@Param("username") String username, @Param("password") String password);
    List<User> getAllUser(@Param("username") String username, @Param("pageStart") int pageStart, @Param("pageSize") int pageSize);
    int getUserCounts(@Param("username") String username);
    int updateState(Integer id, Boolean state);
    int addUser(User user);
    User getUpdateUser(int id);
    int editUser(User user);
    int deleteUser(int id);
}
