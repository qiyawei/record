<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
		<div class="loginwarpper">
		<form action="/confirm.do" id="formbtn" method="post">
				<input type="hidden" name="id" value="${userid}" />
				<label>新密码</label>
				<input type="password" name="pwd"/>
				
				<div class="form-actions">
					<button type="button" id="forgetbtn" class="btn btn-success">提交</button>
				</div>
		</form>
		</div>
	</div>	
	<script src="/static/js/jquery-1.11.3.js"></script>
	<script src="/static/js/md5.js"></script>
	<script>
		$(function(){
			
			$("#forgetbtn").click(function(){
				
				$("#formbtn").submit();
			})	
		});
	</script>
</body>
</html>