<%@ taglib prefix="myfn" uri="http://cdu.nls/functions" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<html>
<head>
    <base href="http://${header.host}${pageContext.request.contextPath}/"/>
    <meta charset="utf-8"/>
    <title>回复留言</title>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<h1>回复留言</h1>

<div class="msg">
    <div class="subject">${msg.subject}</div>
    <div class="content">${msg.content}</div>
    <div>
        <span>留言人: ${msg.user.name}(${msg.user.id})</span>
        <span>留言时间: ${myfn:formatDatetime(msg.addMsgTime)}</span>
    </div>
</div>
</div>
<form action="reply" method="post">
    <input type="text" name="id" value="${msg.id}" readonly hidden><br>
    <textarea name="reply" rows="3" cols="50"></textarea><br>
    <input type="submit" value="回复">
    <input type="reset" value="取消">
</form>

</body>
</html>
