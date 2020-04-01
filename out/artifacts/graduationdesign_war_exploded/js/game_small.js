function gameDetail(gameid) {
    //点击图片后，页面将跳转到对应游戏的详细信息界面
    //但是，要将游戏名或者游戏id等能够标识游戏的参数一起传递过去

    /*$.get("/game/show",{gameid:gameid},function (data) {
        var object = JSON.parse(data);

    });*/
    location="/game/show?gameid="+gameid;
    //location="/room_detail.jsp?gameid="+gameid;
}

