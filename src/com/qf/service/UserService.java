package com.qf.service;

import com.qf.entity.User;

import java.sql.SQLException;

public interface UserService {

    //查询用户名是否存在
    boolean checkedUser(String username) throws SQLException;

    //注册业务逻辑
    int registerUser(User user) throws SQLException;

    //登录的业务逻辑
    User login(String username, String password) throws SQLException;
}
