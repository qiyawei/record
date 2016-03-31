package com.kaishengit.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.kaishengit.entity.Record;
import com.kaishengit.util.DpHelper;

public class RecordDao {
	public Float findYue(){
		String sql = "select yue from t_record where id = (select max(id) from t_record)";
		return DpHelper.query(sql,new ScalarHandler<Float>());
	}

	public void save(Record record) {
		String sql = "insert into t_record(creattime,money,`inout`,yue,typeid,accountid) values(?,?,?,?,?,?)";
		DpHelper.executeUpdate(sql, record.getCreattime(),record.getMoney(),
				record.getInout(),record.getYue(),record.getTypeid(),record.getAccountid());
		
	}
	public Long count(){
		String sql = "select count(*) from t_record";
		return DpHelper.query(sql, new ScalarHandler<Long>());
	}
	public Long countByInout(String inout){
		String sql = "select count(*) from t_record where `inout`=?";
		return DpHelper.query(sql, new ScalarHandler<Long>(),inout);
	}
	public Long countByInoutAndDate(String where,Object...params){
		String sql = "select count(*) from t_record as tr " + where;
		return DpHelper.query(sql, new ScalarHandler<Long>(),params);
	}
	public List<Map<String,Object>> findAll() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT tr.id,creattime,money,tr.inout,");
		sql.append("yue,typeid,accountid,tt.typename,ta.username FROM t_record AS tr ");
		sql.append("INNER JOIN t_type AS tt ON tr.typeid = tt.id ");
		sql.append("INNER JOIN t_account AS ta ON tr.accountid = ta.id ");
		sql.append("order by tr.id desc");
		return DpHelper.query(sql.toString(), new MapListHandler());
	}
	/*public List<Map<String,Object>> findAllByPage(int start,int pageSize) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT tr.id,creattime,money,tr.inout,");
		sql.append("yue,typeid,accountid,tt.typename,ta.username FROM t_record AS tr ");
		sql.append("INNER JOIN t_type AS tt ON tr.typeid = tt.id ");
		sql.append("INNER JOIN t_account AS ta ON tr.accountid = ta.id ");
		sql.append("order by tr.id desc limit ?,?");
		
		return DpHelper.query(sql.toString(), new MapListHandler(),start,pageSize);
	}
	public List<Map<String,Object>> findAllByPageAndInout(int start,int pageSize,String inout) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT tr.id,creattime,money,tr.inout,");
		sql.append("yue,typeid,accountid,tt.typename,ta.username FROM t_record AS tr ");
		sql.append("INNER JOIN t_type AS tt ON tr.typeid = tt.id ");
		sql.append("INNER JOIN t_account AS ta ON tr.accountid = ta.id ");
		sql.append("where tr.`inout`=? ");
		sql.append("order by tr.id desc limit ?,?");
		//System.out.println(456);
		return DpHelper.query(sql.toString(), new MapListHandler(),inout,start,pageSize);
	}*/
	public List<Map<String,Object>> findAllByPageAndInoutAndDate(String where,Object...params) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT tr.id,creattime,money,tr.inout,");
		sql.append("yue,typeid,accountid,tt.typename,ta.username FROM t_record AS tr ");
		sql.append("INNER JOIN t_type AS tt ON tr.typeid = tt.id ");
		sql.append("INNER JOIN t_account AS ta ON tr.accountid = ta.id ");
		sql.append(where);
		sql.append("order by tr.id desc limit ?,?");
		//System.out.println(456);
		return DpHelper.query(sql.toString(), new MapListHandler(),params);
	}
}
