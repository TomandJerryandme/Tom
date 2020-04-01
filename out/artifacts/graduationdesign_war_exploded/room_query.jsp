<%--

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
<jsp:include page="Navigationbar.jsp"></jsp:include>

<div id="warp2">
<%--用来显示游戏，每个游戏的显示都是一个div，每行4-5个游戏，依次排列，主要是显示封面和名字，以及价格--%>
    <c:forEach items="${sessionScope.queryRoom}" var="room">
        <div class="roomshow">
            <a href="/room/show?roomid=${room.roomid}"><img src="/roomphoto/${room.roomphoto}" class="roomcover"/></a><br/>
            <b><i>${room.roomname}</i></b>
        </div>
    </c:forEach>

</div>
</body>
</html>
