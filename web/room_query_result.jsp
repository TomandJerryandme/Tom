<%--
        没有找到相应游戏时
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>游戏查询</title>
    <link rel="stylesheet" type="text/css" href="/css/2.css">
    <link rel="stylesheet" type="text/css" href="/css/game.css">
    <script src="/js/game_small.js"></script>
    <script src="/js/jquery-3.4.1.js"></script>
</head>
<body>

<%--<div id="divList">这一个div是用来测试JavaScript代码的</div>--%>
<%
    String id = request.getParameter("gameid");
%>
<header id="warp">
    <div style="float:right">
        <%--      用于显示用户的头像和用户名 --%>
        <img height="35px" width="35px" src="/image/photo/${sessionScope.user.userpic}" onclick="location='user_show.jsp';<%-- 这个onclick是用来点击后挑战到用户信息显示界面--%>">
        <b onclick="location='user_show.jsp'">${sessionScope.user.username}</b><br/>
        <a href="/user_login.jsp" style="text-decoration:none;color: #ff314c">退出登录</a>
    </div>
    <h1 align="center">游戏商城</h1>
</header>
<nav id="warp1" class="top">
    <ul>

        <li><a href="/index.jsp">首页</a></li>
        <li><a href="/typeroom/init?typeid=1">经济</a></li>
        <li><a href="/typeroom/init?typeid=2">政治</a></li>
        <li><a href="/typeroom/init?typeid=3">军事</a></li>
        <li><a href="/typeroom/init?typeid=4">教育</a></li>
        <li><a href="/typeroom/init?typeid=5">动漫</a></li>
        <li><a href="/typeroom/init?typeid=6">法律</a></li>
        <li><a href="/typeroom/init?typeid=7">生活</a></li>
    </ul>
</nav>

<div id="warp2">
<%--用来显示游戏，每个游戏的显示都是一个div，每行4-5个游戏，依次排列，主要是显示封面和名字，以及价格--%>
    <img src="/image/notFount.jpg" style="height: 150px;width: 300px">
    <h1>对不起，没有找到相对应的游戏>_<</h1>

</div>
</body>
</html>
