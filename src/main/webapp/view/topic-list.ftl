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
                    <a href="${ctx!''}/index">Feedback</a>
                </li>
                <li role="presentation" class="active">
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
                            <th data-sort="field:'topic'">Topic</th>
                            <th data-sort="field:'assignment'">Assignment</th>
                            <th data-sort="field:'prepare'">Prepare</th>
                            <th data-sort="field:'preview'">Preview</th>
                            <th data-sort="field:'presentRelevantTopic'">Relevant to the topic</th>
                            <th data-sort="field:'presentLogisticThinking'">In the logistic way of thinking</th>
                            <th data-sort="field:'presentLanguageUsing'">Clear and precise language using</th>
                            <th data-sort="field:'practiceGroupWork'">Practice-Group Work</th>
                            <th data-sort="field:'practiceIndividualWork'">Practice-Individual Work</th>
                            <th data-sort="field:'produceGroupWork'">Produce-Group Work</th>
                            <th data-sort="field:'produceIndividualWork'">Produce-Individual Work</th>
                            <th data-sort="field:'demonstration'">Demonstration</th>
                            <th data-sort="field:'saGroupEvaluation'">Student Assessment-Group Evaluation</th>
                            <th data-sort="field:'saIndividualEvaluation'">Student Assessment-Individual Evaluation</th>
                            <th data-sort="field:'taGroupEvaluation'">Teacher Assessment-Group Evaluation</th>
                            <th data-sort="field:'taIndividualEvaluation'">Teacher Assessment-Individual Evaluation</th>
                            <th data-sort="field:'id'">手机地址</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list page.list as item>
                        <tr>
                            <td><input type="checkbox" name="itemCb" value=${item.id!''}></td>
                            <td>Topic${item.nums!''}：${item.topic!''}</td>
                            <td>${item.assignment!''}</td>
                            <td>${item.prepare!''}</td>
                            <td>${item.preview!''}</td>
                            <td>${item.presentRelevantTopic!''}星</td>
                            <td>${item.presentLogisticThinking!''}星</td>
                            <td>${item.presentLanguageUsing!''}星</td>
                            <td>${item.practiceGroupWork!''}星</td>
                            <td>${item.practiceIndividualWork!''}星</td>
                            <td>${item.produceGroupWork!''}星</td>
                            <td>${item.produceIndividualWork!''}星</td>
                            <td>${item.demonstration!''}</td>
                            <td>${item.saGroupEvaluation!''}</td>
                            <td>${item.saIndividualEvaluation!''}</td>
                            <td>${item.taGroupEvaluation!''}</td>
                            <td>${item.taIndividualEvaluation!''}</td>
                            <td><a href="http://localhost:8084/show-topic?id=${item.id!''}">http://localhost:8083/show-topic?id=${item.id!''}</a></td>
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
                    window.location.href = '${ctx!''}/topic-new';
                }

                function delRows() {
                    var ids = getIds("itemCb");
                    if (!ids) {
                        notice("请至少选择一条记录删除", "red");
                        return;
                    }
                    $.post("/topic-delete",
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