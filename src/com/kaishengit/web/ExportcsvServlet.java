package com.kaishengit.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.service.RecordService;

public class ExportcsvServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/csv;charset=GBK");
		response.addHeader("Content-Disposition", "attachment;filename=\"export.csv\"");
		RecordService service = new RecordService();
		List<Map<String,Object>> list = service.findAllRecord();
		PrintWriter writer = response.getWriter();//new PrintWriter(response.getOutputStrea)
		writer.println("日期,类别,收入,支出,余额,记账人");
		for(Map<String,Object> map : list){
			if(map.get("inout").equals("in")){
				writer.println(map.get("creattime")+","+map.get("typename")+","+map.get("money")+","+","+map.get("yue")+","+map.get("username"));
			}if(map.get("inout").equals("out")){
				writer.println(map.get("creattime")+","+map.get("typename")+","+","+map.get("money")+","+map.get("yue")+","+map.get("username"));
			}
		
		}
		
		writer.flush();
		writer.close();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
