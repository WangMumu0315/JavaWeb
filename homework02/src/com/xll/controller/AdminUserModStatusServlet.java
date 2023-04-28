package com.xll.controller;

import com.xll.model.User;
import com.xll.model.UserStatus;
import com.xll.service.AdminUserService;
import com.xll.service.CustomerService;
import com.xll.service.impl.AdminUserServiceImpl;
import com.xll.service.impl.CustomerServiceImpl;

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

@WebServlet(urlPatterns = {"/admin/user/freeze", "/admin/user/active"})
public class AdminUserModStatusServlet extends HttpServlet {

    AdminUserService adminUserService = new AdminUserServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sid = req.getParameter("id");
        if (sid != null && !sid.equals("")) {
            int id = Integer.parseInt(sid);
            if (req.getServletPath().contains("freeze")) {
                adminUserService.modStatus(id, UserStatus.FREEZE);
            } else if (req.getServletPath().contains("active")) {
                adminUserService.modStatus(id, UserStatus.NORMAL);
            } else {
                adminUserService.modStatus(id, UserStatus.UNKNOWN);
            }
        }
        //重定向到列表界面
        resp.sendRedirect("list");
    }
}

