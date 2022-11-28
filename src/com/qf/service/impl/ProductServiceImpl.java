package com.qf.service.impl;

import com.qf.dao.ProductDao;
import com.qf.dao.impl.ProductDaoImpl;
import com.qf.entity.PageBean;
import com.qf.entity.Product;
import com.qf.service.ProductService;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 小灰灰呀
 */
public class ProductServiceImpl implements ProductService {
    /**
     * @param tid
     * @param page
     * @param pageSize
     * @return
     * @throws SQLException
     */
    @Override
    public PageBean<Product> findPage(String tid, int page, int pageSize) throws SQLException {
        ProductDao productDao = new ProductDaoImpl();
        //1.查询有多少条数据
        long count = productDao.selectCountByTid(tid);

        List<Product> list = productDao.selectProductByPage(page, pageSize, tid);
        return new PageBean<>(list, page, pageSize, count);
    }

    /**
     * @param pid
     * @return
     * @throws SQLException
     */
    @Override
    public Product findProductByPid(String pid) throws SQLException {
        //1.先到数据库中进行商品查询
        ProductDao productDao = new ProductDaoImpl();

        //2.定义productdao里面对应的方法，返回product
        return productDao.selectProductByPid(pid);
    }
}
