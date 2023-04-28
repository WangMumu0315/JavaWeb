<%--
  Created by IntelliJ IDEA.
  User: 0315
  Date: 2023/4/14
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.xll.model.Book" %>
<%@ page import="com.xll.model.User" %>
<%@ page import="com.xll.model.Cart" %>
<%@ page import="com.xll.model.BookItem" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>购书网站</title>
</head>
<body>

<%
    User customer = (User) session.getAttribute("customer");
    if (customer != null) {
%>
<div>
    <span>你好，<%=customer.getName() %></span>
    <span><a href="<%=request.getContextPath()+"/customer/order/list" %>">订单历史</a></span>
    <span><a href="<%=request.getContextPath()+"/customer/reset?id="+customer.getId() %>">重置密码</a></span>
    <span><a href="<%=request.getContextPath()+"/customer/logout" %>">退出</a></span>
</div>
<%
} else {
%>
<div>
    <span>你好，</span>
    <span><a href="<%=request.getContextPath()+"/customer/login.jsp" %>">登录</a></span>
    <span><a href="<%=request.getContextPath()+"/customer/register.jsp" %>">注册</a></span>
</div>
<%
        // response.sendRedirect(request.getContextPath() + "/customer/login.jsp");
        // return;
    }
%>

<h1>购物车</h1>

<%
    Cart cart = (Cart) session.getAttribute("cart");
    if (cart != null) {
%>

<table>
    <tr>
        <th>图书封面</th>
        <th>书名</th>
        <th>原价</th>
        <th>折扣价</th>
        <th>购买数量</th>
        <th>删除</th>
    </tr>
    <%
        for (int i = 0; i < cart.getBookItemList().size(); i++) {
            BookItem bookItem = cart.getBookItemList().get(i);
            Book book = bookItem.getBook();
    %>
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
        <td><a href="out?id=<%=book.getId() %>">删除</a></td>
    </tr>
    <%
        }
    %>

</table>

<div>
    总价：<%=cart.getTotalPriceString() %>
</div>

<div>
    <a href="clear">清空购物车</a>
    <a href="<%=request.getContextPath()+"/customer/order/order.jsp" %>">确认下单</a>
</div>

<%
    }
%>

<div>
    <a href="<%=request.getContextPath()+"/customer/book/list" %>">继续购物</a>
</div>


<hr>


</body>
</html>
