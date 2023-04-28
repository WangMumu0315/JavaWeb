package com.xll.controller;

import com.xll.model.BookItem;
import com.xll.model.Cart;
import com.xll.model.User;
import com.xll.service.BookService;
import com.xll.service.CartService;
import com.xll.service.impl.BookServiceImpl;
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
@WebServlet("/customer/cart/in")
public class CartInServlet extends HttpServlet {

    CartService cartService = new CartServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sid = req.getParameter("id");
        int bookId = 0;
        if (sid != null && !sid.equals("")) {
            //获取要加入购物车的图书id
            bookId = Integer.parseInt(sid);

        }
//        System.out.println("获取id："+bookId);

        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
//        System.out.println("cart +++++++"+cart);
        cart = cartService.putIn(cart, bookId);
        session.setAttribute("cart", cart);

        //删除成功或失败，都重定向到列表界面
        resp.sendRedirect("info");
    }
}
