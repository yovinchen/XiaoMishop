package com.qf.dao;

import com.qf.entity.Cart;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface CartDao {

    Cart hasCart(int uid, String pid) throws SQLException, InvocationTargetException, IllegalAccessException;

    void updateCart(Cart cart) throws SQLException;

    void insertCart(Cart cart) throws SQLException;

    List<Cart> selectCartByUid(int uid) throws SQLException, InvocationTargetException, IllegalAccessException;

    void deleteCartByCid(String cid) throws SQLException;

    void updateByCid(BigDecimal count, BigDecimal cnumbig, String cid) throws SQLException;

    void clearCartByUid(String uid) throws SQLException;
}
