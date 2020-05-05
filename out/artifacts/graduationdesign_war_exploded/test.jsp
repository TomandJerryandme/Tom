<%--
  Created by IntelliJ IDEA.
  User: liuxuan
  Date: 2020/4/12
  Time: 8:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="http://apps.bdimg.com/libs/jquery/1.9.0/jquery.min.js"></script>
    <script src="/js/user_register.js"></script>
    <script src="/js/jquery-3.4.1.js"></script>
    <style>
        #imgEmoji {
            height: 20px;
            width: 20px;
        }
        #divEmoji {
        /*  存放表情的div的格式*/
            height: 200px;
            width: 230px;
            overflow: scroll;
            display: none;

        }

        #divEmoji img {
            margin: 3px;
            height: 40px;
            width: 40px;
        }

    </style>
    <script>
        function showDiv() {
            $("#divEmoji").css("display","block");
            alert($("#abc").text())
        }
        function hiddenDiv() {
            $("#divEmoji").css("display","none")
        }
    </script>
</head>
<body onload="load()">
<div>
    <h1 id="abc" style="display: none">abc</h1>
    <%--获取到所有的表情图片，foreach放在该div中，使用display:none样式，display不显示后，不会占地方--%>
    <div id="divEmoji"><%--存放表情的地方--%>
        <img src="/emoji/emoji_亲.png" onclick="hiddenDiv()">
        <img src="/emoji/emoji_大笑.png">
        <img src="/emoji/emoji_吃惊.png">
        <img src="/emoji/emoji_呆.png">
        <img src="/emoji/emoji_怒.png">
        <img src="/emoji/emoji_尴尬.png">
        <img src="/emoji/emoji_晕.png">
        <img src="/emoji/emoji_微笑.png">
        <img src="/emoji/emoji_惊呆.png">
    </div>
    <div>
        <span><h3 id="userName" style="margin-left: 20px">${sessionScope.user.username}</h3><img id="imgEmoji" src="/icon/表情.png" onclick="showDiv()"></span><br>
        <textarea id="textarea" style="width: 500px;height: 200px;margin: 5px;margin-right:10px;" name="edit"></textarea>
        <input type="button" id="send" style="width: 150px;height: 200px;background: #7082d7}" align="center" value="发送" onclick="sendMsg();sendMessage()">
    </div>
</div>
<br>
<br>
<img src="/icon/主页_fill.png" id="home">
</body>
</html>
