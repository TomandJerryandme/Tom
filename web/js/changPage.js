//主要用来分页----带有类型的七个，经济，政治，法律，生活等。

function page(page) {
    //application里的typeRoomList，roomPage类型
    $.get("/room/typeChange",{page:page},function (data) {
        if (data=="true"){
            //对页面进行刷新
            // location = "/room_"+$("#type").val()+".jsp";
            location = location;
        }
    })
}

function checkButton(page,totalpage) {
    if (page==1){
        //uppage---下一页
        $("#lowpage").prop({disabled:true});
    }
    if (page==totalpage){
        $("#uppage").prop({disabled:true});
    }
}
