<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>登录 - ${appName!''}</title>
    <#include "/common/header.ftl">
</head>
<body>
<style>
    body {
        padding: 0;
        height: 100%;
    }

    .index-bg {
        position: absolute;
        top: 0;
        left: 0;
        background-color: #FFFFFF;
        height: 100%;
        width: 100%;
    }

    .jBox-Modal {
        width: 310px;
    }

    .jBox-container {
        background: transparent;
    }

    .jBox-Modal .jBox-content {
        padding: 0;
        position: relative;
        overflow: hidden;
        border: 1px solid transparent;
        background: rgba(0, 0, 0, .8);
        _background: 0;
        _border: 0;
        filter: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr='#66000000', EndColorStr='#66000000');
    }

    .form-signin {
        position: relative;
        color: #fff;
        padding: 15px;
    }

    .msg {
        margin-top: 5px;
        color: #ffaa33;
    }

    .footer {
        height: 40px;
        overflow: hidden;
        z-index: 101;
        position: absolute;
        left: 0;
        bottom: 0;
        width: 100%;
        background: rgba(0, 0, 0, .7);
    }
</style>
<#--<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            &lt;#&ndash;<img class="img-responsive" src="${ctx!''}/resources/images/qws_logo.png"
                 style="width: 140px;height: 34px;margin-top: 7px;">&ndash;&gt;
            <span style="font-size: 28px;color: #fff;line-height: 50px;">${appName!''}</span>
        </div>
    </div>
</nav>-->

<div class="container-fluid">
    <div class="index-bg"></div>
    <div id="jBoxID1" class="jBox-wrapper jBox-Modal jBox-Default" style="position: fixed;
		display: block; opacity: 1; z-index: 10000; left: 50%; top: 50%; margin-left: -155px; margin-top: -120px;">
        <div class="jBox-container">
            <div class="jBox-content" style="width: auto; height: auto;">
                <div id="loginbox" class="loginbox" style="display: block;">
                    <form class="form-signin">
                        <h4 class="form-signin-heading">${appName!''}</h4>
                        <label for="inputAccount" class="sr-only">账号</label>
                        <input type="text" id="account" class="form-control" placeholder="账号" >
                        <div style="height: 10px;"></div>
                        <label for="inputPassword" class="sr-only">密码</label>
                        <input type="password" id="password" class="form-control" placeholder="密码" >
                        <div style="height: 20px;"></div>
                        <button class="btn btn-lg btn-block btn-main" type="button" id="login">登 录</button>
                        <div class="msg" id="msg"></div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <#--<div class="footer">
        <div style="text-align: center; line-height: 40px; color: #fff; font-size: 13px;">
        </div>
    </div>-->

</div>
<script src="${ctx!''}/resources/js/bootstrap.min.js"></script>
<script src="${ctx!''}/resources/js/MD5.js"></script>
<script type="text/javascript">

    $(function () {
        $("#login").click(function () {
            submit();
        });

        document.onkeydown = function (e) {
            var ev = document.all ? window.event : e;
            if (ev.keyCode == 13) {
                submit();
            }
        };

    });

    function submit() {
        var account = $.trim($("#account").val());
        var password = $.trim($("#password").val());

        if (account == "") {
            $("#msg").html("账号不能为空");
            return;
        }
        if (password == "") {
            $("#msg").html("密码不能为空");
            return;
        }

        $.post("${ctx!''}/loginSubmit",
                {
                    account: account,
                    password: password
                },
                function (response) {
                    if (response.resultCode == 0) {
                        if(response.message == "user"){
                            window.location.href = '${ctx!''}/student/student-feedback';
                        } else if(response.message == "loan"){
                            window.location.href = '${ctx!''}/loan/loan-list';
                        } else if(response.message == "admin"){
                            window.location.href = '${ctx!''}/student/student-list';
                        }
                    } else {
                        $("#msg").html(response.message);
                    }
                }, "json");
    }
</script>

</body>
</html>