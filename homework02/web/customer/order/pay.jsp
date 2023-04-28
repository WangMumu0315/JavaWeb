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


<h1>已下单，请支付</h1>

<%
    Order order = (Order) request.getAttribute("order");
%>

<div>
    <p>订单编号：<%=order.getOrderId() %>
    </p>
    <p>订单创建时间：<%=order.getCreateTimeString() %>
    </p>
    <p>订单状态：<%=order.getOrderStatus().getName() %>
    </p>
    <p>收货人：<%=order.getReceiverName() %>
    </p>
    <p>收货人电话：<%=order.getReceiverTel() %>
    </p>
    <p>收货地址：<%=order.getReceiverAddress() %>
    </p>
    <p>订单金额：<%=order.getMoneyString() %>
    </p>
    <p>支付平台：
        <a href="pay?orderId=<%=order.getOrderId() %>"><img src="<%=request.getContextPath()+"/images/alipay.png" %>"
                                                            alt="支付宝" height="50px"></a>
        <a href="pay?orderId=<%=order.getOrderId() %>"><img src="<%=request.getContextPath()+"/images/wechatpay.png" %>"
                                                            alt="微信" height="50px"></a>
    </p>
    <p><a href="cancel?orderId=<%=order.getOrderId() %>">取消订单</a></p>
</div>

</body>
</html>
