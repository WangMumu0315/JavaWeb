package com.xll.controller;

import com.xll.model.Book;
import com.xll.service.BookService;
import com.xll.service.impl.BookServiceImpl;

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
@WebServlet("/customer/book/info")
public class BookInfoServlet extends HttpServlet {

    BookService bookService = new BookServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sid = req.getParameter("id");
        if (sid != null && !sid.equals("")) {
            //使用客户端传递的id值在数据库中查询图书
            Book book = bookService.get(Integer.parseInt(sid));
            //在请求范围内保存查询到的图书对象
            req.setAttribute("book", book);
            //请求转发到图书详情页面
            req.getRequestDispatcher("info.jsp").forward(req, resp);
        } else {
            //未获取到id参数，则无法获取图书信息，重定向到列表界面
            resp.sendRedirect("list");
        }

    }
}

