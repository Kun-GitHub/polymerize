<!DOCTYPE html>
<html lang="zh">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta charset="UTF-8">
    <meta name="keywords" content="小企业贷款，广州企业贷款，中小微企业贷款">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="0">
    <title>猜下你能贷多少？</title>
    <link rel="shortcut icon" href="images/favicon.ico"/>
    <link type="text/css" href="${ctx!''}/resources/css/wx.css" rel="stylesheet"/>
    <link type="text/css" href="${ctx!''}/resources/css/ui.css" rel="stylesheet"/>
    <script type="text/javascript" src="${ctx!''}/resources/js/jquery-1.9.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://res.wx.qq.com/open/libs/weui/1.1.2/weui.min.css">
    <script type="text/javascript" src="https://res.wx.qq.com/open/libs/weuijs/1.1.3/weui.min.js"></script>
    <script type="text/javascript" src="${ctx!''}/resources/js/aui-toast.js"></script>
    <style>
        .myPhone {
            padding-top: 0px;
            padding-bottom: 0px;
        }
        .myBtn {
            padding-top: 1px;
        }
    </style>
</head>

<body style="padding: 20px;">

<div class="content">
    <div class="web-width">
        <div class="for-liucheng">
            <div class="liulist for-cur"></div>
            <div class="liulist"></div>
            <div class="liulist"></div>
            <div class="liutextbox">
                <div class="liutext for-cur"><em>1</em><br/><strong>提交申请</strong></div>
                <div class="liutext"><em>2</em><br/><strong>贷款顾问服务</strong></div>
                <div class="liutext"><em>3</em><br/><strong>银行放款</strong></div>
            </div>
        </div><!--for-liucheng/-->
    </div><!--web-width/-->
</div><!--content/-->

<form id="addForm">
<div>
    <div class="weui-cells__title">姓名</div>
    <div class="weui-cells">
        <div class="weui-cell">
            <div class="weui-cell__bd">
                <input class="weui-input" name="name" type="text" placeholder="请输入您的姓名" required="required" />
            </div>
        </div>
    </div>

    <div class="weui-cells__title">怎么称呼</div>
    <div class="weui-cells weui-cells_radio">
        <label class="weui-cell weui-check__label" for="man">
            <div class="weui-cell__bd">
                <p>先生</p>
            </div>
            <div class="weui-cell__ft">
                <input type="radio" class="weui-check" name="sex" id="man" value="man" checked="checked"/>
                <span class="weui-icon-checked"></span>
            </div>
        </label>
        <label class="weui-cell weui-check__label" for="woman">
            <div class="weui-cell__bd">
                <p>女士</p>
            </div>
            <div class="weui-cell__ft">
                <input type="radio" name="sex" class="weui-check" id="woman" value="woman"/>
                <span class="weui-icon-checked"></span>
            </div>
        </label>
    </div>

    <div class="weui-cells__title">联系方式</div>
    <div class="weui-cells">
        <div class="weui-cell myPhone">
            <div class="weui-cell__bd">
                <input class="weui-input phoneNum" name="phone" type="number" placeholder="请输入您的手机号码" required="required" />
            </div>
            <div class="weui-cell__ft">
                <button class="weui-vcode-btn myBtn" onclick="getNum()">获取验证码</button>
            </div>
        </div>
    </div>

    <div class="weui-cells__title">验证码</div>
    <div class="weui-cells">
        <div class="weui-cell">
            <div class="weui-cell__bd">
                <input class="weui-input" name="code" type="number" placeholder="请输入您收到的验证码" required="required" />
            </div>
        </div>
    </div>

    <div class="weui-cells__title">现居住地</div>
    <div class="weui-cells">
        <#--<div class="weui-cell weui-cell_select">
            <div class="weui-cell__bd">
                <select class="weui-select" name="select1">
                    <option selected="" value="1">广东</option>
                </select>
            </div>
        </div>-->
        <div class="weui-cell weui-cell_select">
            <div class="weui-cell__bd">
                <select class="weui-select" name="city">
                    <option selected value="guangzhou">广州</option>
                    <option value="shenzhen">深圳</option>
                    <option value="other">其他城市</option>
                </select>
            </div>
        </div>
    </div>

    <div class="weui-cells__title">贷款额度/万</div>
    <div class="weui-cells">
        <div class="weui-cell">
            <div class="weui-cell__bd">
                <input class="weui-input" name="quota" type="number" placeholder="请输入您需要的贷款额度" required="required" />
            </div>
        </div>
    </div>

    <#--<label for="weuiAgree" class="weui-agree" style="margin-top: 20px;">
        <input id="weuiAgree" type="checkbox" class="weui-agree__checkbox"/>
        <span class="weui-agree__text">
                阅读并同意<a href="javascript:void(0);">《相关条款》</a>
            </span>
    </label>-->

    <div class="weui-btn-area"  style="margin-top: 40px;">
        <a class="weui-btn weui-btn_primary" href="javascript:" onclick="save()">确定</a>
    </div>
</div>
</form>

<div class="weui-footer weui-footer_fixed-bottom">
    <p class="weui-footer__links">
        <a href="" class="weui-footer__link"></a>
    </p>
    <p class="weui-footer__text">Copyright © 2008-2016 weui.io</p>
</div>

<script type="text/javascript">
    var toast = new auiToast({
    })

    var smsStatus = null;

    function save() {
        if(!smsStatus){
            alert("请获取手机验证码");
            return;
        }

        $.post("${ctx!''}/loan-save",
                $("#addForm").serialize(),
                function (data) {
                    if (data.resultCode == 0) {
                        window.location.href = "${ctx!''}/add-loan-success";
                    } else {
                        alert(data.message);
                    }
                }, "json");
    }
    
    function getNum() {

        var phoneNum = $(".phoneNum").val();
        if(null == phoneNum || undefined == phoneNum || "" == phoneNum){
            alert("请输入您的联系方式");
            return;
        }

        toast.loading({
            title:"加载中",
            duration:2000
        });

        $.post("${ctx!''}/sms/send-sms",
                {phone:phoneNum},
                function (data) {
                    smsStatus = false;
                    toast.hide();
                    if(null != data.code && undefined != data.code && data.code == 0){
                        smsStatus = true;
                        toast.success({
                            title:""+data.msg,
                            duration:2000
                        });
                    } else {
                        toast.fail({
                            title:""+data.msg,
                            duration:2000
                        });
                    }
                }, "json");
    }
</script>

</body>
</html>
