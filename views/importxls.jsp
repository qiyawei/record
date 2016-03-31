<%@page import="com.kaishengit.entity.Type"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/static/css/bootstrap.min.css"  media="all"/>
<link rel="stylesheet" href="/static/css/1.css"  media="all"/>
<link rel="stylesheet" href="/static/css/font-awesome.min.css"  media="all"/>
</head>
<body>
	
	<%@ include file="include/navbar.jsp" %>
	
	<div class="container">
		
		<form action="/importxls.do" method="post" enctype="multipart/form-data">
			<legend>excel数据导入</legend>
			<label>选择文件</label>
			<input type="file" name="excel"/>
			<div class="form-actions">
				<button class="btn btn-primary">导入</button>
			</div>
		</form>
		
	</div>
	
	
	
	
	
</body>
</html>