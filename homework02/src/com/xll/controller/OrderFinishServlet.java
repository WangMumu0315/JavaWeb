package com.xll.controller;

import com.xll.service.OrderService;
import com.xll.service.impl.OrderServiceImpl;

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
@WebServlet("/customer/order/finish")
public class OrderFinishServlet extends HttpServlet {

    OrderService orderService = new OrderServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 检查管理员登录状态，之后用过滤器处理，待删除
        // HttpSession session = req.getSession();
        // User admin = (User) session.getAttribute("admin");
        // if (admin == null) {
        // //未登录管理员无法查询订单详情
        // resp.sendRedirect("login.jsp");
        // return;
        // }

        String orderId = req.getParameter("orderId");
        orderService.finish(orderId);

        //重定向到列表界面
        resp.sendRedirect("list");
    }
}
