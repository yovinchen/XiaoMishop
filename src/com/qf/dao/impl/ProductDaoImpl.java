package com.qf.dao.impl;

import com.qf.dao.ProductDao;
import com.qf.entity.Product;
import com.qf.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 小灰灰呀
 */
public class ProductDaoImpl implements ProductDao {
    /**
     * 1.查询
     *
     * @param tid
     * @return
     * @throws SQLException
     */
    @Override
    public long selectCountByTid(String tid) throws SQLException {

        //1.创建 QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

        //2.编写sql
        String sql = "select count(1) from product where t_id = ? ; ";
        Object result = queryRunner.query(sql, new ScalarHandler<>(), tid);

        return (Long) result;
    }

    /**
     * @param page
     * @param pageSize
     * @param tid
     * @return
     * @throws SQLException
     */
    @Override
    public List<Product> selectProductByPage(int page, int pageSize, String tid) throws SQLException {
        //1.创建 QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

        //2.编写sql
        String sql = "select p_id as pid , t_id as tid , p_name as pname , p_time as ptime , " +
                "p_image as pimage , p_state as pstate , p_info as pinfo , p_price as pprice " +
                "from product where t_id = ? limit ? , ? ;";

        return queryRunner.query(sql, new BeanListHandler<>(Product.class), tid, (page - 1) * pageSize, pageSize);
    }

    /**
     * @param pid
     * @return
     * @throws SQLException
     */
    @Override
    public Product selectProductByPid(String pid) throws SQLException {
        //1.创建 QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

        //2.编写sql
        String sql = "select p_id as pid , t_id as tid , p_name as pname , p_time as ptime , " +
                "p_image as pimage , p_state as pstate , p_info as pinfo , p_price as pprice " +
                "from product where p_id = ? ; ";

        return queryRunner.query(sql, new BeanHandler<>(Product.class), pid);
    }
}
