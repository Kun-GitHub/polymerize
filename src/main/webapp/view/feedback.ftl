<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>${appName!''}</title>
    <#include "/common/header.ftl">
    <script type="text/javascript" src="${ctx!''}/resources/js/my97/WdatePicker.js"></script>
    <script type="text/javascript"  src="${ctx!''}/resources/js/my97/skin/WdatePicker.css"></script>
    <style type="text/css">
        .jBox-Modal .jBox-content{overflow: hidden!important;margin: 0!important;padding: 0!important;}
        .div-line{margin-bottom: 8px; border: solid 1px #ccc;min-height: 40px; line-height: 40px;}
        .border-right{ border-right: solid 1px #ccc; }
        .page-header{border-bottom: 1px solid #ddd;font-weight: bold;}
        select{margin-top: 3px;}
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
                <p class="page-header">Student’s info</p>
                <div class="col-sm-12 col-xs-12 div-line">
                    <div class="col-sm-4 col-xs-4 border-right">
                        <span class="red">*</span>Student’s name：<input class="form-control" type="text" name="studentsName" value="${info.studentsName!''}" style="display: inline-block; width: auto;"/>
                    </div>
                    <div class="col-sm-4 col-xs-4 border-right">
                        <span class="red">*</span>Student number：<input class="form-control" type="text" name="studentNumber" value="${info.studentNumber!''}" style="display: inline-block; width: auto;"/>
                    </div>
                    <div class="col-sm-4 col-xs-4 border-right">
                        <span class="red">*</span>Class Schedule：<input class="form-control" type="text" name="classSchedule" value="${info.classSchedule!''}" style="display: inline-block; width: auto;"/>
                    </div>
                </div>
                <div class="col-sm-12 col-xs-12 div-line">
                    <div class="col-sm-4 col-xs-4">
                        <span class="red">*</span>Date：<input class="form-control time_input" value="${(info.feedbackDate?datetime)!''}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" name="feedbackDate"  style="display: inline-block; width: auto;"/>
                    </div>
                </div>
                <p class="page-header">Feedback</p>
                <div class="col-sm-12 col-xs-12 div-line">
                    <div class="col-sm-4 col-xs-4 border-right">
                        <span class="red">*</span>Interaction：
                        <select name="interaction" class="form-control" style="display: inline-block; width: auto;">
                            <option value="4" <#if info.interaction??&&info.interaction==4>selected</#if>>Excellent/非常棒</option>
                            <option value="3" <#if info.interaction??&&info.interaction==3>selected</#if>>Very Good/很好</option>
                            <option value="2" <#if info.interaction??&&info.interaction==2>selected</#if>>Good/好</option>
                            <option value="1" <#if info.interaction??&&info.interaction==1>selected</#if>>Need Improvement/需要提高</option>
                        </select>
                    </div>
                    <div class="col-sm-4 col-xs-4 border-right">
                        <span class="red">*</span>Fluency：
                        <select name="fluency" class="form-control" style="display: inline-block; width: auto;">
                            <option value="4" <#if info.fluency??&&info.fluency==4>selected</#if>>Excellent/非常棒</option>
                            <option value="3" <#if info.fluency??&&info.fluency==3>selected</#if>>Very Good/很好</option>
                            <option value="2" <#if info.fluency??&&info.fluency==2>selected</#if>>Good/好</option>
                            <option value="1" <#if info.fluency??&&info.fluency==1>selected</#if>>Need Improvement/需要提高</option>
                        </select>
                    </div>
                </div>
                <div class="col-sm-12 col-xs-12 div-line" style="min-height: 80px;">
                    <div style="margin-left: 15px;float: left;width: 100px;">Correction：</div>
                    <div style="float: left;width: auto;margin-top: 10px;">
                         <textarea class="form-control" style="display: inline-block; width: 600px;height: 70px;"name="correction">${info.correction!''}</textarea>
                    </div>
                </div>
                <div class="col-sm-12 col-xs-12 div-line" style="min-height: 80px;">
                    <div style="margin-left: 15px;float: left;width: 100px;">Please Review：</div>
                    <div style="float: left;width: auto;margin-top: 10px;">
                         <textarea class="form-control" style="display: inline-block; width: 600px;height: 70px;" name="pleaseReview">${info.pleaseReview!''}</textarea>
                    </div>
                </div>
                <div class="col-sm-12 col-xs-12 div-line" style="min-height: 80px;">
                    <div style="margin-left: 15px;float: left;width: 100px;">Vocabulary Pronunciation：</div>
                    <div style="float: left;width: auto;margin-top: 10px;">
                         <textarea class="form-control" style="display: inline-block; width: 600px;height: 70px;" name="vocabularyPronunciation">${info.vocabularyPronunciation!''}</textarea>
                    </div>
                </div>
                <div class="col-sm-12 col-xs-12 div-line" style="min-height: 80px;">
                    <div style="margin-left: 15px;float: left;width: 100px;">Comments：</div>
                    <div style="float: left;width: auto;margin-top: 10px;">
                         <textarea class="form-control" style="display: inline-block; width: 600px;height: 70px;" name="comments">${info.comments!''}</textarea>
                    </div>
                </div>
                <div class="col-sm-12 col-xs-12 div-line" style="min-height: 80px;">
                    <div style="margin-left: 15px;float: left;width: 100px;">Today’s Homework：</div>
                    <div style="float: left;width: auto;margin-top: 10px;">
                         <textarea class="form-control" style="display: inline-block; width: 600px;height: 70px;" name="todaysHomework">${info.todaysHomework!''}</textarea>
                    </div>
                </div>
                </form>

                <div class="col-sm-12 col-xs-12" style="text-align: center;margin: 20px auto;">
                    <a href="javascript:;" class="btn btn-default" style="width: 80px;" onclick="javascript:history.go(-1);">返回</a>
                    <a class="btn btn-primary" style="width: 80px;" onclick="save()">保存</a>
                </div>
        </div>
    </div>
</div>
</div><!-- /.row -->
<script src="${ctx!''}/resources/js/jquery-1.9.1.min.js"></script>
<script src="${ctx!''}/resources/js/jquery.min.js"></script>

<script type="text/javascript">

    function save() {

        $.post("/feedback-save",
                $("#addForm").serialize(),
                function (data) {
                    if (data.resultCode == 0) {
                        window.location.href = '${ctx!''}/index';
                    } else {
                        notice(data.message, "red");
                    }
                }, "json");
    }

</script>

</body>
</html>