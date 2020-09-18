<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>${appName!''}</title>
    <#include "/common/header.ftl">
    <script type="text/javascript" src="${ctx!''}/resources/js/my97/WdatePicker.js"></script>
    <script type="text/javascript" src="${ctx!''}/resources/js/my97/skin/WdatePicker.css"></script>
    <#include "/common/topnav.ftl">
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar" role="tablist">
                <li role="presentation" class="active">
                    <a href="${ctx!''}/index">贷款人信息</a>
                </li>
                <li role="presentation">
                    <a href="${ctx!''}/sms/sms-list">短信发送记录</a>
                </li>
            </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main tab-content">
            <div class="tab-pane active" role="tabpanel" id="usertab">
                <div class="row placeholders">
                    <div class="col-xs-3 col-sm-1 placeholder">
                        <div class="thumbnail tile tile-medium tile-blue">
                            <a href="#" class="fa-links" onclick="createData()">
                                <i class="icon-plus-sign-alt icon-2x"></i>
                                <h5>新建</h5>
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

                        <input class="form-control" type="text" name="name" value="${vo.name!''}" placeholder="姓名" style="display: inline-block; width: auto;"/>
                        <select class="form-control" name="ammeterType" style="display: inline-block; width: auto;">
                            <option value="">请选择性别</option>
                            <option value="man">先生</option>
                            <option value="woman">女士</option>
                        </select>
                        <input class="form-control" type="text" name="phone" value="${vo.phone!''}" placeholder="联系方式" style="display: inline-block; width: auto;"/>
                        <select class="form-control" name="ammeterType" style="display: inline-block; width: auto;">
                            <option value="">请选择状态</option>
                            <option value="0">待联系</option>
                            <option value="1">无效信息</option>
                            <option value="2">无需贷款</option>
                            <option value="3">已放款</option>
                        </select>
                        <input type="text" name="loanTime1" value="${vo.loanTime1!''}" placeholder="开始申请时间  " class="form-control time_input" style="display: inline-block; width: auto;"
                               onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" >~
                        <input type="text" name="loanTime2" value="${vo.loanTime2!''}" placeholder="结束申请时间  " class="form-control time_input" style="display: inline-block; width: auto;"
                               onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" >
                        <br/><br/>
                        <button type="submit" class="btn btn-primary">查询</button>
                        <button type="button" class="btn btn-default" onclick="resetForm('searchForm')"> 重置</button>
                    </h4>
                </form>
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th class="th-checkbox"><input type="checkbox" onclick="selectAll('itemCb')"></th>
                            <th data-sort="field:'name'">姓名</th>
                            <th data-sort="field:'sex'">性别</th>
                            <th data-sort="field:'phone'">联系方式</th>
                            <th data-sort="field:'city'">居住地</th>
                            <th data-sort="field:'quota'">贷款额度</th>
                            <th data-sort="field:'status'">状态</th>
                            <th data-sort="field:'loanTime'">申请时间</th>
                            <th data-sort="field:'operator'">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list page.list as item>
                        <tr>
                            <td><input type="checkbox" name="itemCb" value=${item.id!''}></td>
                            <td>${item.name!''}</td>
                            <td><#if item.sex??&&item.sex=='woman'>女<#else>男</#if></td>
                            <td>${item.phone!''}</td>
                            <td><#if item.city??&&item.city=='guangzhou'>广州<#elseif item.city??&&item.city=='shenzhen'>
                                深圳<#else>其他</#if></td>
                            <td>${item.quota!''}/万</td>
                            <td><#if item.status??&&item.status==0><span class="label label-warning">待联系</span>
                            <#elseif item.status??&&item.status==1><span class="label label-default">无效信息</span>
                            <#elseif item.status??&&item.status==2><span class="label label-default">无需贷款</span>
                            <#elseif item.status??&&item.status==3><span class="label label-success">已放款</span>
                            <#else><span class="label label-default">待确认</span></#if></td>
                            <td>${(item.loanTime?datetime)!''}</td>
                            <td>
                                <#if item.status??&&item.status==0>
                                    <a href="#" <#--onclick="details(${item.id!''})"-->>修改状态</a>
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
        </div>
    </div>
</div>

<!-- 修改Modal -->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">修改状态</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="editForm">
                    <input type="hidden" name="id"/>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="update()">保存</button>
            </div>
        </div>
    </div>
</div>

<script src="${ctx!''}/resources/js/jquery-1.9.1.min.js"></script>
<script src="${ctx!''}/resources/js/jquery.min.js"></script>
<script type="text/javascript">

    function createData(dataType) {
        window.location.href = '${ctx!''}/add-loan';
    }

    function delRows() {
        var ids = getIds("itemCb");
        if (!ids) {
            notice("请至少选择一条记录删除", "red");
            return;
        }
        $.post("${ctx!''}/loan-delete",
                {ids: ids},
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