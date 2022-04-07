function $(id) {
    return document.getElementById(id);
}

function preRegist() {
    //用户名不能为空，而且是6~16位数字和字母组成
    var unameReg = /[0-9a-zA-Z]{6,16}/;
    var unameTxt = $("unameTxt");
    var uname = unameTxt.value();
    var unameSpan = $("unameSpan");
    if (!unameReg.test(uname)) {
        unameSpan.style.visibility = "visible";
        return false;
    } else {
        unameSpan.style.visibility = "hidden";
    }

    //密码长度至少为八位
    var pwdTxt = $("pwdTxt");
    var pwd = pwdTxt.value;
    var pwdReg = /[\w]{8,}/;
    var pwdSpan = $("pwdSpan");
    if (!pwdReg.test(pwd)) {
        pwdSpan.style.visibility = "visible";
        return false
    } else {
        pwdSpan.style.visibility = "hidden";
    }

    //密码两次输入不一致
    var pwd2 = $("pwdTxt2").value;
    var pwdSpan2 = $("pwdSpan2");
    if (pwd != pwd2) {
        pwdSpan2.style.visibility = "visible";
        return false;
    } else {
        pwdSpan2.style.visibility = "hidden";
    }

    //正确的邮箱格式检验
    var email = $("emailTxt").value;
    var emailSpan = $("emailSpan");
    var emailReg = /^[a-zA-Z0-9_\.-]+@([a-zA-Z0-9-]+[\.]{1})+[a-zA-Z]+$/;
    if (!emailReg.test(email)) {
        emailSpan.style.visibility = "visible";
        return false;
    } else {
        emailSpan.style.visibility = "hidden";
    }

    //检验均通过，返回true，同意提交
    return true;
}


//如果想要发送异步请求，我们需要一个关键的对象 XMLHttpRequest
var xmlHttpRequest;

function createXMLHttpRequest() {
    if (window.xmlHttpRequest) {
        //符合DOM2标准的浏览器 ，xmlHttpRequest的创建方式
        xmlHttpRequest = new xmlHttpRequest();
    } else if (window.ActiveXObject) {
        //IE浏览器
        try {
            xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
        } catch (e) {
            xmlHttpRequest = new ActiveXObject("Msxml2.XMLHTTP");
        }
    }
}

function checkUname(uname) {
    createXMLHttpRequest();
    var url = "user.do?operate=checkUname&uname=" + uname;
    xmlHttpRequest.open("GET", url, true);
    //设置回调函数
    xmlHttpRequest.onreadystatechange = checkUnameCB;
    //发送请求
    xmlHttpRequest.send();
}

//回调函数
function checkUnameCB() {
    if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
        //xmlHttpRequest.responseText 表示 服务器端响应给我的文本内容
        var responseText = xmlHttpRequest.responseText;
        if (responseText == "{'uname':'1'}") {
            alert("用户名已经被注册！");
        } else {
            alert("用户名可以注册！");
        }
    }
}