package com.qf.controller;

import com.qf.entity.Address;
import com.qf.entity.User;
import com.qf.service.AddressService;
import com.qf.service.impl.AddressServiceImpl;
import com.qf.utils.Constants;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author 小灰灰呀
 */
@WebServlet("/address")
public class AddressController extends BaseServlet {

    /**
     * 1.查看收货地址
     */
    public String show(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        //1.判断用户是否登录
        HttpSession session = request.getSession();
        //获取session中的用户信息
        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            session.setAttribute("msg", "查看地址必须先登录!");
            return Constants.FORWARD + "/login.jsp";
        }

        //2.获取请求参数
        int uid = user.getUid();

        //3.调用对应的业务逻辑
        AddressService addressService = new AddressServiceImpl();
        List<Address> addresses = addressService.findAddressByUid(uid);

        //4.将获取到的地址信息转发到共享域中
        request.setAttribute("list", addresses);

        //5.转发到展示方法中
        return Constants.FORWARD + "/self_info.jsp";
    }

    /**
     * 2.新增收货地址
     */
    public String add(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, SQLException {

        //1.获取输入的地址信息
        Map<String, String[]> map = request.getParameterMap();

        Address address = new Address();
        BeanUtils.populate(address, map);

        //2.调用业务逻辑进行地址的添加
        AddressService addressService = new AddressServiceImpl();
        addressService.saveAddress(address);

        //3.转发到展示方法中，重新加载数据
        return Constants.FORWARD + "/address?method=show";
    }

    /**
     * 3.设置默认收货地址
     */
    public String setDefault(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        //1.获取请求参数
        String aid = request.getParameter("aid");

        //2.获取用户uid
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loginUser");
        int uid = user.getUid();

        //3.调用业务逻辑进行地址修改
        AddressService addressService = new AddressServiceImpl();
        addressService.setAddressToDefault(aid, uid);

        //4.转发到展示方法
        return Constants.FORWARD + "/address?method=show";
    }

    /**
     * 4.删除收货地址
     */
    public String delete(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        //1.获取请求参数
        String aid = request.getParameter("aid");

        //2.调用业务逻辑进行地址添加
        AddressService addressService = new AddressServiceImpl();
        addressService.deleteAddressByAid(aid);

        //3.转发到展示方法
        return Constants.FORWARD + "/address?method=show";
    }

    /**
     * 5.修改收货地址
     */
    public String update(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, SQLException {

        //1.获取修改的数据
        Map<String, String[]> map = request.getParameterMap();

        //转换为 Address 类型方便传输
        Address address = new Address();
        BeanUtils.populate(address, map);

        //2.调用业务逻辑进行地址修改
        AddressService addressService = new AddressServiceImpl();
        addressService.updateByAid(address);

        //3.转发到展示方法
        return Constants.FORWARD + "/address?method=show";
    }
}
