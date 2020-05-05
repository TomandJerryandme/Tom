<%--
导航栏的页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="/css/change.css">
    <link rel="stylesheet" type="text/css" href="/css/game.css">
    <link rel="stylesheet" type="text/css" href="/css/shopcar.css">
    <script>
        function abc() {
            $.get("/user/logout",function (data) {
                if (data!="false"){
                    location = "/user_login.jsp";
                }
            })
        }
    </script>
</head>
<body>
<header id="warp">
    <div style="float:right">
        <%--      用于显示用户的头像和用户名 --%>
        <img height="35px" width="35px" src="/image/photo/${sessionScope.user.userpic}" onclick="location='user_show.jsp';<%-- 这个onclick是用来点击后挑战到用户信息显示界面--%>">
        <b onclick="location='user_show.jsp'">${sessionScope.user.username}</b><br/>
        <a href="#" onclick="abc()" style="text-decoration:none;color: #ff314c">退出登录</a>
    </div>
    <h1 align="center">聊天室</h1>

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
<div id="warp3">
    <span></span>
    <input type="text" name="gamequerytext" id="roomquerytext" placeholder="聊天室查询" style="margin-left: 10px">
    <input type="button" value="查询" onclick="gamequery()">
    <div style="float: right;">
        <img src="/image/photo/购物车.jpg" style="height: 20px;width: 20px" onclick="shopcar()"><b onclick="shopcar()">我的收藏</b>
    </div>
</div>
</body>
</html>
