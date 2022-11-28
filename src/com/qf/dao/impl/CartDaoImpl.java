package com.qf.dao.impl;

import com.qf.dao.CartDao;
import com.qf.entity.Cart;
import com.qf.entity.Product;
import com.qf.utils.C3P0Utils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 小灰灰呀
 */
public class CartDaoImpl implements CartDao {
    /**
     * 1.查询购物车
     *
     * @param uid
     * @param pid
     * @return
     * @throws SQLException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
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

        Product product = new Product();
        Cart cart = new Cart();

        //通过工具类 BeanUtils 将对应 key 值的信息进行赋值
        BeanUtils.populate(cart, query);
        BeanUtils.populate(product, query);

        cart.setProduct(product);

        return cart;
    }

    /**
     * 2.更新购物车
     *
     * @param cart
     * @throws SQLException
     */
    @Override
    public void updateCart(Cart cart) throws SQLException {

        //1.创建 QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

        //2.编写sql
        String sql = "update cart set c_num = ? , c_count = ? where c_id = ?;";

        //3.执行sql
        queryRunner.update(sql, cart.getCnum(), cart.getCcount(), cart.getCid());
    }

    /**
     * 3.加入购物车
     *
     * @param cart
     * @throws SQLException
     */
    @Override
    public void insertCart(Cart cart) throws SQLException {

        //1.创建 QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

        //2.编写sql
        String sql = "insert into cart ( u_id , p_id , c_num , c_count ) value ( ? , ? , ? , ? );";

        //3.执行sql
        queryRunner.update(sql, cart.getUid(), cart.getPid(), cart.getCnum(), cart.getCcount());

    }

    /**
     * 4.通过uid查询购物车
     *
     * @param uid
     * @return
     * @throws SQLException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
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

        //3.整合成为 list 集合
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

            //将 product 关联到 Cart
            cart.setProduct(product);
            //添加数据到 carts
            carts.add(cart);
        }

        return carts;
    }

    /**
     * 5.删除购物车中单项物品
     *
     * @param cid
     * @throws SQLException
     */
    @Override
    public void deleteCartByCid(String cid) throws SQLException {

        //1.创建 QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

        //2.编写sql
        String sql = "delete from cart where c_id = ? ;";

        //3.执行sql
        queryRunner.update(sql, cid);
    }


    /**
     * 6.更新购物车中单项物品
     *
     * @param count
     * @param cnumbig
     * @param cid
     * @throws SQLException
     */
    @Override
    public void updateByCid(BigDecimal count, BigDecimal cnumbig, String cid) throws SQLException {

        //1.创建 QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

        //2.编写sql
        String sql = "update cart set c_count = ? , c_num = ? where c_id = ? ;";

        //3.执行sql
        queryRunner.update(sql, count, cnumbig, cid);
    }


    /**
     * 7.清除购物车
     *
     * @param uid
     * @throws SQLException
     */
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
