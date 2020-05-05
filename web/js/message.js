var url = "ws://localhost:8080/message/server/";

//加入聊天室
function joinRoom(username) {

    var ws = null;

    if (ws) {
        alert("你已经在聊天室，不能再加入");
        return;
    }
    $("#send").prop({disabled:false});
    $("#textarea").prop({disabled:false});
    ws = new WebSocket(url + username);
    //与服务端建立连接触发
    ws.onopen = function () {
        console.log("与服务器成功建立连接")
    };
    //服务端推送消息触发
    ws.onmessage = function (ev) {
        talking(ev.data);
    };

    //发生错误触发
    ws.onerror = function () {
        console.log("连接错误")
    };
    //正常关闭触发
    ws.onclose = function () {
        console.log("连接关闭");
    };
}

//退出聊天室
function exitRoom(ws) {
    closeWebSocket();
}

function sendMsg(ws) {
    if(!ws){
        alert("你已掉线，请重新加入");
        return;
    }
    //消息发送，发送后，在server中将message存储并改变messagelist的值。
    ws.send($("#textarea").val());
    $("#textarea").val("");
}

function closeWebSocket(ws) {
    if(ws){
        ws.close();
        ws = null;
    }
}

function talking(content) {
    //服务器有了回应，将信息存储在application域中
    $.get("/message/save",{content:$("#textarea").val()},function (data) {
        if (data!='false'){
            /*var h4 = $("<h4 style='color: #d79cd5;margin: 3px;margin-left: 10px'>");
            h4.html("<span onclick='transfor()'>"+data+"</span>:"+$('#textarea').val());
            h4.appendTo($("#messageShow"));
            $("#textarea").val("");*/
            window.location.reload();
        }else{
            alert("您发送的消息里含有屏蔽字，无法发送该信息");
        }
    });
}

