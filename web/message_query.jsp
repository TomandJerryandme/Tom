<%--
  Created by IntelliJ IDEA.
  User: liuxuan
  Date: 2020/3/31
  Time: 12:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
    <title>信息查询</title>
</head>
<body>
<%--    用户可以设置查询条件，如按照时间，按照消息类型，按照房间--%>
<c:if test="${requestScope.list==null}">
    <%
        response.sendRedirect("/message/query");
    %>
</c:if>

<c:if test="${requestScope.list!=null}">

    <h1 align="center"><font color="blue">新闻列表查询</font></h1>
    <table border="1" cellspacing="0s" align="center" width="800px">
    </table>
    <table border="1" cellspacing="0s" align="center" width="800px">

        <tr>
            <th>发布者</th><th>发布房间</th><th>消息类型</th><th>内容</th><th>发布时间</th>
        </tr>

        <c:forEach items="${requestScope.list}" var="message" varStatus="status">

            <c:if test="${status.index%2==0}">
                <tr bgcolor="#d0d0d0">
            </c:if>
            <c:if test="${status.index%2!=0}">
                <tr bgcolor="#ffffff">
            </c:if>

            <td align="center">
                <!-- <img src="/image/photo/$ {message.user.photo}" />
                    <br/>-->
                    ${message.user.username}
            </td>

            <td align="center">
                <!-- <img src="/image/photo/$ {message.user.photo}" />
                    <br/>-->
                    ${message.room.roomname}
            </td>

            <td align="center">
                <!-- <img src="/image/photo/$ {message.user.photo}" />
                    <br/>-->
                    ${message.messageType.typename}
            </td>
            <td>${message.content}</td>
            <td><fmt:formatDate value="${message.time}" pattern="yyyy年MM月dd日 HH时mm分ss秒" type="both" dateStyle="medium"/></td>

            </tr>

        </c:forEach>

    </table>

</c:if>

</body>
</html>
