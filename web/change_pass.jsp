<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  添加游戏的页面，游戏有封面，游戏名，类型，游戏介绍，游戏价格，折扣等
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加房间</title>
    <script src="/js/user_show_simple.js"></script>
    <script src="/js/jquery-3.4.1.js"></script>
    <script src="/js/game_detail.js"></script>
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

</head>
<body style="background-image: url(/images/back15.jpg);background-size: 100% 100%">
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

<div style="width: 700px;height: auto;margin: 5px auto;border: 1px solid #ff0507">
    <table>
        <tr>
            <td>角色名：</td>
            <td><div id="username001" style="width: 500px"><span>${sessionScope.user.username}</span></div></td>
            <td><span id="username002"><input type="button" value="修改" onclick="changeUsername()"></span></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><div id="password001"><span>${sessionScope.user.password}</span></div></td>
            <td><span id="password002"><input type="button" value="修改" onclick="changePassword()"></span></td>
        </tr>
        <tr>
            <td>性别：</td>
            <td><div id="gender001"><span>${sessionScope.user.gender}</span></div></td>
            <td><span id="gender002"><input type="button" value="修改" onclick="changeUserGender()"></span></td>
        </tr>
        <tr>
            <td>邮箱：</td>
            <td><div id="mail001"><span>${sessionScope.user.email}</span></div></td>
            <td><span id="mail002"><input type="button" value="修改" onclick="changeUserMail()"></span></td>
        </tr>
        <tr>
            <td>电话：</td>
            <td><div id="telephone001"><span>${sessionScope.user.phone}</span></div></td>
            <td><span id="telephone002"><input type="button" value="修改" onclick="changeUserTel()"></span></td>
        </tr>
        <tr>
            <td>问题：</td>
            <td><div id="question001"><span>${sessionScope.user.question}</span></div>
            </td>
            <td><span id="question002"><input type="button" value="修改" onclick="changeUserQuestion()"></span></td>
        </tr>
        <tr>
            <td>答案：</td>
            <td><div id="answer001"><span>${sessionScope.user.answer}</span></div></td>
            <td><span id="answer002"><input type="button" value="修改" onclick="changeUserAnswer()"></span></td>
        </tr>
        <tr>
            <td><input type="button" onclick="changeUserSet()" value="聊天显示设置"></td>
            <td><div id="set001"><span></span></div></td>
            <td><span id="set002"></span></td>
        </tr>
        <tr>
            <td><input type="button" onclick="location='index.jsp'" value="退出"/></td>
            <td><input type="button" onclick="location='/user_login.jsp'" value="更换用户"/></td>
        </tr>
    </table>
</div>
</body>
</html>
