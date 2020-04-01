/*
$("#id").val("这是赋值"); //这是给value赋值
$("#id").html(""); //直接给元素添加内容允许html标签  
$("#id").text(""); //给元素添加内容
$("#id").data("自定义属性","赋值的内容");//给自定义属性赋值
$("#id").attr("属性","值");
$("#id").attr({“属性1”：值1，“属性2”：值2，“属性3”：值3});
*/


function userSet() {
    //用户用来设置显示多少条信息的方法
}

function gamequery() {
    //查询游戏
    //gamequerytext
    //alert($("#gamequerytext").val());
    
    $.get("/room/query",{roomname:$("#roomquerytext").val()},function (data) {
        if (data!="0"){
            location="room_query.jsp";
        }else{
            location="room_query_result.jsp";
        }
    })
}


//游戏详细信息对应的js文件
function gameDownload(id) {
    //进入聊天室对应的方法,跳转至room_show.jsp页面，因为要进行消息存储，所以，将roomid也传过去，而不直接使用跳转语句。

    // location = "room_show.jsp";
    $.get("/room/chatroom",{roomid:id},function (data) {
        if (data){
            location="room_show.jsp";
            alert("进入该聊天室");
        }
    });
}

function commitComment() {
    //评论留言  获取commentContent的内容提交至servlet
    //不用传game类型，在session域里，通过theChosenGame来获得该游戏
    $("#commentResult").attr("style","color:blue;")
    $.get("/comment/add",{content:$("#commentContent").val()},function (data) {
        if (data){
            $("#commentResult").text("评论成功");
            //对页面进行刷新
        }else{
            $("#commentResult").text("评论失败");
        }
    })
}

//收藏模块前台完成
function changeColor(gameid) {
    //点击收藏时将白色五角星变为黄色的
    if ($("#star").attr("src")=="/image/star_white.gif"){
        //如果没有收藏，就收藏
        $("#star").attr("src","/image/star_yellow.gif");
        //收藏
        $.get("/room/collect",{roomid:gameid,state:"sure"},function (data) {
            if (data){

            }else{

            }
        })
        //$("#star").attr("onclick","changeColor()");
    }else if($("#star").attr("src")=="/image/star_yellow.gif"){
        //如果收藏了，就取消收藏
        $("#star").attr("src","/image/star_white.gif");
        $.get("/game/collect",{gameid:gameid,state:"cancel"},function (data) {
            if (data){

            }else{
                
            }
        })
    }
}

function shopcar() {
    //跳转至购物车的servlet，对购物车的内容进行初始化
    $.get("/collection/init",function (date) {
        if (date){
            location="/collection.jsp";
        }
    });
}


//检测收藏与否
// function colorcheck(id,currentPage,totalPage) {
/*
    //对游戏评论,
    if (currentPage==1){
        $("#lowpage").prop({disabled:true});
    }
    if (currentPage == totalPage){
        $("#uppage").prop({disabled:true});
    }*/
function colorcheck(id) {
    //并且对侧边栏的游戏简介进行删选，不能太长
    if ($("#intro").text().length>30){
        //如果太长，对简介进行剪切
        var s=$("#intro").text().substr(0,30);
        $("#intro").text(s);
    }
    //用于判断该游戏是否已经收藏
    $.get("/collection/check",{roomid:id},function (data) {

        if(data=="yes"){
            //如果该用户已经收藏了该房间
            $("#star").attr("src","/image/star_yellow.gif");
        }else if (data=="no"){
            //该用户没有收藏该房间
            $("#star").attr("src","/image/star_white.gif");
        }
    });

}