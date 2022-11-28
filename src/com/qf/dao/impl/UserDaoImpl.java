package com.qf.dao.impl;

import com.qf.dao.UserDao;
import com.qf.entity.User;
import com.qf.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * @author 小灰灰呀
 */
public class UserDaoImpl implements UserDao {
    /**
     * 1.根据用户名查询用户是否存在
     *
     * @param username
     * @return
     * @throws SQLException
     */
    @Override
    public User selectUserByUname(String username) throws SQLException {
        //1.创建一个QueryRunner 对象,传入我们对应的连接池
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        //2.编写sql语句完成查询操作
        String sql = "select u_id as uid , u_name as username , u_password as upassword , " +
                "u_sex as usex , u_status as ustatus , u_code as code , u_email as email , " +
                "u_role as urole from user where u_name = ? ; ";
        //3.执行sql
        return queryRunner.query(sql, new BeanHandler<>(User.class), username);
    }

    /**
     * 2.保存数据的方法
     *
     * @param user
     * @return
     * @throws SQLException
     */
    @Override
    public int insertUser(User user) throws SQLException {
        //1.创建一个QueryRunner对象，传入我们对应的连接池
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

        //2.编写sql
        String sql = "insert into user( u_name , u_password , u_sex , u_status , " +
                "u_code , u_email , u_role ) value ( ? , ? , ? , ? , ? , ? , ? ) ; ";
        //执行sql
        return queryRunner.update(sql, user.getUsername(), user.getUpassword(), user.getUsex(), user.getUstatus(), user.getCode(), user.getEmail(), user.getUrole());
    }
}
