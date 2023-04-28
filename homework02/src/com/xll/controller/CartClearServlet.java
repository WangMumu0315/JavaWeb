package com.xll.controller;

import com.xll.model.Cart;
import com.xll.model.User;
import com.xll.service.CartService;
import com.xll.service.impl.CartServiceImpl;

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
@WebServlet("/customer/cart/clear")
public class CartClearServlet extends HttpServlet {

    CartService cartService = new CartServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User customer = (User) session.getAttribute("customer");

        if (customer == null) {
            //未登录用户，清空session中的购物车
            session.setAttribute("cart", new Cart());
        } else {
            //已登录用户，购物车信息保存在数据库中
            cartService.clear(customer);
        }

        //重定向到列表界面
        resp.sendRedirect("info");
    }
}

