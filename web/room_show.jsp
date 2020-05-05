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
    <script src="http://apps.bdimg.com/libs/jquery/1.9.0/jquery.min.js"></script>
    <script src="/js/user_register.js"></script>
    <style type="text/css">

        <%-- 在该页面来与服务端建立连接 --%>
       /* *{
            margin: 0;
            padding: 0;
            background-color: #EAEAEA;
        }
*/
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
        }

        #divHistory{
            text-align: center;
        }
    </style>


    <style>
        html, body {margin:0; padding:0; background-color: #fbfff9;}
        #divList {width:1500px; height: 680px;  margin: 0px auto; overflow:hidden; position: relative;float: left;}
        .divText {position: absolute;}
        .divText span {display:block; font-weight: bold; font-family:Courier New; }
    </style>

    <script>
        function rand(min, max)
        {
            return min + Math.round(Math.random() * (max - min));
            //作用：取出在min-max之间的一个字符
        }
        function add()
        {
            var x = rand(0, 1500);
            var html = '<div class="divText" style="left:' + x + 'px; bottom:500px;">';
            var color = [];
            for (var i=1; i<17; i++)
            {
                var f = i.toString(16);
                color.push('0' + f + '0');
            }
            var fontSize = rand(9, 24);     //用于决定字体的大小
            for (var i=1; i<17; i++)
            {
                var c = rand(33, 127);      //用于取显示的字母范围
                // var c = rand(48,57);
                var c = String.fromCharCode(c);
                html += '<span class="s' + i + '" style="color:#' + color[i-1] + '; font-size:' + fontSize + 'px; text-shadow:0px 0px 10px #' + color[i-1] + ';">' + c + '</span>';
            }
            html += '</div>';
            $('#divList').append(html);
        }
        function run()
        {
            var x = rand(0, 100);
            if (x < 100)
            {
                add();
            }
            $('#spanCount').html($('.divText').size());
            $('.divText').each(function(){
                var y = $(this).css('bottom');
                y = parseInt(y);
                y -= $(this).find('span').eq(0).height();
                $(this).css('bottom', '' + y + 'px');
                if (y + $(this).height() <= 0)
                {
                    $(this).remove();
                    return;
                }
                $(this).find('span').each(function(){
                    var c = rand(48, 57);       //显示的字符范围48-49表示01
                    var c = String.fromCharCode(c);
                    $(this).html(c);
                });
            });
            window.setTimeout(run, 100);
        }
        run();
    </script>


    <script>
        var url = "ws://localhost:8080/message/server/";
        var ws = null;
        var user = null;

        function getRoom() {
            return document.getElementById("roomid").innerText;
        }


        function getUsername() {
            user = $("#userName").text();
            return user;
        }

        //加入聊天室
        function joinRoom() {
            if (ws) {
                alert("你已经在聊天室，不能再加入");
                return;
            }
            /*alert(document.getElementById("roomid").innerText);*/
            /*$("#send").prop({disabled:false});
            $("#textarea").prop({disabled:false});*/
            var user = document.getElementById("userName").innerText;
            var roomid = document.getElementById("roomid").innerText;
            ws = new WebSocket(url + user + "/" + roomid);
            //alert("正在与服务器建立连接");
            //与服务端建立连接触发
            ws.onopen = function () {
                console.log("与服务器成功建立连接")
            };
            //服务端推送消息触发
            ws.onmessage = function (ev) {
                talking(ev.data);
            };

            //发生错误触发
            ws.onerror = function () {
                console.log("连接错误")
            };
            //正常关闭触发
            ws.onclose = function () {
                console.log("连接关闭");
            };
        }

        //退出聊天室
        function exitRoom() {
            closeWebSocket();
            // history.back();
            location = "/room_detail.jsp";
        }

        function sendMsg(picname) {
            if(!ws){
                alert("你已掉线，请重新加入");
                return;
            }
            if (picname!=null){
                //需要发送的是图片
                alert("图片id："+picname);
                ws.send(picname+".png");
            }else{
                //消息发送，发送后，在server中将message存储并改变messagelist的值。
                ws.send($("#textarea").val());
            }

        }

        function closeWebSocket() {
            if(ws){
                ws.close();
                ws = null;
            }
        }

        function talking(content) {
            //服务器有了回应，将信息存储在application域中
            //对content进行拆解
            var username = null;
            var userid = null;
            var content1 = null;
            var content2 = null;
            var index1 = content.lastIndexOf("￥");
            var roomid = content.substring(index1+1);
            /*alert("房间是"+roomid);*/

            var room = getRoom();
            content2 = content.substring(0,index1);
            if (room == roomid){
                //是这个聊天室的
                var index = content2.lastIndexOf(":");
                username = content2.substring(index+1);
                content1 = content2.substring(0,index);
                if (index!=-1) {    //有内容
                    if(content1.indexOf(".")==-1) {   //不是表情包类型的

                        var h4 = $("<h4 style='color: #000000;margin: 3px;margin-left: 10px'>");
                        h4.html("<a href='#' onclick='transfor(userid)'>" + username + "</a>" + ":" + "<br><span style=\"visibility: hidden\">userx</span>" + content1);
                        h4.appendTo($("#messageShow"));
                        $("#textarea").val("");
                    }else{
                        //表情包类型的
                        var h4 = $("<h4 style='color: #000000;margin: 3px;margin-left: 10px'>");
                        h4.html("<a href='#' onclick='transfor(userid)'>" + username + "</a>" + ":" + "<br><span style=\"visibility: hidden\">userx</span><img style='height: 50px;width: 50px' src='/emoji/"+content1+"'>");
                        h4.appendTo($("#messageShow"));
                    }
                }else{
                    //没有用户发消息，属于系统发的，如：用户XX进入聊天室
                    var h4 = $("<h4 style='color: #000000;margin: 3px;margin-left: 10px'>");
                    h4.html(content1);
                    h4.appendTo($("#messageShow"));
                    $("#textarea").val("");
                }
            }else{
                //不是这个聊天室的
            }

        }
    </script>

    <script>
/*
        function getUser(userid) {
            var temp = null;
            $.ajaxSettings.async = false;
            /!*$.get("/temp/getUsername",{userid:userid},function (data) {
                if (data!=null){
                    var temp = data;
                    return temp;
                }
            });*!/
            $.ajax({
                    url: "/temp/getUsername",
                    dataType:"text",
                    async:false,
                    success:function (data) {
                        if (data!=null){
                            temp = data;
                        }
                    }
                }
            );
            return temp;
        }*/

        function showDiv() {
            //如果已经点过一次，那么就将表情包隐藏
            $("#divEmoji").css("display","block");
            $("#hiddenDiv").css("z-index","2");
            $("#imgEmoji").attr("src","/icon/表情_fill.png")
        }


        function hiddenDiv(picname) {
            // $("#divEmoji").css("display","none");
            $("#imgEmoji").attr("src","/icon/表情.png");
            $("#hiddenDiv").css("z-index","-1");
            //点击后，直接发送图片
            sendPic(picname);
            sendMsg(picname);
        }

        function hisMsg() {
            $.get("/message/history",function (data) {
                if (data!="false"&&data!="all"){
                    location = "/room_show.jsp";
                }else if (data=="all"){
                    //全部消息都已经显示了
                    alert("已经没有啦，不要回顾过去了");
                }
            });
        }

        function transfor(userid) {

            $.get("/room/single",{userid:userid},function (data) {
                if (data=="true"){
                    //进行跳转
                    location = "room_show.jsp";
                }else if (data=="AloneTalk"){
                    alert("您不能与自己聊天哦！！！");
                }
            })
        }
    </script>


</head>
<body style="background-image: url(image/back5.jpg);background-repeat: no-repeat;background-size: 100% 100%" onload="joinRoom()">
<%--    <div id="divList"></div>--%>
<%--    <h1 align="center">${sessionScope.objectUser.username}</h1>--%>
    <div class="center-in-center">

        <div id="hiddenDiv" style="width: 230px;height: 200px;background: #d71e20;z-index: -1;position: absolute;left :0px;top: 382px;overflow: scroll">

            <div id="divEmoji" style="display: none"><%--存放表情的地方,要有id，id是emoji的ID--%>
                <c:forEach items="${applicationScope.emojiList}" var="emoji">
                    <img src="/emoji/${emoji.emojiname}" onclick="hiddenDiv(${emoji.id})" style="height: 30px;width: 30px;margin: 5px">
                </c:forEach>
            </div>
        </div>

        <div style="height: 600px;background-color: #fbfff9;overflow: scroll;background: #82d775">
<%--            通过jQuery在该区域进行显示用户发送的信息,使用appendTo()方法--%>
            <div id="divHistory"><a href="#" onclick="hisMsg()">历史消息</a></div>
            <div id="messageShow">
                <c:forEach items="${applicationScope.messageInit}" var="message">
                    <c:if test="${message.messageType.typeid==1}">
                        <h4 style="color: #000000;margin: 3px;margin-left: 10px"><%--${message.time}---%><a href="#" onclick="transfor(${message.user.userid})">${message.user.username}</a>:<br><span style="visibility: hidden">userx</span>${message.content}</h4>
                    </c:if>
                    <c:if test="${message.messageType.typeid==2}">
                        <h4 style="color: #000000;margin: 3px;margin-left: 10px"><a href="#" onclick="transfor(${message.user.userid})">${message.user.username}</a>:<br><span style="visibility: hidden">userx</span><img src="/emoji/${message.content}" style="height: 50px;width: 50px"></h4>
                    </c:if>
                </c:forEach>
            </div>
        </div>
        <%--<div style="background: #ccff33">
            显示区域与用户输入区域的分界
        </div>--%>
        <%--存放表情包的div--%>
        <div style="background: #d2d2d7;height: 100px">
            <%--获取到所有的表情图片，foreach放在该div中，使用display:none样式，display不显示后，不会占地方--%>
            <div>
                <img id="imgEmoji" src="/icon/表情.png" onclick="showDiv()" style="height: 20px;width: 20px;display: inline-block;margin-left: 10px;margin-top: 3px">
            </div>
            <div>
                <%-- 用户刚进入无法参与聊天，textarea区域处于无法编辑的状态 --%>
                <span style="display: none"><h3 id="userName" style="margin-left: 20px">${sessionScope.user.username}</h3><h3 id="roomid">${sessionScope.chatroom.roomid}</h3></span>
                <textarea id="textarea" style="width: 500px;height: 50px;margin: 5px;margin-right: -1px;float: left" name="edit"></textarea>
                <input type="button" id="send" style="width: 150px;height: 55px;background: #1b6d85}" align="center" value="发送" onclick="sendMsg(null);sendMessage()">
<%--                <input type="button" style="float: right;margin-right: 20px;transform: translate(-15px,50px);height: 50px;" value="加入聊天" onclick="joinRoom()">--%>
                <input type="button" style="float: bottom;margin-right: 20px;/*transform: translate(-15px,-80px);*/height: 50px" value="退出房间" onclick="exitRoom()">
            </div>
        </div>
    </div>

</body>
</html>
