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
@WebServlet("/customer/order/pay")
public class OrderPayServlet extends HttpServlet {

    OrderService orderService = new OrderServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String orderId = req.getParameter("orderId");
        orderService.pay(orderId);

        //支付成功或失败，都重定向到列表界面
        resp.sendRedirect("info?orderId=" + orderId);
    }
}
