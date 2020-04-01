<%@ page import="util.CookieUtil" %>
<%@ page import="util.EncryptUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>首页</title>
    <link rel="stylesheet" type="text/css" href="/css/2.css">
    <link rel="stylesheet" type="text/css" href="/css/game.css">
    <script src="/js/game_small.js"></script>
    <script src="/js/jquery-3.4.1.js"></script>
    <script src="/js/game_detail.js"></script>
    <style>
        html, body {margin:0; padding:0; background-color: #fbfff9;}
        #divList {width:1500px; height: 680px; z-index: 0; margin: 0px auto; overflow:hidden; position: relative;float: left;}
        .divText {position: absolute;}
        .divText span {display:block; font-weight: bold; font-family:Courier New; }
    </style>
    <script src="http://apps.bdimg.com/libs/jquery/1.9.0/jquery.min.js"></script>
    <script>
        function page(page) {
            $.get("/page/change",{page:page},function (data) {
                if (data=="true"){
                    //对页面进行刷新
                    location="/index.jsp";
                }
            })
        }
        function checkButton(page,totalpage) {
            if (page==1){
                //uppage---下一页
                $("#lowpage").prop({disabled:true});
            }
            if (page==totalpage){
                $("#uppage").prop({disabled:true});
            }
        }
    </script>
</head>
<body onload="checkButton(${applicationScope.roompage.currentPage},${applicationScope.roompage.totalPage})">

<c:if test="${sessionScope.user==null}">
    <%
        String userinfo = CookieUtil.findCookie("userinfo", request);

        if(userinfo!=null){

            String[] userinfos = EncryptUtil.decrypt(userinfo).split(":");
            String username = userinfos[0];
            String password = userinfos[1];

            //重定向
            response.sendRedirect("/user/login?username=" + username + "&password=" + password);

            return;
        }
    %>

    对不起,您尚未登录,请先<a href="/user_login.jsp">登录</a>
</c:if>
<c:if test="${sessionScope.user!=null}">
    <%--<div id="divList"></div>--%>

    <div>
        <%--<header id="warp">
            <div style="float:right">
                    &lt;%&ndash; 用于显示用户的头像和用户名 &ndash;%&gt;
                <img height="35px" width="35px" src="/image/photo/${sessionScope.user.userpic}" onclick="location='user_show.jsp';&lt;%&ndash; 这个onclick是用来点击后挑战到用户信息显示界面&ndash;%&gt;">
                <b onclick="location='user_show.jsp'">${sessionScope.user.username}</b><br/>
                <a href="/user_login.jsp" style="text-decoration:none;color: #ff314c">退出登录</a>
            </div>
            <h1 align="center">聊天室</h1>
        </header>--%>
        <jsp:include page="Navigationbar.jsp"></jsp:include>

            <%--  分页实现--%>
        <div id="warp3" align="center">
            <input type="button" value="首页" onclick="page(1)">
            <input id="lowpage" type="button" value="上一页" onclick="page(${applicationScope.roompage.currentPage-1})"/>

            <c:if test="${applicationScope.roompage.currentPage!=1}">
                <input type="button" value="${applicationScope.roompage.currentPage-1}" onclick="page(${applicationScope.roompage.currentPage-1})"/>
            </c:if>

                <%--当前页--%>
            <input type="button" value="${applicationScope.roompage.currentPage}" style="margin-left: 10px;margin-right: 10px;background-color: #ffeb60"/>

            <c:if test="${applicationScope.roompage.currentPage!=applicationScope.roompage.totalPage}">
                <input type="button" value="${applicationScope.roompage.currentPage+1}" onclick="page(${applicationScope.roompage.currentPage+1})"/>
            </c:if>

            <input id="uppage" type="button" value="下一页" onclick="page(${applicationScope.roompage.currentPage+1})"/>
            <input type="button" value="尾页" onclick="page(${applicationScope.roompage.totalPage})">
            <span>共${applicationScope.roompage.currentPage}/${applicationScope.roompage.totalPage}页</span>
        </div>



        <div id="warp2">
                <%--用来显示room，每个room的显示都是一个div，每行4-5个room，依次排列，主要是显示封面和名字--%>
            <c:forEach items="${applicationScope.roompage.dataList}" var="room">
                <div class="roomshow">
                    <a href="/room/show?roomid=${room.roomid}"><img src="/roomphoto/${room.truename}" class="roomcover"/></a><br/>
                    <b><i>${room.roomname}</i></b>
                </div>
            </c:forEach>

        </div>
    </div>
</c:if>
</body>
</html>
