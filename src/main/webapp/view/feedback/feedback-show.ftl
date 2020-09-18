<!doctype html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
    <title>Feedback(反馈)</title>
    <link href="${ctx!''}/resources/feedback/css/common_h5.css" rel="stylesheet" type="text/css" />
    <link href="${ctx!''}/resources/feedback/css/wb411.css" rel="stylesheet" type="text/css" />
    <script language="JavaScript" charset="utf-8" src="${ctx!''}/resources/feedback/js/jquery-1.9.1.min.js"></script>
</head>

<body class="wbbody">
    <!-- <div class="fb_title fr-lg">Feedback(反馈)</div> -->

    <div class="fb_box info ub ub-ac">
        <img src="${ctx!''}/resources/feedback/images/tx.jpg" alt="">
        <div class="ub-f1 pl10 ">
            <div class="ub ub-ac pb8">
                <label>${info.name!''} </label>
                <span>${info.num!''}</span>
            </div>
            <p>${(info.createTime?datetime)!''}</p>
        </div>
        <h3 class="ub-f1 t_r">${info.classes!''}</h3>
    </div>

    <div class="fb-title">Learning Content 学习内容</div>
    <div class="fb-cadr onlytxt">
    ${info.learningContent!''}
    </div>

    <div class="fb-title">Learning Achievement 学习情况</div>
    <div class="fb-cadr">
        <!-- <input type="text" value="0" class="t"> -->
        <div class="yuanbox mt18">
            <div class="yuan1">
                <div class="progressyuan one">
                    <div class="mpro"></div>
                    <div class="proleft pro">
                        <b class="lb">
                            <span></span>
                        </b>
                    </div>
                    <div class="proright pro">
                        <b class="rb">
                            <span></span>
                        </b>
                    </div>
                    <div class="protxt"></div>
                </div>
            </div>
            <div class="yuan2">
                <div class="progressyuan two">
                    <div class="mpro"></div>
                    <div class="proleft pro">
                        <b class="lb">
                            <span></span>
                        </b>
                    </div>
                    <div class="proright pro">
                        <b class="rb">
                            <span></span>
                        </b>
                    </div>
                    <div class="protxt">
                        <label>掌握程度</label>
                        <span class="bf">${info.learningAchievement!'10'}%</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="fb-jd">
            <div class="ub ub-ac">
                <div class="ub-f1 dian3 one">Clear & precise language using 语言准确度</div>
                <div class="ub-f1 t_r dian3 <#if info.clear??&&info.clear==3>two1<#elseif info.clear??&&info.clear==2>two2
                <#elseif info.clear??&&info.clear==1>two3<#else>two4</#if>"><#if info.clear??&&info.clear==3>Excellent 非常棒<#elseif info.clear??&&info.clear==2>Very Good 很好
                <#elseif info.clear??&&info.clear==1>Good 好<#else>Need Improvement 需要提高</#if></div>
            </div>
            <div class="jdbox">
                <div class="line_clear" style="width: 0%;"></div>
            </div>
        </div>
        <div class="fb-jd">
            <div class="ub ub-ac">
                <div class="ub-f1 dian3 one">Fluency 英文流利度</div>
                <div class="ub-f1 t_r dian3 <#if info.fluency??&&info.fluency==3>two1<#elseif info.fluency??&&info.fluency==2>two2
                <#elseif info.fluency??&&info.fluency==1>two3<#else>two4</#if>"><#if info.fluency??&&info.fluency==3>Excellent 非常棒<#elseif info.fluency??&&info.fluency==2>Very Good 很好
                <#elseif info.fluency??&&info.fluency==1>Good 好<#else>Need Improvement 需要提高</#if></div>
            </div>
            <div class="jdbox">
                <div class="line_fluency" style="width: 0%;"></div>
            </div>
        </div>
        <!-- <div class="fb-jd">
            <div class="ub ub-ac">
                <div class="ub-f1 dian3 one">Organization 组织性</div>
                <div class="ub-f1 t_r dian3 two3">Good 好</div>
            </div>
            <div class="jdbox">
                <div style="width: 50%;"></div>
            </div>
        </div> -->
        <div class="fb-jd">
            <div class="ub ub-ac">
                <div class="ub-f1 dian3 one">Creativity 创造性</div>
                <div class="ub-f1 t_r dian3 <#if info.creativity??&&info.creativity==3>two1<#elseif info.creativity??&&info.creativity==2>two2
                <#elseif info.creativity??&&info.creativity==1>two3<#else>two4</#if>"><#if info.creativity??&&info.creativity==3>Excellent 非常棒<#elseif info.creativity??&&info.creativity==2>Very Good 很好
                <#elseif info.creativity??&&info.creativity==1>Good 好<#else>Need Improvement 需要提高</#if></div>
            </div>
            <div class="jdbox">
                <div class="line_creativity" style="width: 0%;"></div>
            </div>
        </div>
        <div class="fb-jd">
            <div class="ub ub-ac">
                <div class="ub-f1 dian3 one">Logical thinking 逻辑思维</div>
                <div class="ub-f1 t_r dian3 <#if info.logicalThinking??&&info.logicalThinking==3>two1<#elseif info.logicalThinking??&&info.logicalThinking==2>two2
                <#elseif info.logicalThinking??&&info.logicalThinking==1>two3<#else>two4</#if>"><#if info.logicalThinking??&&info.logicalThinking==3>Excellent 非常棒<#elseif info.logicalThinking??&&info.logicalThinking==2>Very Good 很好
                <#elseif info.logicalThinking??&&info.logicalThinking==1>Good 好<#else>Need Improvement 需要提高</#if></div>
            </div>
            <div class="jdbox">
                <div class="line_logicalThinking" style="width: 0%;"></div>
            </div>
        </div>
        <div class="fb-jd">
            <div class="ub ub-ac">
                <div class="ub-f1 dian3 one">Homework compleaon完成作业</div>
                <div class="ub-f1 t_r dian3 <#if info.homeworkCompleaon??&&info.homeworkCompleaon==3>two1<#elseif info.homeworkCompleaon??&&info.homeworkCompleaon==2>two2
                <#elseif info.homeworkCompleaon??&&info.homeworkCompleaon==1>two3<#else>two4</#if>"><#if info.homeworkCompleaon??&&info.homeworkCompleaon==3>Excellent 非常棒<#elseif info.homeworkCompleaon??&&info.homeworkCompleaon==2>Very Good 很好
                <#elseif info.homeworkCompleaon??&&info.homeworkCompleaon==1>Good 好<#else>Need Improvement 需要提高</#if></div>
            </div>
            <div class="jdbox">
                <div class="line_homeworkCompleaon" style="width: 0%;"></div>
            </div>
        </div>

    </div>
    <div class="fb-title">Class Performance 课堂表现</div>
    <div class="fb-cadr">
        <div class="fb-ybox">
            <div class="hx one <#if info.concentration??&&info.concentration==2>ex
                    <#elseif info.concentration??&&info.concentration==1>good<#else>imp</#if>">
                <div class="tx"><span></span><label>Concentration<br/>听讲专注表现</label></div>
            </div>
            <div class="hx two <#if info.expression??&&info.expression==2>ex
                    <#elseif info.expression??&&info.expression==1>good<#else>imp</#if>">
                <div class="tx"><span></span><label>Expression<br/>英语表达表现</label></div>
            </div>
            <div class="hx three <#if info.reaction??&&info.reaction==2>ex
                    <#elseif info.reaction??&&info.reaction==1>good<#else>imp</#if>">
                <div class="tx"><span></span><label>Reaction<br/>反应能力表现</label></div>
            </div>
            <div class="hx four <#if info.interaction??&&info.interaction==2>ex
                    <#elseif info.interaction??&&info.interaction==1>good<#else>imp</#if>">
                <div class="tx"><span></span><label>Interaction<br/>师生互动表现</label></div>
            </div>
            <div class="miny">个人表现</div>
        </div>

        <div class="ub ub-ac t_c fb-ytype">
            <div class="ub-f1"><span class="one"></span>有待提高</div>
            <div class="ub-f1"><span class="two"></span>平均水平</div>
            <div class="ub-f1"><span class="three"></span>表现优异</div>
        </div>
        <div class="fb-ybox team">
                <div class="hx one <#if info.ruleConsciousness??&&info.ruleConsciousness==2>ex
                    <#elseif info.ruleConsciousness??&&info.ruleConsciousness==1>good<#else>imp</#if>">
                    <div class="tx"><span></span><label>Rule <br/>consciousness<br/>规则意识</label></div>
                </div>
                <div class="hx two <#if info.concernForOthers??&&info.concernForOthers==2>ex
                    <#elseif info.concernForOthers??&&info.concernForOthers==1>good<#else>imp</#if>">
                    <div class="tx"><span></span><label>Concern for <br/>others<br/>关心他人</label></div>
                </div>
                <div class="hx three <#if info.leaderShip??&&info.leaderShip==2>ex
                    <#elseif info.leaderShip??&&info.leaderShip==1>good<#else>imp</#if>">
                    <div class="tx"><span></span><label>Leader ship <br/>领导力</label></div>
                </div>
                <div class="hx four <#if info.corporation??&&info.corporation==2>ex
                    <#elseif info.corporation??&&info.corporation==1>good<#else>imp</#if>">
                    <div class="tx"><span></span><label>Corporation <br/>协作意识</label></div>
                </div>
                <div class="miny">团队合作</div>
            </div>
    </div>

    <div class="fb-title">Comments On Student 教师评语</div>
    <div  class="fb-sj"></div>
    <div class="fb-cadr com">
    ${info.commentsOnStudent!''}
    </div>

    <div class="fb-title">Today’s Achievement 今日作业</div>
    <div class="fb-cadr home">
    ${info.todayAchievement!''}
    </div>

    <!-- <a class="fb-btn">前往作业</a> -->

    <script src="${ctx!''}/resources/feedback/js/jquery-1.9.1.min.js"></script>
    <script>
        $(function () {
            yuan(".one", ${info.learningAchievement!'10'})
            yuan(".two", ${info.learningAchievement!'10'})
            $(".t").keyup(function () {
                yuan(".one", $(this).val())
                yuan(".two", $(this).val())
            })

            var clear = ${info.clear!0}*25+25;
            $(".line_clear").css("width",clear+"%");
            var fluency = ${info.fluency!0}*25+25;
            $(".line_fluency").css("width",fluency+"%");
            var creativity = ${info.creativity!0}*25+25;
            $(".line_creativity").css("width",creativity+"%");
            var logicalThinking = ${info.logicalThinking!0}*25+25;
            $(".line_logicalThinking").css("width",logicalThinking+"%");
            var homeworkCompleaon = ${info.homeworkCompleaon!0}*25+25;
            $(".line_homeworkCompleaon").css("width",homeworkCompleaon+"%");
        })

        function yuan(id, k) {
            $(id + " .bf").html(k + '%');
            if (k < 50 && k > 0) {
                $(id + " .lb").css("transform", "rotateZ(180deg)").find("span").hide()
                $(id + " .rb").css("transform", "rotateZ(" + (k * 3.6 + 180) + "deg)").find("span").show()
            } else if (k >= 50 && k <= 100) {
                $(id + " .rb").css("transform", "rotateZ(360deg)").find("span").hide()
                $(id + " .lb").css("transform", "rotateZ(" + (k * 3.6) + "deg)").find("span").show()
            } else {
                $(id + " .lb," + id + " .rb").css("transform", "rotateZ(180deg)").find("span").hide()
            }
        }
    </script>
</body>

</html>