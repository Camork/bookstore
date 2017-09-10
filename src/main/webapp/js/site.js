/**
 * Created by Camork on 2017-05-01.
 * functions
 */

$(function () {

    // $(".legitRipple").ripple({maxDiameter: "120%"});

    $('.modal').modal({
        opacity: .5, // Opacity of modal background
        in_duration: 300, // Transition in duration
        out_duration: 200, // Transition out duration
    });

    $('.close').click(function () {
        $(this).closest('.register-form').toggleClass('open');
    });

    $("#pass_again").blur(function () {
        if ($('#reg_pass').val() !== $('#pass_again').val()) {
            Materialize.toast('两次密码输入不同', 4000);
        }
    });

    $("#reg_username").blur(function () {
        $.post(getContextPath() + "/user/checkUserName", {
            userName: $("#reg_username").val()
        }, function (result) {
            if (result.available) {
                Materialize.toast('该用户名可用', 4000);
            } else {
                Materialize.toast('对不起，该用户名已被占用，请更换', 4000);
            }
        });
    });

    if (document.readyState === 'complete') {
        $('body').addClass('loaded');

        var version=IEVersion();
        if(version!=-1){
            Materialize.toast('您的浏览器为'+version+'可能出现兼容错误',4000);
        }

    }
});

function IEVersion() {
    var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
    var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1; //判断是否IE<11浏览器
    var isEdge = userAgent.indexOf("Edge") > -1 && !isIE; //判断是否IE的Edge浏览器
    var isIE11 = userAgent.indexOf('Trident') > -1 && userAgent.indexOf("rv:11.0") > -1;
    if(isIE) {
     return 'IE';
    }  else if(isIE11) {
        return 'IE11'; //IE11
    }else{
        return -1;//不是ie浏览器
    }
}

function getContextPath() {
    var webroot = document.location.href;
    webroot = webroot.substring(webroot.indexOf('//') + 2, webroot.length);
    webroot = webroot.substring(webroot.indexOf('/') + 1, webroot.length);
    webroot = webroot.substring(0, webroot.indexOf('/'));
    return "/" + webroot;
}

function delayURL(url) {
    setTimeout("top.location.href = '" + url + "'", 500);
}

function refreshURL(url) {
    $('#loaderModal').modal("open");
    $.get(url, function (result) {
        if (result.state === "ok") {
            $('#loaderModal').modal('close');
            location.reload();
        } else {
            Materialize.toast('刷新失败', 4000);
            $('#loaderModal').modal('close');
        }
    });
}

function login() {
    let userName = $("#username").val();
    let userPass = $("#password").val();
    let checked = document.getElementById("filled-box").checked;

    $.post(getContextPath() + "/user/login", {
        userName: userName,
        userPass: userPass,
        checked: checked
    }, function (result) {
        var arr = result.login;

        for (let x in arr) {
            Materialize.toast(arr[x], 4000);
            if (arr[x] === "登录成功") {
                delayURL("index");
            }
        }

    });
}

function reg() {

    let userName = $("#reg_username").val();
    let userPass = $("#reg_pass").val();
    let userPassConfirm = $("#pass_again").val();

    $.post(getContextPath() + "/user/reg",
        {
            userName: userName,
            userPass: userPass,
            userPassConfirm: userPassConfirm
        }, function (result) {
            let arr = result.reg;
            for (let x in arr) {
                Materialize.toast(arr[x], 4000);
                if (arr[x] === "注册成功") {
                    $('#close').click();
                }
            }
        }
    );
}

function updateType() {
    $('#loaderModal').modal("open");
    $.get(getContextPath() + "/admin/updateType", function (result) {
        if (result.state === "ok") {
            Materialize.toast('刷新成功', 4000);
            $('#loaderModal').modal('close');
        } else {
            Materialize.toast('刷新失败', 4000);
            $('#loaderModal').modal('close');
        }
    })
}

function updateBookByType() {
    $('#loaderModal').modal("open");
    let typeName = $('#xxx').val();
    $.post(getContextPath() + "/admin/updateBookByType",
        {
            type: typeName
        }, function (result) {
            if (result.state === "ok") {
                Materialize.toast('刷新成功', 4000);
                $('#loaderModal').modal('close');
            } else {
                Materialize.toast('刷新失败', 4000);
                $('#loaderModal').modal('close');
            }
        })
}

function updateNewBook() {
    $('#loaderModal').modal("open");
    $.get(getContextPath() + "/admin/updateNewBook", function (result) {
        if (result.state === "ok") {
            Materialize.toast('刷新成功', 4000);
            $('#loaderModal').modal('close');
        } else {
            Materialize.toast('刷新失败', 4000);
            $('#loaderModal').modal('close');
        }
    })
}

function getTag(typeName) {
    $('body').removeClass("loaded");
    window.location.href = getContextPath() + "/book/type/" + typeName;
}