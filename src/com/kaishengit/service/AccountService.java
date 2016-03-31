package com.kaishengit.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;

import com.kaishengit.dao.AccountDao;
import com.kaishengit.entity.Account;
import com.kaishengit.exception.TimeOutException;
import com.kaishengit.util.EmailHelper;

public class AccountService {
	AccountDao dao = new AccountDao();
	private final String SALT ="bifbiervberbvierv";
	private static Map<String,Object[]> map = new HashMap<String,Object[]>();
	/**
	 * 用户登录业务
	 * @param name 账户
	 * @param password 密码
	 * @param ip 登陆IP地址
	 */
	public Account login(final String name, String password,final String ip) {
		
		
		//String code = DigestUtils.md5Hex(SALT + password);
		//System.out.println(code);
		final Account account = dao.findByUsername(name);
		
		long time = System.currentTimeMillis();
		Date date = new Date(time);
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
		final String now = sdf.format(date);
		
		
		if(account != null&&password.equals(account.getPassword())){
			
			
			account.setLastloginip(ip);
			account.setLastlogintime(now);
			dao.update(account);
//			Thread thread = new Thread(new Runnable() {
//				
//				@Override
//				public void run() {
//					String subject = "凯盛软件-系统登录提示";
//					String html = "<h3>"+name+":</h3>您刚才登录了系统，登录时间为:" + now + ",登录的IP为:" + ip + "<br/>凯盛软件";
//					
//					EmailHelper.sendHtmlEmail(account.getEmail(), "登陆信息", "恭喜您登陆成功");					
//				}
//			});
//			thread.start();
			
			return account;
		}else{
			return null;
		}
		
		
		
	}

	public boolean changePassword(String oldPassword, String newPassword,
			Account account) {
		if(account.getPassword().equals(oldPassword)) {
			account.setPassword(newPassword);
			dao.update(account);
			return true;
		}
		return false;
	}

	public void sengForgetEail(String email) {
		Account account = dao.findByEmail(email);
//		if(account != null) {
//			
//			//判断用户是否有重置密码的随机数
//			String key = null;
//			for(Entry<String, Object[]> entry : map.entrySet()) {
//				Integer id = (Integer) entry.getValue()[0];
//				if(id.equals(account.getId())) {
//					key = entry.getKey();
//					break;
//				}
//			}
//			
//			if(key != null) {
//				map.remove(key);
//			}
			
		if(account != null){
			for(String key : map.keySet()){
				Integer id = (Integer)map.get(key)[0];
				if(id.equals(account.getId())){
					map.remove(key);
					//System.out.println(444);
				}
			}
			String random = UUID.randomUUID().toString();
			String subject = "找回密码邮件";
			String url = "http://localhost/confirm.do?r=" + random ;
			String html = "您好<br>请点击以下超链接进行密码重置<br><a href=\""+url +"\">"+url+"</a><br>该链接30分钟后失效";
			Object[] array = new Object[2];
			array[0] = account.getId();
			array[1] = DateTime.now();
			map.put(random, array);
			EmailHelper.sendHtmlEmail(email, subject, html);
//			String random = UUID.randomUUID().toString();
//			
//			String subject = "凯盛软件-密码找回邮件";
//			String url = "http://localhost/confirm.do?r=" + random;
//			String html = "你好:<br>请点击以下超链接进行密码重置:<br><a href=\""+url+"\">"+url+"</a><br>该链接30分钟内有效";
//			
//			Object[] array = new Object[2];
//			array[0] = account.getId();
//			array[1] = DateTime.now();
//			map.put(random, array);
//			EmailHelper.sendHtmlEmail(email, subject, html);

			
			
			
		}
		
	}

	public Integer validataForgetUrl(String r) {
//		if(map.containsKey(r)) {
//			System.out.println(5555555);
//			Object[] array = map.get(r);
//			Integer id = (Integer) array[0];
//			DateTime sendTime = (DateTime) array[1];
//			
//			//判断时间是否在30分钟内
//			if(sendTime.plusMinutes(30).isBeforeNow()) {
//				//发送时间+30分钟，依然在当前时间前面（链接过期）
//				throw new TimeOutException("随机码无效");
//			} else {
//				System.out.println(77773333);
//				
//				map.remove(r); //让链接无效
//				return id;
//			}
//			
//			
//		} 
//		throw new TimeOutException("随机码无效");
		if(map.containsKey(r)){
			//System.out.println(44444);
			Object[] array = map.get(r);
			Integer id = (Integer) array[0];
			DateTime sendTime = (DateTime)array[1];
			//DateTime sendTime = DateTime.parse(time);
			if(sendTime.plusMinutes(30).isBeforeNow()){
				throw new TimeOutException("链接已超时");
			}else{
				//System.out.println(777899);
				map.remove(r);
				return id;
				
			} 
		}
			throw new TimeOutException("随机码无效");
		
		
	}

	public void resetPassword(Integer id, String pwd) {
		Account account = dao.findById(id);
		pwd = DigestUtils.md5Hex(pwd);
		account.setPassword(pwd);
		dao.update(account);
	}

	public void setAccountImg(String id, String key) {
		Account account = dao.findById(Integer.valueOf(id));
		account.setImg(key);
		dao.update(account);
		
	}

}
