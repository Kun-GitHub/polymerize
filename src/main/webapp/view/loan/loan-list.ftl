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
                    <div class="col-xs-3 col-sm-1 placeholder">
                        <div class="thumbnail tile tile-medium tile-blue">
                            <a href="#" class="fa-links" data-toggle="modal" data-target="#addLoanInfo">
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
<#--                    </#if>-->
                </div>
                <form method="get" id="searchForm">
                    <h4 class="sub-header" style="clear:both;">
                        <input class="form-control" type="text" name="name" value="${vo.name!''}" placeholder="姓名" style="display: inline-block; width: auto;"/>
                        <input class="form-control" type="text" name="phone" value="${vo.phone!''}" placeholder="卡号" style="display: inline-block; width: auto;"/>
                        <#--<select class="form-control" name="ammeterType" style="display: inline-block; width: auto;">
                            <option value="">请选择跟进结果</option>
                            <option value="0">待跟进</option>
                            <option value="1">跟进中</option>
                            <option value="2">条件不符</option>
                            <option value="3">电话未接通</option>
                            <option value="4">用户考虑中</option>
                            <option value="5">用户放弃</option>
                            <option value="6">邀约到店中</option>
                            <option value="7">提交资料</option>
                            <option value="8">提交审批中</option>
                            <option value="9">审批通过</option>
                            <option value="10">审批未通过</option>
                            <option value="11">完成放款</option>
                            <option value="12">异地客户</option>
                        </select>-->
                        <input type="text" name="loanTime1" value="${vo.loanTime1!''}" placeholder="开始添加时间  " class="form-control time_input" style="display: inline-block; width: auto;"
                               onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" >~
                        <input type="text" name="loanTime2" value="${vo.loanTime2!''}" placeholder="结束添加时间  " class="form-control time_input" style="display: inline-block; width: auto;"
                               onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" ><br/><br/>
                        <#--<input class="form-control" type="text" name="userName" value="${vo.userName!''}" placeholder="跟进人" style="display: inline-block; width: auto;"/>-->
                        <input class="form-control" type="text" name="source" value="${vo.source!''}" placeholder="订单号" style="display: inline-block; width: auto;"/>
                        <button type="submit" class="btn btn-primary">查询</button>
                        <button type="button" class="btn btn-default" onclick="resetForm('searchForm')"> 重置</button>
                    </h4>
                </form>
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th class="th-checkbox"><input type="checkbox" onclick="selectAll('itemCb')" disabled></th>
                            <th data-sort="field:'name'">姓名</th>
                            <th data-sort="field:'phone'">卡号</th>
                            <th data-sort="field:'price'">金额</th>
                            <#--<th data-sort="field:'city'">居住地</th>-->
                            <#--<th data-sort="field:'quota'">贷款额度</th>-->
                            <th data-sort="field:'status'">订单状态</th>
                            <#--<th data-sort="field:'userName'">商户</th>-->
                            <th data-sort="field:'source'">订单号</th>
                            <th data-sort="field:'loanTime'">添加时间</th>
                            <th data-sort="field:'operator/remark'">操作</th>
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
                            <td>${item.name!''}</td>
                            <td>${item.phone!''}</td>
                            <td>${item.price!''}</td>
                            <#--<td><#if item.city??&&item.city=='guangzhou'>广州<#elseif item.city??&&item.city=='shenzhen'>
                                深圳<#else>其他</#if></td>-->
                            <#--<td>${item.quota!''}</td>-->
                            <td><#if item.status??&&item.status==0><span class="label label-warning">未下发</span>
                            <#else><span class="label label-default">已下发</span></#if></td>
                            <#--<td>${item.userName!''}</td>-->
                            <td><#if item.source??>${item.source!''}<#else>无</#if></td>
                            <td>${(item.loanTime?datetime)!''}</td>
                            <td>
                                <#if item.status??&&item.status==0>
                                    <#if type??&&type=='admin'>
                                    <a href="#"
                                       onclick="robbing(${item.id!''})">点击下发</a>&nbsp;&nbsp;</#if>
                                <#--<#elseif item.status??&&item.status!=0&&item.status!=11&&item.status!=10&&item.status!=5&&item.status!=2&&item.status!=12>
                                    &lt;#&ndash;<a href="#" data-toggle="modal" data-target="#setUserModal"
                                       onclick="initEdit(${item.id!''})">安排跟进</a>&nbsp;&nbsp;&ndash;&gt;
                                    <a href="#" data-toggle="modal" data-target="#updateStatusModal"
                                       onclick="initEdit(${item.id!''})">跟进结果</a>&nbsp;&nbsp;
                                    <a href="#" data-toggle="modal" data-target="#updateLoanModal"
                                       onclick="initEdit(${item.id!''})">客户信息</a>&nbsp;&nbsp;-->
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
                                <#--<div class="form-group">
                                    <label class="col-sm-3 control-label"> 贷款额度：</label>
                                    <div class="col-sm-7">
                                        <input type="number" class="form-control" placeholder="" name="quota">
                                    </div>
                                </div>-->
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span class="red">*</span> 金额：</label>
                                    <div class="col-sm-7">
                                        <input type="number" class="form-control" placeholder="" name="price"
                                               data-rule-required="true" >
                                    </div>
                                </div>
                                <#--<div class="form-group">
                                    <label class="col-sm-3 control-label"> 来源：</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" placeholder="" name="source" >
                                    </div>
                                </div>-->
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
            <div class="modal fade" id="setUserModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <h4 class="modal-title">安排员工跟进</h4>
                        </div>
                        <div class="modal-body">
                            <form class="form-horizontal" id="setUserForm">
                                <input type="hidden" name="id"/>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span class="red">*</span>跟进人：</label>
                                    <div class="col-sm-6">
                                        <select name="userId" id="userId" class="form-control userId" data-rule-required="true">
                                            <option value="">请选择跟进人</option>
                                        </select>
                                    </div>
                                    <div class="col-sm-3 errorText"></div>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <button type="button" class="btn btn-primary" onclick="setUser()">确定</button>
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
                            <h4 class="modal-title">更新跟进结果</h4>
                        </div>
                        <div class="modal-body">
                            <form class="form-horizontal" id="updateStatusForm">
                                <input type="hidden" name="id"/>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span class="red">*</span>跟进结果：</label>
                                    <div class="col-sm-6">
                                        <select name="status" class="form-control status" data-rule-required="true">
                                            <option value="">请选择跟进结果</option>
                                            <option value="2">条件不符</option>
                                            <option value="3">电话未接通</option>
                                            <option value="4">用户考虑中</option>
                                            <option value="5">用户放弃</option>
                                            <option value="6">邀约到店中</option>
                                            <option value="7">提交资料</option>
                                            <option value="8">提交审批中</option>
                                            <option value="9">审批通过</option>
                                            <option value="10">审批未通过</option>
                                            <option value="11">完成放款</option>
                                            <option value="12">异地客户</option>
                                        </select>
                                    </div>
                                    <div class="col-sm-3 errorText"></div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"> 备注：</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" placeholder="" name="remark">
                                    </div>
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

            <!-- 新增Modal -->
            <div class="modal fade" id="updateLoanModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <h4 class="modal-title">更新客户信息</h4>
                        </div>
                        <div class="modal-body">
                            <form class="form-horizontal" id="updateLoanForm">
                                <input type="hidden" name="id"/>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span class="red">*</span> 客户姓名：</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" placeholder="" name="name"
                                               data-rule-required="true" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"> 贷款额度：</label>
                                    <div class="col-sm-6">
                                        <input type="number" class="form-control" placeholder="" name="quota">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">所在地：</label>
                                    <div class="col-sm-6">
                                        <select name="city" class="form-control status">
                                            <option value="">请选择跟进结果</option>
                                            <option value="guangzhou">广州</option>
                                            <option value="shenzhen">深圳</option>
                                            <option value="other">其他城市</option>
                                        </select>
                                    </div>
                                    <div class="col-sm-3 errorText"></div>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <button type="button" class="btn btn-primary" onclick="updateLoan()">确定</button>
                        </div>
                    </div>
                </div>
            </div>

        <#include "/common/footer.ftl">

            <script type="text/javascript">
                $(function () {
                    $("#userId").selection("${ctx!''}/user/user-select",${vo.userId!'0'});
                });

                function createData(dataType) {
                    window.location.href = '${ctx!''}/loan/add-loan?source=${uniqueIdentify!''}';
                }

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

                function robbing(dataId) {
                    $.get(ctx + "/loan/loan-robbing",
                            {id: dataId},
                            function (data) {
                                location.reload();
                                alert(data.message);
                            }, "json");
                }

                function setUser() {
                    if (!$("#setUserForm").valid()) {
                        return;
                    }
                    $.post(ctx + "/loan/loan-user",
                            $("#setUserForm").serialize(),
                            function (data) {
                                if (data.success) {
                                    location.reload();
                                } else {
                                    notice(data.message, "red");
                                }
                            }, "json");
                }

                function updateStatus() {
                    if (!$("#updateStatusForm").valid()) {
                        return;
                    }
                    $.post(ctx + "/loan/loan-status",
                            $("#updateStatusForm").serialize(),
                            function (data) {
                                if (data.success) {
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
                                if (data.success) {
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
                                if (data.success) {
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
                                if (data.success) {
                                    location.reload();
                                } else {
                                    notice(data.message, "red");
                                }
                            }, "json");
                }

            </script>
</body>
</html>