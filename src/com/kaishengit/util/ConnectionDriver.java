package com.kaishengit.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

public class ConnectionDriver {
	
	private static BasicDataSource dataSource = new BasicDataSource();
	private static String Driver;
	private static String url;
	private static String username ;
	private static String password ;
	static{
		
		Properties prop = new Properties();
		try {
			prop.load(ConnectionDriver.class.getClassLoader().getResourceAsStream("db.properties"));
			Driver = prop.getProperty("jdbc.driver");
			url = prop.getProperty("jdbc.url");
			username = prop.getProperty("jdbc.username");
			password = prop.getProperty("jdbc.password");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		/*dataSource.setDriverClassName(Driver);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setInitialSize(8);
		dataSource.setMaxActive(15);
		dataSource.setMaxWait(50000);*/
	}
	
//	public static DataSource getDataSource(){
//		return dataSource;
//	}	
//		
	public static Connection getConnection(){
		
		/*try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new DateException("Êý¾Ý¿âÒì³£",e);
			//e.printStackTrace();
		}*/
	
		
		
		try {
			Class.forName(Driver);
			Connection conn = DriverManager.getConnection(url,username,password);
			return conn;
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		return null;
	}
	
}
