
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script>
        var timer;

        function startTimer() {
            timer = setInterval("showRestTime()", 1000);
        }

        var restTime = 3;

        function showRestTime() {

            var spanTime = document.getElementById("spanTime");

            if(restTime > 1){
                spanTime.innerText = --restTime;
            }else{
                //停止计时器
                clearInterval(timer);

                //跳转到主页
                location = "/user_login.jsp";
            }
        }

    </script>
</head>
<body onload="startTimer()">

<h1 align="center">恭喜，注册成功，系统将在<font color="red" size="10"><span id="spanTime">3</span></font>秒后跳转到主页</h1>
<h2 align="center">如果无法跳转，请点击<a href="/index.jsp">这里</a></h2>

</body>


</html>
