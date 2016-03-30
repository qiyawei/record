package com.kaishengit.util;

import java.util.HashMap;
import java.util.Map;

public class SimpleCache {
	private static Map<String,Object> cache = new HashMap<String,Object>();
	
	public static void put(String key,Object value){
		cache.put(key, value);
	}
	public static Object get(String key){
		return cache.get(key);
	}
	public static void remove(String key){
		cache.remove(key);
	}
}
