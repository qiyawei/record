package com.kaishengit.util;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import com.kaishengit.exception.DateException;

public class DpHelper {

	public static void executeUpdate(String sql,Object...params) {
		QueryRunner runner = new QueryRunner();//(ConnectionDriver.getDataSource());
		try {
			runner.update(ConnectionDriver.getConnection(),sql,params);
		} catch (SQLException e) {
			throw new DateException("数据库异常",e);
			//e.printStackTrace();
		}
	}
	
	public static <T> T query(String sql,ResultSetHandler<T> handler,Object... params) {
		QueryRunner runner = new QueryRunner();
		try {
			return runner.query(ConnectionDriver.getConnection(), sql, handler, params);
		} catch (SQLException e) {
			
			throw new DateException("执行sql异常[" + sql +"]",e);
//			e.printStackTrace();
//			return null;
		}
	}
	
	
	
} 