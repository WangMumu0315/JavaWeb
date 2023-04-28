package com.xll.controller;

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
@WebServlet(urlPatterns = {"/admin/user/reset", "/admin/reset"})
public class AdminUserPwdResetServlet extends HttpServlet {

    AdminUserService adminUserService = new AdminUserServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sid = req.getParameter("id");
        if (sid != null && !sid.equals("")) {
            //使用客户端传递的id值在数据库中重置密码
            adminUserService.resetPwd(Integer.parseInt(sid));
        }
        //删除成功或失败，都重定向到列表界面
        resp.sendRedirect("list");
    }
}

