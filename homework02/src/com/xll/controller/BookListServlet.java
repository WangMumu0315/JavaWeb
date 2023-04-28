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
import java.util.List;

/**
 * @author 谢琳琳
 * @version 1.0
 */

@WebServlet(urlPatterns = {"/admin/book/list", "/admin/book/query", "/customer/book/list"})
public class BookListServlet extends HttpServlet {

    BookService bookService = new BookServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 从客户端获取模糊查询条件
        req.setCharacterEncoding("utf-8"); //处理中文乱码
        Book condition = new Book();
        condition.setTitle(req.getParameter("title"));
        condition.setAuthor(req.getParameter("author"));
        condition.setPress(req.getParameter("press"));
        System.out.println("--------------模糊查询条件：" + condition.getTitle() + "," + condition.getAuthor() + "," + condition.getPress());

        // 从客户端获取分页信息
        int page = 1;
        String sPage = req.getParameter("p");
        if (sPage != null && !"".equals(sPage)) {
            page = Integer.parseInt(req.getParameter("p"));
        }
        int pageSize = 2;
        int booksCount = bookService.count(condition);
        int pageCount = booksCount % pageSize == 0 ? booksCount / pageSize : booksCount / pageSize + 1;

        // 从模型层获取到查询结果
        List<Book> bookList = bookService.get(condition, page, pageSize);

        // 在请求范围内保存图书列表数据
        req.setAttribute("books", bookList);
        req.setAttribute("p", page);
        req.setAttribute("pCount", pageCount);
        // 页面跳转：请求转发至列表页面
        req.getRequestDispatcher("list.jsp").forward(req, resp);


    }
}
