<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<html>
<head>
    <base href="http://${header.host}${pageContext.request.contextPath}/"/>
    <meta charset="utf-8"/>
    <title>留言本</title>
</head>
<body>
<h1>用户登录</h1>

<form action="login" method="post">
    用户名：<input type="text" name="name"><br>
    密码：<input type="password" name="password"><br>
    <input type="submit" value="登  录">
</form>

</body>
</html>
