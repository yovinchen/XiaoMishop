package com.qf.dao;

import com.qf.entity.Cart;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 小灰灰呀
 */
public interface CartDao {

    /**
     * @param uid
     * @param pid
     * @return
     * @throws SQLException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    Cart hasCart(int uid, String pid) throws SQLException, InvocationTargetException, IllegalAccessException;

    /**
     * @param cart
     * @throws SQLException
     */
    void updateCart(Cart cart) throws SQLException;

    /**
     * @param cart
     * @throws SQLException
     */
    void insertCart(Cart cart) throws SQLException;

    /**
     * @param uid
     * @return
     * @throws SQLException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    List<Cart> selectCartByUid(int uid) throws SQLException, InvocationTargetException, IllegalAccessException;

    /**
     * @param cid
     * @throws SQLException
     */
    void deleteCartByCid(String cid) throws SQLException;

    /**
     * @param count
     * @param cnumbig
     * @param cid
     * @throws SQLException
     */
    void updateByCid(BigDecimal count, BigDecimal cnumbig, String cid) throws SQLException;

    /**
     * @param uid
     * @throws SQLException
     */
    void clearCartByUid(String uid) throws SQLException;

}
