<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="myfn" uri="http://cdu.nls/functions" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<html>
<head>
    <base href="http://${header.host}${pageContext.request.contextPath}/"/>
    <meta charset="utf-8"/>
    <title>留言本</title>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<div class="header">
    <c:if test="${empty user}">
        <div>如需留言，请先<a href='login.do'>登录</a>
            或<a href="'register.do">注册</a></div>
    </c:if>
    <c:if test="${!empty user}">
        <div>你好，${user.name}     <a href="logout">退出</a></div>
    </c:if>
</div>

<h1>所有留言</h1>

<c:forEach items="${msgList}" varStatus="s" var="msg">
    <div class="msg">
        <div class="subject">${msg.subject}</div>
        <div class="content">${msg.content}</div>
        <div><span>留言人: ${msg.user.name}</span>
            <span>留言时间: ${myfn:formatDatetime(msg.addMsgTime)}</span>
            <c:if test="${msg.isReplied==1 && user.isAdmin}">
                <a href="reply?id=${msg.id}">回 复</a>
            </c:if>
            <c:if test="${msg.user.id==user.id||user.isAdmin}">
                <a href="delMsg?id=${msg.id}">删 除</a>
            </c:if>
        </div>
        <c:if test="${msg.isReplied==2}">
            <div class="reply">
                ${msg.reply}
                <div><span>回复人: ${msg.reUser.name}</span>
                    <span>回复时间: ${myfn:formatDatetime(msg.reTime)}</span></div>
            </div>
        </c:if>
    </div>

</c:forEach>

<div class="page">
    <c:if test="${page>1}">
        <a href="list?page=${page-1}">上一页</a>
    </c:if>
    <c:if test="${page<pageCount}">
        <a href="list?page=${page+1}">下一页</a>
    </c:if>
</div>

<c:if test="${!empty user}">
    <form action="addMsg" method="post">
        主题：<input type="text" name="subject"><br>
        内容：<textarea name="content" rows="5" cols="25"></textarea><br>
        <input type="submit" value="留 言">
    </form>
</c:if>


</body>
</html>
