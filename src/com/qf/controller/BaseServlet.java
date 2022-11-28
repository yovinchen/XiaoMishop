package com.qf.controller;

import com.qf.utils.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    /**
     * 重写service方法，来处理反射的业务逻辑
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        //1.获取请求参数（标识符）
        String  methodStr =req.getParameter(Constants.TAG);
        //2.如果method没有获取到值  就跳转到首页 （标识符异常处理）
        if (methodStr==null&& methodStr.equals("")){
            methodStr= Constants.INDEX;
        }

        //3.反射调用对应的业务逻辑方法
        Class clazz=this.getClass();
        try {
            Method method=clazz.getMethod(methodStr, HttpServletRequest.class, HttpServletResponse.class);
            Object result= method.invoke(this,req,resp);

            //4.集中处理返回值响应
            if (result != null) {
                //转发  重定向  返回字符串
                String str=(String)result;
                if (str.startsWith(Constants.FORWARD)){
                    //转发   截取
                    String path= str.substring(str.indexOf(Constants.FLAG)+1);
                    req.getRequestDispatcher(path).forward(req,resp);
                }else if (str.startsWith(Constants.REDIRECT)){
                    //重定向
                    String path=str.substring(str.indexOf(Constants.FLAG)+1);
                    resp.sendRedirect(path);
                }else{
                    //就是要返回一个字符串
                    resp.getWriter().println(str);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            //没有反射到方法
        }

    }
    // /user?method=""
    //返回到首页的方法
    /**
     * 当method标识符"没有值"或者是空字符串时，我们默认赋index  访问每个Controller的index方法！
     * 我们将方法提取到BaseServlet中即可!
     *  默认处理：跳转到程序的首页！
     * @param req
     * @param resp
     * @return
     * @throws
     * @throws ServletException
     */
    public String index (HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        return Constants.FORWARD+"/index.jsp";
    }
}
