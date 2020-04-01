<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: liuxuan
  Date: 2020/3/26
  Time: 9:24
  To change this template use File | Settings | File Templates.
  聊天室内的显示界面:分为两个部分，如同qq一样，一个是显示用户端的文本编辑器，另外一个是显示区域
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
<%--    <meta http-equiv="refresh" content="1">--%>
    <script src="/js/jquery-3.4.1.js"></script>
    <script src="/js/message_solve.js"></script>
    <style type="text/css">
        *{
            margin: 0;
            padding: 0;
            background-color: #EAEAEA;
        }

        .center-in-center{
            width: 800px;
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
        }
    </style>
    <script>
        function transfor(message) {
            //跳转到与该用户的对话界面
            $.get("/user/transfor",{messageid:message.messageid},function (data) {
                if (data){
                    location="user_room.jsp";
                }else {
                    alert("系统出错");
                }
            })
        }
    </script>
</head>
<body style="background-image: url(image/jingse.jpg)">
    <h1 align="center">${sessionScope.objectUser.username}</h1>
    <div class="center-in-center">
        <div style="height: 400px;background-color: #fbfff9;overflow: scroll">
<%--            通过jQuery在该区域进行显示用户发送的信息,使用appendTo()方法--%>
            <div id="messageShow">
                <c:forEach items="${sessionScope.messageInit}" var="message">
                    <h4 style="color: #d79cd5;margin: 3px;margin-left: 10px"><%--${message.time}---%><span onclick="transfor(${message.messageid})">${message.user.username}</span>:${message.content}</h4>
                </c:forEach>
            </div>
        </div>
        <div style="background: #ccff33">
            显示区域与用户输入区域的分界
        </div>
        <div style="height: 300px">
            <div>
                <textarea id="textarea" style="width: 500px;height: 250px;margin: 10px;float: left" name="edit"></textarea>
                <input type="button" style="width: 150px;height: 200px" align="center" value="发送" onclick="sendMessage(1<%--${sessionScope.user.username}--%>)">
                <input type="button" value="退出房间" onclick="history.back()">
            </div>
        </div>
    </div>
    <script>
        window.scrollTo(0,document.body.scrollHeight)
    </script>
</body>
</html>
