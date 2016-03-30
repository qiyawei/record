package com.kaishengit.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Account;
import com.kaishengit.service.AccountService;

public class IndexServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {
		
		Cookie[] cookies = req.getCookies();
		String name = null;
		String pwd = null ;
		if(cookies != null){
			for(Cookie cookie : cookies){
				if("name".equals(cookie.getName())){
					 name = cookie.getValue();
				}else if("pwd".equals(cookie.getName())){
					pwd = cookie.getValue();
				}
			}	
		}
		if(name != null&&pwd != null){
			String ip = req.getRemoteAddr();
			AccountService service = new AccountService();
			Account account = service.login(name, pwd, ip);
			if(account != null){
				req.getSession().setAttribute("account", account);
				response.sendRedirect("/list.do?p=1");
				return;
			}
		}
		
		
		req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, response);
	}

}
