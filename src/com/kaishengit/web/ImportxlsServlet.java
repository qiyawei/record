package com.kaishengit.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.kaishengit.entity.Type;
import com.kaishengit.service.RecordService;



public class ImportxlsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/importxls.jsp").forward(req, resp);
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RecordService service = new RecordService();
		if(ServletFileUpload.isMultipartContent(req)){
			ServletContext application = req.getServletContext();
			File repository = (File)application.getAttribute("javax.servlet.context.tempdir");
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(1024);
			factory.setRepository(repository);
			ServletFileUpload fileUpload = new ServletFileUpload(factory);
			fileUpload.setSizeMax(1024*1024*20);
			try {
				List<FileItem> items =  fileUpload.parseRequest(req);
				for(FileItem item : items){
					if(!item.isFormField()){
						InputStream in = req.getInputStream();
						Workbook wb = new HSSFWorkbook(in);
						Sheet sheet = wb.getSheetAt(0);
						int maxRowNumber = sheet.getLastRowNum();
						for(int i = 0;i <= maxRowNumber;i++){
							Type type = new Type();
							Row row = sheet.getRow(i);
							int maxCellNumber = row.getLastCellNum();
							for(int j = 0;i < maxCellNumber;i++){
								Cell cell = row.getCell(j);
								if(j ==  1){
									
									String cellValue = cell.getStringCellValue();
									cellValue = "ÊÕÈë".equals(cellValue)?"in":"out";
									type.setInout(cellValue);
										
									}else if(j == 2) {
										String name = cell.getStringCellValue();
										type.setTypename(name);
									}
								}
								service.saveType(type);
							}
						}
					}
				
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
