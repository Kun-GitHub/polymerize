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
                    <span style="font-weight: bold;font-size: 16px;">Topic</span>
                </p>

                <form class="form-horizontal" id="addForm">
                <p class="page-header">Topic’s info</p>
                <div class="col-sm-12 col-xs-12 div-line">
                    <div class="col-sm-4 col-xs-4 border-right">
                        <span class="red">*</span>Topic number：<input class="form-control" type="text" name="nums" value="${info.nums!''}" style="display: inline-block; width: auto;"/>
                    </div>
                    <div class="col-sm-4 col-xs-4 border-right">
                        <span class="red">*</span>Topic’s name：<input class="form-control" type="text" name="topic" value="${info.topic!''}" style="display: inline-block; width: auto;"/>
                    </div>
                    <div class="col-sm-4 col-xs-4">
                        Feedback：
                        <select name="feedbackId" id="feedbackId" class="form-control" style="display: inline-block; width: auto;">
                        </select>
                    </div>
                </div>
                <div class="col-sm-12 col-xs-12 div-line" style="min-height: 80px;">
                    <div style="margin-left: 15px;float: left;width: 100px;">Assignment：</div>
                    <div style="float: left;width: auto;margin-top: 10px;">
                         <textarea class="form-control" style="display: inline-block; width: 600px;height: 70px;" name="assignment">${info.assignment!''}</textarea>
                    </div>
                </div>
                <div class="col-sm-12 col-xs-12 div-line" style="min-height: 80px;">
                    <div style="margin-left: 15px;float: left;width: 100px;">Prepare：</div>
                    <div style="float: left;width: auto;margin-top: 10px;">
                         <textarea class="form-control" style="display: inline-block; width: 600px;height: 70px;" name="prepare">${info.prepare!''}</textarea>
                    </div>
                </div>
                <div class="col-sm-12 col-xs-12 div-line" style="min-height: 80px;">
                    <div style="margin-left: 15px;float: left;width: 100px;">Preview：</div>
                    <div style="float: left;width: auto;margin-top: 10px;">
                         <textarea class="form-control" style="display: inline-block; width: 600px;height: 70px;" name="preview">${info.preview!''}</textarea>
                    </div>
                </div>
                <div class="col-sm-12 col-xs-12 div-line">
                    <div class="col-sm-4 col-xs-4 border-right">
                        <span class="red">*</span>Relevant to the topic：
                        <select name="presentRelevantTopic" class="form-control" style="display: inline-block; width: auto;">
                            <option value="5">5星</option>
                            <option value="4">4星</option>
                            <option value="3">3星</option>
                            <option value="2">2星</option>
                            <option value="1">1星</option>
                        </select>
                    </div>
                    <div class="col-sm-4 col-xs-4 border-right">
                        <span class="red">*</span>In the logistic way of thinking：
                        <select name="presentLogisticThinking" class="form-control" style="display: inline-block; width: auto;">
                            <option value="5">5星</option>
                            <option value="4">4星</option>
                            <option value="3">3星</option>
                            <option value="2">2星</option>
                            <option value="1">1星</option>
                        </select>
                    </div>
                    <div class="col-sm-4 col-xs-4 border-right">
                        <span class="red">*</span>Clear and precise language using：
                        <select name="presentLanguageUsing" class="form-control" style="display: inline-block; width: auto;">
                            <option value="5">5星</option>
                            <option value="4">4星</option>
                            <option value="3">3星</option>
                            <option value="2">2星</option>
                            <option value="1">1星</option>
                        </select>
                    </div>
                </div>
                <div class="col-sm-12 col-xs-12 div-line">
                    <div class="col-sm-4 col-xs-4 border-right">
                        <span class="red">*</span>Practice-Group Work：
                        <select name="practiceGroupWork" class="form-control" style="display: inline-block; width: auto;">
                            <option value="5">5星</option>
                            <option value="4">4星</option>
                            <option value="3">3星</option>
                            <option value="2">2星</option>
                            <option value="1">1星</option>
                        </select>
                    </div>
                    <div class="col-sm-4 col-xs-4 border-right">
                        <span class="red">*</span>Practice-Individual Work：
                        <select name="practiceIndividualWork" class="form-control" style="display: inline-block; width: auto;">
                            <option value="5">5星</option>
                            <option value="4">4星</option>
                            <option value="3">3星</option>
                            <option value="2">2星</option>
                            <option value="1">1星</option>
                        </select>
                    </div>
                </div>
                <div class="col-sm-12 col-xs-12 div-line">
                    <div class="col-sm-4 col-xs-4 border-right">
                        <span class="red">*</span>Produce-Group Work：
                        <select name="produceGroupWork" class="form-control" style="display: inline-block; width: auto;">
                            <option value="5">5星</option>
                            <option value="4">4星</option>
                            <option value="3">3星</option>
                            <option value="2">2星</option>
                            <option value="1">1星</option>
                        </select>
                    </div>
                    <div class="col-sm-4 col-xs-4 border-right">
                        <span class="red">*</span>Produce-Individual Work：
                        <select name="produceIndividualWork" class="form-control" style="display: inline-block; width: auto;">
                            <option value="5">5星</option>
                            <option value="4">4星</option>
                            <option value="3">3星</option>
                            <option value="2">2星</option>
                            <option value="1">1星</option>
                        </select>
                    </div>
                </div>
                <div class="col-sm-12 col-xs-12 div-line" style="min-height: 80px;">
                    <div style="margin-left: 15px;float: left;width: 120px;">Demonstration：</div>
                    <div style="float: left;width: auto;margin-top: 10px;">
                         <textarea class="form-control" style="display: inline-block; width: 600px;height: 70px;" name="demonstration">${info.demonstration!''}</textarea>
                    </div>
                </div>
                <div class="col-sm-12 col-xs-12 div-line" style="min-height: 80px;">
                    <div style="margin-left: 15px;float: left;width: 200px;">Student Assessment-Group Evaluation：</div>
                    <div style="float: left;width: auto;margin-top: 10px;">
                         <textarea class="form-control" style="display: inline-block; width: 600px;height: 70px;" name="saGroupEvaluation">${info.saGroupEvaluation!''}</textarea>
                    </div>
                </div>
                <div class="col-sm-12 col-xs-12 div-line" style="min-height: 80px;">
                    <div style="margin-left: 15px;float: left;width: 220px;">Student Assessment-Individual Evaluation：</div>
                    <div style="float: left;width: auto;margin-top: 10px;">
                         <textarea class="form-control" style="display: inline-block; width: 600px;height: 70px;" name="saIndividualEvaluation">${info.saIndividualEvaluation!''}</textarea>
                    </div>
                </div>
                <div class="col-sm-12 col-xs-12 div-line" style="min-height: 80px;">
                    <div style="margin-left: 15px;float: left;width: 200px;">Teacher Assessment-Group Evaluation：</div>
                    <div style="float: left;width: auto;margin-top: 10px;">
                         <textarea class="form-control" style="display: inline-block; width: 600px;height: 70px;" name="taGroupEvaluation">${info.taGroupEvaluation!''}</textarea>
                    </div>
                </div>
                <div class="col-sm-12 col-xs-12 div-line" style="min-height: 80px;">
                    <div style="margin-left: 15px;float: left;width: 220px;">Teacher Assessment-Individual Evaluation：</div>
                    <div style="float: left;width: auto;margin-top: 10px;">
                         <textarea class="form-control" style="display: inline-block; width: 600px;height: 70px;" name="taIndividualEvaluation">${info.taIndividualEvaluation!''}</textarea>
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
    $(function () {
        $.ajax({
            type : "GET",
            url : "/selectFeedbackId",
            async : true,
            data : {},
            dataType : "json",
            success : function(data) {
                try {
                    var options = '';
                    for (var i = 0; i < data.length; i++) {
                        options+='<option value="'+data[i].id+'" >'+data[i].text+'</option>';
                    }
                    $("#feedbackId").append(options);
                } catch (e) {
                    alert("下拉框ajax方法出错："+e);
                }
            }
        });
    });


    function save() {
        $.post("/topic-save",
                $("#addForm").serialize(),
                function (data) {
                    if (data.resultCode == 0) {
                        window.location.href = '${ctx!''}/topic-list';
                    } else {
                        notice(data.message, "red");
                    }
                }, "json");
    }

</script>

</body>
</html>