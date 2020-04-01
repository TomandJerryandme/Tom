<%--
  Created by IntelliJ IDEA.
  User: liuxuan
  Date: 2019/11/20
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>政治</title>
    <link rel="stylesheet" type="text/css" href="/css/2.css">
    <link rel="stylesheet" type="text/css" href="/css/game.css">
    <script src="/js/game_small.js"></script>
    <script src="/js/jquery-3.4.1.js"></script>
    <script src="/js/game_detail.js"></script>
</head>
<body>
<%--<header id="warp">
    <div style="float:right">
        &lt;%&ndash;      用于显示用户的头像和用户名 &ndash;%&gt;
        <img height="35px" width="35px" src="/image/photo/${sessionScope.user.photo}" onclick="location='user_show.jsp';&lt;%&ndash; 这个onclick是用来点击后挑战到用户信息显示界面&ndash;%&gt;">
        <b onclick="location='user_show.jsp'">${sessionScope.user.username}</b><br/>
        <a href="/user_login.jsp" style="text-decoration:none;color: #ff314c">退出登录</a>
    </div>
    <h1 align="center">聊天室</h1>
</header>--%>
<jsp:include page="Navigationbar.jsp"></jsp:include>

<div id="warp2">
    <%--用来显示游戏，每个游戏的显示都是一个div，每行4-5个游戏，依次排列，主要是显示封面和名字，以及价格--%>
        <c:forEach items="${applicationScope.typeRoomList.dataList}" var="room">
            <div class="roomshow">
                <a href="/room/show?roomid=${room.roomid}"><img src="/roomphoto/${room.truename}" class="roomcover"/></a><br/>
                <b><i>${room.roomname}</i></b>
            </div>
        </c:forEach>


</div>
</body>
</html>
