
/**
 * 禁用页面回车事件
 */
document.onkeydown = function(e) {
	var ev = (typeof event!= 'undefined') ? window.event : e;
	if(ev.keyCode == 13 && document.activeElement.id != "pageNumInput") {
		return false;
	}
}

/**
 * 全选、反选
 */
function selectAll(checkboxName) {

	var selectAll = window.event.srcElement || window.event.target;
	var checkboxs = document.getElementsByName(checkboxName);
	if (selectAll.checked) {
		for (var i = 0; i < checkboxs.length; i++) {
			var e = checkboxs[i];
			if(!e.disabled){
				e.checked = true;
				$(e).parent().parent().addClass("selected-tr");
			}
				
		}
	} else {
		for (var i = 0; i < checkboxs.length; i++) {
			var e = checkboxs[i];  
			e.checked = false;
			$(e).parent().parent().removeClass("selected-tr");
		}
	}
}

/**
 * 根据checkbox名称获取所选id组合
 * 
 * @param checkboxName
 * @returns
 */
function getIds(checkboxName) {
	var checkboxs = document.getElementsByName(checkboxName);
	var arr = new Array();
	for (var i = 0; i < checkboxs.length; i++) {
		var e = checkboxs[i];
		if (e.checked) {
			arr.push(e.value);
		}
	}
	return arr.join();
}

/**
 * 清空表单
 */
function resetForm(formId){
	$("#"+formId).find('input[type=text]').val('');
	//$("#"+formId).find('select option:first').prop("selected", 'selected');
	$("#"+formId).find('select').prop('selectedIndex', 0);
}


/**
 * 弹框提示信息
 * @param msg
 * @param callback
 */
function alertMsg(msg,callback){
	new jBox('Modal', {
		title: '温馨提示',
		content: msg,
		theme: 'ModalBorder',
		overlay: false,
		closeOnClick: false,
		minWidth:250
	}).open();
}

/**
 * 提示信息
 * @param msg
 * @param color ['black', 'red', 'green', 'blue', 'yellow']
 */
function notice(msg,color){
	new jBox('Notice', {
		autoClose: 2500,
		animation: 'flip',
		position: {
			x: 'center',
			y: 60
		},
		content: msg,
		onInit: function() {
			if(color)
				this.options.color = color;
			else
				this.options.color = 'red';
		},
		zIndex: 12000
	});
}


/**
 * 获取url查询参数的值
 * 调用方式：getSearch()['name']
 */
function getSearch(href){
    href = href || window.location.search;
    var str = href.substring(1),
        slist = str.split('&'),
        data = {};
    for(var i=0;i<slist.length;i++){
        var d = slist[i].split('=');
        d[0]&&(data[d[0]] = d[1]||null);
    }
    return data;
}


$(function(){
	
	//获取页面url 
	var url = window.location.href;
	if(url.indexOf("?") < 0){
		url += "?";
	}
	
	$("th[data-sort]").css("cursor","pointer");
	$("th[data-sort]").prepend('<i class="icon-sort"></i>');
	
	
	var columnName = getSearch()['columnName'];
	var orderType = getSearch()['orderType'];
	
	if(columnName){
		$("th[data-sort]").each(function(){
			var th = $(this);
			var dataSort = th.attr("data-sort");
			if(dataSort.indexOf(columnName) > -1 ){
				if(orderType == "asc"){
					th.find("i").removeAttr("class").addClass("icon-sort-up");
				}
				else if(orderType == "desc"){
					th.find("i").removeAttr("class").addClass("icon-sort-down");
				}
				else{
					th.find("i").removeAttr("class").addClass("icon-sort");
				}
				return false;
			}
		});
	}
	
	
	
	
	$("th[data-sort]").click(function(){
		var th = $(this);
		var dataSort = th.attr("data-sort");
		if(dataSort){
			dataSort = eval('({' + dataSort + '})');
			if(dataSort.field){
				
				if(url.indexOf("columnName=") > -1){
					url = url.replace("columnName="+(columnName==null?"":columnName),"columnName="+dataSort.field);
				}
				else{
					url = url + "&columnName="+dataSort.field;
				}
				//alert(url);
				
				if(orderType && orderType=="asc"){
					orderType = "desc";
				}
				else{
					orderType = "asc";
				}
				var oldOrderType = getSearch()['orderType'];
				
				if(url.indexOf("orderType=") > -1){
					url = url.replace("orderType="+(oldOrderType==null?"":oldOrderType),"orderType="+orderType);
				}
				else{
					url = url + "&orderType="+orderType;
				}
				
				//alert(url);
				window.location.href = url;
			}
		}
	});
	
	 
});


/**
 * 自定义下拉框ajax方法
 */
jQuery.fn.selection = function (url, defaultValue, callback) {
	//加随机数，防止缓存
	if(url.indexOf("?") > -1){
		url += "&rn="+new Date().getTime();
	}
	else{
		url += "?rn="+new Date().getTime();
	}
	var that = $(this);
	$.ajax({
		type : "GET",
		url : url,
		async : true,
		data : {},
		dataType : "json",
		success : function(data) {
			try {
				var options = '';
				for (var i = 0; i < data.length; i++) {
					options+='<option value="'+data[i].id+'" '+(data[i].id==defaultValue?'selected':'')+'>'+data[i].text+'</option>';
				}
				that.append(options);
				if(callback){
					callback();
				}
			} catch (e) {
				alert("下拉框ajax方法出错："+e);
			}
		}
	});
	
};

function getWinHeight() {
    if (window.innerHeight)
        return window.innerHeight;
    if ((document.body) && (document.body.clientHeight))
        return document.body.clientHeight;
    return 1366;
}

function getWinWidth() {
    if (window.innerWidth)
        return window.innerWidth;
    if ((document.body) && (document.body.clientWidth))
        return document.body.clientWidth;
    return 768;
}

Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}