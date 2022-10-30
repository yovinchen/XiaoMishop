package com.qf.service;

import com.qf.entity.Cart;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface CartService {
    void createCart(int uid, String pid) throws SQLException, InvocationTargetException, IllegalAccessException;

    List<Cart> findAll(int uid) throws SQLException, InvocationTargetException, IllegalAccessException;

    void deleteCartByCid(String cid) throws SQLException;

    void updateCartByCid(String cid, String price, String cnum) throws SQLException;

    void clearCartByUid(String uid) throws SQLException;
}
