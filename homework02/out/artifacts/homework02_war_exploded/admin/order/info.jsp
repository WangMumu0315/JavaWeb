<%--
  Created by IntelliJ IDEA.
  User: 0315
  Date: 2023/4/14
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="com.xll.model.*" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>购书网站</title>
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

<h1>订单详情</h1>

<%
    Order order = (Order) request.getAttribute("order");
    if (order == null) {
        out.print("<h2>未获取到订单详情</h2>");
        return;
    }

%>

<table>
    <tr>
        <th>图书封面</th>
        <th>书名</th>
        <th>原价</th>
        <th>折扣价</th>
        <th>购买数量</th>
    </tr>
    <%
        List<BookItem> bookItemList = order.getBookItemList();
        if (bookItemList != null) {
            for (int i = 0; i < bookItemList.size(); i++) {
                BookItem item = bookItemList.get(i);
                Book book = item.getBook();
    %>
    <tr>
        <td><a href="<%=request.getContextPath()+"/customer/book/info?id="+book.getId() %>"><img
                src="<%=book.getCoverUrl() %>" height="50px" alt="图书封面"></a>
        </td>
        <td>
            <a href="<%=request.getContextPath()+"/customer/book/info?id="+book.getId() %>"><%=book.getTitle() %>
            </a>
        </td>
        <td><%=book.getPriceString() %>
        </td>
        <td><%=book.getSalePriceString() %>
        </td>
        <td><%=item.getNum() %>
        </td>
    </tr>
    <%
            }
        }
    %>

</table>


<div>
    <p>订单编号：<%=order.getOrderId() %>
    </p>
    <p>订单创建时间：<%=order.getCreateTimeString() %>
    </p>
    <p>订单状态：<%=order.getOrderStatus().getName() %>
    </p>
    <%
        if (order.getOrderStatus().equals(OrderStatus.SHIPPED)) {
    %>
    <p>物流单号：<%=order.getExpressNumber() %>
    </p>
    <%
    } else if (order.getOrderStatus().equals(OrderStatus.PAID)) {
    %>
    <form action="shipped?orderId=<%=order.getOrderId() %>">
        <input type="text" name="expressNumber">
        <input type="submit" value="发货"/>
    </form>
    <%
        }
    %>

    <p>状态更新时间：<%=order.getUpdateTimeString() %>
    </p>
    <p>收货人：<%=order.getReceiverName() %>
    </p>
    <p>收货人电话：<%=order.getReceiverTel() %>
    </p>
    <p>收货地址：<%=order.getReceiverAddress() %>
    </p>
    <p>订单金额：<%=order.getMoneyString() %>
    </p>
</div>

</body>
</html>
