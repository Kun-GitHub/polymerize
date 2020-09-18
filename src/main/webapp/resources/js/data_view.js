
var datas={
	tw:{
		tit:"预警数",
		data:{"5.12":19,"5.13":31,"5.14":26,"5.15":23,"5.16":24,"5.17":37,"5.18":34,"5.19":42,"5.20":42,"5.21":14}
	},
	tc:{
		tit:"预警信息数",
		data:{"t00":212,"t01":256,"t02":265,"t03":231,"t04":287,"t05":264,"t06":257,"t07":245,"t08":224,"t09":257,"t10":247,"t11":224,"t12":257,"t13":254,"t14":254,"t15":214,"t16":254,"t17":254,"t18":224,"t19":241,"t20":254,"t21":245,"t22":245,"t23":285}
	},
	rs:{
		tit:"正常运行的设备数",
		data:{"警灯":4,"LED屏幕":2,"工业预警器":4,"星光球机":8,"辅助照明灯":8}
	}
};
function xr(){
// xrtj('today_warning',datas.tw.data,datas.tw.tit);
// xrzx('today_count',datas.tc.data,datas.tc.tit);
xrzb('running_state',datas.rs.data,datas.rs.tit);

    alert('');
	$.post("../alarm/query-alarmcount-groupby-date",{bridgeId:1},function (lists) {
        alert(lists);
	});
}

// xr();
// window.onresize=function(){xr()}

function xrtj(id,datas,tit){
	var labels = new Array();
	var values = new Array();
	for(var key in datas){
		labels.push(key);
		values.push(datas[key]);
	}
	
	//渲染表格（想看明白请看ECharts 3.0官方api） 
	var myChart = echarts.init(document.getElementById(id));
		option = {
			tooltip : {
				trigger: 'axis',
				axisPointer : {            // 坐标轴指示器，坐标轴触发有效
					type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
				}
			},
			grid: {
				top:'5%',
				left: '3%',
				right: '4%',
				bottom: '3%',
				containLabel: true
			},
			xAxis:  {
				type: 'value',
				axisLabel:{textStyle:{color:'rgba(255,255,255,0.3)'}},
				axisTick:{lineStyle:{color:'rgba(255,255,255,0.3)'}},
				axisLine:{lineStyle:{color:'rgba(255,255,255,0.3)'}},
				splitLine:{show:false}
			},
			yAxis: {
				type: 'category',
				splitLine:{show:false},
				axisTick:{lineStyle:{color:'rgba(255,255,255,0.3)'}},
				axisLabel:{textStyle:{color:'rgba(255,255,255,0.3)'}},
				axisLine:{lineStyle:{color:'rgba(255,255,255,0.3)'}},
				data:labels
			},
			series: [
				{
					name: tit,
					type: 'bar',
					barWidth:'60%',
					itemStyle:{normal:{color:'rgba(255,255,255,0.3)'}},
					data: values
				}
			]
		};

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
}

function xrzx(id,datas,tit){
	var labels = new Array();
	var values = new Array();
	for(var key in datas){
		labels.push(key);
		values.push(datas[key]);
	}
	//渲染表格（想看明白请看ECharts 3.0官方api） 
	var myChart = echarts.init(document.getElementById(id));
		option = {
			tooltip : {
				trigger: 'axis',
				axisPointer : {            // 坐标轴指示器，坐标轴触发有效
					type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
				}
			},
			grid: {
				top:'5%',
				left: '3%',
				right: '4%',
				bottom: '3%',
				containLabel: true
			},
			yAxis:  {
				type: 'value',
				axisLabel:{textStyle:{color:'rgba(255,255,255,0.3)'}},
				axisTick:{lineStyle:{color:'rgba(255,255,255,0.3)'}},
				axisLine:{lineStyle:{color:'rgba(255,255,255,0.3)'}},
				splitLine:{show:false}
			},
			xAxis: {
				type: 'category',
				splitLine:{show:false},
				axisTick:{lineStyle:{color:'rgba(255,255,255,0.3)'}},
				axisLabel:{textStyle:{color:'rgba(255,255,255,0.3)'}},
				axisLine:{lineStyle:{color:'rgba(255,255,255,0.3)'}},
				data:labels
			},
			series: [
				{
					name: tit,
					type: 'line',
					barWidth:'60%',
					areaStyle: {normal: {}},
					smooth:true,
					itemStyle:{normal:{color:'rgba(255,255,255,0.3)'}},
					data: values
				}
			]
		};

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
}

function xrzb(id,datas,tit){
	var labels = new Array();
	var values = new Array();
	for(var key in datas){
		labels.push(key);
		values.push({value:datas[key],name:key});
	}
	 
	//渲染表格（想看明白请看ECharts 3.0官方api） 
	var myChart = echarts.init(document.getElementById(id));
		option = {
			tooltip: {
				trigger: 'item',
				formatter: "{b}: {c}件正常"
			},
			
			series: [
				{
					name:tit,
					type:'pie',
					radius: ['40%', '55%'],
					avoidLabelOverlap: false,
					startAngle:20,
					center: ['50%', '50%'],
					itemStyle:{normal:{
						color:'rgba(255,255,255,0.3)',
						borderWidth:1,
						borderColor:'#051524'
					}},
					label:{normal:{/*formatter:"{b}\n{c}件正常",*/textStyle:{fontSize:12},show:true}},
					labelLine:{normal:{smooth:false,length:8,length2:4,show:true}},
					data:values
				}
			]
		};

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
}

	
	