package com.kaishengit.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

public class EmailHelper {
	private static String host;
	private static String port;
	private static String username;
	private static String password;
	private static String from;
	private static String encode;
	
	static{
		Properties prop = new Properties();
		try {
			prop.load(EmailHelper.class.getClassLoader().getResourceAsStream("email.properties"));
			host = prop.getProperty("email.host");
			port = prop.getProperty("email.port");
			username = prop.getProperty("email.username");
			password = prop.getProperty("email.password");
			from = prop.getProperty("email.from");
			encode = prop.getProperty("email.encode");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void sendHtmlEmail(String address,String subject,String msg){
		HtmlEmail mail = new HtmlEmail();
		mail.setHostName(host);
		mail.setSmtpPort(Integer.valueOf(port));
		mail.setAuthentication(username, password);
		mail.setCharset(encode);
		mail.setSSL(true);
		mail.setTLS(true);
		
		try {
			mail.setFrom(from);
			mail.setSubject(subject);
			mail.setHtmlMsg(msg);
			mail.addTo(address);
			mail.send();
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
		public static void sendTextEmail(String address,String subject,String msg){
			SimpleEmail mail = new SimpleEmail();
			mail.setHostName(host);
			mail.setSmtpPort(Integer.valueOf(port));
			mail.setAuthentication(username, password);
			mail.setCharset("encode");
			mail.setTLS(true);
			
			try {
				mail.setFrom(from);
				mail.setSubject(subject);
				mail.setMsg(msg);
				mail.addTo(address);
				mail.send();
			} catch (EmailException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
}
