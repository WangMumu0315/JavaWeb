<%--
  Created by IntelliJ IDEA.
  User: 0315
  Date: 2023/4/14
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.xll.model.Book" %>
<%@ page import="com.xll.model.User" %>
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
    <span><a href="<%=request.getContextPath()+"/customer/cart/info" %>">购物车</a></span>
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
    <span><a href="<%=request.getContextPath()+"/customer/cart/info" %>">购物车</a></span>
</div>
<%
        // response.sendRedirect(request.getContextPath() + "/customer/login.jsp");
        // return;
    }
%>


<h1>图书详情</h1>
<%
    Book book = (Book) request.getAttribute("book");
%>

<div>
    <img src="<%=book.getCoverUrl() %>" alt="图书封面" height="300px">
    <p>书名：<%=book.getTitle() %>
    </p>
    <p>作者：<%=book.getAuthor() %>
    </p>
    <p>出版社：<%=book.getPress() %>
    </p>
    <p>价格：<%=book.getPriceString() %>
    </p>
    <p>折扣：<%=book.getSale() %>
    </p>
    <p>出版日期：<%=book.getPublishDateString() %>
    </p>
    <p>上架日期：<%=book.getMarketDateString() %>
    </p>
    <p><a href="<%=request.getContextPath()+"/customer/cart/in?id="+book.getId() %>">加入购物车</a></p>
    <div><%=book.getInfo() %>
    </div>
</div>


</body>
</html>
