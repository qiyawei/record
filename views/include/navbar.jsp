<%@page import="com.kaishengit.entity.Account"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<%
	String menu = request.getParameter("menu");
	Account account = (Account)session.getAttribute("account");
%>

<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a href="/list.do" class="brand">记账系统</a>
				<ul class="nav">
					<li class="<%="main".equals(menu) ? "active" : ""%>"><a href="/list.do">首页</a></li>
					<li class="<%="total".equals(menu) ? "active" : ""%>"><a href="#">统计</a></li>
				</ul>
				<ul class="nav pull-right">
					<li class="dropdown">
						<a href="javascript:;" data-toggle="dropdown"><%=account.getUsername() %> <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="/changepwd.do">修改密码</a></li>
							<li><a href="/logout.do">安全退出</a></li>
							<li><a href="/uploadimg.do">设置头像</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</div>