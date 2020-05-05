<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  添加游戏的页面，游戏有封面，游戏名，类型，游戏介绍，游戏价格，折扣等
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加房间</title>

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
            height: 600px;
            transform: translate(50%,-10%);
        }
        table tr td{
            font-size: 20px;
        }

    </style>

    <script>
        function message() {
            //$.get("/message/queryinit");
            location="/message/queryinit";
        }
    </script>
</head>
<body style="background-image: url(images/back2.gif);background-size: 100% 100%;background-repeat: no-repeat">
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
<center>
<div class="center-in-center">
    <h1 style="transform: translate(0,-100%)">添加一个新的聊天室</h1>
    <form action="/room/add" method="post" enctype="multipart/form-data">
        <table border="1px">
            <tr>
                <td>
                    房间名：
                </td>
                <td>
                    <input type="text" name="roomname" placeholder="请输入游戏名"/>
                </td>
            </tr>
            <tr>
                <td>
                    房间简介：
                </td>
                <td>
                    <textarea name="introduce" placeholder="房间简介" style="height: 200px"></textarea>
                </td>
            </tr>
            <tr>
                <td>
                    房间类型类型：
                </td>
                <td>
                    <select name="roomtype">
                        <c:forEach items="${applicationScope.roomTypeList}" var="type">
                            <option value="${type.typeid}">${type.typename}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    房间封面：
                </td>
                <td>
                    <!-- 上传游戏封面-->
                    <input type="file" name="cover" value="上传房间封面"/>
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
</center>
</body>
</html>
