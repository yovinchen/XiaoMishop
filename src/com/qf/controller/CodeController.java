package com.qf.controller;

import cn.dsna.util.images.ValidateCode;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 小灰灰呀
 */
@WebServlet("/code")
public class CodeController extends BaseServlet {
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //1.生成验证码的对象
        ValidateCode validateCode = new ValidateCode(100, 35, 4, 25);
        //2.获取到对应的验证码
        String code = validateCode.getCode();
        //3.将获取到的验证码存到session中
        request.getSession().setAttribute("code", code);
        //4.创建出对应的字节文件对象
        ServletOutputStream outputStream = response.getOutputStream();
        //写回    表示验证码通过这个字节文件写回页面
        validateCode.write(outputStream);
    }
}
