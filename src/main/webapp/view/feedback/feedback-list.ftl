<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>课程登记系统 - Feedback列表</title>
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
                <li role="presentation">
                    <a href="${ctx!''}/student/student-list">学员信息
                    </a>
                </li>
                <li role="presentation" class="active">
                    <a href="${ctx!''}/feedback/feedback-list">Feedback</a>
                </li>
            </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main tab-content">

            <div class="tab-pane active" role="tabpanel" id="roletab">
                <div class="row placeholders">
                    <div class="col-xs-3 col-sm-1 placeholder">
                        <div class="thumbnail tile tile-medium tile-blue">
                            <a href="/feedback/feedback-add" class="fa-links">
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
                        <input class="form-control" type="text" name="name" value="${vo.name!''}" placeholder="姓名"
                               style="display: inline-block; width: auto;"/>
                        <button type="submit" class="btn btn-primary">查询</button>
                        <button type="button" class="btn btn-default" onclick="resetForm('searchForm')"> 重置</button>
                    </h4>
                </form>
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th class="th-checkbox"><input type="checkbox" onclick="selectAll('itemCb')"></th>
                            <th>姓名</th>
                            <th>学习内容</th>
                            <th>掌握程度</th>
                            <th>语言准确度</th>
                            <th>英文流利度</th>
                            <th>创造性</th>
                            <th>逻辑思维</th>
                            <th>完成作业</th>
                            <th>听讲专注</th>
                            <th>英语表达</th>
                            <th>反应能力</th>
                            <th>师生互动</th>
                            <th>规则意识</th>
                            <th>关心他人</th>
                            <th>领导力</th>
                            <th>协作意识</th>
                            <th>教师评语</th>
                            <th>今日作业</th>
                            <th>创建时间</th>
                            <th>链接</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list page.list as item>
                        <tr>
                            <td><input type="checkbox" name="itemCb" value=${item.id!''}></td>
                            <td>${item.name!''}</td>
                            <td>${item.learningContent!''}</td>
                            <td>${item.learningAchievement!''}%</td>
                            <td><#if item.clear??&&item.clear==3>非常棒<#elseif item.clear??&&item.clear==2>很好
                            <#elseif item.clear??&&item.clear==1>好<#else>需要提高</#if></td>
                            <td><#if item.fluency??&&item.fluency==3>非常棒<#elseif item.fluency??&&item.fluency==2>很好
                            <#elseif item.fluency??&&item.fluency==1>好<#else>需要提高</#if></td>
                            <td><#if item.creativity??&&item.creativity==3>非常棒<#elseif item.creativity??&&item.creativity==2>很好
                            <#elseif item.creativity??&&item.creativity==1>好<#else>需要提高</#if></td>
                            <td><#if item.logicalThinking??&&item.logicalThinking==3>非常棒<#elseif item.logicalThinking??&&item.logicalThinking==2>很好
                            <#elseif item.logicalThinking??&&item.logicalThinking==1>好<#else>需要提高</#if></td>
                            <td><#if item.homeworkCompleaon??&&item.homeworkCompleaon==3>非常棒<#elseif item.homeworkCompleaon??&&item.homeworkCompleaon==2>很好
                            <#elseif item.homeworkCompleaon??&&item.homeworkCompleaon==1>好<#else>需要提高</#if></td>
                            <td><#if item.concentration??&&item.concentration==2>表现优异
                            <#elseif item.concentration??&&item.concentration==1>平均水平<#else>有待提高</#if></td>
                            <td><#if item.expression??&&item.expression==2>表现优异
                            <#elseif item.expression??&&item.expression==1>平均水平<#else>有待提高</#if></td>
                            <td><#if item.reaction??&&item.reaction==2>表现优异
                            <#elseif item.reaction??&&item.reaction==1>平均水平<#else>有待提高</#if></td>
                            <td><#if item.interaction??&&item.interaction==2>表现优异
                            <#elseif item.interaction??&&item.interaction==1>平均水平<#else>有待提高</#if></td>
                            <td><#if item.ruleConsciousness??&&item.ruleConsciousness==2>表现优异
                            <#elseif item.ruleConsciousness??&&item.ruleConsciousness==1>平均水平<#else>有待提高</#if></td>
                            <td><#if item.concernForOthers??&&item.concernForOthers==2>表现优异
                            <#elseif item.concernForOthers??&&item.concernForOthers==1>平均水平<#else>有待提高</#if></td>
                            <td><#if item.leaderShip??&&item.leaderShip==2>表现优异
                            <#elseif item.leaderShip??&&item.leaderShip==1>平均水平<#else>有待提高</#if></td>
                            <td><#if item.corporation??&&item.corporation==2>表现优异
                            <#elseif item.corporation??&&item.corporation==1>平均水平<#else>有待提高</#if></td>
                            <td>${item.commentsOnStudent!''}</td>
                            <td>${item.todayAchievement!''}</td>
                            <td>${(item.createTime?datetime)!''}</td>
                            <td>
                                <a href="/feedback/feedback-show?id=${item.id!''}">查看</a>&nbsp;&nbsp;
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            <#include "/common/pager.ftl">
            <@pager pager=page baseUrl=uri parameter=parameter />
            </div>

        <#include "/common/footer.ftl">

            <script type="text/javascript">
                function delRows() {
                    var ids = getIds("itemCb");
                    if (!ids) {
                        notice("请至少选择一条记录删除", "red");
                        return;
                    }
                    $.post(ctx + "/feedback/feedback-delete",
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