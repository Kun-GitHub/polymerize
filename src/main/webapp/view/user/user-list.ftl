<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>${appName!''}</title>
    <#include "/common/header.ftl">
    <script type="text/javascript" src="${ctx!''}/resources/js/my97/WdatePicker.js"></script>
    <script type="text/javascript" src="${ctx!''}/resources/js/my97/skin/WdatePicker.css"></script>
</head>
<body>
<#include "/common/topnav.ftl">

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar" role="tablist">
                <#if type??&&type=='admin'>
                    <li role="presentation" class="active">
                        <a href="${ctx!''}/user/user-list">商户管理</a>
                    </li>
                </#if>
                <li role="presentation">
                    <a href="${ctx!''}/loan/loan-list">订单管理</a>
                </li>
                <li role="presentation">
                    <a href="${ctx!''}/recharge/recharge-list">充值记录</a>
                </li>
                <li role="presentation">
                    <a href="${ctx!''}/deduction/deduction-list">扣费记录</a>
                </li>
                <#--<#if type??&&type=='admin'>
                    <li role="presentation">
                        <a href="${ctx!''}/sms/sms-list">短信发送记录</a>
                    </li>
                </#if>-->
            </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main tab-content">

            <div class="tab-pane active" role="tabpanel" id="roletab">
                <div class="row placeholders">
                    <div class="col-xs-3 col-sm-1 placeholder">
                        <div class="thumbnail tile tile-medium tile-blue">
                            <a href="#" class="fa-links" data-toggle="modal" data-target="#addUserInfo">
                                <i class="icon-plus-sign-alt icon-2x"></i>
                                <h5>新增</h5>
                            </a>
                        </div>
                    </div>
                    <div class="col-xs-3 col-sm-1 placeholder">
                        <div class="thumbnail tile tile-medium tile-danger">
                            <a href="#" class="fa-links" data-confirm="确定要删除选中项吗？" onclick="delRows()">
                                <i class="icon-trash icon-2x"></i>
                                <h5>删除</h5>
                            </a>
                        </div>
                    </div>
                </div>
                <form method="get" id="searchForm">
                    <h4 class="sub-header" style="clear:both;">
                        <input class="form-control" type="text" name="account" value="${vo.account!''}" placeholder="账号"
                               style="display: inline-block; width: auto;"/>
                        <button type="submit" class="btn btn-primary">查询</button>
                        <button type="button" class="btn btn-default" onclick="resetForm('searchForm')"> 重置</button>
                    </h4>
                </form>
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <#if page.list?? &&page.list?size!=1>
                                <th class="th-checkbox"><input type="checkbox" onclick="selectAll('itemCb')"></th>
                            </#if>
                            <th data-sort="field:'account'">商户账号</th>
                            <th data-sort="field:'parentName'">上级</th>
                            <th data-sort="field:'rate'">商户费率</th>
                            <th data-sort="field:'money'">总金额</th>
                            <th data-sort="field:'profit'">总利润</th>
                            <th data-sort="field:'surplus'">可用余额</th>
                            <#if page.list?? &&page.list?size!=1><th>操作</th></#if>
                            <#if page.list?? &&page.list?size!=1><th>充值</th></#if>
                        </tr>
                        </thead>
                        <tbody>
                        <#list page.list as item>
                        <tr>
                            <#if page.list?? &&page.list?size!=1>
                                <td>
                                    <#if page.page!=1>
                                        <input type="checkbox" name="itemCb" value=${item.id!''}>
                                    <#elseif item??&&item_index!=0>
                                        <input type="checkbox" name="itemCb" value=${item.id!''}>
                                    </#if>
                                </td>
                            </#if>
                            <td>${item.account!''}</td>
                            <td>${item.parentName!''}</td>
                            <td><#if item.rate??>${item.rate!''}%<#else>无</#if></td>
                            <td>${item.money!''}</td>
                            <td>${item.profit!''}</td>
                            <td>${item.surplus!''}</td>
                            <#if page.list?? &&page.list?size!=1>
                                <td>
                                    <#if page.page!=1 || item??&&item_index!=0>
                                        <a href="#" data-toggle="modal" data-target="#editUserInfo"
                                           onclick="initEdit(${item.id!''})">编辑信息</a>&nbsp;&nbsp;
                                    </#if>
                                </td>
                                <td>
                                    <#if page.page!=1 || item??&&item_index!=0>
                                        <a href="#" data-toggle="modal" data-target="#recharge"
                                           onclick="initEdit(${item.id!''})">充值</a>&nbsp;&nbsp;
                                    </#if>
                                </td>
                            </#if>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            <#include "/common/pager.ftl">
            <@pager pager=page baseUrl=uri parameter=parameter />
            </div>

            <div class="modal fade" id="addUserInfo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">新增子商户</h4>
                        </div>
                        <div class="modal-body">
                            <form class="form-horizontal" id="addForm">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span class="red">*</span> 商户账号：</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" placeholder="" name="account"
                                               data-rule-required="true">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span class="red">*</span> 商户费率：</label>
                                    <div class="col-sm-7">
                                        <input type="number" class="form-control" placeholder="" name="rate"
                                               data-rule-required="true">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span class="red">*</span> 密码：</label>
                                    <div class="col-sm-7">
                                        <input type="password" class="form-control" placeholder="" name="pwd"
                                               data-rule-required="true">
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="button" class="btn btn-primary" onclick="save()">保存</button>
                        </div>
                    </div>
                </div>
            </div>

            <!-- 修改电表Modal -->
            <div class="modal fade" id="editUserInfo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">修改商户信息</h4>
                        </div>
                        <div class="modal-body">
                            <form class="form-horizontal" id="editForm">
                                <input type="hidden" name="id"/>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span class="red">*</span> 商户账号：</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" placeholder="" name="account"
                                               data-rule-required="true" disabled>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span class="red">*</span> 商户费率：</label>
                                    <div class="col-sm-7">
                                        <input type="number" class="form-control" placeholder="" name="rate"
                                               data-rule-required="true">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span class="red">*</span> 密码：</label>
                                    <div class="col-sm-7">
                                        <input type="password" class="form-control" placeholder="" name="pwd"
                                               data-rule-required="true">
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="button" class="btn btn-primary" onclick="update()">保存</button>
                        </div>
                    </div>
                </div>
            </div>

            <!-- 修改电表Modal -->
            <div class="modal fade" id="recharge" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">充值</h4>
                        </div>
                        <div class="modal-body">
                            <form class="form-horizontal" id="editForm">
                                <input type="hidden" name="id"/>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span class="red">*</span> 商户账号：</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" placeholder="" name="account"
                                               data-rule-required="true" disabled>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span class="red">*</span> 充值金额：</label>
                                    <div class="col-sm-7">
                                        <input type="number" class="form-control" placeholder="" name="surplus"
                                               data-rule-required="true">
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="button" class="btn btn-primary" onclick="recharge()">保存</button>
                        </div>
                    </div>
                </div>
            </div>

        <#include "/common/footer.ftl">

            <script type="text/javascript">
                function initEdit(dataId) {
                    $.get(ctx + "/user/user-load",
                            {id: dataId},
                            function (data) {
                                if (data.message) {
                                    notice(data.message, "red");
                                } else {
                                    $("#editForm").setForm(data);
                                }
                            }, "json");
                }

                function save() {
                    if (!$("#addForm").valid()) {
                        return;
                    }
                    $.post(ctx + "/user/user-save",
                            $("#addForm").serialize(),
                            function (data) {
                                if (data.resultCode == 0) {
                                    location.reload();
                                } else {
                                    notice(data.message, "red");
                                }
                            }, "json");
                }

                function update() {
                    if (!$("#editForm").valid()) {
                        return;
                    }
                    $.post(ctx + "/user/user-update",
                            $("#editForm").serialize(),
                            function (data) {
                                if (data.resultCode == 0) {
                                    location.reload();
                                } else {
                                    notice(data.message, "red");
                                }
                            }, "json");
                }

                function recharge() {
                    if (!$("#editForm").valid()) {
                        return;
                    }
                    $.post(ctx + "/user/user-recharge",
                        $("#editForm").serialize(),
                        function (data) {
                            if (data.resultCode == 0) {
                                location.reload();
                            } else {
                                notice(data.message, "red");
                            }
                        }, "json");
                }

                function delRows() {
                    var ids = getIds("itemCb");
                    if (!ids) {
                        notice("请至少选择一条记录删除", "red");
                        return;
                    }
                    $.post(ctx + "/user/user-delete",
                            {ids: ids},
                            function (data) {
                                if (data.resultCode == 0) {
                                    location.reload();
                                } else {
                                    notice(data.message, "red");
                                }
                            }, "json");
                }
            </script>
</body>
</html>