package com.qf.controller;

import com.qf.entity.PageBean;
import com.qf.entity.Product;
import com.qf.service.ProductService;
import com.qf.service.impl.ProductServiceImpl;
import com.qf.utils.Constants;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * @author 小灰灰呀
 */
@WebServlet("/product")
public class ProductController extends BaseServlet {

    /**
     * 1.商品分页展示
     *
     * @param request
     * @param response
     * @return
     * @throws SQLException
     */
    public String show(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        //1.获取请求参数
        String tid = request.getParameter("tid");
        //获取当前页和页容量
        int pageSize = 8;
        //当前页
        String currentPage = request.getParameter("currentPage");
        //默认值 1
        int page = 1;
        if (currentPage != null) {
            page = Integer.parseInt(currentPage);
        }
        //2.调用业务逻辑
        ProductService productService = new ProductServiceImpl();
        PageBean<Product> pageBean = productService.findPage(tid, page, pageSize);

        //3.响应
        request.setAttribute("pageBean", pageBean);
        return Constants.FORWARD + "/goodsList.jsp";
    }

    /**
     * 2.商品详情展示
     *
     * @param request
     * @param response
     * @return
     * @throws SQLException
     */
    public String detail(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        //1.获取请求参数
        String pid = request.getParameter("pid");
        //2.调用业务逻辑
        ProductService productService = new ProductServiceImpl();
        //3.声明方法
        Product product = productService.findProductByPid(pid);
        //3.响应
        request.setAttribute("product", product);
        //4.跳转到对应的商品页面
        return Constants.FORWARD + "/goodsDetail.jsp";
    }
}
