package com.xll.controller;

import com.xll.model.UserStatus;
import com.xll.service.CustomerService;
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
@WebServlet(urlPatterns = {"/admin/customer/freeze", "/admin/customer/active"})
public class CustomerModStatusServlet extends HttpServlet {

    CustomerService customerService = new CustomerServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sid = req.getParameter("id");
        if (sid != null && !sid.equals("")) {
            int id = Integer.parseInt(sid);
            if (req.getServletPath().contains("freeze")) {
                customerService.modStatus(id, UserStatus.FREEZE);
            } else if (req.getServletPath().contains("active")) {
                customerService.modStatus(id, UserStatus.NORMAL);
            } else {
                customerService.modStatus(id, UserStatus.UNKNOWN);
            }
        }
        //重定向到列表界面
        resp.sendRedirect("list");
    }
}
