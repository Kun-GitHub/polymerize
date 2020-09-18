<!doctype html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
    <title>Topic</title>
    <link href="${ctx!''}/resources/feedback/css/common_h5.css" rel="stylesheet" type="text/css" />
    <link href="${ctx!''}/resources/feedback/css/wb411.css" rel="stylesheet" type="text/css" />
    <script language="JavaScript" charset="utf-8" src="${ctx!''}/resources/feedback/js/jquery-1.9.1.min.js"></script>
</head>

<body class="wbbody">

<div class="yc" style="display:block !important;">
    <div class="topic_box">
        <div class="topic_list pt0 ">
            <ul>
                <#list list as item>
                    <li> <a href="/feedback/feedback-show?id=${item.id!''}">${item.name!''}_${(item.createTime?datetime)!''}</a> </li>
                </#list>
            </ul>
            <#--<div class="t_c mt16">
                <a  class="topic_btn mr10">上一页</a>
                <a  class="topic_btn">下一页</a>
            </div>-->

        </div>
    </div>
</div>
</body>

<script>
    /*$(function () {
        var topic = [{
            tit: "Topic 1: Say Hello to the world",
            Assignment: "Search from the internet or books to find the different greeting ways of different countries.",
            Prepare: "Pictures or role play",
            Preview: "Internet or books(magazines)"
        },
            {
                tit: "Topic 2: Colorful World",
                Assignment: "Pick one of your favorite paint and learn the colors of the paint. You can ask for help through Wechat with your teacher.",
                Prepare: "Creatively draw a color list",
                Preview: "Internet pages"
            },
            {
                tit: "Topic 3: Interesting Numbers",
                Assignment: "Learn numbers and song of “Ten little Indians”",
                Prepare: "List of numbers.",
                Preview: "Listen to the song."
            },
            {
                tit: "Topic 4: Wonderful Friends",
                Assignment: "Watch the Video of_______, learn animal names, find out the name of their babies.",
                Prepare: "Draw the animals down on the right place.",
                Preview: "Watch the video."
            },
            {
                tit: "Topic 5: Wonderful Friends & Adjectives",
                Assignment: "Describe the animals with the Adjective words that teacher gave.",
                Prepare: "adjectives",
                Preview: "Word cards"
            },
            {
                tit: "Topic 6: Adjectives",
                Assignment: "Use adjectives to describe five different animals and memories them. You can ask for help from your teacher.",
                Prepare: "adjectives",
                Preview: ""
            },
            {
                tit: "Topic 7: Four Seasons",
                Assignment: "Draw a picture of your favourite season, describe it in English.",
                Prepare: "",
                Preview: ""
            },
            {
                tit: "Topic 8: Like & Dislike",
                Assignment: "List the animals you like and dislike. The list can be beautifully drawn.",
                Prepare: "",
                Preview: ""
            },
            {
                tit: "Topic 9: Life Education",
                Assignment: "",
                Prepare: "",
                Preview: ""
            },
            {
                tit: "Topic 10: In the Zoo",
                Assignment: "Think about the warning sign they have in the Zoo, and draw them out in English.",
                Prepare: "",
                Preview: ""
            }
        ];

        for (var i = 0; i < 1; i++) {
            for (var key in topic[i]) {
                $("#topicbox").find("." + key).html(topic[i][key]);
            }
            $(".wbbody").append($("#topicbox").html());

        }
        $("#topicbox").remove();
        var jdtype=[
            {tx:"Excellent 非常棒",baifen:100,color:"two1"},
            {tx:"Very Good 很好",baifen:75,color:"two2"},
            {tx:"Good 好",baifen:50,color:"two3"},
            {tx:"Need Improvement 需要提高",baifen:25,color:"two4"}
        ]
        $(".fb-jd").each(function(i){
            var k=0;
            $(this).find(".jdbox").click(function(){
                if(k>3) k=0;
                $(this).find("div").css("width",jdtype[k].baifen+"%")
                $(this).parent().find(".jbtype").html(jdtype[k].tx).removeClass("two1").removeClass("two2").removeClass("two3").removeClass("two4")
                $(this).parent().find(".jbtype").html(jdtype[k].tx).addClass(jdtype[k].color)
                k++
            })

        })

        // $(".wbbody").on("click",".topic_title",function(){
        //     $(this).parent().find(".topic_list").slideToggle(100);
        // })
    });*/
</script>

</html>