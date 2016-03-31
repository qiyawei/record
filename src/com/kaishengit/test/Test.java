package com.kaishengit.test;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;


public class Test {

	public static void main(String[] args) throws Exception {
		//文本邮件
//		SimpleEmail mail = new SimpleEmail();
//		mail.setHostName("smtp.qq.com");
//		mail.setSmtpPort(465);
//		mail.setCharset("UTF-8");
//		mail.setAuthentication("1318717894@qq.com", "qyw1109");
//		mail.setTLS(true);
//		
//		
//		mail.setFrom("1318717894@qq.com");
//		mail.setSubject("练习发邮件");
//		mail.setMsg("你好啊，小伙子");
//		mail.addTo("956342117@qq.com");
//		
//		mail.send();
		
		//HTML邮件
		//tpxcxzwicljgbebf
		HtmlEmail mail1 = new HtmlEmail();
		
		
		mail1.setHostName("smtp.qq.com");
		mail1.setSmtpPort(465);
		mail1.setCharset("UTF-8");
		mail1.setAuthentication("956342117", "tpxcxzwicljgbebf");
		mail1.setTLS(true);
		mail1.setSSL(true);
		
		
		mail1.setFrom("956342117@qq.com");
		mail1.setSubject("邮件来了");
		mail1.setHtmlMsg("<h1 style=\"color:red\">你好啊，亲</h1>");
		mail1.addTo("541247826@qq.com");
		
		mail1.send();
		//带附件邮件
		
//		MultiPartEmail mail1 = new MultiPartEmail();
//		
//		
//		mail1.setHostName("smtp.qq.com");
//		mail1.setSmtpPort(465);
//		mail1.setCharset("UTF-8");
//		mail1.setAuthentication("956342117@qq.com", "tpxcxzwicljgbebf");
//		mail1.setTLS(true);
//		
//		
//		mail1.setFrom("9563421174@qq.com");
//		mail1.setSubject("邮件来了");
//		mail1.setMsg("<h1 style=\"color:red\">你好啊，亲</h1>");
//		mail1.addTo("1318717894@qq.com");
//		
//		EmailAttachment attachment = new EmailAttachment();
//		attachment.setPath("");
//		mail1.attach(attachment);
//		
//		mail1.send();
//		
		
		
		
		
		
		
		
		
		
		
//		Properties prop = new Properties();
//		//prop.load(new FileInputStream("d:/db.properties"));
//		prop.load(Test.class.getClassLoader().getResourceAsStream("db.properties"));
//		String driver = prop.getProperty("jdbc.driver");
//		System.out.println(driver);
		
	}

}
