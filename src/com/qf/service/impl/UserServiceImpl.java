package com.qf.service.impl;

import com.qf.dao.UserDao;
import com.qf.dao.impl.UserDaoImpl;
import com.qf.entity.User;
import com.qf.service.UserService;
import com.qf.utils.MD5Utils;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    //查询用户名是否存在
    @Override
    public boolean checkedUser(String username) throws SQLException {

        //根据dao访问对象
        UserDao userDao = new UserDaoImpl();
        User user = userDao.selectUserByUname(username);

        //处理返回值
        if (user != null) {
            return true;
        }

        //没有查询到
        return false;
    }

    //注册业务逻辑
    @Override
    public int registerUser(User user) throws SQLException {
        UserDao userDao = new UserDaoImpl();
        int row = userDao.insertUser(user);
        return row;
    }

    //登录的业务逻辑
    @Override
    public User login(String username, String password) throws SQLException {
        //1.密码加密处理（数据库中密码已加密处理）便于比较
        String md5password = MD5Utils.md5(password);
        //2.根据用户名查询用户
        UserDao userDao = new UserDaoImpl();
        User user = userDao.selectUserByUname(username);
        //3.账号密码对比（正确返回user）
        if (user != null && user.getUpassword().equals(md5password)) {
            return user;
        }
        //错误返回空 null
        return null;
    }
}
