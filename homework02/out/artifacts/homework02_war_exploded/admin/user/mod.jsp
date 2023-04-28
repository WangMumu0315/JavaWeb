<%--
  Created by IntelliJ IDEA.
  User: 0315
  Date: 2023/4/14
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.xll.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>管理员用户管理</title>
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

<h1>修改管理员</h1>

<%
    User user = (User) request.getAttribute("user");
%>

<form action="mod" method="post">
    id：<input type="text" name="id" readonly value="<%=user.getId() %>"><br>
    用户名：<input type="text" name="name" value="<%=user.getName() %>"><br>
    <input type="submit" value="提交">
    <input type="reset" value="重置"><br>

    <input type="text" name="status" value="<%=user.getStatus()%>" hidden="hidden"><br>
    <input type="text" name="createTime" value="<%=user.getCreateTime()%>" hidden="hidden"><br>
    <input type="text" name="lastAccessTime" value="<%=user.getLastAccessTime()%>" hidden="hidden"><br>

</form>

</body>
</html>
