<%@ page language="java"  pageEncoding="UTF-8"%>
<html>
<head>
<meta  charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/static/css/bootstrap.min.css"  media="all"/>
<style type="text/css">
	.loginwarpper{
		width:206px;
		margin:50px auto;
	
	}

</style>
</head>
<body>
	
	<div class="container">
	<%
		String state = request.getParameter("state");
		if("error".equals(state)){
	%>
		<div class="alert">账号或密码错误</div>
		<%}else if("logout".equals(state)){ %>
			<div class="alert">您已经安全退出</div>
		<%}else if("changepwd".equals(state)){ %>
			<div class="alert">密码修改成功，请重新登陆</div>
		<%}else if("unlogin".equals(state)){ %>
			<div class="alert">您还没登陆，请您先登录</div>
		<%} else if("reset".equals(state)){%>
			<div class="alert">密码重置成功，请重新登录</div>
		<%} %>	
			
		<div class="loginwarpper">
			<form action="/login.do" id="formbtn" method="post">
				<legend>用户登录</legend>
				<label>账号</label>
				<input type="text" name="username"/>
				<label>密码</label>
				<input type="password" id="pwd" name="password"/>
				<label class="checkbox">
					<input  name="box" value="remember" type="checkbox"/>记住我
				</label>
				<div class="form-actions">
					<button type="button" id="loginbtn"class="btn btn-success">登陆</button>
					<a href="/forget.do">忘记密码</a>
				</div>
			</form>
		
		</div>
	
	
	</div>
	<script src="/static/js/jquery-1.11.3.js"></script>
	<script src="/static/js/md5.js"></script>
	<script>
		$(function(){
			
			$("#loginbtn").click(function(){
				var pwd = $("#pwd").val();
				pwd = CryptoJS.MD5(pwd);
				
				 $("#pwd").val(pwd);
				
				
				$("#formbtn").submit();
			})
			
			
			
			
		})
	
	
	
	
	</script>
	
</body>
</html>