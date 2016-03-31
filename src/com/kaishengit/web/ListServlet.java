package com.kaishengit.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Pager;
import com.kaishengit.service.RecordService;

public class ListServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String p = request.getParameter("p");//当前页数
		String inout = request.getParameter("inout");//表单提交的搜索条件
		String creattime = request.getParameter("creattime");
		int pageNo = 1;
		if(StringUtils.isNumeric(p)){
			pageNo = Integer.parseInt(p);
		}
		//List<Map<String,Object>> recordList = new RecordService().findAllRecord();
		//List<Map<String,Object>> recordList = new RecordService().findAllRecordByPage(pageNo);
		//request.setAttribute("recordList", recordList);
		//Pager pager = new RecordService().findRecordByPage(pageNo);
		//Pager<Map<String, Object>>  pager = new RecordService().findRecordByPageAndInout(pageNo, inout);
		Pager<Map<String, Object>>  pager = new RecordService().findRecordByPageAndInoutAndDate(pageNo, inout,creattime);

		request.setAttribute("pager", pager);
		request.getRequestDispatcher("/WEB-INF/views/main2.jsp").forward(request, response);
	}

}
