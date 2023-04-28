package com.xll.controller;

import com.xll.model.User;
import com.xll.service.AdminUserService;
import com.xll.service.impl.AdminUserServiceImpl;

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
@WebServlet("/admin/user/mod")
public class AdminUserModServlet extends HttpServlet {

    AdminUserService adminUserService = new AdminUserServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //从客户端获取新用户信息
        req.setCharacterEncoding("utf-8");
        User user = new User();
        user.setId(Integer.parseInt(req.getParameter("id")));
        user.setName(req.getParameter("name"));
        user.setPassword(req.getParameter("password"));
        user.setCreateTime(Long.parseLong(req.getParameter("createTime")));
        user.setLastAccessTime(Long.parseLong(req.getParameter("lastAccessTime")));
        user.setStatus(req.getParameter("status"));

        if (adminUserService.mod(user)) {
            //修改成功，重定向至列表界面
            resp.sendRedirect("list");
        } else {
            //修改失败，将请求转发至用户修改界面
            req.setAttribute("user", user);
            req.getRequestDispatcher("mod.jsp").forward(req, resp);
        }

    }
}

