<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    展示游戏详细信息，玩家可以在这个页面进行评论，侧边栏还可以对玩家进行推送相同类型的游戏
    该页面仅此一份，所有游戏都能使用该页面
    是游戏详情的框架
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${requestScope.gamename}</title>
    <script src="/js/jquery-3.4.1.js"></script>
    <script src="/js/game_detail.js"></script>
    <link type="text/css" rel="stylesheet" href="/css/gamedetail.css">
    <link type="text/css" rel="stylesheet" href="/css/big.css">
</head>

<body onload="colorcheck(${sessionScope.theChosenRoom.roomid})">
<%--<header id="warp">
    <div style="float:right">
        &lt;%&ndash;      用于显示用户的头像和用户名 &ndash;%&gt;
        <img height="35px" width="35px" src="/image/photo/${sessionScope.user.userpic}" onclick="location='user_show.jsp';&lt;%&ndash; 这个onclick是用来点击后挑战到用户信息显示界面&ndash;%&gt;">
        <b onclick="location='user_show.jsp'">${sessionScope.user.username}</b><br/>
        <a href="/user_login.jsp" style="text-decoration:none;color: #ff314c">退出登录</a>
    </div>
    <h1 align="center">${sessionScope.theChosenRoom.roomname}</h1>

</header>--%>

<jsp:include page="Navigationbar.jsp"></jsp:include>

<div id="warp2">
        <article>
            <section>
                <img src="/roomphoto/${sessionScope.theChosenRoom.truename}" width="120px" height="180px" style="float: left">
                <h4>${sessionScope.theChosenRoom.roomname}</h4>
                房间简介：${sessionScope.theChosenRoom.introduce}<br>
<%--                点击后，跳转到聊天室的页面--%>
                <input id="gamedownload" type="button" value="进入聊天室" onclick="gameDownload(${sessionScope.theChosenRoom.roomid})"/>

                <img id="star" src="/image/star_white.gif" alt="收藏该房间" onclick="changeColor(${sessionScope.theChosenRoom.roomid})">
            </section>

        </article>

        <aside>
<%-- 这是一个JSTL循环，显示相似的聊天室--%>
            <c:forEach items="${sessionScope.reRoom}" var="thisroom">
<%--              这个div会重叠在一起  --%>
                <div onclick="location='/room/show?roomid=${thisroom.roomid}'" style="position: relative;float: left;width: 200px">
                    <img src="/roomphoto/${thisroom.roomphoto}" height="100px" width="75px" style="float: left">
                    <div>
                        <h3>${thisroom.roomname}</h3>
                        <div id="intro" style="height: 75px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap">${thisroom.introduce}</div>
                    </div>
                </div>
            </c:forEach>
        </aside>
</div>

</body>
</html>
