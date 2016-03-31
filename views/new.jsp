<%@page import="com.kaishengit.entity.Type"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/static/css/bootstrap.min.css" media="all" />
<link rel="stylesheet" href="/static/css/style.css" media="all" />
<link rel="stylesheet" href="/static/css/font-awesome.min.css" media="all" />
</head>
<body>
	
	<%@ include file="include/navbar.jsp" %>
	
	<%
		List<Type> typeList = (List<Type>)request.getAttribute("typeList");
		String typeName = (String)request.getAttribute("type");
	%>
	
	<div class="container">
			
		<form action="/new.do" method="post">
			<input type="hidden" name="inout" value="<%=typeName%>"/>
			<legend>新建账目</legend>
			<label>类别</label>
			<select name="typeid">
				<option value="">请选择类别</option>
				<%
					for(Type type : typeList) {
				%>
				<option value="<%=type.getId()%>"><%=type.getTypename() %></option>
				<%} %>
			</select>
			<label>金额</label>
			<input type="text" name="money"/>
			<label>备注</label>
			<input type="text" name="message"/>
			
			<div class="form-actions">
				<button class="btn btn-primary">保存</button>
			</div>
			
		
		</form>
		
	</div>
	
	
	
	
	<script src="/static/js/jquery-1.10.1.min.js"></script>
	<script src="/static/js/bootstrap.min.js"></script>
	
</body>
</html>