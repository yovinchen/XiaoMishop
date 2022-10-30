package com.qf.controller;

import com.qf.entity.Cart;
import com.qf.entity.User;
import com.qf.service.CartService;
import com.qf.service.impl.CartServiceImpl;
import com.qf.utils.Constants;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/cart")

public class CartController extends BaseServlet {

    //加入购物车
    public String create(HttpServletRequest request, HttpServletResponse response) throws SQLException, InvocationTargetException, IllegalAccessException {

        //1.判断用户是否登录，没有登录跳转到登录页面
        HttpSession session = request.getSession();
        //获取session中的用户信息
        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            session.setAttribute("msg", "添加购物车必须先登录!");
            return Constants.FORWARD + "/login.jsp";
        }

        //2.获取到对应的商品 id 和用户 id
        int uid = user.getUid();
        String pid = request.getParameter("pid");

        //3.调用对应的业务逻辑
        CartService cartService = new CartServiceImpl();
        cartService.createCart(uid, pid);


        //4.跳转到添加购物车成功页面
        return Constants.FORWARD + "/cartSuccess.jsp";
    }

    //查看购物车
    public String show(HttpServletRequest request, HttpServletResponse response) throws SQLException, InvocationTargetException, IllegalAccessException {
        //1.判断用户是否登录
        HttpSession session = request.getSession();
        //获取session中的用户信息
        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            session.setAttribute("msg", "查看购物车必须先登录!");
            return Constants.FORWARD + "/login.jsp";
        }

        //2.获取请求参数
        int uid = user.getUid();

        //3.调用对应业务逻辑进行数据查询（查询当前客户购物车中信息）
        CartService cartService = new CartServiceImpl();
        //查询当前用户购物车中所有信息
        List<Cart> list = cartService.findAll(uid);

        //4.将获取到的信息转发到共享域中
        request.setAttribute("list", list);

        return Constants.FORWARD + "/cart.jsp";
    }

    //购物车中删除物品
    public String delete(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        //1.获取请求参数
        String cid = request.getParameter("cid");

        //2.调用对应的业务逻辑
        CartService cartService = new CartServiceImpl();
        cartService.deleteCartByCid(cid);

        //3.转达到展示的处理方法中
        return Constants.FORWARD + "/cart?method=show";
    }

    //购物车中数量更改
    public String update(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        //1.获取请求参数      cid     商品单价price       商品修改后数量cnum
        String cid = request.getParameter("cid");
        String price = request.getParameter("price");
        String cnum = request.getParameter("cnum");

        //2.调用对应的业务逻辑
        CartService cartService = new CartServiceImpl();
        cartService.updateCartByCid(cid, price, cnum);

        //3.转发到展示方法中
        return Constants.FORWARD + "/cart?method=show";
    }

    //清空购物车
    public String clear(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        //1.获取请求参数
        String uid = request.getParameter("uid");

        //2.调用对应的业务逻辑   清空购物车
        CartService cartService = new CartServiceImpl();
        cartService.clearCartByUid(uid);

        //3.转发到展示方法中
        return Constants.FORWARD + "/cart?method=show";
    }
}
