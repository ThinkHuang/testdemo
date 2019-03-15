/**
 * 
 */
package com.huang.other;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.huang.entity.QuestionNaire;

/**
 * @author dell
 *
 */
public class BeanToMap {

	
	public static void main(String[] args) throws Exception {
		
		QuestionNaire question = new QuestionNaire();
		Map<String,Object> map = new HashMap<String,Object>();
		beanToMap(question, map);
		
	}

	/**
	 * @param question
	 * @param map
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 */
	private static void beanToMap(Object bean,
			Map<String, Object> map) throws Exception {
		//获取对象的class对象
		Class<?> clazz = bean.getClass();
		//获取属性数组
		Field[] fields = clazz.getDeclaredFields();
		
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			String key = fields[i].getName();
			Object value = fields[i].get(bean);
			map.put(key, value);
		}
		
		for (Iterator<?> it = map.keySet().iterator(); it.hasNext();) {
			String key = (String) it.next();
			Object value = map.get(key);
			
			System.out.println("key:" + key + " value:" + value);
		}
	}
}
