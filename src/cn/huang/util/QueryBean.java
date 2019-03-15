package com.huang.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 查询实体映射
 * @author Faker
 *
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface QueryBean {

	/**
	 * 值类型
	 * @return
	 */
	public int valueType() default STRING;
	
	
	/**
	 * 匹配类型
	 * @return
	 */
	public int equalsType() default EQUALS;
	
	
	
	public int STRING = 1;
	public int INTEGER = 2;
	public int DOUBLE = 3;
	public int DATE = 4;
	
	/**
	 * 完全匹配
	 */
	public int EQUALS = 5;
	
	/**
	 * 模糊匹配
	 */
	public int LIKE = 6;
	
	
	
}
