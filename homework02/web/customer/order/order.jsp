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
    <span><a href="<%=request.getContextPath()+"/customer/order/list" %>">订单历史</a></span>
    <span><a href="<%=request.getContextPath()+"/customer/reset?id="+customer.getId() %>">重置密码</a></span>
    <span><a href="<%=request.getContextPath()+"/customer/logout" %>">退出</a></span>
</div>


<h1>确认下单</h1>

<%
    Cart cart = (Cart) session.getAttribute("cart");
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
        for (int i = 0; i < cart.getBookItemList().size(); i++) {
            BookItem bookItem = cart.getBookItemList().get(i);
            Book book = bookItem.getBook(); %>
    <tr>
        <td><a href="<%=request.getContextPath()+"/customer/book/info?id="+book.getId() %>"><img
                src="<%=book.getCoverUrl() %>" height="50px" alt="图书封面"></a>
        </td>
        <td><a href="<%=request.getContextPath()+"/customer/book/info?id="+book.getId() %>"><%=book.getTitle() %>
        </a>
        </td>
        <td><%=book.getAuthor() %>
        </td>
        <td><%=book.getPriceString() %>
        </td>
        <td><%=book.getSalePriceString() %>
        </td>
        <td><%=bookItem.getNum() %>
        </td>
    </tr>
    <%
        }
    %>

</table>

<div>
    总价：<%=cart.getTotalPriceString() %>
</div>

<form action="submit" method="post">
    收货人：<input type="text" name="receiverName"><br>
    收货人电话：<input type="text" name="receiverTel"><br>
    收货地址：<input type="text" name="receiverAddress"><br>
    <input type="submit" value="确认下单">
</form>
<div>
    <a href="<%=request.getContextPath()+"/customer/book/list" %>">取消下单，继续购物</a>
</div>

<hr>

</body>
</html>
