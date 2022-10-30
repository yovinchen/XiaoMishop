package com.qf.service;

import com.qf.entity.PageBean;
import com.qf.entity.Product;

import java.sql.SQLException;

public interface ProductService {
    PageBean<Product> findPage(String tid, int page, int pageSize) throws SQLException;

    Product findProductByPid(String pid) throws SQLException;
}
