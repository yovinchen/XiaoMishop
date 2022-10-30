package com.qf.dao.impl;

import com.qf.dao.CartDao;
import com.qf.entity.Cart;
import com.qf.entity.Product;
import com.qf.utils.C3P0Utils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CartDaoImpl implements CartDao {
    @Override
    public Cart hasCart(int uid, String pid) throws SQLException, InvocationTargetException, IllegalAccessException {
        //cart-->product 间接查询,多表查询
        //1.创建 QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

        //2.编写sql
        String sql = "select p.p_name as pname, p.p_id as pid, p.t_id as tid, " +
                "p.p_time as ptime, p.p_image as piamge, p.p_state as pstate, " +
                "p.p_info as pinfo, p.p_price as pprice, c.c_id as cid, " +
                "c.u_id as uid, c.c_count as ccount, c.c_num as cnum " +
                "from product p join cart c on p.p_id = c.p_id where c.u_id = ? and c.p_id = ?";

        //3.执行sql语句
        Map<String, Object> query = queryRunner.query(sql, new MapHandler(), uid, pid);


        //4.判断并处理信息
        if (query == null) {
            return null;
        }

//        Product product = queryRunner.query(sql, new BeanHandler<Product>(Product.class), uid, pid);
//        Cart cart = queryRunner.query(sql, new BeanHandler<Cart>(Cart.class), uid, pid);
        Product product = new Product();
        Cart cart = new Cart();

        //通过工具类 BeanUtils 将对应 key 值的信息进行赋值
        BeanUtils.populate(cart, query);
        BeanUtils.populate(product, query);

        cart.setProduct(product);

        return cart;
    }

    @Override
    public void updateCart(Cart cart) throws SQLException {

        //1.创建 QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

        //2.编写sql
        String sql = "update cart set c_num = ? , c_count = ? where c_id = ?;";

        //3.执行sql
        queryRunner.update(sql, cart.getCnum(), cart.getCcount(), cart.getCid());
    }

    @Override
    public void insertCart(Cart cart) throws SQLException {

        //1.创建 QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

        //2.编写sql
        String sql = "insert into cart ( u_id , p_id , c_num , c_count ) value ( ? , ? , ? , ? );";

        //3.执行sql
        queryRunner.update(sql, cart.getUid(), cart.getPid(), cart.getCnum(), cart.getCcount());

    }

    @Override
    public List<Cart> selectCartByUid(int uid) throws SQLException, InvocationTargetException, IllegalAccessException {

        //1.创建 QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

        //2.编写sql
        String sql = "select p.p_name as pname, p.p_id as pid, p.t_id as tid, " +
                "p.p_time as ptime, p.p_image as piamge, p.p_state as pstate, " +
                "p.p_info as pinfo, p.p_price as pprice, c.c_id as cid, " +
                "c.u_id as uid, c.c_count as ccount, c.c_num as cnum " +
                "from product p join cart c on p.p_id = c.p_id where c.u_id = ? ;";

        //3.执行sql
        List<Map<String, Object>> list = queryRunner.query(sql, new MapListHandler(), uid);

        //判空
        if (list == null) {
            return null;
        }

        //4.创建集合放购物车中对应的商品
        List<Cart> carts = new ArrayList<>();
        //for循环遍历存储信息
        for (Map<String, Object> map : list) {
            Product product = new Product();
            Cart cart = new Cart();

            //通过工具类 BeanUtils 将对应 key 值的信息进行赋值
            BeanUtils.populate(cart, map);
            BeanUtils.populate(product, map);

            //手动将 product 关联到 Cart
            cart.setProduct(product);
            //添加数据到 carts
            carts.add(cart);
        }

        return carts;
    }

    @Override
    public void deleteCartByCid(String cid) throws SQLException {

        //1.创建 QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

        //2.编写sql
        String sql = "delete from cart where c_id = ? ;";

        //3.执行sql
        queryRunner.update(sql, cid);
    }

    @Override
    public void updateByCid(BigDecimal count, BigDecimal cnumbig, String cid) throws SQLException {

        //1.创建 QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

        //2.编写sql
        String sql = "update cart set c_count = ? , c_num = ? where c_id = ? ;";

        //3.执行sql
        queryRunner.update(sql, count, cnumbig, cid);
    }

    @Override
    public void clearCartByUid(String uid) throws SQLException {
        //1.创建 QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

        //2.编写sql
        String sql = "delete from cart where u_id = ? ;";

        //3.执行sql
        queryRunner.update(sql, uid);
    }

}
