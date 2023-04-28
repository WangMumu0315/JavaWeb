package com.xll.controller;

import com.xll.model.Book;
import com.xll.model.Order;
import com.xll.model.User;
import com.xll.service.OrderService;
import com.xll.service.impl.OrderServiceImpl;
import com.sun.org.apache.xpath.internal.operations.Or;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author 谢琳琳
 * @version 1.0
 */
@WebServlet(urlPatterns = {"/admin/order/list", "/admin/order/query", "/customer/order/list"})
public class OrderListServlet extends HttpServlet {

    OrderService orderService = new OrderServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 从客户端获取模糊查询条件
        req.setCharacterEncoding("utf-8"); //处理中文乱码
        Order condition = new Order();
        condition.setOrderId(req.getParameter("orderId"));
        condition.setCustomerId(req.getParameter("customerId"));
        condition.setStatus(req.getParameter("status"));

        // 从客户端获取分页信息
        int page = 1;
        String sPage = req.getParameter("p");
        if (sPage != null && !"".equals(sPage)) {
            page = Integer.parseInt(req.getParameter("p"));
        }
        int pageSize = 10;
        int ordersCount = orderService.count(condition);
        int pageCount = ordersCount % pageSize == 0 ? ordersCount / pageSize : ordersCount / pageSize + 1;

        // 从模型层获取到查询结果
        List<Order> orderList = orderService.get(condition, page, pageSize);

        // 在请求范围内保存图书列表数据
        req.setAttribute("orders", orderList);
        req.setAttribute("p", page);
        req.setAttribute("pCount", pageCount);
        // 页面跳转：请求转发至列表页面
        req.getRequestDispatcher("list.jsp").forward(req, resp);

    }
}
