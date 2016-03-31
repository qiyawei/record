package com.kaishengit.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.IOUtils;
import org.joda.time.DateTime;

public class BackDbServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("GBK");
		
		//设置下载项
		response.setContentType("text/plain;charset=GBK");
		//设置下载对话框
		String fileName = DateTime.now().toString("yyyy-MM-dd")+"-db.sql";
		response.addHeader("Content-Disposition", 
				"attachment; filename=\""+fileName+"\"");
		String comand = "mysqldump -uroot -p123456 --default-character-set=gbk demo";
		Runtime runtime = Runtime.getRuntime();
		Process process = runtime.exec(comand);
		InputStream in = process.getInputStream();
		OutputStream out = response.getOutputStream();
		IOUtils.copy(in, out);
		out.flush();
		out.close();
		in.close();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
