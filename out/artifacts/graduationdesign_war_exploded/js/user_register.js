function showFile(){

    var file = document.getElementById("file");
    var photo = document.getElementById("photo");

    if(photo.checked){  //自定义头像按钮被选中
        file.style.visibility = "visible";
    }else{
        file.style.visibility = "hidden";
    }
}

function checkRegisterTel() {

   /* var tel = document.getElementById("textphone").innerText;
    var reg = "^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$";

    var teltext = document.getElementById("telephoneResult");
    // /^1[3456789]\d{9}$/
    if(!(/^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$/.test(tel))){
        //手机号码格式不正确
        teltext.innerHTML = "<img src='/image/no.gif'><font color='red'><b>电话格式不正确</b></font>";
    }else{
        alert("格式正确");
        teltext.innerHTML = "<img src='/image/yes.gif'><font color='red'><b>格式正确</b></font>";
    }*/

    $.get("/filed/check",{telephone:$("#textphone").val()},function (data) {
        if (data=="false"){
            var html = $("<img src='/image/no.gif'><font color='red'><b>电话格式不正确</b></font>");
            html.appendTo($("#telephoneResult"));
            // $("#telephoneResult").html("<img src='/image/no.gif'/><font color='red'><b>电话格式不正确</b></font>");
        }else{
            var html = $("<img src='/image/yes.gif'><font color='red'><b>格式正确</b></font>");
            html.appendTo($("#telephoneResult"));
            // $("#telephoneResult").html("<img src='/image/yes.gif'/><font color='green'><b>格式正确</b></font>");
        }
    })
}

function checkRegisterMail() {

  /*  var mail = document.getElementById("textemail").innerText;

    var mailtext = document.getElementById("mailResult");

    // /^1[3456789]\d{9}$/
    if(!(/^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/.test(mail))){
        //手机号码格式不正确
        mailtext.innerHTML = "<img src='/image/no.gif'><font color='red'><b>邮箱格式不正确</b></font>";
    }else{
        mailtext.innerHTML = "<img src='/image/yes.gif'><font color='red'><b>格式正确</b></font>";
    }*/

    $.get("/filed/check",{mail:$("#textemail").val()},function (data) {
        if (data=="false"){
            var html = $("<img src='/image/no.gif'><font color='red'><b>邮箱格式不正确</b></font>");
            html.appendTo($("#mailResult"));
        }else{
            var html = $("<img src='/image/yes.gif'><font color='red'><b>格式正确</b></font>");
            html.appendTo($("#mailResult"));
            // $("#mailResult").html("<img src='/image/yes.gif'/><font color='green'><b>格式正确</b></font>");

        }
    })
}

//字段级检查
function checkUsername() {

    var username = document.getElementById("username");
    var usernameResult = document.getElementById("usernameResult");

    if(username.value.length<1 || username.value.length>8) {
        //usernameResult.innerText = "用户名的长度必须在1-10个字符之间";
        usernameResult.innerHTML = "<img src='/image/no.gif'/><font color='red'><b>长度为1-8个字符</b></font>";
        return false;
    }else{
        usernameResult.innerHTML = "<img src='/image/yes.gif'/><font color='green'><b>用户名合法</b></font>";
        return true;
    }

}

//字段级检查
function checkPassword() {

    var password = document.getElementById("password");
    var passwordResult = document.getElementById("passwordResult");

    if(password.value.length==0) {
        //if(password.value=="") {
        passwordResult.innerText = "密码不能为空";
        passwordResult.innerHTML = "<img src='/image/no.gif'/><font color='red'><b>密码不能为空</b></font>";

        return false;
    }else{
        passwordResult.innerText = "";
        return true;
    }

}

//字段级检查
function checkPassword2() {

    var password = document.getElementById("password");
    var password2 = document.getElementById("password2");
    var password2Result = document.getElementById("password2Result");

    if(password.value!=password2.value) {
        //password2Result.innerText = "两次密码不一致";
        password2Result.innerHTML = "<img src='/image/no.gif'/><font color='red'><b>两次密码不一致</b></font>";

        return false;
    }else{
        password2Result.innerText = "";
        return true;
    }

}

//表单级检查---方式1---弹框
/* function checkData() {

     var username = document.getElementById("username");
     if(username.value.length<5 || username.value.length>8) {
         alert("用户名的长度必须在5-8个字符");
         return false;
     }

     var password = document.getElementById("password");
     if(password.value=="") {
         alert("密码不能为空");
         return false;
     }

     return true;
 }*/

//表单级检查---方式2---span
function checkData() {

    return checkUsername() && checkPassword() && checkPassword2() && checkFile();

}

//字段级检查
function checkFile(){

    var file = document.getElementById("file");
    var photo = document.getElementById("photo");

    if(photo.checked && file.value==""){
        alert("必须选择自定义头像文件")
        return false;
    }else{
        return true;
    }
}