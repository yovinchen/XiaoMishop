package com.qf.controller;

import com.qf.service.CartService;
import com.qf.service.impl.CartServiceImpl;
import com.qf.utils.Constants;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@WebServlet("/address")
public class AddressController extends BaseServlet {

    public String show(HttpServletRequest request, HttpServletResponse response){

        //1.获取请求参数

        //2.调用对应的业务逻辑   清空购物车

        //3.转发到展示方法中
        return Constants.FORWARD + "/cart?method=show";
    }


}
