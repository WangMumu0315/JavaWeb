package com.xll.controller;

import com.xll.dao.UserDao;
import com.xll.dao.impl.CustomerDaoImpl;
import com.xll.model.User;
import com.xll.model.UserStatus;
import com.xll.service.CustomerService;
import com.xll.service.impl.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @author 谢琳琳
 * @version 1.0
 */
@WebServlet(urlPatterns = {"/customer/reg"})
public class CustomerRegServlet extends HttpServlet {

    CustomerService customerService = new CustomerServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从客户端获取新用户信息
        req.setCharacterEncoding("utf-8");
        User user = new User();
        user.setName(req.getParameter("name"));
        user.setPassword(req.getParameter("password"));

        //从模型层调用dao方法在数据库中添加用户
        customerService.add(user);
        //顾客用户注册成功，返回顾客登录页面
        resp.sendRedirect("login.jsp");
    }
}

