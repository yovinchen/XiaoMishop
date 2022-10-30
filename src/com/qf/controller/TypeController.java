package com.qf.controller;

import com.google.gson.Gson;
import com.qf.entity.Type;
import com.qf.service.TypeService;
import com.qf.service.impl.TypeServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/type")
public class TypeController extends BaseServlet {
    public String findAll(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        //将查询到的数据转换成json的格式，传递给前端进行展示

        //1.调用对象调用查询方法
        TypeService typeService = new TypeServiceImpl();
        List<Type> types = typeService.findAll();

        //2.将集合转换为 json 数据
        Gson gson = new Gson();
        String json = gson.toJson(types);

        return json;
    }
}
