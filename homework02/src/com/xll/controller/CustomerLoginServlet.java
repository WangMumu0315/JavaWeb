package com.xll.controller;

import com.xll.model.Cart;
import com.xll.model.User;
import com.xll.model.UserStatus;
import com.xll.service.CartService;
import com.xll.service.CustomerService;
import com.xll.service.impl.CartServiceImpl;
import com.xll.service.impl.CustomerServiceImpl;
import com.xll.util.Encrypt;

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
@WebServlet(urlPatterns = {"/customer/login"})
public class CustomerLoginServlet extends HttpServlet {

    CustomerService customerService = new CustomerServiceImpl();
    CartService cartService = new CartServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从客户端获取用户登录信息
        req.setCharacterEncoding("utf-8");
        User loginUser = new User();
        loginUser.setName(req.getParameter("name"));
        loginUser.setPassword(req.getParameter("password"));

        //服务器端输入验证
        if (loginUser.getName() == null || loginUser.getPassword() == null || loginUser.getName().isEmpty() || loginUser.getPassword().isEmpty()) {
            //登录失败
            System.out.println("Servlet 用户登录失败: 验证失败" + loginUser);
            resp.sendRedirect("login.jsp");
            return;
        }

        //验证码输入验证
        String inputCode = req.getParameter("inputCode");
        if (inputCode == null || inputCode.isEmpty()) {
            //登录失败
            System.out.println("Servlet 用户登录失败: 验证码为空");
            resp.sendRedirect("login.jsp");
            return;
        }

        //获取会话对象
        HttpSession session = req.getSession();
        //验证验证码
        String validCode = (String) session.getAttribute("validCode");
        if (!inputCode.equalsIgnoreCase(validCode)) {
            //登录失败
            System.out.println("Servlet 用户登录失败: 验证码错误");
            System.out.println("用户输入的验证码：" + inputCode);
            System.out.println("会话中的验证码：" + validCode);
            resp.sendRedirect("login.jsp");
            return;
        }


        //调用dao方法在数据库中检查是否存在该用户
        loginUser.setPassword(Encrypt.toMd5(loginUser.getPassword()));
        User customer = customerService.get(loginUser);
        if (customer == null) {
            //登录失败
            System.out.println("Servlet 用户登录失败: 数据库查询失败" + customer);
            resp.sendRedirect("login.jsp");
            return;
        }

        // 检查帐户状态是否正常
        if (!customerService.checkStatus(customer)) {
            //登录失败
            System.out.println("Servlet 用户登录失败: 帐户状态异常" + customer);
            resp.sendRedirect("login.jsp");
            return;
        }

        //登录成功
        System.out.println("Servlet 用户登录成功: " + customer);
        customerService.modAccessTime(customer);
        //在会话范围内保存登录用户信息
        session.setAttribute("customer", customer);

        //合并用户登录前的购物车信息
        Cart cart = (Cart) session.getAttribute("cart");
        cart = cartService.merge(cart, customer);
        session.setAttribute("cart", cart);

        //顾客用户登录成功，页面跳转至图书列表页面
        resp.sendRedirect(req.getContextPath() + "/customer/book/list");
    }
}
