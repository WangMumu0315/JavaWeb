<%--
  Created by IntelliJ IDEA.
  User: 0315
  Date: 2023/4/14
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="com.xll.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>订单管理</title>
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

<h1>订单列表</h1>

<form action="query" method="post">
    订单编号：<input type="text" name="orderId">
    顾客id：<input type="text" name="customerId">
    订单状态：
    <select name="status">
        <option value="未付款">未付款</option>
        <option value="已付款">已付款</option>
        <option value="已发货">已发货</option>
        <option value="已完成">已完成</option>
        <option value="已取消">已取消</option>
        <option value="异常">异常</option>
    </select>
    <input type="submit" value="查询">
</form>

<%
    List<Order> orders = (List<Order>) request.getAttribute("orders");
    if (orders == null || orders.isEmpty()) {
        out.print("<h2>暂无订单</h2>");
        out.close();
    }
%>

<table>
    <tr>
        <th>订单编号</th>
        <th>顾客</th>
        <th>创建时间</th>
        <th>订单金额</th>
        <th>收货人</th>
        <th>订单状态</th>
        <th>详情</th>
        <th>物流单号</th>
    </tr>
    <%
        for (Order order : orders) {
    %>
    <tr>
        <td><%=order.getOrderId() %>
        </td>
        <td><%=order.getCustomer().getName() %>
        </td>
        <td><%=order.getCreateTimeString() %>
        </td>
        <td><%=order.getMoneyString() %>
        </td>
        <td><%=order.getReceiverName() %>
        </td>
        <td><%=order.getOrderStatus().getName() %>
        </td>
        <td>
            <a href="info?orderId=<%=order.getOrderId() %>">订单详情</a>
        </td>
        <td>
            <%
                if (order.getOrderStatus().equals(OrderStatus.SHIPPED)) {
                    out.print(order.getExpressNumber());
                } else if (order.getOrderStatus().equals(OrderStatus.PAID)) {
            %>
            <form action="shipped">
                <input type="text" name="orderId" value="<%=order.getOrderId() %>" hidden>
                <input type="text" name="expressNumber">
                <input type="submit" value="发货"/>
            </form>
            <%
                }
            %>
        </td>
    </tr>
    <%
        }
    %>

</table>


</body>
</html>
