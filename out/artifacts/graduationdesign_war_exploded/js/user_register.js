function showFile(){

    var file = document.getElementById("file");
    var photo = document.getElementById("photo");

    if(photo.checked){  //自定义头像按钮被选中
        file.style.visibility = "visible";
    }else{
        file.style.visibility = "hidden";
    }
}

//字段级检查
function checkUsername() {

    var username = document.getElementById("username");
    var usernameResult = document.getElementById("usernameResult");

    if(username.value.length<1 || username.value.length>8) {
        //usernameResult.innerText = "用户名的长度必须在1-10个字符之间";
        usernameResult.innerHTML = "<img src='/image/no.gif'/><font color='red'><b>用户名的长度必须在1-10个字符之间</b></font>";
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