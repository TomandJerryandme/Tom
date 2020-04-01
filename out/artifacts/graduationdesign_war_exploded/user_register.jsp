<%--
  用户注册页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>用户注册</title>
    <link rel="stylesheet" type="text/css" href="/css/user_register.css">
    <style>
        html, body {margin:0; padding:0; background-color: #fbfff9;}
        #divList {width:1500px; height: 680px;  margin: 0px auto; overflow:hidden; position: relative;float: left;}
        .divText {position: absolute;}
        .divText span {display:block; font-weight: bold; font-family:Courier New; }
    </style>
    <script src="http://apps.bdimg.com/libs/jquery/1.9.0/jquery.min.js"></script>
    <script src="/js/user_register.js"></script>
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
                    var c = rand(33, 127);       //显示的字符范围48-49表示01
                    var c = String.fromCharCode(c);
                    $(this).html(c);
                });
            });
            window.setTimeout(run, 100);
        }
        run();
    </script>
</head>
<body>
<div id="divList">
</div>
<div style="margin: 0px auto">
<form action="/user/register" method="post" enctype="multipart/form-data">

    <h3 align="center"><font color="red" size="5">新用户注册</font></h3>
    <table align="center" border="1">

        <tr>
            <td>用户名</td>
            <td width="500px">
                <input type="text" name="username" id="username" maxlength="10" onblur="checkUsername()" placeholder="请输入用户名"/>
                <span id="usernameResult"></span>
            </td>
        </tr>

        <tr>
            <td>密码</td>
            <td>
                <input type="password" name="password" id="password" onblur="checkPassword()" placeholder="密码" />
                <span id="passwordResult"></span>
            </td>
        </tr>

        <tr>
            <td>确认密码</td>
            <td>
                <input type="password" id="password2" onblur="checkPassword2()" placeholder="再次输入密码" />
                <span id="password2Result"></span>
            </td>
        </tr>
        <tr>
            <td>性别</td>
            <td>
                <input type="radio" name="gender" value="男" checked />男
                <input type="radio" name="gender" value="女" />女
            </td>
        </tr>
        <tr>

            <td>邮箱</td>
            <td><input type="text" name="email" placeholder="请输入你的邮箱"></td>
        </tr>

        <tr>

            <td>电话号码</td>
            <td><input type="text" name="phone" placeholder="请输入你的电话号码"></td>
        </tr>
        <tr>
            <td>密保问题</td>
            <td><input type="text" name="question" placeholder="请输入你的密保问题"></td>

        </tr>

        <tr>
            <td>密保问题的答案</td>
            <td><input type="text" name="answer" placeholder="请输入密保问题的答案"></td>
        </tr>

        <tr>
            <td>头像</td>
            <td>
                <input type="radio" name="userpic" value="1.gif"  onclick="showFile()" checked /><img src="/image/photo/1.gif" />
                <input type="radio" name="userpic" value="2.gif"  onclick="showFile()" /><img src="/image/photo/2.gif" />
                <input type="radio" name="userpic" value="3.gif"  onclick="showFile()"/><img src="/image/photo/3.gif" />
                <input type="radio" name="userpic" value="4.gif"  onclick="showFile()"/><img src="/image/photo/4.gif" />
                <input type="radio" name="userpic" value="5.gif"  onclick="showFile()" /><img src="/image/photo/5.gif" />
                <br/>
                <input type="radio" name="photo" id="photo" value="0.gif" onclick="showFile()"/>自定义头像
                <input type="file" name="file" id="file" style="visibility: hidden"/><br/>
            </td>
        </tr>

        <tr>
            <td>验证码</td>
            <td>
                <input type="text" name="valcode" />
                <img src="/valcode" id="valcode" onclick="this.src = this.src + '?'"/>
                <input type="button" value="看不清，换一张" onclick="document.getElementById('valcode').src = document.getElementById('valcode').src + '?'">
            </td>
        </tr>

        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="立即注册" />
                <input type="reset" value="重新填写" />
            </td>
        </tr>

    </table>

</form>
</div>
</body>
</html>
