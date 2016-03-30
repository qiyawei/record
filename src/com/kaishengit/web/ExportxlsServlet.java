package com.kaishengit.web;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.kaishengit.service.RecordService;

public class ExportxlsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/vnd.ms-excel");
		response.addHeader("Content-Disposition", "attachment;filename=\"export.xls\"");
		RecordService service = new RecordService();
		List<Map<String,Object>> list = service.findAllRecord();
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet("记录表");
		Row rowtitle = sheet.createRow(0);
		Cell c1 = rowtitle.createCell(0);
		Cell c2 = rowtitle.createCell(1);
		Cell c3 = rowtitle.createCell(2);
		Cell c4 = rowtitle.createCell(3);
		Cell c5 = rowtitle.createCell(4);
		Cell c6 = rowtitle.createCell(5);
		c1.setCellValue("日期");
		c2.setCellValue("类别");
		c3.setCellValue("收入");
		c4.setCellValue("支出");
		c5.setCellValue("余额");
		c6.setCellValue("操作人");
	for(int i = 0;i < list.size();i++ ){
			Row rowdata = sheet.createRow(i+1);
			Cell c11 = rowdata.createCell(0);
			Cell c22= rowdata.createCell(1);
			Cell c33= rowdata.createCell(2);
			Cell c44= rowdata.createCell(3);
			Cell c55= rowdata.createCell(4);
			Cell c66= rowdata.createCell(5);
			c11.setCellValue((String) list.get(i).get("creattime"));
			c22.setCellValue((String)list.get(i).get("typename"));
			if(list.get(i).get("inout").equals("in")){
				
				c33.setCellValue( (Float)list.get(i).get("money"));
				c44.setCellValue("");
			}else if(list.get(i).get("inout").equals("out")){

				c33.setCellValue("");
				c44.setCellValue((Float)list.get(i).get("money"));
			}
			c55.setCellValue((Float) list.get(i).get("yue"));
			c66.setCellValue((String) list.get(i).get("username"));
		}
		OutputStream out = response.getOutputStream();
		wb.write(out);
		out.flush();
		out.close();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
