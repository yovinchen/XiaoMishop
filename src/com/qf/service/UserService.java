package com.qf.service;

import com.qf.entity.User;

import java.sql.SQLException;

/**
 * @author 小灰灰呀
 */
public interface UserService {

    /**
     * 1.查询用户名是否存在
     *
     * @param username
     * @return
     * @throws SQLException
     */
    boolean checkedUser(String username) throws SQLException;

    /**
     * 2.注册业务逻辑
     *
     * @param user
     * @return
     * @throws SQLException
     */
    int registerUser(User user) throws SQLException;

    /**
     * 3.登录的业务逻辑
     *
     * @param username
     * @param password
     * @return
     * @throws SQLException
     */
    User login(String username, String password) throws SQLException;
}
