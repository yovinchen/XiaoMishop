package com.qf.dao;

import com.qf.entity.User;

import java.sql.SQLException;

/**
 * 数据库访问接口
 * @author 小灰灰呀
 */
public interface UserDao {
    /**
     * 根据用户名查询用户是否存在
     * @param username
     * @return
     * @throws SQLException
     */
    User selectUserByUname(String username) throws SQLException;

    /**
     * 保存数据的方法
     * @param user
     * @return
     * @throws SQLException
     */
    int insertUser(User user) throws SQLException;

}
