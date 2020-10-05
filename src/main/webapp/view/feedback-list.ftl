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
                    <a href="${ctx!''}/index">Feedback</a>
                </li>
                <li role="presentation">
                    <a href="${ctx!''}/topic-list">Topic</a>
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
                </form>
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th class="th-checkbox"><input type="checkbox" onclick="selectAll('itemCb')"></th>
                            <th data-sort="field:'studentsName'">学生</th>
                            <th data-sort="field:'classSchedule'">课程</th>
                            <th data-sort="field:'feedbackDate'">时间</th>
                            <th data-sort="field:'interaction'">课堂互动</th>
                            <th data-sort="field:'fluency'">英文流利度</th>
                            <th data-sort="field:'correction'">问题更正</th>
                            <th data-sort="field:'pleaseReview'">复习单元</th>
                            <th data-sort="field:'vocabularyPronunciation'">单词及发音问题</th>
                            <th data-sort="field:'comments'">评价</th>
                            <th data-sort="field:'todaysHomework'">今日作业</th>
                            <th data-sort="field:'id'">手机地址</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list page.list as item>
                        <tr>
                            <td><input type="checkbox" name="itemCb" value=${item.id!''}></td>
                            <td>${item.studentsName!''}</td>
                            <td>${item.classSchedule!''}</td>
                            <td>${(item.feedbackDate?datetime)!''}</td>
                            <td><#if item.interaction??&&item.interaction==4>非常棒
                            <#elseif item.interaction??&&item.interaction==3>很好
                            <#elseif item.interaction??&&item.interaction==2>好<#else>需要提高</#if></td>
                            <td><#if item.fluency??&&item.fluency==4>非常棒
                            <#elseif item.fluency??&&item.fluency==3>很好
                            <#elseif item.fluency??&&item.fluency==2>好<#else>需要提高</#if></td>
                            <td>${item.correction!''}</td>
                            <td>${item.pleaseReview!''}</td>
                            <td>${item.vocabularyPronunciation!''}</td>
                            <td>${item.comments!''}</td>
                            <td>${item.todaysHomework!''}</td>
                            <td><a href="http://localhost:8083/show-feedback?id=${item.id!''}">http://localhost:8083/show-feedback?id=${item.id!''}</a></td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            <#include "/common/pager.ftl">
            <@pager pager=page baseUrl=uri parameter=parameter />
            </div>
            <script src="${ctx!''}/resources/js/jquery-1.9.1.min.js"></script>
            <script src="${ctx!''}/resources/js/jquery.min.js"></script>
            <script type="text/javascript">

                function createData(dataType){
                    window.location.href = '${ctx!''}/feedback-new';
                }

                function delRows() {
                    var ids = getIds("itemCb");
                    if (!ids) {
                        notice("请至少选择一条记录删除", "red");
                        return;
                    }
                    $.post("/feedback-delete",
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