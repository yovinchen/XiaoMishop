package com.qf.service;

import com.qf.entity.PageBean;
import com.qf.entity.Product;

import java.sql.SQLException;

/**
 * @author 小灰灰呀
 */
public interface ProductService {
    /**
     * @param tid
     * @param page
     * @param pageSize
     * @return
     * @throws SQLException
     */
    PageBean<Product> findPage(String tid, int page, int pageSize) throws SQLException;

    /**
     * @param pid
     * @return
     * @throws SQLException
     */
    Product findProductByPid(String pid) throws SQLException;
}
