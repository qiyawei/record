package com.kaishengit.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Account;
import com.kaishengit.entity.Type;
import com.kaishengit.service.RecordService;

public class NewServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type");
		RecordService service = new RecordService();
		List<Type> typeList;
		if("in".equals(type)){
			typeList = service.getInTypeList();
			request.setAttribute("type", "in");
		}else if("out".equals(type)){
			typeList = service.getOutTypeList();
			request.setAttribute("type", "out");
		}else{
			response.sendError(404);
			return;
		}
		request.setAttribute("typeList", typeList);
		request.getRequestDispatcher("/WEB-INF/views/new.jsp").forward(request, response);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Account account = (Account)request.getSession().getAttribute("account");
		String typeid = request.getParameter("typeid");
		String inout = request.getParameter("inout");
		String money = request.getParameter("money");
		String message = request.getParameter("message");
		RecordService service = new RecordService();
		service.saveRecord(typeid,money,message,inout,account);
		response.sendRedirect("/list.do");
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
