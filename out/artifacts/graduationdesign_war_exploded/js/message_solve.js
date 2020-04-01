function sendMessage(status) {
    //  /message/save,将用户发送的信息传递给servlet

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