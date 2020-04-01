<%--
  Created by IntelliJ IDEA.
  User: liuxuan
  Date: 2020/3/28
  Time: 12:44
  To change this template use File | Settings | File Templates.
  收藏界面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>我的收藏</title>
    <link rel="stylesheet" type="text/css" href="/css/2.css">
    <link rel="stylesheet" type="text/css" href="/css/game.css">
    <script src="/js/game_small.js"></script>
    <script src="/js/jquery-3.4.1.js"></script>
    <script src="/js/game_detail.js"></script>
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
<body onload="checkButton(${sessionScope.collectionpage.currentPage},${sessionScope.collectionpage.totalPage})">
<div>
    <jsp:include page="Navigationbar.jsp"></jsp:include>

    <%--  分页实现--%>
    <div id="warp3" align="center">
        <input type="button" value="首页" onclick="page(1)">
        <input id="lowpage" type="button" value="上一页" onclick="page($sessionScope.collectionpage.currentPage-1})"/>

        <c:if test="${sessionScope.collectionpage.currentPage!=1}">
            <input type="button" value="${sessionScope.collectionpage.currentPage-1}" onclick="page(${sessionScope.collectionpage.currentPage-1})"/>
        </c:if>

        <%--当前页--%>
        <input type="button" value="${sessionScope.collectionpage.currentPage}" style="margin-left: 10px;margin-right: 10px;background-color: #ffeb60"/>

        <c:if test="${sessionScope.collectionpage.currentPage!=sessionScope.collectionpage.totalPage}">
            <input type="button" value="${sessionScope.collectionpage.currentPage+1}" onclick="page(${asessionScope.collectionpage.currentPage+1})"/>
        </c:if>

        <input id="uppage" type="button" value="下一页" onclick="page(${sessionScope.collectionpage.currentPage+1})"/>
        <input type="button" value="尾页" onclick="page(${sessionScope.collectionpage.totalPage})">
        <span>共${sessionScope.collectionpage.currentPage}/${sessionScope.collectionpage.totalPage}页</span>
    </div>


    <div id="warp2">
        <%--用来显示room，每个room的显示都是一个div，每行4-5个room，依次排列，主要是显示封面和名字--%>
        <c:forEach items="${sessionScope.collectionpage.dataList}" var="collection">
            <div class="roomshow">
                <a href="/room/show?roomid=${collection.room.roomid}"><img src="/roomphoto/${collection.room.roomphoto}" class="roomcover"/></a><br/>
                <b><i>${collection.room.roomname}</i></b>
            </div>
        </c:forEach>

    </div>
</div>

</body>
</html>
