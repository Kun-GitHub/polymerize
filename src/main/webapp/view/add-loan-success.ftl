<!DOCTYPE html>
<html lang="zh">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="0">
    <title>猜下你能贷多少？</title>
    <link rel="shortcut icon" href="images/favicon.ico"/>
    <link type="text/css" href="${ctx!''}/resources/css/wx.css" rel="stylesheet"/>
    <script type="text/javascript" src="${ctx!''}/resources/js/jquery-1.9.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://res.wx.qq.com/open/libs/weui/1.1.2/weui.min.css">
    <script type="text/javascript" src="https://res.wx.qq.com/open/libs/weuijs/1.1.3/weui.min.js"></script>
    <style>
        .apply-finish {
            text-align: center;
            font-family: 微软雅黑;
        }

        div {
            display: block;
        }

        .apply-finish-header {
            padding-top: 50px;
        }

        .apply-finish-msg {
            font-weight: bold;
            font-size: 24px;
            color: #77b852;
            margin-top: 30px;
        }
    </style>
</head>

<body style="padding: 20px;">

<div class="content">
    <div class="web-width">
        <div class="for-liucheng">
            <div class="liulist for-cur"></div>
            <div class="liulist for-cur"></div>
            <div class="liulist"></div>
            <div class="liutextbox">
                <div class="liutext for-cur"><em>1</em><br/><strong>提交申请</strong></div>
                <div class="liutext for-cur"><em>2</em><br/><strong>贷款顾问服务</strong></div>
                <div class="liutext"><em>3</em><br/><strong>银行放款</strong></div>
            </div>
        </div><!--for-liucheng/-->
    </div><!--web-width/-->
</div><!--content/-->


<div class="apply-finish">
    <div class="apply-finish-header">
        <img src="${ctx!''}/resources/images/success.png">
        <div class="apply-finish-msg">提交成功！请稍后留意电话，我们的贷款顾问会为您服务</div>
    </div>

</div>

<div class="weui-footer weui-footer_fixed-bottom">
    <p class="weui-footer__links">
        <a href="" class="weui-footer__link"></a>
    </p>
    <p class="weui-footer__text">Copyright © 2008-2016 weui.io</p>
</div>

</body>
</html>
