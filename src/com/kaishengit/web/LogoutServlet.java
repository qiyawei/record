package com.kaishengit.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kaishengit.entity.Account;

public class LogoutServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("account");
		Cookie cookie = new Cookie("name",account.getUsername());
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
		
		Cookie cookie1 = new Cookie("pwd",account.getPassword());
		cookie1.setMaxAge(0);
		cookie1.setPath("/");
		response.addCookie(cookie1);
		request.getSession().invalidate();
		response.sendRedirect("index.do?state=logout");
	}

}
