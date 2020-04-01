<%--
  Created by IntelliJ IDEA.
  User: liuxuan
  Date: 2020/3/31
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加屏蔽字</title>
</head>
<body>
<form action="/word/remove" method="post">
    <table border="1px">
        <tr>
            <td>
                屏蔽词：
            </td>
            <td>
                <input type="text" name="word" placeholder="请输入要删除的屏蔽词"/>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="删除"/>
                <input type="button" value="退出" onclick="location='/user_manager_index.jsp'"/>
            </td>
        </tr>
    </table>
</form>

</body>
</html>
