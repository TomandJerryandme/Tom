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
    <script src="/js/changPage.js"></script>
    <script src="/js/game_small.js"></script>
    <script src="/js/jquery-3.4.1.js"></script>
    <script src="/js/game_detail.js"></script>
    <style>
        .asidestyle{
            float: left;
            padding: 10px;
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
<body  style="background-image: url(images/back15.jpg);background-size: 100% 100%" onload="checkButton(${applicationScope.typeRoomList.currentPage},${applicationScope.typeRoomList.totalPage})">
<h6 id="type">1</h6>
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
                <img src="/icon/主页_fill.png" style="height: 25px;width: 25px">
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


<div id="warp3" align="center">
    <input type="button" value="首页" onclick="page(1)">
    <input id="lowpage" type="button" value="上一页" onclick="page(${applicationScope.typeRoomList.currentPage-1})"/>

    <c:if test="${applicationScope.typeRoomList.currentPage!=1}">
        <input type="button" value="${applicationScope.typeRoomList.currentPage-1}" onclick="page(${applicationScope.typeRoomList.currentPage-1})"/>
    </c:if>

    <%--当前页--%>
    <input type="button" value="${applicationScope.typeRoomList.currentPage}" style="margin-left: 10px;margin-right: 10px;background-color: #ffeb60"/>

    <c:if test="${applicationScope.typeRoomList.currentPage!=applicationScope.typeRoomList.totalPage}">
        <input type="button" value="${applicationScope.typeRoomList.currentPage+1}" onclick="page(${applicationScope.typeRoomList.currentPage+1})"/>
    </c:if>

    <input id="uppage" type="button" value="下一页" onclick="page(${applicationScope.typeRoomList.currentPage+1})"/>
    <input type="button" value="尾页" onclick="page(${applicationScope.typeRoomList.totalPage})">
    <span>共${applicationScope.typeRoomList.currentPage}/${applicationScope.typeRoomList.totalPage}页</span>
</div>



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
