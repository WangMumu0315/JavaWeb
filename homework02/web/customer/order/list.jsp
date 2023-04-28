<%--
  Created by IntelliJ IDEA.
  User: 0315
  Date: 2023/4/14
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="com.xll.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>购书网站</title>
</head>
<body>

<%
    User customer = (User) session.getAttribute("customer");
    if (customer == null) {
        response.sendRedirect(request.getContextPath() + "/customer/login.jsp");
        return;
    }
%>
<div>
    <span>你好，<%=customer.getName() %></span>
    <span><a href="<%=request.getContextPath()+"/customer/book/list" %>">继续购物</a></span>
    <span><a href="<%=request.getContextPath()+"/customer/cart/info" %>">购物车</a></span>
    <span><a href="<%=request.getContextPath()+"/customer/reset?id="+customer.getId() %>">重置密码</a></span>
    <span><a href="<%=request.getContextPath()+"/customer/logout" %>">退出</a></span>
</div>

<h1>订单历史</h1>

<%
    List<Order> orders = (List<Order>) request.getAttribute("orders");
    if (orders == null || orders.isEmpty()) {
        out.print("<h2>暂无订单</h2>");
        return;
    }
%>

<table>
    <tr>
        <th>订单编号</th>
        <th>创建时间</th>
        <th>订单金额</th>
        <th>收货人</th>
        <th>订单状态</th>
        <th>操作</th>
    </tr>
    <%
        for (Order order : orders) {
    %>
    <tr>
        <td><%=order.getOrderId() %>
        </td>
        <td><%=order.getCreateTimeString() %>
        </td>
        <td><%=order.getMoneyString() %>
        </td>
        <td><%=order.getReceiverName() %>
        </td>
        <td>
            <%
                out.print(order.getOrderStatus().getName());
                switch (order.getOrderStatus()) {
                    case SHIPPED:
                        out.print(" 物流单号：" + order.getExpressNumber());
                        out.print(" <a href='finish?orderId=" + order.getOrderId() + "'>确认收货</a>");
                        break;
                    case UNPAID:
                    case PAID:
                        out.print(" <a href='cancel?orderId=" + order.getOrderId() + "'>取消订单</a>");
                        break;
                    case CANCEL:
                        out.print(" <a href='del?orderId=" + order.getOrderId() + "'>删除订单</a>");
                }
            %>
        </td>
        <td><a href="info?orderId=<%=order.getOrderId() %>">订单详情</a></td>
    </tr>
    <%
        }
    %>

</table>


</body>
</html>
