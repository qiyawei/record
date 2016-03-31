package com.kaishengit.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

import com.google.gson.Gson;
import com.kaishengit.entity.Account;
import com.kaishengit.service.AccountService;

@WebServlet("/imgcallback.do")
public class ImgCallBackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uploadRet = request.getParameter("upload_ret");
		uploadRet = new String(Base64.decodeBase64(uploadRet));
		Gson gson = new Gson();
		Map<String,String> map = gson.fromJson(uploadRet, HashMap.class);
		String key = map.get("key");
		String id = map.get("x:id");
		Account account = (Account)request.getSession().getAttribute("account");
		account.setImg(key);
		AccountService service = new AccountService();
		service.setAccountImg(id,key);
		response.sendRedirect("/uploadimg.do");
	}

}
