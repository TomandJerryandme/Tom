<%--
  Created by IntelliJ IDEA.
  User: liuxuan
  Date: 2020/3/31
  Time: 8:55
  To change this template use File | Settings | File Templates.
  管理员的主页面，增加房间，增加屏蔽词等。
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
这是管理员用户的界面<br>
<input type="button" value="添加屏蔽字" onclick="location='word_add.jsp'">
<br>
<input type="button" value="添加聊天室" onclick="location='room_add.jsp'">
<br>
<input type="button" value="删除屏蔽字" onclick="location='word_remove.jsp'">
<br>
<br>
<input type="button" value="退出" onclick="location='user_login.jsp'">


</body>
</html>
