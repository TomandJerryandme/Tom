<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  添加游戏的页面，游戏有封面，游戏名，类型，游戏介绍，游戏价格，折扣等
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加房间</title>
</head>
<body>
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
</body>
</html>
