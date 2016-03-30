

package com.kaishengit.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kaishengit.entity.Account;

public class LoginFilter extends AbstractFilter {
	List<String> list = new ArrayList<String>();
	String[] urls1 = null;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String config1 = filterConfig.getInitParameter("except");
		String config2 = filterConfig.getInitParameter("exceptDir");
		String[] urls = null;
		
		if(config1.contains(",")){
			urls = config1.split(",");
		}else{
			urls = new String[]{config1};
		}		
		for(String url : urls){
			list.add(url);
			//System.out.println(url);
		}
		
		if(config2.contains(",")){
			urls1 = config2.split(",");
		}else{
			urls1 = new String[]{config2};
		}
		
		
		
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse)servletResponse;
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("account");
		String url = request.getRequestURI();
		//System.out.println(url);
		
		for(String temp : urls1){
			//System.out.println(temp);
			if(url.startsWith(temp)){
				filterChain.doFilter(request, response);
				return;
			}
		}
		
		
	
		if(list.contains(url)){
			filterChain.doFilter(request, response);
		}else{
			if(account == null){
				//System.out.println(123);
				response.sendRedirect("/index.do?state=unlogin");
			}else{
				filterChain.doFilter(request, response);
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse)servletResponse;
		String url = request.getRequestURI();
		if("/".equals(url)||"/index.jsp".equals(url)||"/login.do".equals(url)||"/index.do".equals(url)){
			filterChain.doFilter(request, response);
		}else{
			HttpSession session = request.getSession();
			Account account = (Account)session.getAttribute("account");
			if(account != null){
				filterChain.doFilter(request, response);
			}else{
				response.sendRedirect("/index.do?state=unlogin");
			}*/
		//}
		
		
		
	}

}
