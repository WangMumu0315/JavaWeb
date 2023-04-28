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
@WebServlet("/customer/cart/info")
public class CartServlet extends HttpServlet {

    CartService cartService = new CartServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User customer = (User) session.getAttribute("customer");

        //未登录用户，购物车信息暂存在session中
        Cart cart = (Cart) session.getAttribute("cart");

        if (customer != null) {
            // 已登录用户，从数据库中获取购物车数据
            cart = cartService.get(customer);
        }

        if (cart == null) {
            cart = new Cart();
        }
        session.setAttribute("cart", cart);

        //删除成功或失败，都重定向到列表界面
        resp.sendRedirect("info.jsp");
    }
}

