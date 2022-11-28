package com.qf.dao;

import com.qf.entity.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 小灰灰呀
 */
public interface ProductDao {
    /**
     * @param tid
     * @return
     * @throws SQLException
     */
    long selectCountByTid(String tid) throws SQLException;

    /**
     * @param page
     * @param pageSize
     * @param tid
     * @return
     * @throws SQLException
     */
    List<Product> selectProductByPage(int page, int pageSize, String tid) throws SQLException;

    /**
     * @param pid
     * @return
     * @throws SQLException
     */
    Product selectProductByPid(String pid) throws SQLException;
}
