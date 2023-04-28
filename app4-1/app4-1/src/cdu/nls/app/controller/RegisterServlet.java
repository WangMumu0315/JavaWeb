package cdu.nls.app.controller;

import cdu.nls.app.model.User;
import cdu.nls.app.service.UserService;
import cdu.nls.app.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        User user = new User();
        user.setName(req.getParameter("name"));
        user.setPassword(req.getParameter("password"));

        if (userService.add(user)) {
            resp.sendRedirect("login.do");
        } else {
            resp.sendRedirect("list");
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
