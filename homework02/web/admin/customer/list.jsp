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
    <title>普通用户管理</title>
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

<h1>用户(顾客)管理列表</h1>

<form action="query" method="post">
    顾客用户名：<input type="text" name="name">
    <input type="submit" value="查询">
</form>

<table>
    <tr>
        <th>id</th>
        <th>用户名</th>
        <th>创建时间</th>
        <th>最后一次访问时间</th>
        <th>密码重置</th>
        <th>帐户状态</th>
        <th>操作</th>
    </tr>
    <%
        List<User> customers = (List<User>) request.getAttribute("customers");
        for (User customer : customers) {
    %>
    <tr>
        <td><%=customer.getId() %>
        </td>
        <td><%=customer.getName() %>
        </td>
        <td><%=customer.getCreateTimeString() %>
        </td>
        <td><%=customer.getLastAccessTimeString() %>
        </td>
        <td><%=customer.getStatus().getName() %>
        </td>
        <td><a href="reset?id=<%=customer.getId() %>">密码重置</a></td>
        <td>
            <%
                if (customer.getStatus().equals(UserStatus.NORMAL)) {
            %>
            <a href="freeze?id=<%=customer.getId() %>">冻结</a>
            <%
            } else {
            %>
            <a href="active?id=<%=customer.getId() %>">解冻</a>
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
