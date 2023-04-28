<%--
  Created by IntelliJ IDEA.
  User: 0315
  Date: 2023/4/14
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.xll.model.Book" %>
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
<a href="<%=request.getContextPath()+"/admin/customer/list" %>">普通用户管理</a>
<a href="<%=request.getContextPath()+"/admin/order/list" %>">订单管理</a>
<hr>

<h1>修改图书</h1>

<%
    Book book = (Book) request.getAttribute("book");
%>

<form action="mod" method="post" enctype="multipart/form-data">
    id：<input type="text" name="id" readonly value="<%=book.getId() %>"><br>
    书名：<input type="text" name="title" value="<%=book.getTitle() %>"><br>
    作者：<input type="text" name="author" value="<%=book.getAuthor() %>"><br>
    出版社：<input type="text" name="press" value="<%=book.getPress() %>"><br>
    价格：<input type="text" name="price" value="<%=book.getPrice() %>"><br>
    折扣：<input type="text" name="sale" value="<%=book.getSale() %>"><br>
    库存：<input type="text" name="stock" value="<%=book.getStock() %>"><br>
    出版日期：<input type="text" name="publishDate" value="<%=book.getPublishDateString() %>"><br>
    上架日期：<input type="text" name="marketDate" value="<%=book.getMarketDateString() %>"><br>
    封面：<input type="hidden" name="coverUrl" value="<%=book.getCoverUrl() %>"><input type="file" name="coverUrl"><br>
    简介：<textarea rows="5" cols="50" name="info">
 <%=book.getInfo() %>
 </textarea><br>
    <input type="submit" value="提交">
    <input type="reset" value="重置"><br>
    <img src="<%=book.getCoverUrl() %>" height="300px" alt="图书封面">
</form>

</body>
</html>
