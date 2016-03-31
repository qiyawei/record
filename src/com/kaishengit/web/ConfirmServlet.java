package com.kaishengit.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.service.AccountService;

public class ConfirmServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String r = request.getParameter("r");
		//System.out.println(r);
		if(StringUtils.isNotEmpty(r)){
			AccountService service = new AccountService();
			try {
				Integer id = service.validataForgetUrl(r);
				request.setAttribute("userid", id);
				request.getRequestDispatcher("/WEB-INF/views/newpwd.jsp").forward(request, response);
				
			} catch (Exception e) {
				String msg = e.getMessage();
				request.setAttribute("errorMsg", msg);
				request.getRequestDispatcher("/WEB-INF/views/confirmerror.jsp").forward(request, response);
			}
		}else{
			response.sendError(404);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("id");
		System.out.println(77777);
		String pwd = req.getParameter("pwd");
		AccountService service = new AccountService();
		service.resetPassword( Integer.valueOf(id),pwd);
		resp.sendRedirect("/index.do?state=reset");
		
	}

}
