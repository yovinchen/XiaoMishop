package com.qf.dao;

import com.qf.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {
    long selectCountByTid(String tid) throws SQLException;

    List<Product> selectProductByPage(int page, int pageSize, String tid) throws SQLException;

    Product selectProductByPid(String pid) throws SQLException;
}
