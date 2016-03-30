package com.kaishengit.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Account;
import com.kaishengit.service.AccountService;

public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/changepwd.jsp").forward(request, resp);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String oldPassword = request.getParameter("oldpassword");
		String newPassword = request.getParameter("newpassword");
		
		Account account = (Account) request.getSession().getAttribute("account"); 
		
		AccountService service = new AccountService();
		boolean isOk = service.changePassword(oldPassword,newPassword,account);
	
		if(isOk) {
			response.sendRedirect("/index.do?state=changepwd");
		} else {
			response.sendRedirect("/changepwd.do?state=error");
		}
	
	}

}
