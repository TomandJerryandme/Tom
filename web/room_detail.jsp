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

    <script>
        function release() {
            $.get("/user/release",function (data) {
                if (data!="false"){
                    alert("请求成功，等待管理员进行处理");
                }
            })
        }
    </script>
</head>

<body onload="colorcheck(${sessionScope.theChosenRoom.roomid})" style="background-image: url(images/back15.jpg);background-size: 100% 100%">
<%--<header id="warp">
    <div style="float:right">
        &lt;%&ndash;      用于显示用户的头像和用户名 &ndash;%&gt;
        <img height="35px" width="35px" src="/image/photo/${sessionScope.user.userpic}" onclick="location='user_show.jsp';&lt;%&ndash; 这个onclick是用来点击后挑战到用户信息显示界面&ndash;%&gt;">
        <b onclick="location='user_show.jsp'">${sessionScope.user.username}</b><br/>
        <a href="/user_login.jsp" style="text-decoration:none;color: #ff314c">退出登录</a>
    </div>
    <h1 align="center">${sessionScope.theChosenRoom.roomname}</h1>

</header>--%>
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


<jsp:include page="Navigationbar.jsp"></jsp:include>

<div id="warp2">
        <article style="height: 400px">
            <section style="height: 250px">
                <img src="/roomphoto/${sessionScope.theChosenRoom.truename}" width="120px" height="180px" style="float: left">
                <h4>${sessionScope.theChosenRoom.roomname}</h4>
                房间简介：
                <div>${sessionScope.theChosenRoom.introduce}<br>
    <%--                点击后，跳转到聊天室的页面--%>
                    <input id="gamedownload" type="button" value="进入聊天室" style="visibility: ${sessionScope.legal==0? "visible" : "hidden"}" onclick="gameDownload(${sessionScope.theChosenRoom.roomid})"/>
                    <input type="button" value="请求解除禁言" onclick="release()" style="visibility: ${sessionScope.legal==0? "hidden" : "visible"}">
                    <img id="star" src="/image/star_white.gif" alt="收藏该房间" onclick="changeColor(${sessionScope.theChosenRoom.roomid})"><br>
                <h3 style="visibility: ${sessionScope.legal==0? "hidden" : "visible"}">对不起，因为您发送过多违禁言论或者管理员干涉，系统对您进行了封禁，您不能参与聊天</h3>
                </div>
            </section>

        </article>

        <aside style="overflow: scroll">
<%-- 这是一个JSTL循环，显示相似的聊天室--%>
            <c:forEach items="${sessionScope.reRoom}" var="thisroom">
<%--              这个div会重叠在一起  --%>
                <div onclick="location='/room/show?roomid=${thisroom.roomid}'" style="position: relative;float: left;width: 200px">
                    <img src="/roomphoto/${thisroom.truename}" height="100px" width="75px" style="float: left">
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
