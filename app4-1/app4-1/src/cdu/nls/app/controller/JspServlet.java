package cdu.nls.app.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("*.do")
public class JspServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getRequestURI();
//        System.out.println("path: " + path);
        String root = req.getContextPath();
//        System.out.println("root: " + root);
        path = path.substring(root.length(), path.length() - 3);
        path = "/WEB-INF/jsp" + path + ".jsp";
//        System.out.println("path: " + path);

        req.getRequestDispatcher(path).forward(req, resp);
    }
}
