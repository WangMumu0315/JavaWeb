<%--
  Created by IntelliJ IDEA.
  User: 0315
  Date: 2023/4/14
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.xll.model.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="com.xll.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>图书管理</title>
</head>
<body>

<%
    User admin = (User) session.getAttribute("admin");
    if (admin == null) {
        response.sendRedirect(request.getContextPath() + "/admin/login.jsp");
        return;
    }
%>
<div>
    <span>你好，管理员：<%=admin.getName() %></span>
    <span><a href="<%=request.getContextPath()+"/admin/reset?id="+admin.getId() %>">重置密码</a></span>
    <span><a href="<%=request.getContextPath()+"/admin/logout" %>">退出</a></span>
</div>

<hr>
<a href="<%=request.getContextPath()+"/admin/book/list" %>">图书管理</a>
<a href="<%=request.getContextPath()+"/admin/user/list" %>">管理员管理</a>
<a href="<%=request.getContextPath()+"/admin/customer/list" %>">顾客管理</a>
<a href="<%=request.getContextPath()+"/admin/order/list" %>">订单管理</a>
<hr>

<h1>图书管理列表</h1>
<a href="add.jsp">添加图书</a>

<form action="query" method="post">
    书名：<input type="text" name="title">
    作者：<input type="text" name="author">
    出版社：<input type="text" name="press">
    <input type="submit" value="查询">
</form>

<table>
    <tr>
        <th>封面</th>
        <th>id</th>
        <th>书名</th>
        <th>作者</th>
        <th>出版社</th>
        <th>价格</th>
        <th>折扣</th>
        <th>库存</th>
        <th>出版日期</th>
        <th>上架日期</th>
        <th>操作</th>
    </tr>
    <%
        List<Book> books = (List<Book>) request.getAttribute("books");
        for (Book book : books) {
    %>
    <tr>
        <td><img src="<%=book.getCoverUrl() %>" width="50" height="50"></td>
        <td><%=book.getId() %>
        </td>
        <td><%=book.getTitle() %>
        </td>
        <td><%=book.getAuthor() %>
        </td>
        <td><%=book.getPress() %>
        </td>
        <td><%=book.getPriceString() %>
        </td>
        <td><%=book.getSale() %>
        </td>
        <td><%=book.getStock() %>
        </td>
        <td><%=book.getPublishDateString() %>
        </td>
        <td><%=book.getMarketDateString() %>
        </td>
        <td><a href="modPre?id=<%=book.getId() %>">修改</a>
            <a href="del?id=<%=book.getId() %>">删除</a>
        </td>
    </tr>
    <%
        }
    %>
</table>

<%
    //分页导航
    int p = (int) request.getAttribute("p");
    int pCount = (int) request.getAttribute("pCount");

%>
<%
    if (p > 1) {
%>
<a href="list?p=<%=p-1 %>">上一页</a>
<%
    }
%>

<%
    if (p < pCount) {
%>
<a href="list?p=<%=p+1 %>">下一页</a>
<%
    }
%>


</body>
</html>
