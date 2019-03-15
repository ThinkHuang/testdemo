package com.huang.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 
 * @author guxiaqing
 * @date 2016-08-02 13:50:12
 *
 */
public class ReflectionUtil {

	private static final Logger logger = Logger.getLogger(ReflectionUtil.class);
	
	
	private static final String SERIAL_VERSION_UID = "serialVersionUID";
	
	/** 
     * Converts a map to a JavaBean. 
     *  
     * @param type type to convert 
     * @param map map to convert 
     * @return JavaBean converted 
     * 
     */  
    public static final Object objToBean(Class<? extends Serializable> type, Map<String, ? extends Object> map){  
        logger.info("------objToBean------");
    	Object obj = null;
		try {
			obj = getInstance(type);
			PropertyDescriptor[] propertyDescriptors = descriptor(type); 
			PropertyDescriptor descriptor = null;
			String propertyName = "";
			Object value = null;
			Object[] args = null;
			for (int i = 0,len = propertyDescriptors.length; i< len; i++) {  
			    descriptor = propertyDescriptors[i];  
			    propertyName = descriptor.getName();  
			    if (map.containsKey(propertyName)) {  
			        value = map.get(propertyName);  
			        args = new Object[1];  
			        args[0] = value;  
			        descriptor.getWriteMethod().invoke(obj, args);  
			    }  
			}
		} catch (IllegalArgumentException e) {
			logger.error("IllegalArgumentException:", e);
		} catch (InvocationTargetException e) {
			logger.error("InvocationTargetException:", e);
		} catch (IllegalAccessException e) {
			logger.error("IllegalAccessException:", e);
		}
        return obj;  
    }  
      
    
    /** 
     * Converts a JavaBean to a map 
     *  
     * @param bean JavaBean to convert 
     * @return map converted 
     * 
     */  
    public static final Map<String, Object> beanToMap(Object bean){  
    	logger.info("------beanToMap------");
        Map<String, Object> returnMap = new HashMap<String, Object>();  
        Class<? extends Object> cls = bean.getClass();
        List<Field> fields = getFields(cls);
        
        try {
			PropertyDescriptor[] propertyDescriptors = descriptor(cls);
			PropertyDescriptor descriptor = null;
			String propertyName = "";
			Method readMethod = null;
			Object result = null;
			for (int i = 0,len = propertyDescriptors.length; i< len; i++) {  
			    descriptor = propertyDescriptors[i];  
			    propertyName = descriptor.getName();  
			    if (!"class".equals(propertyName)) {  
			        readMethod = descriptor.getReadMethod();  
			        result = readMethod.invoke(bean, new Object[0]);  
			        if (result != null &&!"".equals(result.toString().trim())) {
			        	for (Field f : fields) {
			        		//需要进行模糊匹配的属性和需要查询的属性名称相同
							if(propertyName.equals(f.getName())){
								QueryBean annotation = f.getAnnotation(QueryBean.class);
								switch(annotation.equalsType()){
									case QueryBean.LIKE:returnMap.put(propertyName, "%"+result+"%");break;
									case QueryBean.EQUALS:returnMap.put(propertyName, result);break;
								    default: break;
								}
							}
						}
			        	
			        	
			           
			        } /*else {  
			            returnMap.put(propertyName, "");  
			        }  */
			    }  
			}
		} catch (IllegalAccessException e) {
			logger.error("IllegalAccessException:", e);
		}  catch (InvocationTargetException e) {
			logger.error("InvocationTargetException:", e);
		} 
        return returnMap;  
    }  
    
 
    
    /**
     * 获取属性描述器
     * @param <T>
     * @param bean
     * @return
     */
    private static <T> PropertyDescriptor[] descriptor(Class<T> bean){
    	BeanInfo beanInfo = null;
    	PropertyDescriptor[] propertyDescriptors = null;
		try {
			beanInfo = Introspector.getBeanInfo(bean);
			propertyDescriptors = beanInfo.getPropertyDescriptors();  
		} catch (IntrospectionException e) {
			logger.error("IntrospectionException:", e);
		}  
		 return propertyDescriptors;
    }
    
    
    /**
     * 反射创建对象
     * @param <T>
     * @param type
     * @return
     */
    public static <T> T getInstance(Class<T> type){
    	T newInstance = null;
		try {
			newInstance = type.newInstance();
		} catch (InstantiationException e) {
			logger.error("InstantiationException:", e);
		} catch (IllegalAccessException e) {
			logger.error("IllegalAccessException:", e);
		}
    	return newInstance;
    	
    }
    
	
	
	/**
	 * 获取指定对象的所有属性值
	 * @param c
	 * @return
	 */
	public static <T> List<Field> getFields(Class<T> c){
		if(c == null){return null;}
		List<Field> listFields = new ArrayList<Field>();
		Collections.addAll(listFields, c.getDeclaredFields());
		Iterator<Field> iterator = listFields.iterator();
		Field next = null;
		while(iterator.hasNext()){
			 next = iterator.next();
			 if(SERIAL_VERSION_UID.equals(next.getName()) || next.getAnnotation(QueryBean.class) ==null){
				 iterator.remove();
				
			 }
		}
		return listFields;
		
	}
    
    
}
