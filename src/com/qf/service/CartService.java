package com.qf.service;

import com.qf.entity.Cart;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 小灰灰呀
 */
public interface CartService {
    /**
     * @param uid
     * @param pid
     * @throws SQLException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    void createCart(int uid, String pid) throws SQLException, InvocationTargetException, IllegalAccessException;

    /**
     * @param uid
     * @return
     * @throws SQLException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    List<Cart> findAll(int uid) throws SQLException, InvocationTargetException, IllegalAccessException;

    /**
     * @param cid
     * @throws SQLException
     */
    void deleteCartByCid(String cid) throws SQLException;

    /**
     * @param cid
     * @param price
     * @param cnum
     * @throws SQLException
     */
    void updateCartByCid(String cid, String price, String cnum) throws SQLException;

    /**
     * @param uid
     * @throws SQLException
     */
    void clearCartByUid(String uid) throws SQLException;
}
