package com.kaishengit.dao;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.kaishengit.entity.Type;
import com.kaishengit.util.DpHelper;

public class TypeDao {
	public List<Type> findByType(String type){
		String sql = "select *from t_type where `inout` = ?";
		return DpHelper.query(sql,new BeanListHandler<Type>(Type.class), type);
	}
	public void save(Type type) {
		String sql = "insert into t_type(typename,`inout`) values(?,?)";
		DpHelper.executeUpdate(sql, type.getTypename(),type.getInout());
	}
}
