<%--
  Created by IntelliJ IDEA.
  User: liuxuan
  Date: 2020/3/30
  Time: 17:39
  To change this template use File | Settings | File Templates.
  //修改密码使用的界面，修改密码时需要使用到密保问题等
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="/js/jquery-3.4.1.js"></script>
    <script>
        function checkAnswer() {
            $.get("/user/changepass",{answer:$("#useranswer").val()},function (data) {
                if (data!="false"){
                    location="change_pass.jsp";
                }else{
                    alert("对不起，您的回答错误，无法修改");
                }
            })
        }
    </script>
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

    </style>

</head>
<body style="background-image: url(images/back15.jpg);background-size: 100% 100%">
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
                <a href="/contact.jsp" style="text-decoration:none">联系我</a>
            </div>
        </tr>
    </table>
</aside>
<div style="text-align: center">
<img src="/icon/lock.jpg" style="width: 600px;height: 200px" align="center">

<table  cellspacing="5" align="center" style="margin-top: 20px">
    <tr>
        <th>修改密码需要填写密保问题</th>
    </tr>

    <tr>
        <td>密保问题</td>
        <td>${sessionScope.user.question}</td>
    </tr>
    <tr>
        <td>您的回答</td>
        <td><input type="text" id="useranswer" placeholder="请填写密保答案" name="answer"></td>
    </tr>
    <tr>
        <td><input type="button" value="提交" onclick="checkAnswer()"></td>
        <td><input type="button" value="退出" onclick="location='user_show.jsp'"></td>
    </tr>
</table>
</div>
</body>
</html>
