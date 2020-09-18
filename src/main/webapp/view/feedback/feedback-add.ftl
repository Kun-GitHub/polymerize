<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>课程登记系统 - 新增Feedback</title>
    <#include "/common/header.ftl">
    <script type="text/javascript" src="${ctx!''}/resources/js/my97/WdatePicker.js"></script>
    <script type="text/javascript" src="${ctx!''}/resources/js/my97/skin/WdatePicker.css"></script>
    <style type="text/css">
        .jBox-Modal .jBox-content {
            overflow: hidden !important;
            margin: 0 !important;
            padding: 0 !important;
        }

        .div-line {
            margin-bottom: 8px;
            border: solid 1px #ccc;
            min-height: 40px;
            line-height: 40px;
        }

        .border-right {
            border-right: solid 1px #ccc;
        }

        .page-header {
            border-bottom: 1px solid #ddd;
            font-weight: bold;
        }

        select {
            margin-top: 3px;
        }
    </style>
</head>
<body>
<#include "/common/topnav.ftl">
<div class="container-fluid">
    <div class="row">

        <div class="col-sm-12 col-xs-12 main">
            <span class="tab-pane active">
                <p style="height: 45px;line-height: 45px;text-align: center;">
                    <span style="font-weight: bold;font-size: 16px;">Feedback</span>
                </p>

                <form class="form-horizontal" id="addForm">
                <p class="page-header">Feedback’s info</p>
                <div class="col-sm-12 col-xs-12 div-line">
                    <div class="col-sm-4 col-xs-4">
                        学员：
                        <select name="studentId" id="studentId" class="form-control"
                                style="display: inline-block; width: auto;">
                            <option value="0">请选择学员...</option>
                        </select>
                    </div>
                </div>
                <div class="col-sm-12 col-xs-12 div-line" style="min-height: 80px;">
                    <div style="margin-left: 15px;float: left;width: 150px;">Learning Content <br/>学习内容：</div>
                    <div style="float: left;width: auto;margin-top: 10px;">
                         <textarea class="form-control" style="display: inline-block; width: 600px;height: 70px;"
                                   name="learningContent">${info.learningContent!''}</textarea>
                    </div>
                </div>
                    <p class="page-header">Learning Achievement 学习情况</p>
                    <div class="col-sm-12 col-xs-12 div-line">
                    <div class="col-sm-4 col-xs-4 border-right">
                        掌握程度：
                        <select name="learningAchievement" class="form-control"
                                style="display: inline-block; width: auto;">
                            <option value="10">10%</option>
                            <option value="20">20%</option>
                            <option value="30">30%</option>
                            <option value="40">40%</option>
                            <option value="50">50%</option>
                            <option value="60">60%</option>
                            <option value="70">70%</option>
                            <option value="80">80%</option>
                            <option value="90">90%</option>
                            <option value="100">100%</option>
                        </select>
                    </div>
                    </div>
                <div class="col-sm-12 col-xs-12 div-line">
                    <div class="col-sm-4 col-xs-4 border-right">
                        Clear & precise language using 语言准确度：
                        <select name="clear" class="form-control"
                                style="display: inline-block; width: auto;">
                            <option value="0">需要提高</option>
                            <option value="1">好</option>
                            <option value="2">很好</option>
                            <option value="3">非常棒</option>
                        </select>
                    </div>
                    <div class="col-sm-4 col-xs-4 border-right">
                        Fluency 英文流利度：
                        <select name="fluency" class="form-control"
                                style="display: inline-block; width: auto;">
                            <option value="0">需要提高</option>
                            <option value="1">好</option>
                            <option value="2">很好</option>
                            <option value="3">非常棒</option>
                        </select>
                    </div>
                    <div class="col-sm-4 col-xs-4">
                        Creativity 创造性：
                        <select name="creativity" class="form-control"
                                style="display: inline-block; width: auto;">
                            <option value="0">需要提高</option>
                            <option value="1">好</option>
                            <option value="2">很好</option>
                            <option value="3">非常棒</option>
                        </select>
                    </div>
                </div>
                <div class="col-sm-12 col-xs-12 div-line">
                    <div class="col-sm-4 col-xs-4 border-right">
                        Logical thinking 逻辑思维：
                        <select name="logicalThinking" class="form-control"
                                style="display: inline-block; width: auto;">
                            <option value="0">需要提高</option>
                            <option value="1">好</option>
                            <option value="2">很好</option>
                            <option value="3">非常棒</option>
                        </select>
                    </div>
                    <div class="col-sm-4 col-xs-4 border-right">
                        Homework compleaon 完成作业：
                        <select name="homeworkCompleaon" class="form-control"
                                style="display: inline-block; width: auto;">
                            <option value="0">需要提高</option>
                            <option value="1">好</option>
                            <option value="2">很好</option>
                            <option value="3">非常棒</option>
                        </select>
                    </div>
                </div>
                    <p class="page-header">个人表现</p>
                    <div class="col-sm-12 col-xs-12 div-line">
                    <div class="col-sm-4 col-xs-4 border-right">
                        Concentration 听讲专注表现：
                        <select name="concentration" class="form-control"
                                style="display: inline-block; width: auto;">
                            <option value="0">有待提高</option>
                            <option value="1">平均水平</option>
                            <option value="2">表现优异</option>
                        </select>
                    </div>
                    <div class="col-sm-4 col-xs-4 border-right">
                        Expression 英语表达表现：
                        <select name="expression" class="form-control"
                                style="display: inline-block; width: auto;">
                            <option value="0">有待提高</option>
                            <option value="1">平均水平</option>
                            <option value="2">表现优异</option>
                        </select>
                    </div>
                    <div class="col-sm-4 col-xs-4">
                        Reaction 反应能力表现：
                        <select name="reaction" class="form-control"
                                style="display: inline-block; width: auto;">
                            <option value="0">有待提高</option>
                            <option value="1">平均水平</option>
                            <option value="2">表现优异</option>
                        </select>
                    </div>
                </div>
                    <div class="col-sm-12 col-xs-12 div-line">
                    <div class="col-sm-4 col-xs-4 border-right">
                        Interaction 师生互动表现：
                        <select name="interaction" class="form-control"
                                style="display: inline-block; width: auto;">
                            <option value="0">有待提高</option>
                            <option value="1">平均水平</option>
                            <option value="2">表现优异</option>
                        </select>
                    </div>
                </div>
                    <p class="page-header">团队合作</p>
                    <div class="col-sm-12 col-xs-12 div-line">
                    <div class="col-sm-4 col-xs-4 border-right">
                        Rule consciousness 规则意识：
                        <select name="ruleConsciousness" class="form-control"
                                style="display: inline-block; width: auto;">
                            <option value="0">有待提高</option>
                            <option value="1">平均水平</option>
                            <option value="2">表现优异</option>
                        </select>
                    </div>
                    <div class="col-sm-4 col-xs-4 border-right">
                        Concern for others 关心他人：
                        <select name="concernForOthers" class="form-control"
                                style="display: inline-block; width: auto;">
                            <option value="0">有待提高</option>
                            <option value="1">平均水平</option>
                            <option value="2">表现优异</option>
                        </select>
                    </div>
                    <div class="col-sm-4 col-xs-4">
                        Leader ship 领导力：
                        <select name="leaderShip" class="form-control"
                                style="display: inline-block; width: auto;">
                            <option value="0">有待提高</option>
                            <option value="1">平均水平</option>
                            <option value="2">表现优异</option>
                        </select>
                    </div>
                </div>
                    <div class="col-sm-12 col-xs-12 div-line">
                    <div class="col-sm-4 col-xs-4 border-right">
                        Corporation 协作意识：
                        <select name="corporation" class="form-control"
                                style="display: inline-block; width: auto;">
                            <option value="0">有待提高</option>
                            <option value="1">平均水平</option>
                            <option value="2">表现优异</option>
                        </select>
                    </div>
                </div>
                    <div class="col-sm-12 col-xs-12 div-line" style="min-height: 80px;">
                    <div style="margin-left: 15px;float: left;width: 150px;">Comments On Student <br/>教师评语：</div>
                    <div style="float: left;width: auto;margin-top: 10px;">
                         <textarea class="form-control" style="display: inline-block; width: 600px;height: 70px;"
                                   name="commentsOnStudent">${info.commentsOnStudent!''}</textarea>
                    </div>
                </div>
                    <div class="col-sm-12 col-xs-12 div-line" style="min-height: 80px;">
                    <div style="margin-left: 15px;float: left;width: 150px;">Today’s Achievement <br/>今日作业：</div>
                    <div style="float: left;width: auto;margin-top: 10px;">
                         <textarea class="form-control" style="display: inline-block; width: 600px;height: 70px;"
                                   name="todayAchievement">${info.todayAchievement!''}</textarea>
                    </div>
                </div>
                </form>

                <div class="col-sm-12 col-xs-12" style="text-align: center;margin: 20px auto;">
                    <a href="javascript:;" class="btn btn-default" style="width: 80px;"
                       onclick="javascript:history.go(-1);">返回</a>
                    <a class="btn btn-primary" style="width: 80px;" onclick="save()">保存</a>
                </div>
        </div>
    </div>
</div>
</div><!-- /.row -->
<script src="${ctx!''}/resources/js/jquery-1.9.1.min.js"></script>
<script src="${ctx!''}/resources/js/jquery.min.js"></script>
<script type="text/javascript">
    $(function () {
        $.ajax({
            type: "GET",
            url: "/student/student-select",
            async: true,
            data: {},
            dataType: "json",
            success: function (data) {
                try {
                    var options = '';
                    for (var i = 0; i < data.length; i++) {
                        options += '<option value="' + data[i].id + '" >' + data[i].text + '</option>';
                    }
                    $("#studentId").append(options);
                } catch (e) {
                    alert("下拉框ajax方法出错：" + e);
                }
            }
        });
    });


    function save() {
        $.post("/feedback/feedback-save",
                $("#addForm").serialize(),
                function (data) {
                    if (data.success) {
                        window.location.href = '${ctx!''}/feedback/feedback-list';
                    } else {
                        notice(data.message, "red");
                    }
                }, "json");
    }

</script>

</body>
</html>