<%--
&lt;%&ndash;
  用户登录界面：用户名，密码，验证码等。
&ndash;%&gt;
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
--%>


<%--
  Created by IntelliJ IDEA.
  User: liuxuan
  Date: 2020/4/3
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>系统登录</title>
    <link href="css/login.css" rel="stylesheet" rev="stylesheet" type="text/css" media="all" />
    <link href="css/demo.css" rel="stylesheet" rev="stylesheet" type="text/css" media="all" />
    <script type="text/javascript" src="js/jquery1.42.min.js"></script>
    <script type="text/javascript" src="js/jquery.SuperSlide.js"></script>
    <script type="text/javascript" src="js/Validform_v5.3.2_min.js"></script>

    <script>
        $(function(){

            $(".i-text").focus(function(){
                $(this).addClass('h-light');
            });

            $(".i-text").focusout(function(){
                $(this).removeClass('h-light');
            });

            $("#username").focus(function(){
                var username = $(this).val();
                if(username=='输入账号'){
                    $(this).val('');
                }
            });

            $("#username").focusout(function(){
                var username = $(this).val();
                if(username==''){
                    $(this).val('输入账号');
                }
            });

            $("#password").focus(function(){
                var username = $(this).val();
                if(username=='输入密码'){
                    $(this).val('');
                }
            });

            $("#yzm").focus(function(){
                var username = $(this).val();
                if(username=='输入验证码'){
                    $(this).val('');
                }
            });

            $("#yzm").focusout(function(){
                var username = $(this).val();
                if(username==''){
                    $(this).val('输入验证码');
                }
            });

            /*$(".registerform").Validform({
                tiptype:function(msg,o,cssctl){
                    var objtip=$(".error-box");
                    cssctl(objtip,o.type);
                    objtip.text(msg);
                },
                ajaxPost:true
            });*/

        });
    </script>
</head>

<body>


<div class="banner">

    <div class="login-aside">
        <div id="o-box-up"></div>
        <div id="o-box-down"  style="table-layout:fixed;">
            <div class="error-box"></div>

            <form class="registerform" action="/user/login" method="post">
                <div class="fm-item">
                    <label for="logonId" class="form-label">用户登陆：</label>
                    <input type="text" value="输入账号" maxlength="100"  name="username" id="username" class="i-text" ajaxurl="demo/valid.jsp"  <%--datatype="s6-18" errormsg="用户名至少6个字符,最多18个字符！" --%> >
                    <%--<div class="ui-form-explain"></div>--%>
                </div>

                <div class="fm-item">
                    <label for="logonId" class="form-label">登陆密码：</label>
                    <input type="password" value="" maxlength="100" id="password" name="password" class="i-text" nullmsg="请输入密码！">
                    <%--                    <div class="ui-form-explain"></div>--%>
                </div>

                <div class="fm-item pos-r">
                    <label for="logonId" class="form-label">验证码</label>
                    <input type="text" name="验证码" maxlength="100" id="yzm" class="i-text yzm" placeholder="请输入验证码！" >
                    <div class="ui-form-explain"><img src="/valcode" class="yzm-img" /></div>
                    <input type="checkbox" name="remember" /><i><font color="white">记住用户名和密码</font></i>
                    <input type="checkbox" name="autoLogin" /><i><font color="white">7天免登录</font></i>
                </div>

                <div class="fm-item">
                    <label for="logonId" class="form-label"></label>
                    <input type="submit" value="" tabindex="4" id="send-btn" class="btn-login">
                    <%--                    <div class="ui-form-explain"></div>--%>
                </div>

            </form>

        </div>

    </div>

    <div class="bd">
        <ul>
            <li style="background:url(themes/theme-pic1.jpg) #CCE1F3 center 0 no-repeat;"><a target="_blank" href="#"></a></li>
            <li style="background:url(themes/theme-pic2.jpg) #BCE0FF center 0 no-repeat;"><a target="_blank" href="#"></a></li>
        </ul>
    </div>

    <div class="hd"><ul></ul></div>
</div>
<script type="text/javascript">jQuery(".banner").slide({ titCell:".hd ul", mainCell:".bd ul", effect:"fold",  autoPlay:true, autoPage:true, trigger:"click" });</script>


<div class="banner-shadow"></div>

<div class="footer">
    <%--    <p>Copyright &copy; 2014.Company name All rights reserved.More Templates <a href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a> - Collect from <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a></p>--%>
    <p>该系统是太原理工大学软件学院毕业生刘轩构成 <a href="http://www.baidu.com/" target="_blank" title="模板之家">刘轩</a> - Collect from <a href="http://www.baidu.com/" title="网页模板" target="_blank">个人主页</a></p>

</div>

</body>
</html>

