package com.qf.service.impl;

import com.qf.dao.CartDao;
import com.qf.dao.ProductDao;
import com.qf.dao.impl.CartDaoImpl;
import com.qf.dao.impl.ProductDaoImpl;
import com.qf.entity.Cart;
import com.qf.entity.Product;
import com.qf.service.CartService;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 小灰灰呀
 */
public class CartServiceImpl implements CartService {
    /**
     * 1.向购物车中添加物品
     *
     * @param uid
     * @param pid
     * @throws SQLException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Override
    public void createCart(int uid, String pid) throws SQLException, InvocationTargetException, IllegalAccessException {
        //1.判断商品是否已经存在
        CartDao cartDao = new CartDaoImpl();
        Cart cart = cartDao.hasCart(uid, pid);

        if (cart != null) {
            //已存在该物品，修改数量
            cart.setCnum(cart.getCnum() + 1);
            cartDao.updateCart(cart);
        } else {
            //不存在该物品，添加该物品
            ProductDao productDao = new ProductDaoImpl();
            Product product = productDao.selectProductByPid(pid);

            //完成添加
            cart = new Cart();
            cart.setCnum(1);
            cart.setPid(Integer.parseInt(pid));
            cart.setProduct(product);
            cart.setUid(uid);

            //完成插入操作
            cartDao.insertCart(cart);
        }

    }

    /**
     * 2.根据uid查询所有商品
     *
     * @param uid
     * @return
     * @throws SQLException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Override
    public List<Cart> findAll(int uid) throws SQLException, InvocationTargetException, IllegalAccessException {

        CartDao cartDao = new CartDaoImpl();
        //1.根据用户 id 查询对应购物车里面的商品
        return cartDao.selectCartByUid(uid);
    }

    /**
     * 3.根据cid删除购物车中物品
     *
     * @param cid
     * @throws SQLException
     */
    @Override
    public void deleteCartByCid(String cid) throws SQLException {
        CartDao cartDao = new CartDaoImpl();
        cartDao.deleteCartByCid(cid);
    }

    /**
     * 4.购物车修改数量
     *
     * @param cid
     * @param price
     * @param cnum
     * @throws SQLException
     */
    @Override
    public void updateCartByCid(String cid, String price, String cnum) throws SQLException {

        //1.将对应的  cnum    和   price   转换成对应的大数据类型
        BigDecimal cnumbig = new BigDecimal(cnum);
        BigDecimal prciebig = new BigDecimal(price);

        //2.进行小计计算
        BigDecimal count = prciebig.multiply(cnumbig);

        //3.数据库中修改
        CartDao cartDao = new CartDaoImpl();
        cartDao.updateByCid(count, cnumbig, cid);
    }

    /**
     * 5.清空购物车
     *
     * @param uid
     * @throws SQLException
     */
    @Override
    public void clearCartByUid(String uid) throws SQLException {
        CartDao cartDao = new CartDaoImpl();
        cartDao.clearCartByUid(uid);
    }
}
