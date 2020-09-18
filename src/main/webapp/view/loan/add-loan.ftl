<!DOCTYPE html>
<html>
<head>
<title>专业贷款平台-无抵押无担保信用贷款</title>
<!-- for-mobile-apps -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
		function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- //for-mobile-apps -->
<link href="${ctx!''}/resources/loan/css/style.css" rel="stylesheet" type="text/css" media="all" />
<!-- font-awesome icons -->
<link href="${ctx!''}/resources/loan/css/font-awesome.css" rel="stylesheet">
<!-- //font-awesome icons -->
</head>
<body>
	<div class="main">
		<div class="w3_agile_main_grids">
			<div class="agile_main_grid_left">
				<div class="w3_agileits_main_grid_left_grid">
					<h2>根 据 银 行 信 用 系 统 评 估</h2>
					<h3>1~300 <span>万？</span></h3>
                    <p><h4 style="color:#ffffff;">更低利率、更高额度、更快放款</h4></p>
				</div>
			</div>
			<div class="agileits_w3layouts_main_grid_right">
				<div class="wthree_main_grid_right_grid">
					<div class="w3ls_main_grid_right_grid_form">
						<form id="addForm">
                            <input type="hidden" name="source" value="${source!''}">
							<div class="w3l_main_grid_right_grid_form1">
								<label>Name</label>
								<input type="text" name="name" placeholder="请输入您的真实姓名">
							</div>
							<div class="w3l_main_grid_right_grid_form1">
								<label>Phone</label>
								<input type="text" name="phone" placeholder="请输入您的联系方式" class="phoneNum">
							</div>
							<div class="w3l_main_grid_right_grid_form1">
								<label>Check Code</label>
								<input type="text" name="code" placeholder="请输入您收到的验证码">
							</div>
                            <div class="w3l_main_grid_right_grid_form1">
                                <label>City</label>
                                <select id="w3_agileits_select1" name="city" class="w3layouts_select">
                                    <option value="guangzhou">广州</option>
                                    <option value="shenzhen">深圳</option>
                                    <option value="other">其他城市</option>
                                </select>
                            </div>
                            <div class="w3l_main_grid_right_grid_form1">
                                <label>Quota</label>
                                <input type="text" name="quota" placeholder="请输入您想要贷的额度">
                            </div>
							<input type="button" value="获 取 验 证 码" onclick="onSubmit()" id="submitButton">
						</form>
                        <div id="log" style="height:40px; overflow: hidden; margin-top:1.5em; font-size: 13px; color: #ffffff; font-weight: 300; font-family: 'Raleway', sans-serif; text-align: center">
                            <p style="width: 100%;margin-top: 5px;">喜报：134****2323 成功放款20万；</p>
                            <p style="width: 100%;margin-top: 5px;">喜报：181****6184 成功放款10万；</p>
                            <p style="width: 100%;margin-top: 5px;">喜报：159****1067 成功放款30万；</p>
                            <p style="width: 100%;margin-top: 5px;">喜报：150****8782 成功放款18万；</p>
                            <p style="width: 100%;margin-top: 5px;">喜报：135****9003 成功放款28万；</p>
                            <p style="width: 100%;margin-top: 5px;">喜报：181****7698 成功放款25万；</p>
                            <p style="width: 100%;margin-top: 5px;">喜报：134****3020 成功放款32万；</p>
                            <p style="width: 100%;margin-top: 5px;">喜报：135****2786 成功放款20万；</p>
                            <p style="width: 100%;margin-top: 5px;">喜报：180****3584 成功放款12万；</p>
                            <p style="width: 100%;margin-top: 5px;">喜报：159****1097 成功放款35万；</p>
                            <p style="width: 100%;margin-top: 5px;">喜报：159****8702 成功放款17万；</p>
                            <p style="width: 100%;margin-top: 5px;">喜报：135****9343 成功放款22万；</p>
                            <p style="width: 100%;margin-top: 5px;">喜报：182****1191 成功放款25万；</p>
                            <p style="width: 100%;margin-top: 5px;">喜报：136****3327 成功放款31万；</p>
                        </div>
					</div>
				</div>
			</div>
			<div class="clear"> </div>
		</div>
		<div class="agileits_copyright">
			<p><#if source??&&source=="3rd_UC">版权：厦门小小雨点金融信息服务有限公司<#else>版权：安融信息咨询（广州）有限公司</#if><br/>提示语：市场有风险，入市需谨慎。具体放款时间因个人情况而定</p>
		</div>
	</div>
</body>
<script type="text/javascript" src="${ctx!''}/resources/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
    var ammeterLog = document.getElementById('log');
    var speed = 50;
    log();
    // setInterval(log, 300000);

    function log() {
        var top = ammeterLog.scrollTop;
        var temp = -1;

        function Marquee() {
            if(top == temp){
                ammeterLog.scrollTop = 0;
                temp = -1;
            } else {
                temp = ammeterLog.scrollTop;
                ammeterLog.scrollTop++;
                top = ammeterLog.scrollTop;
            }
        }
        var MyMar = setInterval(Marquee, speed);        //设置定时器
        ammeterLog.onmouseover = function () {
            clearInterval(MyMar)
        }    //鼠标经过时清除定时器达到滚动停止的目的
        ammeterLog.onmouseout = function () {
            MyMar = setInterval(Marquee, speed)
        }
    }
</script>
<script type="text/javascript">

    var clientStatus = true;
    var smsSend = false;
    var p = null;

    function onSubmit() {

        var phoneNum = $(".phoneNum").val();
        if(null == phoneNum || undefined == phoneNum || "" == phoneNum){
            alert("请输入您的联系方式");
            return;
        }
        if(!clientStatus){
            if(smsSend){
                alert("验证码已发送");
			} else {
                alert("正在连接网络");
			}
            return;
		}
        clientStatus = false;

        if(smsSend){
            if(p != phoneNum){
                alert("请使用获取验证码的手机号");
                return;
			}

            $.post("${ctx!''}/loan/loan-save",
                    $("#addForm").serialize(),
                    function (data) {
                        clientStatus = true;
                        if (data.success) {
                            // alert('提交成功，请保持手机通畅，半个小时内会有客服联系');
                            // window.location.reload();
                            window.location.href = "${ctx!''}/loan/add-loan-success?source=${source!''}"
                        } else {
                            alert(data.message);
                        }
                    }, "json");
		} else {
            $.post("${ctx!''}/sms/send-sms",
                    {phone:phoneNum,source:'${source!''}'},
                    function (data) {
                        clientStatus = true;
                        if(null != data.code && undefined != data.code && data.code == 0){
                            p = phoneNum;
                            smsSend = true;
                            $("#submitButton").val('提 交');
                            alert("发送成功，如果10分钟内没收到验证码，请刷新页面重新获取");
                        } else {
                            alert(data.msg);
                        }
                    }, "json");
		}


    }
</script>
</html>