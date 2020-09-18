<!doctype html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
    <title>Topic</title>
    <link href="${ctx!''}/resources/mobile/css/common_h5.css" rel="stylesheet" type="text/css" />
    <link href="${ctx!''}/resources/mobile/css/wb411.css" rel="stylesheet" type="text/css" />
    <script language="JavaScript" charset="utf-8" src="${ctx!''}/resources/mobile/js/jquery-1.9.1.min.js"></script>
</head>

<body class="wbbody">

    <div class="yc" id="topicbox">
        <div class="topic_box">
            <div class="topic_title tit">Topic ${info.nums!''}: ${info.topic!''}</div>
            <div class="topic_list">
                <label>Assignment</label>
                <textarea class="topic_input Assignment" rows="2">${info.assignment!''}</textarea>
            </div>
            <div class="topic_list">
                <label>Prepare</label>
                <textarea class="topic_input Prepare" rows="1">${info.prepare!''}</textarea>
            </div>
            <div class="topic_list">
                <label>Preview</label>
                <textarea class="topic_input Preview" rows="1">${info.preview!''}</textarea>
            </div>
            <div class="topic_list">
                <label>Present</label>
                <div class="ub ub-ac">
                    <span class="ub-f1">Relevant to the topic</span>
                    <div class="topic_star ub-f1 clear3">
                    <#if info.presentRelevantTopic??&&info.presentRelevantTopic==5>
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                    <#elseif info.presentRelevantTopic??&&info.presentRelevantTopic==4>
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                    <#elseif info.presentRelevantTopic??&&info.presentRelevantTopic==3>
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                    <#elseif info.presentRelevantTopic??&&info.presentRelevantTopic==2>
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                    <#else>
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                    </#if>
                    </div>
                </div>
                <div class="ub ub-ac">
                    <span class="ub-f1">In the logistic way of thinking</span>
                    <div class="topic_star ub-f1 clear3">
                    <#if info.presentLogisticThinking??&&info.presentLogisticThinking==5>
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                    <#elseif info.presentLogisticThinking??&&info.presentLogisticThinking==4>
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                    <#elseif info.presentLogisticThinking??&&info.presentLogisticThinking==3>
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                    <#elseif info.presentLogisticThinking??&&info.presentLogisticThinking==2>
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                    <#else>
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                    </#if>
                    </div>
                </div>
                <div class="ub ub-ac">
                    <span class="ub-f1">Clear and precise language using</span>
                    <div class="topic_star ub-f1 clear3">
                    <#if info.presentLanguageUsing??&&info.presentLanguageUsing==5>
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                    <#elseif info.presentLanguageUsing??&&info.presentLanguageUsing==4>
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                    <#elseif info.presentLanguageUsing??&&info.presentLanguageUsing==3>
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                    <#elseif info.presentLanguageUsing??&&info.presentLanguageUsing==2>
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                    <#else>
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                    </#if>
                    </div>
                </div>


            </div>
            <div class="topic_list">
                <label>Practice</label>
                <div class="ub ub-ac">
                    <span class="ub-f1">Group Work</span>
                    <div class="topic_star ub-f1 clear3">
                    <#if info.practiceGroupWork??&&info.practiceGroupWork==5>
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                    <#elseif info.practiceGroupWork??&&info.practiceGroupWork==4>
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                    <#elseif info.practiceGroupWork??&&info.practiceGroupWork==3>
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                    <#elseif info.practiceGroupWork??&&info.practiceGroupWork==2>
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                    <#else>
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                    </#if>
                    </div>
                </div>
                <div class="ub ub-ac">
                    <span class="ub-f1">Individual Work</span>
                    <div class="topic_star ub-f1 clear3">
                    <#if info.practiceIndividualWork??&&info.practiceIndividualWork==5>
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                    <#elseif info.practiceIndividualWork??&&info.practiceIndividualWork==4>
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                    <#elseif info.practiceIndividualWork??&&info.practiceIndividualWork==3>
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                    <#elseif info.practiceIndividualWork??&&info.practiceIndividualWork==2>
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                    <#else>
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                    </#if>
                    </div>
                </div>

            </div>
            <div class="topic_list">
                <label>Produce</label>
                <div class="ub ub-ac">
                    <span class="ub-f1">Group Work</span>
                    <div class="topic_star ub-f1 clear3">
                    <#if info.produceGroupWork??&&info.produceGroupWork==5>
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                    <#elseif info.produceGroupWork??&&info.produceGroupWork==4>
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                    <#elseif info.produceGroupWork??&&info.produceGroupWork==3>
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                    <#elseif info.produceGroupWork??&&info.produceGroupWork==2>
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                    <#else>
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                    </#if>
                    </div>
                </div>
                <div class="ub ub-ac">
                    <span class="ub-f1">Individual Work</span>
                    <div class="topic_star ub-f1 clear3">
                    <#if info.produceIndividualWork??&&info.produceIndividualWork==5>
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                    <#elseif info.produceIndividualWork??&&info.produceIndividualWork==4>
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                    <#elseif info.produceIndividualWork??&&info.produceIndividualWork==3>
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                    <#elseif info.produceIndividualWork??&&info.produceIndividualWork==2>
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                    <#else>
                        <img src="${ctx!''}/resources/mobile/images/star.png" alt="">
                    </#if>
                    </div>
                </div>

            </div>
            <div class="topic_list">
                <label>Demonstration</label>
                <textarea class="topic_input" rows="2">${info.demonstration!''}</textarea>
            </div>
            <div class="topic_list">
                <label>Evaluation</label>

                <span>Student Assessment Group Evaluation</span>
                <textarea class="topic_input mt0" rows="2">${info.saGroupEvaluation!''}</textarea>
                <span>Student Assessment Individual Evaluation</span>
                <textarea class="topic_input mt0" rows="2">${info.saIndividualEvaluation!''}</textarea>

                <span class="mt20">Teacher Assessment Group Evaluation</span>
                <textarea class="topic_input mt0" rows="2">${info.taGroupEvaluation!''}</textarea>
                <span>Teacher Assessment Individual Evaluation</span>
                <textarea class="topic_input mt0" rows="2">${info.taIndividualEvaluation!''}</textarea>
            </div>
        </div>
    </div>
</body>

<script>
    $(function(){
        var topic=[
            {

            },
            {
                tit:"Topic 2: Colorful World",
                Assignment:"Pick one of your favorite paint and learn the colors of the paint. You can ask for help through Wechat with your teacher.",
                Prepare:"Creatively draw a color list",
                Preview:"Internet pages"
            },
            {
                tit:"Topic 3: Interesting Numbers",
                Assignment:"Learn numbers and song of “Ten little Indians”",
                Prepare:"List of numbers.",
                Preview:"Listen to the song."
            },
            {
                tit:"Topic 4: Wonderful Friends",
                Assignment:"Watch the Video of_______, learn animal names, find out the name of their babies.",
                Prepare:"Draw the animals down on the right place.",
                Preview:"Watch the video."
            },
            {
                tit:"Topic 5: Wonderful Friends & Adjectives",
                Assignment:"Describe the animals with the Adjective words that teacher gave.",
                Prepare:"adjectives",
                Preview:"Word cards"
            },
            {
                tit:"Topic 6: Adjectives",
                Assignment:"Use adjectives to describe five different animals and memories them. You can ask for help from your teacher.",
                Prepare:"adjectives",
                Preview:""
            },
            {
                tit:"Topic 7: Four Seasons",
                Assignment:"Draw a picture of your favourite season, describe it in English.",
                Prepare:"",
                Preview:""
            },
            {
                tit:"Topic 8: Like & Dislike",
                Assignment:"List the animals you like and dislike. The list can be beautifully drawn.",
                Prepare:"",
                Preview:""
            },
            {
                tit:"Topic 9: Life Education",
                Assignment:"",
                Prepare:"",
                Preview:""
            },
            {
                tit:"Topic 10: In the Zoo",
                Assignment:"Think about the warning sign they have in the Zoo, and draw them out in English.",
                Prepare:"",
                Preview:""
            }
        ];

        for(var i=0;i<1;i++){
            for(var key in topic[i] ){
                $("#topicbox").find("."+key).html(topic[i][key]);
            }
            $(".wbbody").append($("#topicbox").html());
            
        }
        $("#topicbox").remove();

        // $(".wbbody").on("click",".topic_title",function(){
        //     $(this).parent().find(".topic_list").slideToggle(100);
        // })
    });
</script>

</html>