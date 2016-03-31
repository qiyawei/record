<%@page import="com.kaishengit.entity.Type"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<div class="container">
		
		<h4>当前头像</h4>
		<c:choose>
			<c:when test="${not empty sessionScope.account.img }">
				<img src="http://7xq3kx.com1.z0.glb.clouddn.com/${sessionScope.account.img}-pic" alt="" class="img-circle" />
			</c:when>
			<c:otherwise>
				<img src="http://7xq3kx.com1.z0.glb.clouddn.com/FiLnYme0Jb0166iz3x-7HWaTSleT-pic" alt="" class="img-circle"/>
			</c:otherwise>
		</c:choose>
		
		<form action="http://upload.qiniu.com" method="post" enctype="multipart/form-data">
			<input type="hidden" name="x:id" value="${sessionScope.account.id }"/>			
			<input type="hidden" name="token" value="${uploadToken}"/>
			<legend>设置头像</legend>
			<label>请选择文件</label>
			<input type="file" name="file" />
			<div class="form-actions">
				<button class="btn btn-primary">上传</button>
			</div>
		</form>
		
	</div>
	
	
	
	
	<script src="/static/js/jquery-1.11.3.js"></script>
	<script src="/static/js/bootstrap.min.js"></script>
	
</body>
</html>