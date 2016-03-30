package com.kaishengit.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import com.kaishengit.dao.RecordDao;
import com.kaishengit.dao.TypeDao;
import com.kaishengit.entity.Account;
import com.kaishengit.entity.Pager;
import com.kaishengit.entity.Record;
import com.kaishengit.entity.Type;

public class RecordService {
	private static final String Record = null;
	TypeDao dao = new TypeDao();
	RecordDao recordDao = new RecordDao();
	public List<Type> getInTypeList(){
		return dao.findByType("in");
	}
	public List<Type> getOutTypeList(){
		return dao.findByType("out");
	}
	public void saveRecord(String typeid, String money, String message,
			String inout, Account account) {
		Record record = new Record();
		
		Float yue = recordDao.findYue();
		//System.out.println(yue);
		yue = yue == null ? 0F : yue;
		if("in".equals(inout)){
			record.setYue(yue + Float.valueOf(money));
		}else if("out".equals(inout)){
			record.setYue(yue - Float.valueOf(money));
		}

		long time = System.currentTimeMillis();
		Date date = new Date(time);
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
		String now = sdf.format(date);
		record.setCreattime(now);
		record.setMoney(Float.valueOf(money));
		record.setInout(inout);
		record.setTypeid(Integer.valueOf(typeid));
		record.setAccountid(account.getId());
		recordDao.save(record);
		
		
	}
	
	public List<Map<String,Object>> findAllRecord(){
		return recordDao.findAll();
	}
	
	
	public void saveType(Type type) {
		dao.save(type);
	}
/*	public List<Map<String, Object>> findAllRecordByPage(int pageNo) {
		int pageSize = 3;
		if(pageNo < 1){
			pageNo = 1;
		}
		Long totalSize = recordDao.count();
		Long totalPages = totalSize/pageSize;
		if(totalSize % pageSize != 0){
			totalPages++;
		}
		if(pageNo > totalPages){
			pageNo = new Long(totalPages).intValue();
		}
		int start = (pageNo-1)*3;
		return recordDao.findAllByPage(start, pageSize);
	}*/
/*	public Pager findRecordByPage(int pageNo) {
		Long pageSize = 3L;
		Long totalSize = recordDao.count();
		Pager<Map<String, Object>> pager = new Pager<Map<String, Object>>(new Long(pageNo),pageSize,totalSize);
		List<Map<String, Object>> list = recordDao.findAllByPage(pager.getStart().intValue(), pager.getPageSize().intValue());
		pager.setItems(list);
				
		return pager;
	}
	
	
	public Pager<Map<String, Object>> findRecordByPageAndInout(int pageNo,String inout) {
		//System.out.println(789);
		Long pageSize = 3L;
		Long totalSize = recordDao.count();
		List<Map<String, Object>> list;
		Pager<Map<String, Object>> pager = null;
		if("in".equals(inout) || "out".equals(inout)){
			totalSize = recordDao.countByInout(inout);
			pager = new Pager<Map<String, Object>>(new Long(pageNo),pageSize,totalSize);
			list = recordDao.findAllByPageAndInout(pager.getStart().intValue(), pager.getPageSize().intValue(),inout);
			
		}else{
			pager = new Pager<Map<String, Object>>(new Long(pageNo),pageSize,totalSize);
			list = recordDao.findAllByPage(pager.getStart().intValue(), pager.getPageSize().intValue());

		}
		
		pager.setItems(list);
				
		return pager;
	}*/
	public Pager<Map<String, Object>> findRecordByPageAndInoutAndDate(int pageNo, String inout, String creattime) {
		//System.out.println(pageNo);
		StringBuilder where = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		if("in".equals(inout) || "out".equals(inout)){
			where.append(" tr.`inout`=?");
			params.add(inout);
		}
		if(StringUtils.isNotEmpty(creattime)){
			where.append(" and creattime >= ? and creattime <=? ");
			DateTime now = new DateTime();
			String today = now.toString("yyyy-MM-dd HH:mm:ss");
			String before = null;
			if("3days".equals(creattime)){
				before = now.minusDays(3).toString("yyyy-MM-dd HH:mm:ss");
				
			}else if("7days".equals(creattime)){
				before = now.minusDays(7).toString("yyyy-MM-dd HH:mm:ss");
				
			}else if("30days".equals(creattime)){
				before = now.minusDays(30).toString("yyyy-MM-dd HH:mm:ss");
				
			}else if("ago".equals(creattime)){
				today = now.minusDays(30).toString("yyyy-MM-dd HH:mm:ss");
				before = now.minusDays(10000).toString("yyyy-MM-dd HH:mm:ss");	
			}
			params.add(before);
			params.add(today);
		}
		String sql = where.toString();
		if(sql.startsWith(" and ")){
			sql = sql.substring(4);
		}
		if(StringUtils.isNotEmpty(sql)){
			sql = "where" + sql; 
		}
		
		Long pageSize = 3L;
		Object[] objects = params.toArray();
		Long totalSize = recordDao.countByInoutAndDate(sql, objects);
		
		Pager<Map<String, Object>> pager = new Pager<Map<String, Object>>(new Long(pageNo),pageSize,totalSize);
		params.add(pager.getStart());
		params.add(pager.getPageSize());
	    objects = params.toArray();
	    //System.out.println(totalSize +":"+ pager.getStart());
		List<Map<String, Object>> list =(List<Map<String, Object>>)recordDao.findAllByPageAndInoutAndDate( sql, objects);
		pager.setItems(list);
				
		return pager;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
