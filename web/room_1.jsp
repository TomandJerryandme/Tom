<%--
  横版过关游戏
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>经济</title>
    <script>
        function test() {       //用于测试JavaScript代码的一个方法
            var i = 15;
            var f = i.toString(9);     //toString()方法是将i变为一个字符串，该字符串是9进制的对应为i的值
            var color = [];
            color.push('0'+f+'0')       //push()方法相当于字符串拼接的作用
            alert(f);
            alert(color);
        }
    </script>
    <link rel="stylesheet" type="text/css" href="/css/2.css">
    <link rel="stylesheet" type="text/css" href="/css/game.css">
    <script src="/js/game_small.js"></script>
    <script src="/js/jquery-3.4.1.js"></script>
    <script src="/js/game_detail.js"></script>
</head>
<body>

<%--<div id="divList">这一个div是用来测试JavaScript代码的</div>--%>
<%
    String id = request.getParameter("gameid");
%>
<%--<header id="warp">
    <div style="float:right">
        &lt;%&ndash;      用于显示用户的头像和用户名 &ndash;%&gt;
        <img height="35px" width="35px" src="/image/photo/${sessionScope.user.photo}" onclick="location='user_show.jsp';&lt;%&ndash; 这个onclick是用来点击后挑战到用户信息显示界面&ndash;%&gt;">
        <b onclick="location='user_show.jsp'">${sessionScope.user.username}</b><br/>
        <a href="/user_login.jsp" style="text-decoration:none;color: #ff314c">退出登录</a>
    </div>
    <h1 align="center">游戏商城</h1>
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
<%--<footer id="warp3">
    <h3 align="center">
        版权所有2018
    </h3>
</footer>--%>

</body>
</html>
