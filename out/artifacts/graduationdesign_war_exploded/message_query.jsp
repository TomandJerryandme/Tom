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
    <script src="/js/laydate/laydate.js"></script>
    <script src="/js/jquery-3.4.1.js"></script>
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

    <script>

      /*  $().ready(function () {
            if ($("#role").val()=="1"){
                $(".asidestyle").attr("visibility",hidden)
            }
        })
*/
        function changeTimeRange(regtime){

            var timerange = document.getElementById("timerange");

            if(regtime=="全部时间"){
                timerange.style.visibility = "hidden";
            }else{
                timerange.style.visibility = "visible";
            }
        }

        function page(page) {
            location = "/message/changePage?page="+page;
        }

        function checkB(page,totalpage) {
            if (page == 1) {
                //uppage---下一页
                $("#lowpage").prop({disabled: true});
            }
            if (page == totalpage) {
                $("#uppage").prop({disabled: true});
            }
        }
    </script>
</head>
<body style="background-image: url(/images/back15.jpg);background-size: 100% 100%" onload="checkB(${requestScope.messagePage.currentPage},${requestScope.messagePage.totalPage})">

<%--    用户可以设置查询条件，如按照时间，按照消息类型，按照房间--%>
<c:if test="${requestScope.messagePage==null}">
    <%
        response.sendRedirect("/message/queryinit");
    %>
</c:if>

<c:if test="${requestScope.messagePage!=null}">
    <h3 hidden id="role">${requestScope.role}</h3>
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

<div>
    <h1 align="center"><font color="blue">信息列表查询</font></h1>

<center>
    <form action="/message/query" method="post">

        <table>
            <tr>
                <td>用户</td>
                <td>
                    <c:if test="${requestScope.role==0}">
                        <%-- 该用户是普通用户--%>
                        <select name="username" onclick="alert('对不起，您不是管理员，无法查看其它用户信息》_《')">
                            <option value="${sessionScope.user.username}">${sessionScope.user.username}</option>
                        </select>
                    </c:if>
                    <c:if test="${requestScope.role==1}">
                    <%-- 该用户是管理员--%>
                        <select name="username">
                            <option value="" ${ "".equals(requestScope.selectUser) ? "selected" : "" }>全部</option>
                            <c:forEach items="${applicationScope.userList}" var="user">
                                <option value="${user.username}" ${ user.username.equals(requestScope.selectUser) ? "selected" : ""}>${user.username}</option>
                            </c:forEach>
                        </select>
                    </c:if>
                </td>

                <td>房间</td>
                <td>
                <%--         <option value="" ${ "".equals(job) ? "selected" : "" } >全部</option>           --%>
                    <select name="roomid">
                        <option value="" ${ "".equals(requestScope.selectRoom) ? "selected" : "" }>全部</option>
                        <c:forEach items="${applicationScope.roomList}" var="room">
                            <option value="${room.roomid}" ${ room.roomname.equals(requestScope.selectRoom) ? "selected" : ""} >${room.roomname}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>

            <tr>
                <td>信息类型</td>
                <td>
                    <select name="messagetype">
                        <option value="" ${ "".equals(requestScope.selectType) ? "selected" : ""}>全部</option>
                        <option value="1" ${ "1".equals(requestScope.selectType) ? "selected" : ""}>文字类型</option>
                        <option value="2" ${ "2".equals(requestScope.selectType) ? "selected" : ""}>图片类型</option>
                        <option value="3" ${ "3".equals(requestScope.selectType) ? "selected" : ""}>视频类型</option>
                    </select>
                </td>
            </tr>

            <tr>
                <td>时间</td>
                <td>
                    <select name="time" id="regtime" onchange="changeTimeRange(this.value)">
                        <option value="全部时间" ${ "全部时间".equals(requestScope.regtime) ? "selected" : "" } >全部时间</option>
                        <option value="指定时间" ${ "指定时间".equals(requestScope.regtime) ? "selected" : "" } >指定时间</option>
                    </select>
                </td>
                <td colspan="2">
                    <span id="timerange" style="visibility: ${ "全部时间".equals(regtime) ? "hidden" : "visible" }">
                        从<input type="text" name="begintime" size="8" value="${begintime}" class="laydate-icon" onfocus="laydate()" readonly>
                        到<input type="text" name="endtime" size="8" value="${endtime}" class="laydate-icon" onfocus="laydate()" readonly>
                    </span>
                </td>
            </tr>
            <tr>
                <td colspan="4" align="center">
                    <input type="submit" value="查询"/>
                </td>
            </tr>
        </table>
    </form>
</center>


    <table border="1" cellspacing="0s" align="center" width="800px">

        <tr>
            <td colspan="5" align="center">
                <input id="lowpage" type="button" value="上一页" onclick="page(${requestScope.messagePage.currentPage-1})">
                <input id="uppage" type="button" value="下一页" onclick="page(${requestScope.messagePage.currentPage+1})">
                第${requestScope.messagePage.currentPage}页/共${requestScope.messagePage.totalPage}页
            </td>
        </tr>

        <tr>
            <th>发布者</th><th>发布房间</th><th>消息类型</th><th>内容</th><th>发布时间</th>
        </tr>


        <c:forEach items="${requestScope.messagePage.dataList}" var="message" varStatus="status">

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
            <td>
                <fmt:formatDate value="${message.time}" pattern="yyyy年MM月dd日 HH时mm分ss秒" type="both" dateStyle="medium"/>
            </td>
            </tr>

        </c:forEach>

    </table>
</div>
</c:if>
</body>
</html>
