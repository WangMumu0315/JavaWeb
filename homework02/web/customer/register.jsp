<%--
  Created by IntelliJ IDEA.
  User: 0315
  Date: 2023/4/14
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>用户注册</title>
</head>
<body>

<h1>用户注册</h1>

<form action="reg" method="post">
    用户名：<input type="text" name="name"><br>
    密码：<input type="password" name="password"><br>
    <input type="submit" value="提交">
    <input type="reset" value="重置">
</form>

<a href="login.jsp">用户登录</a><br>
<a href="<%=request.getContextPath()+"/admin/login.jsp" %>">管理员登录</a>

</body>
</html>
