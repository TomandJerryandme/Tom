<%--
  Created by IntelliJ IDEA.
  User: liuxuan
  Date: 2020/3/31
  Time: 14:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>title</title>

</head>
<body style="background-image: url(image/jingse.jpg)">
<h1 align="center">${sessionScope.objectUser.username}</h1>
<div class="center-in-center">
    <div style="height: 400px;background-color: #fbfff9">
        <%--            通过jQuery在该区域进行显示用户发送的信息,使用appendTo()方法--%>
        <div id="messageShow">
            <h3 align="center">与${sessionScope.objectUser.username}的对话</h3>
        </div>
    </div>
    <div style="background: #ccff33">
        显示区域与用户输入区域的分界
    </div>
    <div style="height: 300px">
        <div>
            <textarea id="textarea" style="width: 500px;height: 250px;margin: 10px;float: left" name="edit"></textarea>
            <input type="button" style="width: 150px;height: 200px" align="center" value="发送" onclick="sendMessage(<%--${sessionScope.user.username}--%>)">
        </div>
    </div>
</div>
</body>
</html>
