<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: liuxuan
  Date: 2020/3/31
  Time: 8:55
  To change this template use File | Settings | File Templates.
  管理员的主页面，增加房间，增加屏蔽词,主页主要进行等。该页面还需要进行页面的优化
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<head>
    <title>Title</title>
    <script src="/js/jquery-3.4.1.js"></script>
    <script>
        function message() {
            //$.get("/message/queryinit");
            location="/message/queryinit";
        }

        function jinyan(messageid) {
            $.get("/user/jinyan",{messageid:messageid},function (data) {
                if (data!="false"){
                    location = "/user_manager_index.jsp";
                }
            })
        }

        function jiechu(userid) {
            $.get("/user/forbid",{userid:userid},function (data) {
                if (data!="false"){
                    location = "/user_manager_index.jsp";
                }
            })
        }

        function userjinyan(userid){
            $.get("/user/forbidden",{userid:userid},function (data) {
                if (data!="false"){
                    location = "/user_manager_index.jsp";
                }
            })
        }
    </script>


    <style>

        .center-in-center{
            /*z-index: 1;*/
            margin: 10px;
            width: 780px;
            height: 700px;
            position: absolute;
            border: 1px solid purple;
            top: 50%;
            left: 50%;
            -webkit-transform: translate(-50%, -50%);
            -moz-transform: translate(-50%, -50%);
            -ms-transform: translate(-50%, -50%);
            -o-transform: translate(-50%, -50%);
            transform: translate(-50%, -50%);
            overflow: scroll;
        }

        .asidestyle{
            float: right;
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
            height: 150px;
        }

        .asidestyle>div>a{
            font-size: 25px;
        }
    </style>
</head>
<body style="background-image: url(images/back15.jpg);background-size: 100% 100%;background-repeat: no-repeat;height: 100%">
<aside class="asidestyle">
    <table>
        <tr>
            <div style="height: 50px; text-align: center"><img src="/image/photo/${sessionScope.user.userpic}" align="center"></div>
        </tr>
        <tr>
            <div>
                <img src="/icon/添加-2.png" style="height: 25px;width: 25px">
                <a href="/room_add.jsp" style="text-decoration:none">添加房间</a>
            </div>
        </tr>
        <tr>
            <div>
                <img src="/icon/添加.png" style="height: 25px;width: 25px">
                <a href="/word_add.jsp" style="text-decoration:none">添加屏蔽字</a>
            </div>
        </tr>
        <tr>
            <div>
                <img src="/icon/删除.png" style="height: 25px;width: 25px">
                <a href="/word_remove.jsp" style="text-decoration:none">删除屏蔽字</a>
            </div>
        </tr>
        <tr>
            <div>
                <img src="/icon/查询.png" style="height: 25px;width: 25px">
                <a href="#" onclick="message()" style="text-decoration:none">查询</a>
            </div>
        </tr>
    </table>
</aside>

<div class="center-in-center">
    <div style="text-align: center">
        <h1>这是管理员用户的界面</h1><br>
    </div>


    <%--这个页面用来解除用户禁言--%>
    <table border="1px" cellpadding="10px" style="float: left">
        <tr>
            <th>用户</th>
            <th>内容</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${applicationScope.releaseList}" var="message">
            <tr>
                <td>
                        ${message.user.username}
                </td>
                <td>
                        ${message.content}
                </td>
                <td>
                    <a href="#" onclick="jinyan(${message.messageid})">解除禁言</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <table style="float: right" border="1px" cellpadding="10px">
        <tr>
            <th>
                用户名
            </th>
            <th colspan="3">
                操作
            </th>
            <th>
                状态
            </th>
        </tr>
        <c:forEach items="${applicationScope.allUser}" var="user">
            <tr>
                <td>${user.username}</td>
                <td><a href="#" onclick="userjinyan(${user.userid})">禁言</a></td>
                <td><a href="#" onclick="jiechu(${user.userid})">解除禁言</a></td>
                <td><a href="#">查看用户信息</a></td>
                <td>状态<%--分为正常与禁言状态，或者登录状态--%></td>
            </tr>
        </c:forEach>
        <tr>

        </tr>
    </table>
</div>


</body>
</html>
