<%--
  用户登录界面：用户名，密码，验证码等。
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="/css/user_login.css">
</head>
<body style="width: 900px;height: auto;border: 1px solid purple;margin: 5px auto;">
<div>
    <img src="/image/photo/panda.jpg">
    <form action="/user/login" method="post">
        <table>
            <tr class="abc">
                <td>用户名：</td>
                <td><input type="text" name="username" value="${sessionScope.username}"></td>
            </tr>
            <tr class="abc">
                <td>密码：</td>
                <td><input type="password" name="password" value="${sessionScope.password}"></td>
            </tr>
            <tr></tr>
            <tr class="abc">
                <td><input type="text" name="验证码"></td>
                <td><!--生成验证码-->
                    <img src="/valcode" id="valcode" onclick="this.src = this.src + '?'"/>
                    <input type="button" value="看不清，换一张" onclick="document.getElementById('valcode').src = document.getElementById('valcode').src + '?'"></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="checkbox" name="remember" /><i>记住用户名和密码</i>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="checkbox" name="autoLogin" /><i>7天免登录</i>
                </td>
            </tr>
            <tr class="abc">
                <td colspan="2" align="center">
                    <input type="submit" value="登录">
                    <input type="reset" value="重置">
                    <input type="button" value="注册" onclick="location='/user_register.jsp'">
                </td>
            </tr>
        </table>

    </form>
    请注意，如果未注册请先注册在登录
</div>
</body>
</html>
