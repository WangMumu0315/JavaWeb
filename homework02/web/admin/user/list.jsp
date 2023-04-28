<%--
  Created by IntelliJ IDEA.
  User: 0315
  Date: 2023/4/14
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.xll.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.xll.model.UserStatus" %>
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

<h1>管理员用户管理列表</h1>
<a href="add.jsp">添加管理员</a>

<form action="query" method="post">
    管理员用户名：<input type="text" name="name">
    <input type="submit" value="查询">
</form>


<table>
    <tr>
        <th>id</th>
        <th>管理员用户名</th>
        <th>密码</th>
        <th>创建时间</th>
        <th>最后一次访问时间</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    <%
        List<User> users = (List<User>) request.getAttribute("users");
        for (User user : users) {
    %>
    <tr>
        <td><%=user.getId() %>
        </td>
        <td><%=user.getName() %>
        </td>
        <td><%=user.getPassword() %>
        </td>
        <td><%=user.getCreateTimeString() %>
        </td>
        <td><%=user.getLastAccessTimeString() %>
        </td>
        <td><%=user.getStatus().getName() %>
        </td>
        <td><a href="modPre?id=<%=user.getId() %>">修改</a>
            <a href="reset?id=<%=user.getId() %>">重置密码</a>
            <a href="del?id=<%=user.getId() %>">删除</a>
            <%
                if (user.getStatus().equals(UserStatus.NORMAL)) {
            %>
            <a href="freeze?id=<%=user.getId() %>">冻结</a>
            <%
            } else {
            %>
            <a href="active?id=<%=user.getId() %>">解冻</a>
            <%
                }
            %>

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
