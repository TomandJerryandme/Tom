<%--
  Created by IntelliJ IDEA.
  User: liuxuan
  Date: 2020/3/31
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加屏蔽字</title>
    <script>
        function message() {
            //$.get("/message/queryinit");
            location="/message/queryinit";
        }
    </script>
    <style>
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

        .center-in-center{
            text-align: center;
            margin: 10px;
            width: 800px;
            height: 700px;
            position: absolute;
            top: 50%;
            left: 50%;
            -webkit-transform: translate(-50%, -50%);
            -moz-transform: translate(-50%, -50%);
            -ms-transform: translate(-50%, -50%);
            -o-transform: translate(-50%, -50%);
            transform: translate(-50%, -50%);
        }

        table{
            height: 200px;

            transform: translate(120%,30%);
        }
    </style>
</head>
<body style="background-image: url(images/back2.gif);background-size: 100% 100%">
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
<%--<center>--%>
<div class="center-in-center">
    <h1 style="color: #d71b0a">添加屏蔽字</h1>
<form action="/word/add" method="post">
    <table border="1px">
        <tr>
            <td>
                屏蔽词1：
            </td>
            <td>
                <input type="text" name="word1" placeholder="请输入第一个屏蔽词"/>
            </td>
        </tr>
        <tr>
            <td>
                屏蔽词2：
            </td>
            <td>
                <input type="text" name="word2" placeholder="请输入第二个屏蔽词"/>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="添加"/>
                <input type="reset" value="重置" />
                <input type="button" value="退出" onclick="location='/user_manager_index.jsp'"/>
            </td>
        </tr>
    </table>
</form>
</div>
<%--</center>--%>
</body>
</html>
