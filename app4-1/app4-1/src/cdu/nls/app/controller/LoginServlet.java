package cdu.nls.app.controller;

import cdu.nls.app.model.User;
import cdu.nls.app.service.UserService;
import cdu.nls.app.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        HttpSession session = req.getSession();
        User user = userService.get(name, password);

        if (user != null) {
            session.setAttribute("user", user);
        }

//        req.getRequestDispatcher("list.do").forward(req, resp);
        resp.sendRedirect("list");
    }

}
