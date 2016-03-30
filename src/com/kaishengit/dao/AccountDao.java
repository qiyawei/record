package com.kaishengit.dao;

import org.apache.commons.dbutils.handlers.BeanHandler;

import com.kaishengit.entity.Account;
import com.kaishengit.util.DpHelper;
import com.kaishengit.util.EhcacheUtil;
import com.kaishengit.util.SimpleCache;

public class AccountDao {
	private EhcacheUtil ehcache = new EhcacheUtil("account");
	public Account findByUsername(String username){
		Account account = (Account)ehcache.get("account:" + username);
		if(account == null){
			
			String sql = "select *from t_account where username=?";
			account =  DpHelper.query(sql, new BeanHandler<Account>(Account.class), username);
			ehcache.put("account:"+username, account);
			return account;
		}else{
			return account;
		}
	}

	public void update(Account account) {
		SimpleCache.remove("user:" + account.getUsername());
		String sql = "update t_account set username = ?,password=?,lastlogintime=?,lastloginip=?,email=?,img=? where id = ?";
		DpHelper.executeUpdate(sql, account.getUsername(),account.getPassword(),account.getLastlogintime(),account.getLastloginip(),account.getEmail(),account.getImg(),account.getId());
		
	}

	public Account findByEmail(String email) {
		String sql = "select * from t_account where email=?";
		return DpHelper.query(sql, new BeanHandler<Account>(Account.class), email);
		
	}

	public Account findById(Integer id) {
		String sql = "select *from t_account where id=?";
		return DpHelper.query(sql, new BeanHandler<Account>(Account.class), id);
	}
}
