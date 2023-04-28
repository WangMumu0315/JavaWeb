package com.xll.controller;

import com.xll.model.Order;
import com.xll.model.User;
import com.xll.service.OrderService;
import com.xll.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 谢琳琳
 * @version 1.0
 */
@WebServlet(urlPatterns = {"/customer/order/info", "/admin/order/info"})
public class OrderInfoServlet extends HttpServlet {

    OrderService orderService = new OrderServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 检查顾客和管理员登录状态，之后用过滤器处理，待删除
        // HttpSession session = req.getSession();
        // if(req.getServletPath().contains("customer")) {
        // User customer = (User) session.getAttribute("customer");
        // if (customer == null) {
        // //未登录用户无法查询订单详情
        // resp.sendRedirect("login.jsp");
        // return;
        // }
        // }
        // if(req.getServletPath().contains("admin")) {
        // User admin = (User) session.getAttribute("admin");
        // if (admin == null) {
        // //未登录管理员无法查询订单详情
        // resp.sendRedirect("login.jsp");
        // return;
        // }
        // }

        String orderId = req.getParameter("orderId");
        Order order = orderService.get(orderId);
        req.setAttribute("order", order);
        req.getRequestDispatcher("info.jsp").forward(req, resp);

    }
}
