<!DOCTYPE html>
<html>
<head>
<title>评估您的贷款额度</title>
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
					<h3>提交成功</h3>
					<p><h4 style="color:#ffffff;">恭喜您已经获得<span class="loan">20</span>万授信额度，请保持手机畅通，稍后会有客服电话联系您。</h4></p>
				</div>
			</div>
			<div class="clear"> </div>
		</div>
		<div class="agileits_copyright">
            <p><#if source??&&source=="3rd_UC">版权：厦门小小雨点金融信息服务有限公司<#else>版权：安融信息咨询（广州）有限公司</#if><br/>提示语：市场有风险，入市需谨慎。具体放款时间因个人情况而定</p>
        </div>
	</div>
</body>

<script src="${ctx!''}/resources/js/jquery.min.js"></script>
<script type="text/javascript">

    $(document).ready(function(){
        var num = Math.floor(Math.random()*10+10);
        $(".loan").html(num)
    });

</script>
</html>