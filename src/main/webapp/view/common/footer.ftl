<footer class="main-footer" >
    李志坤  &nbsp; 版权所有
    <div class="pull-right hidden-xs">
        <b>Version</b> 2.0
    </div>
</footer>

<script src="${ctx!''}/resources/js/bootstrap.min.js"></script>

<script src="${ctx!''}/resources/js/jquery.formHelp.js"></script>
<script src="${ctx!''}/resources/js/jquery-validation/jquery.validate.min.js"></script>
<script src="${ctx!''}/resources/js/jquery-validation/jquery.validate.extend.js"></script>
<script src="${ctx!''}/resources/js/MD5.js"></script>

<script type="text/javascript">
    var ctx = "${ctx!''}";
    new jBox('Confirm', {
        confirmButton: '确定',
        cancelButton: '取消'
    });

    var url = window.location.pathname;
    $("#navbar").find("a").each(function () {
        var curUrl = $(this).attr("href");
        if(curUrl.indexOf("?")>-1){
            curUrl = curUrl.substring(0,curUrl.indexOf("?"));
        }
        if (url.indexOf(curUrl)>-1) {
            $("#navbar").find("li").removeClass("active");
            $(this).parent("li").addClass("active");
            return false;
        }
    });

    $('.showpic').click(function () {
        var path = $(this).children("a").children("img").attr("src");
        new jBox('Modal', {
            content: '<img  src="' + path + '" />',
            theme: 'ModalBorder',
            overlay: false,
            closeOnClick: true
        }).open();
    });

    $(function () {
        var bodyHeight = $("body").height()+50;
        var winHeight = getWinHeight();
        if(bodyHeight < winHeight){
            $(".main-footer").addClass("fix-footer");
        }
    });

</script>

<!-- 个人信息Modal -->
<div class="modal fade" id="myInfoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">个人信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myInfoForm">
                    <input type="hidden" name="id"/>
                    <div class="form-group">
                        <label class="col-sm-3 control-label"><span class="red">*</span> 用户账号：</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" placeholder="" name="account" disabled="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label"><span class="red">*</span> 用户姓名：</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" placeholder="" name="userName"
                                   data-rule-required="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label"> 手机号码：</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" placeholder="" name="mobile" data-rule-isMobile="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label"> 电子邮箱：</label>
                        <div class="col-sm-7">
                            <input type="email" class="form-control" placeholder="" name="email" data-rule-email="true">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="updateMyInfo()">保存</button>
            </div>
        </div>
    </div>
</div>

<!-- 重置密码Modal -->
<div class="modal fade" id="myPwdModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">修改密码</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myPwdForm">
                    <div class="form-group">
                        <label class="col-sm-3 control-label"><span class="red">*</span> 原始密码：</label>
                        <div class="col-sm-7">
                            <input type="password" class="form-control" placeholder="" name="oldPassword"
                                   data-rule-required="true" id="oldPassword">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label"><span class="red">*</span> 新密码：</label>
                        <div class="col-sm-7">
                            <input type="password" class="form-control" placeholder="" name="newPassword"
                                   data-rule-required="true" id="newPassword" data-rule-minlength="6">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label"><span class="red">*</span> 确认新密码：</label>
                        <div class="col-sm-7">
                            <input type="password" class="form-control" placeholder="" name="confirmPassword"
                                   data-rule-required="true" data-rule-equalTo="#newPassword">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="updateMyPwd()">保存</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    function loadMyInfo() {
        $.get(ctx + "/bas/myInfo-load",
                function (data) {
                    if (data.message) {
                        notice(data.message, "red");
                    } else {
                        $("#myInfoForm").setForm(data);
                    }
                }, "json");
    }

    function updateMyInfo() {
        if (!$("#myInfoForm").valid()) {
            return;
        }

        $.post(ctx + "/bas/user-update",
                $("#myInfoForm").serialize(),
                function (data) {
                    if (data.success) {
                        location.reload();
                    } else {
                        notice(data.message, "red");
                    }
                }, "json");
    }


    function updateMyPwd() {
        if (!$("#myPwdForm").valid()) {
            return;
        }

        $.post(ctx + "/user/pwd-update",
                {
                    oldPassword:$("#oldPassword").val(),
                    newPassword:$("#newPassword").val(),
                },
                function (data) {
                    if (data.success) {
                        location.reload();
                    } else {
                        notice(data.message, "red");
                    }
                }, "json");
    }

</script>