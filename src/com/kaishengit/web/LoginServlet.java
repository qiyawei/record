package com.kaishengit.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kaishengit.entity.Account;
import com.kaishengit.service.AccountService;

public class LoginServlet extends HttpServlet {
	
//	public LoginServlet(){
//		System.out.println(123);
//	}
	
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
//			throws ServletException, IOException {
//		System.out.println(456);
//	}
	
	
	
	 
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
			String name = request.getParameter("username");
			String password = request.getParameter("password");
			String remember = request.getParameter("box");
			String ip = request.getRemoteAddr();
			AccountService service = new AccountService();
			Account account = service.login(name,password,ip);
			if(account != null){
				//System.out.println(123);
				if("remember".equals(remember)){
					Cookie cookie = new Cookie("name",name);
					cookie.setMaxAge(60*60*24);
					cookie.setPath("/");
					response.addCookie(cookie);
					
					Cookie cookie1 = new Cookie("pwd",password);
					cookie1.setMaxAge(60*60*24);
					cookie1.setPath("/");
					response.addCookie(cookie1);
					
				}
				
				
				
				
				
				
				HttpSession session = request.getSession();
				session.setAttribute("account", account);
				response.sendRedirect("/list.do?p=1");
			}else{
				response.sendRedirect("/index.do?state=error");
			}
			
	}
}











