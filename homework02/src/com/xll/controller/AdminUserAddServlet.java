package com.xll.controller;

import com.xll.model.User;
import com.xll.service.AdminUserService;
import com.xll.service.impl.AdminUserServiceImpl;
import com.xll.util.Encrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 谢琳琳
 * @version 1.0
 */
@WebServlet(urlPatterns = {"/admin/user/add"})
public class AdminUserAddServlet extends HttpServlet {

    AdminUserService adminUserService = new AdminUserServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从客户端获取新用户信息
        req.setCharacterEncoding("utf-8");
        User user = new User();
        user.setName(req.getParameter("name"));
        user.setPassword(req.getParameter("password"));

        //从模型层调用dao方法在数据库中添加用户
        adminUserService.add(user);

        //页面重定向到列表页面
        if (req.getServletPath().contains("admin")) {
            //管理员添加用户成功，返回用户管理页面
            resp.sendRedirect("list");
        } else {
            //顾客用户注册成功，返回顾客登录页面
            resp.sendRedirect("login.jsp");
        }
    }
}
