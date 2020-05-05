function ordershow() {
    $.get("/order/init",function (data) {
        if (data=="true"){
            location="/order_show.jsp";
        }
    });
}

function changeName() {

    //alert($("#usernameinput").val().trim().length);

    if ($("#usernameinput").val().trim().length > 10) {
        $("#namespan").css({color:"#F80000"})
        $("#namespan").val("用户名不能超过十个字符");

    } else {
        $.get("/user/change", {username: $("#usernameinput").val()}, function (data) {
            if (data == "true") {
                $("#namespan").val("用户名修改成功");
                location = location;
            }
        })
    }
}

function changePass() {
    $.get("/user/change",{password:$("#passwordinput").val()},function (data) {
        if (data=="true"){
            $("#passspan").val("密码修改成功");
            location=location;
        }
    })
}

function cancel() {
    location=location;
}

function changeMail() {
    $.get("/user/change",{email:$("#mailinput").val()},function (data) {
        if (data=="true"){
            $("#mailspan").val("邮箱修改成功");
            location=location;
        }
    })
}

function changeTel() {
    $.get("/user/change",{phone:$("#telephoneinput").val()},function (data) {
        if (data=="true"){
            $("#telspan").val("电话修改成功");
            location=location;
        }
    })
}

function changeGender() {
    $.get("/user/change",{gender:$('input:radio:checked').val()},function (data) {
        if (data=="true"){
            $("#genderspan").val("性别修改成功");
            location=location;
        }
    })
}

function changeInterest() {
    $.get("/user/change",{question:$("#interestinput").val(),answer:$("#jobinput").val()},function (data) {
        if (data=="true"){
            $("#interestspan").val("问题修改成功");
            location=location;
        }
    })
}

function changeJob() {
    $.get("/user/change",{answer:$("#jobinput").val()},function (data) {
        if (data=="true"){
            $("#jobspan").val("答案修改成功");
            location=location;
        }
    })
}

function changeSet() {
    $.get("/user/change",{count:$("#setinput").val()},function (data) {
        if (data=="true"){
            $("#setspan").val("设置成功");
            location=location;
        }
    })
}

function changeUsername(){
    $("#username001").html("<input type='text' id='usernameinput' maxlength='10' style='width: 300px' placeholder='请输入修改后的用户名'/>");
    $("#username002").html("<span id='namespan' style='color: #3828ff'></span><input type='button' id='cancel' value='取消' onclick='cancel()'/><input type='button' id='ensure' value='确定' onclick='changeName()'/>");

}
function changePassword() {
    $("#password001").html("<input type='text' id='passwordinput' style='width: 300px' placeholder='请输入修改后的密码'/><span id='passspan' style='color: #3828ff'></span>");

    $("#password002").html("<input type='button' id='cancel' value='取消' onclick='cancel()'/><input type='button' id='ensure' value='确定' onclick='changePass()'/>");

}

function changeLocation() {
    location = "password_change.jsp";
}

function changeUserGender() {
    $("#gender001").html("<input type='radio' name='genderselect' id='genderinput' value='男'/>男<input type='radio' name='genderselect' value='女'/>女<span id='genderspan' style='color: #3828ff'></span>");

    $("#gender002").html("<input type='button' id='cancel' value='取消' onclick='cancel()'/><input type='button' id='ensure' value='确定' onclick='changeGender()'/>");

}

function changeUserAnswer() {
    $("#answer001").html("<input type='text' id='jobinput' style='width: 300px' placeholder='请输入答案'/><span id='jobspan' style='color: #3828ff'></span>");

    $("#answer002").html("<input type='button' id='cancel' value='取消' onclick='cancel()'/><input type='button' id='ensure' value='确定' onclick='changeJob()'/>");

}

function changeUserMail() {
    $("#mail001").html("<input type='text' id='mailinput' style='width: 300px' placeholder='请输入修改后的邮箱'/><span id='mailspan' style='color: #3828ff'></span>");

    $("#mail002").html("<input type='button' id='cancel' value='取消' onclick='cancel()'/><input type='button' id='ensure' value='确定' onclick='changeMail()'/>");

}

function changeUserTel() {
    $("#telephone001").html("<input type='text' id='telephoneinput' style='width: 300px' placeholder='请输入修改后的电话'/><span id='telspan' style='color: #3828ff'></span>");

    $("#telephone002").html("<input type='button' id='cancel' value='取消' onclick='cancel()'/><input type='button' id='ensure' value='确定' onclick='changeTel()'/>");

}

function changeUserQuestion() {
    $("#question001").html("<input type='text' id='interestinput' style='width: 300px' placeholder='请输入修改后的兴趣'/><span id='interestspan style='color: #3828ff''></span>");

    $("#question002").html("<input type='button' id='cancel' value='取消' onclick='cancel()'/><input type='button' id='ensure' value='确定' onclick='changeInterest()'/>");


//    在修改问题时，要连答案一同修改
    $("#answer001").html("<input type='text' id='jobinput' style='width: 300px' placeholder='请输入答案'/><span id='jobspan' style='color: #3828ff'></span>");

    $("#answer002").html();

}


function changeUserSet() {
    $("#set001").html("<input type='text' id='setinput' style='width: 300px' placeholder='进入聊天室时显示几条信息呢？'/><span id='setspan' style='color: #3828ff'></span>");

    $("#set002").html("<input type='button' id='cancel' value='取消' onclick='cancel()'/><input type='button' id='ensure' value='确定' onclick='changeSet()'/>");

}


function queryMessage() {
    //查询信息所使用的跳转方法
    location="/message/queryinit";
    // location = "message_query.jsp";
}