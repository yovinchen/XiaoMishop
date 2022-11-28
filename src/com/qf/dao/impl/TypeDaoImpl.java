package com.qf.dao.impl;

import com.qf.dao.TypeDao;
import com.qf.entity.Type;
import com.qf.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 小灰灰呀
 */
public class TypeDaoImpl implements TypeDao {
    /**
     * @return
     * @throws SQLException
     */
    @Override
    public List<Type> selectAll() throws SQLException {
        //1.创建 QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

        //2.编写sql(limit 5)
        String sql = "select t_id as tid ,t_name as tname ,t_info as tinfo from type limit 5;";

        //3.执行sql语句
        return queryRunner.query(sql, new BeanListHandler<>(Type.class));
    }
}
