package com.kaishengit.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

public class UploadImgServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Auth auth = Auth.create("sGxR6n5CfLUZuMrpw1ilQm79pvIRqUZQ5h1gqSwF", "yNIN4pNkMCd-MHAPHVqZ3LxcvlUuO-4-GM9ZQiit");
		StringMap map = new StringMap();
		map.put("returnUrl","http://localhost/imgcallback.do");
		String uploadToken = auth.uploadToken("kaishengitdemo",null,3600,map);
		request.setAttribute("uploadToken", uploadToken);
		request.getRequestDispatcher("/WEB-INF/views/img.jsp").forward(request, response);
		
	}
}