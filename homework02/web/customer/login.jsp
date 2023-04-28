<%--
  Created by IntelliJ IDEA.
  User: 0315
  Date: 2023/4/14
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.xll.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>用户登录</title>
</head>
<body>

<h1>用户登录</h1>

<form action="login" method="post">
    用户名：<input type="text" name="name"><br>
    密码：<input type="password" name="password"><br>
    验证码：<input type="text" name="inputCode">
    <img src="validCode" width="100px" height="20px" id="vCode" onclick="refreshCode()"><br>
    <input type="submit" value="提交">
    <input type="reset" value="重置">
</form>

<a href="register.jsp">新用户注册</a><br>
<a href="<%=request.getContextPath()+"/admin/login.jsp" %>">管理员登录</a>

</body>
</html>

<script>
    function refreshCode() {
        document.getElementById("vCode").src = "validCode?r=" + Math.random();
    }
</script>
