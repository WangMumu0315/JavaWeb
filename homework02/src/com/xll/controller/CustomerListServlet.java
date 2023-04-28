package com.xll.controller;

import com.xll.model.User;
import com.xll.service.CustomerService;
import com.xll.service.impl.CustomerServiceImpl;

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

@WebServlet(urlPatterns = {"/admin/customer/list", "/admin/customer/query"})
public class CustomerListServlet extends HttpServlet {

    CustomerService customerService = new CustomerServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 从客户端获取模糊查询条件
        req.setCharacterEncoding("utf-8"); //处理中文乱码
        User condition = new User();
        condition.setName(req.getParameter("name"));
        System.out.println("--------------模糊查询条件：" + condition.getName());

        // 从客户端获取分页信息
        int page = 1;
        String sPage = req.getParameter("p");
        if (sPage != null && !"".equals(sPage)) {
            page = Integer.parseInt(req.getParameter("p"));
        }
        int pageSize = 5;
        int usersCount = customerService.count(condition);
        int pageCount = usersCount % pageSize == 0 ? usersCount / pageSize : usersCount / pageSize + 1;

        // 从模型层获取到查询结果
        List<User> userList = customerService.get(condition, page, pageSize);

        // 在请求范围内保存用户列表数据
        req.setAttribute("customers", userList);
//        System.out.println("customers+++++++++" +userList);
        req.setAttribute("p", page);
        req.setAttribute("pCount", pageCount);
        // 页面跳转：请求转发至列表页面
        req.getRequestDispatcher("list.jsp").forward(req, resp);


    }
}
