<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.kaishengit.entity.Pager" %>
<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/static/css/bootstrap.min.css"  media="all"/>
<link rel="stylesheet" href="/static/css/1.css"  media="all"/>
<link rel="stylesheet" href="/static/css/font-awesome.min.css"  media="all"/>
</head>
<body>
	
		
		<jsp:include page="include/navbar.jsp">
			<jsp:param value="main" name="menu"/>
		</jsp:include>
		<div class="container">
			<div class="well">
				<form action="/list.do" method="get" class="form-search">
				<input type="hidden" name="p" value="${pager.pageNo }" id="pageNo"/>
					<select name="inout">
						<option value="">不限</option>
						<option value="in" ${param.inout=='in'?'selected':'' }>收入</option>
						<option value="out" ${param.inout=='out'?'selected':'' }>支出</option>
					</select>
					<select name="creattime">
						<option value="">不限</option>
						<option value="3days" ${param.creattime=='3days'?'selected':'' }>三天内</option>
						<option value="7days" ${param.creattime=='7days'?'selected':'' }>一周内</option>
						<option value="30days"${param.creattime=='30days'?'selected':'' }>一个月内</option>
						<option value="ago"   ${param.creattime=='ago'?'selected':'' }>更早</option>
					</select>
					<button class="btn btn-primary">搜索</button>
				</form>
			</div>
			<a href="/new.do?type=in" class="btn btn-success"><i class="fa fa-plus-square"></i>记收入</a>
			<a href="/new.do?type=out" class="btn btn-danger"><i class="fa fa-minus-square"></i>记支出</a>
			<a href="/exportcsv.do" class="btn btn-primary"><i class="fa fa-file-text-o""></i> 导出csv文件</a>
			<a href="/exportxls.do" class="btn btn-primary"><i class="fa fa-file-excel-o""></i> 导出xls文件</a>	
			<a href="/importxls.do" class="btn btn-primary"><i class="fa fa-file-excel-o""></i> 导入xls文件</a>	
			<a href="/backdb.do" class="btn btn-danger"><i class="fa fa-file-excel-o""></i> 备份数据库</a>		
				
				<table class="table">
				
				<thead>
					<tr>
						<th>日期</th>
						<th>类别</th>
						<th>收入</th>
						<th>支出</th>
						<th>余额</th>
						<th>记账人</th>
						<th>备注</th>
					</tr>
				</thead>
				<tbody>
					
					<%
					
						//List<Map<String,Object>> list = (List<Map<String,Object>>)request.getAttribute("recordList");
						Pager<Map<String,Object>>  pager =  (Pager<Map<String,Object>>)request.getAttribute("pager");
						for(Map<String,Object> map : pager.getItems()){
					
					%>
					<tr>
						<td><%=map.get("creattime") %></td>
						<td><%=map.get("typename") %></td>
						<%
							if("in".equals(map.get("inout"))){
						%>
						<td class="text-success">+<%=map.get("money") %></td>
						<td></td>
						<%}else{ %>
						<td></td>
						<td class="text-error">-<%=map.get("money") %></td>
						<%} %>
						<td><%=map.get("yue") %></td>
						<td><%=map.get("username") %></td>
						<td></td>
						
					<%} %>
					</tr>
				
				
				</tbody>
				<tfooter>
				
				<tr>
					<td>
						总共${pager.totalSize }条记录，${pager.pageNo }/${pager.totalPages}页
					</td>
				</tr>
				
				</tfooter>
			
			
			</table>
		
			<div class="pagination pagination-right">
				<ul>
				<c:choose>
					<c:when test="${pager.pageNo == 1 }">
						<li class="active"><a  href="javascript:;">首页</a></li>
						<li class="active"><a  href="javascript:;">上一页</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="javascript:;" class="pagelink" data-pageNo=1>首页</a></li>
						<li><a href="javascript:;" class="pagelink" data-pageNo=${pager.pageNo-1}>上一页</a></li>
					</c:otherwise>
				
				</c:choose>
				<c:choose>
					<c:when test="${pager.pageNo ==  pager.totalPages}">
						<li  class="active"><a  href="javascript:;" >下一页</a></li>
						<li class="active"><a   href="javascript:;"  >末页</a></li>
					</c:when>
					<c:otherwise>	
						<li><a href="javascript:;"class="pagelink" data-pageNo=${pager.pageNo+1}>下一页</a></li>
						<li><a href="javascript:;"class="pagelink" data-pageNo=${pager.totalPages}>末页</a></li>
					</c:otherwise>
				</c:choose>
				
				
				
				</ul>
			</div>
		</div>
	<script src="/static/js/jquery-1.11.3.js"></script>
	<script src="/static/js/bootstrap.js"></script>
	<script>
	$(function(){
		$(".pagelink").click(function(){
			var pageNo = $(this).attr("data-pageNo");
			$("#pageNo").val(pageNo);
			$(".form-search").submit();
		});	
	});
	
	
	
	
	
 </script>

</body>
</html>