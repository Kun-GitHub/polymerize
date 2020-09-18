<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
<title>feedback</title>
<link href="${ctx!''}/resources/mobile/css/common_h5.css" rel="stylesheet" type="text/css"/>
<link href="${ctx!''}/resources/mobile/css/wb411.css" rel="stylesheet" type="text/css"/>
<script language="JavaScript" charset="utf-8" src="${ctx!''}/resources/mobile/js/jquery-1.9.1.min.js"></script>
</head>
<body class="wbbody">
    <div class="fb_title">Feedback</div>
    <div class="fb_box info">
        <div class="fb_list">
            <label>Student’s name:</label>
            <span>${info.studentsName!''}</span>
        </div>
        <div class="fb_list">
            <label>Student number:</label>
            <span>${info.studentNumber!''}</span>
        </div>
        <div class="fb_list">
            <label>Class Schedule:</label>
            <span>${info.classSchedule!''}</span>
        </div>
        <div class="fb_list">
            <label>Date:</label>
            <span>${(info.feedbackDate?datetime)!''}</span>
        </div>
    </div>
    <div class="fb_box cont">
        <div class="fb_list">
            <label>Interaction 课堂互动</label>
            <div class="fb_radio">
                <a <#if info.interaction??&&info.interaction==4>class="active"</#if>>Excellent 非常棒</a>
                <a <#if info.interaction??&&info.interaction==3>class="active"</#if>>Very Good 很好</a>
                <a <#if info.interaction??&&info.interaction==2>class="active"</#if>>Good 好</a>
                <a <#if info.interaction??&&info.interaction==1>class="active"</#if>>Need Improvement 需要提高</a>
            </div>
        </div>
        <div class="fb_list">
            <label>Fluency 英文流利度</label>
            <div class="fb_radio">
                <a <#if info.fluency??&&info.fluency==4>class="active"</#if>>Excellent 非常棒</a>
                <a <#if info.fluency??&&info.fluency==3>class="active"</#if>>Very Good 很好</a>
                <a <#if info.fluency??&&info.fluency==2>class="active"</#if>>Good 好</a>
                <a <#if info.fluency??&&info.fluency==1>class="active"</#if>>Need Improvement 需要提高</a>
            </div>
        </div>
        <div class="fb_list">
            <label>Correction 问题更正</label> <span>Student’s Answer / Sentence</span><span>Correction</span>
            <textarea class="fb_input" rows="3" readonly="readonly">${info.correction!''}</textarea>
        </div>
        <div class="fb_list">
            <label>Please Review 需要复习单元</label>
            <textarea class="fb_input" rows="3" readonly="readonly">${info.pleaseReview!''}</textarea>
        </div>
        <div class="fb_list">
            <label>Vocabulary&Pronunciation 单词及发音问题</label>
            <textarea class="fb_input" rows="3" readonly="readonly">${info.vocabularyPronunciation!''}</textarea>
        </div>
        <div class="fb_list">
            <label>Comments 评价</label>
            <textarea class="fb_input" rows="3" readonly="readonly">${info.comments!''}</textarea>
        </div>
        <div class="fb_list">
            <label>Today’s Homework 今日作业</label>
            <textarea class="fb_input" rows="5" readonly="readonly">${info.todaysHomework!''}</textarea>
        </div>
    </div>
</body>
</html>
