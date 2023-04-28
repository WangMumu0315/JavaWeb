package com.xll.controller;

import com.xll.dao.UserDao;
import com.xll.dao.impl.AdminUserDaoImpl;
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
@WebServlet("/admin/user/modPre")
public class AdminUserModPreServlet extends HttpServlet {

    AdminUserService adminUserService = new AdminUserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sid = req.getParameter("id");
        if (sid != null && !sid.equals("")) {
            //使用客户端传递的id值在数据库中查询要修改的用户
            User user = adminUserService.get(Integer.parseInt(sid));
            //在请求范围内保存查询到的用户对象
            req.setAttribute("user", user);
            //请求转发到修改用户页面
            req.getRequestDispatcher("mod.jsp").forward(req, resp);
        } else {
            //未获取到id参数，则无法获取用户信息，重定向到列表界面
            resp.sendRedirect("list");
        }
    }
}
