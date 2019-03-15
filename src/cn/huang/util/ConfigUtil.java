package com.huang.util;
import java.io.UnsupportedEncodingException;

/**
 * 项目参数工具类
 * @author wxy
 */
public class ConfigUtil {
	
	private static final PropertiesUtil util = new PropertiesUtil("sysConfig.properties");
	
	public static final String get(String key) {
		String value = "";
		try {
			value = new String(util.readProperty(key).getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public static final String get1(String key) {
		String value = util.readProperty(key); 
		return value;
	}
}
