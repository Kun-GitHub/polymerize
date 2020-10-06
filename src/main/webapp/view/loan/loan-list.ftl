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
                    <li role="presentation">
                        <a href="${ctx!''}/user/user-list">商户管理</a>
                    </li>
                </#if>
                <li role="presentation" class="active">
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
                    <#--<div class="col-xs-3 col-sm-1 placeholder">
                        <div class="thumbnail tile tile-medium tile-blue">
                            <a href="#" class="fa-links" onclick="createData()">
                                <i class="icon-plus-sign-alt icon-2x"></i>
                                <h5>推广页面</h5>
                            </a>
                        </div>
                    </div>-->
<#--                    <#if type == 'admin'>-->
                    <#--<div class="col-xs-3 col-sm-1 placeholder">
                        <div class="thumbnail tile tile-medium tile-blue">
                            <a href="#" class="fa-links" data-toggle="modal" data-target="#addLoanInfo">
                                <i class="icon-plus-sign-alt icon-2x"></i>
                                <h5>新增</h5>
                            </a>
                        </div>
                    </div>-->
                    <div class="col-xs-3 col-sm-1 placeholder">
                        <div class="thumbnail tile tile-medium tile-danger">
                            <a href="#" class="fa-links" data-confirm="确定要删除选中项吗？" onclick="delRows()">
                                <i class="icon-trash icon-2x"></i>
                                <h5>删除</h5>
                            </a>
                        </div>
                    </div>
<#--                    </#if>-->
                </div>
                <form method="get" id="searchForm">
                    <h4 class="sub-header" style="clear:both;">
                        <input class="form-control" type="text" name="name" value="${vo.name!''}" placeholder="姓名" style="display: inline-block; width: auto;"/>
                        <input class="form-control" type="text" name="orderNumber" value="${vo.orderNumber!''}" placeholder="订单号" style="display: inline-block; width: auto;"/>
                        <input type="text" name="loanTime1" value="${vo.loanTime1!''}" placeholder="开始添加时间  " class="form-control time_input" style="display: inline-block; width: auto;"
                               onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" >~
                        <input type="text" name="loanTime2" value="${vo.loanTime2!''}" placeholder="结束添加时间  " class="form-control time_input" style="display: inline-block; width: auto;"
                               onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" ><br/><br/>
                        <#--<input class="form-control" type="text" name="userName" value="${vo.userName!''}" placeholder="跟进人" style="display: inline-block; width: auto;"/>-->
                        <input class="form-control" type="text" name="source" value="${vo.orderNumber!''}" placeholder="订单号" style="display: inline-block; width: auto;"/>
                        <button type="submit" class="btn btn-primary">查询</button>
                        <button type="button" class="btn btn-default" onclick="resetForm('searchForm')"> 重置</button>
                    </h4>
                </form>
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th class="th-checkbox"><input type="checkbox" onclick="selectAll('itemCb')" disabled></th>
                            <th data-sort="field:'account'">商户账号</th>
                            <th data-sort="field:'rate'">商户费率</th>
                            <th data-sort="field:'orderNumber'">订单号</th>
                            <th data-sort="field:'name'">姓名</th>
                            <th data-sort="field:'bankNo'">银行卡号</th>
                            <th data-sort="field:'bankLocation'">银行名称</th>
                            <th data-sort="field:'price'">金额</th>
                            <th data-sort="field:'status'">订单状态</th>
                            <th data-sort="field:'loanTime'">添加时间</th>
                            <th data-sort="field:'issueTime'">下发时间</th>
                            <#--<th data-sort="field:'notifyStatus'">是否回调</th>-->
                            <th data-sort="field:'operator'">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list page.list as item>
                        <tr>
                            <td>
                                <#if item.status??&&item.status==0>
                                    <input type="checkbox" name="itemCb" value=${item.id!''}>
                                <#else>
                                    <input type="checkbox" name="itemCb" value=${item.id!''} disabled>
                                </#if>
                            </td>
                            <td>${item.account!''}</td>
                            <td><#if item.rate??>${item.rate!''}%<#else>无</#if></td>
                            <td><#if item.orderNumber??>${item.orderNumber!''}<#else>无</#if></td>
                            <td>${item.name!''}</td>
                            <td>${item.bankNo!''}</td>
                            <td>${item.bankLocation!''}</td>
                            <td>${item.price!''}</td>
                            <td><#if item.status??&&item.status==0><span class="label label-warning">待处理</span>
                                <#elseif item.status??&&item.status==1><span class="label label-success">已下发</span>
                                <#elseif item.status??&&item.status==-1><span class="label label-danger">下发失败</span>
                                <#elseif item.status??&&item.status==-2><span class="label label-danger">取消订单</span>
                            <#else><span class="label label-default">未知状态</span></#if></td>
                            <td>${(item.loanTime?datetime)!''}</td>
                            <td><#if item.issueTime??>${(item.issueTime?datetime)!''}<#else>/</#if></td>
                            <#--<td><#if item.notifyStatus??&&item.notifyStatus==0><span class="label label-warning">未回调</span>
                                <#else><span class="label label-success">已回调</span></#if></td>-->
                            <td>
                                <#if item.status??&&item.status==0>
                                    <#if type??&&type=='admin'>
                                    <a href="#" data-toggle="modal" data-target="#updateStatusModal"
                                       onclick="initEdit(${item.id!''})">下发结果</a>&nbsp;&nbsp;</#if>
                                </#if>
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            <#include "/common/pager.ftl">
            <@pager pager=page baseUrl=uri parameter=parameter />
            </div>

            <div class="modal fade" id="addLoanInfo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">新增订单</h4>
                        </div>
                        <div class="modal-body">
                            <form class="form-horizontal" id="addLoanForm">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span class="red">*</span> 姓名：</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" placeholder="" name="name"
                                               data-rule-required="true">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span class="red">*</span> 卡号：</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" placeholder="" name="phone"
                                               data-rule-required="true">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span class="red">*</span> 金额：</label>
                                    <div class="col-sm-7">
                                        <input type="number" class="form-control" placeholder="" name="price"
                                               data-rule-required="true" >
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

            <!-- 新增Modal -->
            <div class="modal fade" id="updateStatusModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <h4 class="modal-title">更新下发结果</h4>
                        </div>
                        <div class="modal-body">
                            <form class="form-horizontal" id="updateStatusForm">
                                <input type="hidden" name="id"/>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"> 订单号：</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" placeholder="" name="orderNumber"
                                               data-rule-required="true" disabled>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"> 金额：</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" placeholder="" name="price"
                                               data-rule-required="true" disabled>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"> 银行卡号：</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" placeholder="" name="bankNo"
                                               data-rule-required="true" disabled>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span class="red">*</span>下发结果：</label>
                                    <div class="col-sm-6">
                                        <select name="status" class="form-control status" data-rule-required="true">
                                            <option value="" selected>请选择下发结果</option>
                                            <option value="1">下发成功</option>
                                            <option value="-1">下发失败</option>
                                            <option value="-2">取消订单</option>
                                        </select>
                                    </div>
                                    <div class="col-sm-3 errorText"></div>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <button type="button" class="btn btn-primary" onclick="updateStatus()">确定</button>
                        </div>
                    </div>
                </div>
            </div>


        <#include "/common/footer.ftl">

            <script type="text/javascript">
                $(function () {
                    $("#userId").selection("${ctx!''}/user/user-select",${vo.userId!'0'});
                });

                function initEdit(dataId) {
                    $.get(ctx + "/loan/loan-load",
                            {id: dataId},
                            function (data) {
                                if (data.message) {
                                    notice(data.message, "red");
                                } else {
                                    $("#setUserForm").setForm(data);
                                    $("#updateStatusForm").setForm(data);
                                    $("#updateLoanForm").setForm(data);
                                }
                            }, "json");
                }

                function updateStatus() {
                    if (!$("#updateStatusForm").valid()) {
                        return;
                    }
                    $.post(ctx + "/loan/loan-robbing",
                        $("#updateStatusForm").serialize(),
                        function (data) {
                            if (data.resultCode == 0) {
                                location.reload();
                            } else {
                                notice(data.message, "red");
                            }
                        }, "json");
                }

                function updateLoan() {
                    if (!$("#updateLoanForm").valid()) {
                        return;
                    }
                    $.post(ctx + "/loan/loan-update",
                            $("#updateLoanForm").serialize(),
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
                    $.post("${ctx!''}/loan/loan-delete",
                            {ids: ids},
                            function (data) {
                                if (data.resultCode == 0) {
                                    location.reload();
                                } else {
                                    notice(data.message, "red");
                                }
                            }, "json");
                }

                function save() {
                    if (!$("#addLoanForm").valid()) {
                        return;
                    }
                    $.post(ctx + "/loan/loan-new",
                            $("#addLoanForm").serialize(),
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