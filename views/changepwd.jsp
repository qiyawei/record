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

	<jsp:include page="include/navbar.jsp">
		<jsp:param value="main" name="menu"/>
	</jsp:include>
	
	<div class="container">
		
		<%
			String state = request.getParameter("state");
			if("error".equals(state)) {
		%>
			<div class="alert">原始密码错误</div>
		<%} %>
		
		<form action="/changepwd.do" method="post">
			<legend>修改密码</legend>
			<label>原始密码</label>
			<input type="password" name="oldpassword"/>
			<label>新密码</label>
			<input type="password" name="newpassword"/>
			<label>确认新密码</label>
			<input type="password" name="newpassword2"/>
			
			<div class="form-actions">
				<button class="btn btn-primary">保存</button>
			</div>
		
		
		</form>
	
	
	</div>



	<script src="/static/js/jquery-1.10.1.min.js"></script>
	<script src="/static/js/bootstrap.min.js"></script>
</body>
</html>