<%--
  Created by IntelliJ IDEA.
  User: 0315
  Date: 2023/4/14
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
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

<h1>添加图书</h1>

<form action="add" method="post" enctype="multipart/form-data">
    书名：<input type="text" name="title"><br>
    作者：<input type="text" name="author"><br>
    出版社：<input type="text" name="press"><br>
    价格：<input type="text" name="price" value="0.0"><br>
    折扣：<input type="text" name="sale" value="100"><br>
    库存：<input type="text" name="stock" value="0"><br>
    出版日期：<input type="text" name="publishDate" value="2023年3月"><br>
    封面：<input type="file" name="coverUrl"><br>
    简介：<textarea rows="5" cols="50" name="info">
 </textarea><br>
    <input type="submit" value="提交">
    <input type="reset" value="重置">
</form>

</body>
</html>
