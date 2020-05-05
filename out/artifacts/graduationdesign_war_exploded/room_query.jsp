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

    <style>
        .asidestyle{
            float: left;
            padding: 10px;
            /*background: #d79957;*/
            height: 100%;
            width: 200px;
        }
        .asidestyle>div{
            /*  侧边栏中div的样式表  */
            width: 100%;
            text-align: center;
            child-align: middle;
            height: 100px;
        }

        .asidestyle>div>a{
            font-size: 25px;
        }
        table{
            height: 600px;
        }
        table tr td{
            font-size: 20px;
        }
    </style>
</head>
<body style="background-image: url(images/back15.jpg);background-size: 100% 100%">
<aside class="asidestyle">
    <table>
        <tr>
            <div style="height: 50px; text-align: center"><img src="/image/photo/${sessionScope.user.userpic}" align="center"></div>
        </tr>
        <tr>
            <div>
                <img src="/icon/user.png" style="height: 25px;width: 25px">
                <a href="/user_show.jsp" style="text-decoration:none">个人中心</a>
            </div>
        </tr>
        <tr>
            <div>
                <img src="/icon/collection.png" style="height: 25px;width: 25px">
                <a href="#" onclick="shopcar()" style="text-decoration:none">我的收藏</a>
            </div>
        </tr>
        <tr>
            <div>
                <img src="/icon/主页.png" style="height: 25px;width: 25px">
                <a href="/index.jsp" style="text-decoration:none">网站主页</a>
            </div>
        </tr>
        <tr>
            <div>
                <img src="/icon/联系.png" style="height: 25px;width: 25px">
                <a href="/contact.jsp" onclick="shopcar()" style="text-decoration:none">联系我</a>
            </div>
        </tr>
    </table>


    <%--    <a href="#" onclick="ordershow()" style="text-decoration:none">我的购买记录</a><br/>--%>
    <%--    <div ><a href="/user_show.jsp" style="text-decoration:none">个人中心</a><br/></div>

        <div><a href="#" onclick="shopcar()" style="text-decoration:none">我的收藏</a><br/></div>--%>
</aside>
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
