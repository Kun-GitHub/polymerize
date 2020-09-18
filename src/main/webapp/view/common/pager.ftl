<#-- 分页（Pager对象、链接URL、参数Map、最大页码显示数） -->
<#macro pager pager baseUrl parameter="" >
	<#local pageNumber = pager.page />
	<#local pageSize = pager.pageSize />
	<#local pageCount = pager.totalPages />
	<#local totalRows = pager.totalRows />
	
	<#if baseUrl?contains("?")>
		<#local baseUrl = baseUrl + "&" />
	<#else>
		<#local baseUrl = baseUrl + "?" />
	</#if>
	<#local firstPageUrl = baseUrl + "page=1" + parameter />
	<#local lastPageUrl = baseUrl + "page=" + pageCount + parameter />
	<#local prePageUrl = baseUrl + "page=" + (pageNumber - 1) + parameter />
	<#local nextPageUrl = baseUrl + "page=" + (pageNumber + 1) + parameter />
	
	<div class="page">
		<div style="float:left;font-size: 14px;">共 ${totalRows} 条记录</div>
		
		<#if (pageCount > 1)>
		<ul  style="float:right;">
			<#if (pageNumber > 1)>
				<li>
					<a class="txt" href="${firstPageUrl}">首页</a>
				</li>
			<#else>
				<li>
					<a class="txt active">首页</a>
				</li>
			</#if>
			
			<#if (pageNumber > 1)>
				<li>
					<a class="txt" href="${prePageUrl}">上一页</a>
				</li>
			<#else>
				<li>
					<a class="txt active">上一页</a>
				</li>
			</#if>
			
			<#if (pageNumber < pageCount)>  
                <li>  
                    <a class="txt" href="${nextPageUrl}">下一页</a>  
                </li>  
	        <#else>  
	            <li>  
	                <a class="txt active">下一页</a> 
	            </li>  
	        </#if>
			
			<#if (pageNumber < pageCount)>
				<li>
					<a class="txt" href="${lastPageUrl}">末页</a>
				</li>
			<#else>
				<li>
					<a class="txt active">末页</a>
				</li>
			</#if>
			
            <li>共 ${pageCount} 页，到第  <span class="page_input"><input type="text" value="${pageNumber!''}" onkeypress="EnterPress(event)" id="pageNumInput" data-url="${baseUrl}" data-param="${parameter}"></span> 页</li>    
	     </ul>
	     </#if>
    </div>
    
<style type="text/css">
/*分页样式*/
.page {
	font-size: 12px;
	height: 25px;
	line-height: 25px;
	width: inherit;
}

.page ul li {
	float: left;
	list-style: none;
}

.page {
	margin: 0 0 20px;
}

.page a {
	text-decoration: none;
	display: inline-block;
	line-height: 14px;
	padding: 4px 8px;
	color: #2283c5;
	border: 1px solid #6faed9;
	margin: 0 2px;
}

.page a:hover {
	background: #6faed9;
	color: #fff;
	border: 1px solid #6faed9;
}

.page a.active,.page a.active:hover {
	background: none;
	border: 1px solid #ccc;
	color: #999;
	cursor: default;
}

.page .page_input input {
	width: 35px;
	height: 22px;
	font-size: 12px;
	border: 1px solid #6faed9;
}
</style>

<script type="text/javascript">
	function EnterPress(e){
		var pageNumInput = document.getElementById("pageNumInput");
		var e = e || window.event; 
		if(e.keyCode == 13){ 
			url = pageNumInput.getAttribute("data-url");
			page = pageNumInput.value;
			param = pageNumInput.getAttribute("data-param");
			if(page && /^\d+$/g.test(page)){
				self.location= url+"page="+page+param;
			}
		} 
	};
</script>
</#macro>