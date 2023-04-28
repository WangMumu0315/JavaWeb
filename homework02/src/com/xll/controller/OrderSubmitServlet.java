package com.xll.controller;

import com.xll.model.Cart;
import com.xll.model.Order;
import com.xll.model.OrderStatus;
import com.xll.model.User;
import com.xll.service.CartService;
import com.xll.service.OrderService;
import com.xll.service.impl.CartServiceImpl;
import com.xll.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * @author 谢琳琳
 * @version 1.0
 */

@WebServlet("/customer/order/submit")
public class OrderSubmitServlet extends HttpServlet {

    OrderService orderService = new OrderServiceImpl();
    CartService cartService = new CartServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        User customer = (User) session.getAttribute("customer");
        if (customer == null) {
            //未登录用户无法下单
            resp.sendRedirect(req.getContextPath() + "/customer/login.jsp");
            return;
        }

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            //购物车为空，无法下单
            resp.sendRedirect(req.getContextPath() + "/customer/cart/info");
            return;
        }

        req.setCharacterEncoding("utf-8");
        String receiverName = req.getParameter("receiverName");
        String receiverTel = req.getParameter("receiverTel");
        String receiverAddress = req.getParameter("receiverAddress");
        if (receiverName == null || receiverName.isEmpty() || receiverTel == null || receiverTel.isEmpty() || receiverAddress == null || receiverAddress.isEmpty()) {
            //收货人信息不全，无法下单
            resp.sendRedirect("order.jsp");
            return;
        }

        //构造订单对象
        //TODO
        Order order = new Order();
        order.setOrderId(new Date().getTime() + "" + customer.getId());
        order.setCustomer(customer);
        order.setBookItemList(cart.getBookItemList());
        order.setMoney(cart.getTotalPrice());
        order.setCreateTime(new Date().getTime());
        order.setOrderStatus(OrderStatus.UNPAID);
        order.setReceiverName(receiverName);
        order.setReceiverTel(receiverTel);
        order.setReceiverAddress(receiverAddress);

        //保存订单
        if (orderService.save(order)) {
            //清空购物车
            cartService.clear(customer);
            session.setAttribute("cart", new Cart());
            //转发至订单支付界面
            req.setAttribute("order", order);
            req.getRequestDispatcher("pay.jsp").forward(req, resp);
        } else {
            //失败，重定向至确认订单界面
            resp.sendRedirect("order.jsp");
        }
    }
}
