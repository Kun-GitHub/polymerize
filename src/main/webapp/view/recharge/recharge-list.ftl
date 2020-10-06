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
                <li role="presentation">
                    <a href="${ctx!''}/loan/loan-list">订单管理</a>
                </li>
                <li role="presentation" class="active">
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
                            <a href="#" class="fa-links" data-toggle="modal" data-target="#addRechargeInfo">
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
                    </div>-->
<#--                    </#if>-->
                </div>
                <form method="get" id="searchForm">
                    <h4 class="sub-header" style="clear:both;">
                        <input class="form-control" type="text" name="account" value="${vo.account!''}" placeholder="商户账号" style="display: inline-block; width: auto;"/>
                        <input type="text" name="rechargeTime1" value="${vo.rechargeTime1!''}" placeholder="开始充值时间  " class="form-control time_input" style="display: inline-block; width: auto;"
                               onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" >~
                        <input type="text" name="rechargeTime2" value="${vo.rechargeTime2!''}" placeholder="结束充值时间  " class="form-control time_input" style="display: inline-block; width: auto;"
                               onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" ><br/><br/>
                        <button type="submit" class="btn btn-primary">查询</button>
                        <button type="button" class="btn btn-default" onclick="resetForm('searchForm')"> 重置</button>
                    </h4>
                </form>
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th data-sort="field:'account'">商户账号</th>
                            <th data-sort="field:'price'">充值金额</th>
                            <th data-sort="field:'rechargeTime'">充值时间</th>
                            <th data-sort="field:'operator'">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list page.list as item>
                        <tr>
                            <td>${item.account!''}</td>
                            <td>${item.price!''}</td>
                            <td>${(item.rechargeTime?date)!''}</td>
                            <td><a href="#"
                                   onclick="robbing(${item.id!''})">明细</a></td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            <#include "/common/pager.ftl">
            <@pager pager=page baseUrl=uri parameter=parameter />
            </div>
        <#include "/common/footer.ftl">
</body>
</html>