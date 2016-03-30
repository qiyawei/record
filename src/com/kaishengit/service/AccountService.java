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
	 * �û���¼ҵ��
	 * @param name �˻�
	 * @param password ����
	 * @param ip ��½IP��ַ
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
//					String subject = "��ʢ���-ϵͳ��¼��ʾ";
//					String html = "<h3>"+name+":</h3>���ղŵ�¼��ϵͳ����¼ʱ��Ϊ:" + now + ",��¼��IPΪ:" + ip + "<br/>��ʢ���";
//					
//					EmailHelper.sendHtmlEmail(account.getEmail(), "��½��Ϣ", "��ϲ����½�ɹ�");					
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
//			//�ж��û��Ƿ�����������������
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
			String subject = "�һ������ʼ�";
			String url = "http://localhost/confirm.do?r=" + random ;
			String html = "����<br>�������³����ӽ�����������<br><a href=\""+url +"\">"+url+"</a><br>������30���Ӻ�ʧЧ";
			Object[] array = new Object[2];
			array[0] = account.getId();
			array[1] = DateTime.now();
			map.put(random, array);
			EmailHelper.sendHtmlEmail(email, subject, html);
//			String random = UUID.randomUUID().toString();
//			
//			String subject = "��ʢ���-�����һ��ʼ�";
//			String url = "http://localhost/confirm.do?r=" + random;
//			String html = "���:<br>�������³����ӽ�����������:<br><a href=\""+url+"\">"+url+"</a><br>������30��������Ч";
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
//			//�ж�ʱ���Ƿ���30������
//			if(sendTime.plusMinutes(30).isBeforeNow()) {
//				//����ʱ��+30���ӣ���Ȼ�ڵ�ǰʱ��ǰ�棨���ӹ��ڣ�
//				throw new TimeOutException("�������Ч");
//			} else {
//				System.out.println(77773333);
//				
//				map.remove(r); //��������Ч
//				return id;
//			}
//			
//			
//		} 
//		throw new TimeOutException("�������Ч");
		if(map.containsKey(r)){
			//System.out.println(44444);
			Object[] array = map.get(r);
			Integer id = (Integer) array[0];
			DateTime sendTime = (DateTime)array[1];
			//DateTime sendTime = DateTime.parse(time);
			if(sendTime.plusMinutes(30).isBeforeNow()){
				throw new TimeOutException("�����ѳ�ʱ");
			}else{
				//System.out.println(777899);
				map.remove(r);
				return id;
				
			} 
		}
			throw new TimeOutException("�������Ч");
		
		
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
