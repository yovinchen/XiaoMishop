package com.qf.controller;

import com.qf.entity.User;
import com.qf.service.UserService;
import com.qf.service.impl.UserServiceImpl;
import com.qf.utils.Constants;
import com.qf.utils.MD5Utils;
import com.qf.utils.RandomUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/user")
public class UserController extends BaseServlet {

    //1.查询用户是否存在
    public String check(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        //1.获取用户名
        String username = request.getParameter("username");
        if (username == null) {
            return "1";
        }
        //2.调用对应的业务逻辑判断用户名是否存在
        UserService userService = new UserServiceImpl();
        boolean b = userService.checkedUser(username);
        //3.响应
        if (b) {
            //用户名存在
            return "1";
        }
        return "0";
    }

    //2.注册功能
    public String register(HttpServletRequest request, HttpServletResponse response) {
        //1.获取到我们的用户信息
        Map<String, String[]> parameterMap = request.getParameterMap();
        //定义一个User
        User user = new User();

        try {
            BeanUtils.populate(user, parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //2.完善我们用户信息
        user.setUstatus("0");
        user.setUrole(0);
        user.setCode(RandomUtils.createActive());
        //对密码进行加密处理
        user.setUpassword(MD5Utils.md5(user.getUpassword()));
        //3.调用对应的业务逻辑进行注册
        UserService userService = new UserServiceImpl();

        try {
            userService.registerUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("registerMsg", "注册失败！");
            return Constants.FORWARD + "/register.jsp";
        }
        //4.响应
        return Constants.FORWARD + "/registerSuccess.jsp";
    }

    //3.登录功能
    public String login(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        //1.获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //用户输入的验证码
        String code = request.getParameter("code");

        //2.获取正确的验证码（在session中）
        HttpSession session = request.getSession();
        String codestr = (String) session.getAttribute("code");

        //3.判断验证码是否正确（空或者不匹配）
        if (code == null || !code.equalsIgnoreCase(codestr)) {
            //错误提示
            request.setAttribute("msg", "验证码错误！");
            //将页面返回登录页面
            return Constants.FORWARD + "login.jsp";
        }
        //4.调用业务逻辑判断账号密码是否正确（验证码正确的情况下）
        UserService userService = new UserServiceImpl();
        User user = userService.login(username, password);
        //5.响应
        if (user == null) {
            //错误提示
            request.setAttribute("msg", "用户名或密码错误！");
            //将页面返回登录页面
            return Constants.FORWARD + "login.jsp";
        }
        //6.进行登录
        session.setAttribute("loginUser", user);
        return Constants.FORWARD + "/index.jsp";
    }

    //4.注销登录
    public String logOut(HttpServletRequest request, HttpServletResponse response) {
        //1.清空session中的用户数据
        HttpSession session = request.getSession();
        session.removeAttribute("loginUser");
        //2.提示注销并将页面转发到登录页面
        request.setAttribute("msg", "注销登录成功！");
        return Constants.FORWARD + "login.jsp";
    }
}
