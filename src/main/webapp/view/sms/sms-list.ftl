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
                <li role="presentation">
                    <a href="${ctx!''}/user/user-list">员工信息</a>
                </li>
                <li role="presentation">
                    <a href="${ctx!''}/loan/loan-list">贷款人信息</a>
                </li>
                <#if type??&&type=='admin'>
                    <li role="presentation" class="active">
                        <a href="${ctx!''}/sms/sms-list">短信发送记录</a>
                    </li>
                </#if>
            </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main tab-content">
            <div class="tab-pane active" role="tabpanel" id="usertab">
                <div class="row placeholders">
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

                        <input class="form-control" type="text" name="phone" value="${vo.phone!''}" placeholder="手机号码" style="display: inline-block; width: auto;"/>
                        <input type="text" name="lastTime1" value="${vo.lastTime1!''}" placeholder="开始发送时间  " class="form-control time_input" style="display: inline-block; width: auto;"
                               onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" >~
                        <input type="text" name="lastTime2" value="${vo.lastTime2!''}" placeholder="结束发送时间  " class="form-control time_input" style="display: inline-block; width: auto;"
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
                            <th data-sort="field:'phone'">手机号码</th>
                            <th data-sort="field:'code'">验证码</th>
                            <th data-sort="field:'sms'">短信内容</th>
                            <th data-sort="field:'nums'">返回编码</th>
                            <th data-sort="field:'source'">来源</th>
                            <th data-sort="field:'lastTime'">发送时间</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list page.list as item>
                        <tr>
                            <td><input type="checkbox" name="itemCb" value=${item.id!''}></td>
                            <td>${item.phone!''}</td>
                            <td>${item.code!''}</td>
                            <td>${item.sms!''}</td>
                            <td>${item.nums!''}</td>
                            <td>${item.source!''}</td>
                            <td>${(item.lastTime?datetime)!''}</td>
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


<script src="${ctx!''}/resources/js/jquery-1.9.1.min.js"></script>
<script src="${ctx!''}/resources/js/jquery.min.js"></script>
<script type="text/javascript">

    function delRows() {
        var ids = getIds("itemCb");
        if (!ids) {
            notice("请至少选择一条记录删除", "red");
            return;
        }
        $.post("${ctx!''}/sms/sms-delete",
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