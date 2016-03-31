package com.kaishengit.util;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

public class EhcacheUtil {
//private static CacheManager cacheManager = new CacheManager();
	
	/*private Ehcache cache;
	public EhcacheUtil(String cacheName) {
		cache = cacheManager.getEhcache(cacheName);
	}
	
	public void put(String key,Object value) {
		Element element = new Element(key,value);
		cache.put(element);
	}
	
	public Object get(String key) {
		Element ele = cache.get(key);
		if(ele == null) {
			return null;
		} else {
			return ele.getObjectValue();
		}
	}
	
	public void remove(String key) {
		cache.remove(key);
	}*/
	
	private static CacheManager cacheManager = new CacheManager();
	private Ehcache cache;
	public EhcacheUtil(String ehcacheName){
		 cache = cacheManager.getEhcache(ehcacheName);			
	}
	public void put(String key,Object value){
		Element el = new Element(key,value);
		cache.put(el);
		
	}
	public Object get(String key){
		Element el = cache.get(key);
		if(el != null){
			return el.getObjectValue();
		}else{
			return null;
		}
	}
	public void remove(String key){
		cache.remove(key);
	}
}
