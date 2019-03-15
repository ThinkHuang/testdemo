/**
 * 
 */
package com.huang.demo;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

import com.huang.demo.Meta_AnnotationDemo.FruitColor.Color;

/**
 * <p>Project:test
 * <p>Package:com.huang.demo
 * <p>Title:Meta_AnnotationDemo
 * <p>Description:进行元注解的测试
 * <p>Company:ShangCheng Tech
 * @author huangyejun
 * Create Date:2017-12-16 下午03:16:08
 * @version
 */
public class Meta_AnnotationDemo {
	
	/*
	 * 测试@Target元注解，用于描述其作用对象是什么
	 * 疑问：该注解的作用是什么？怎么对对象起作用？
	 */
	@Target(ElementType.TYPE)
	@interface Table{
		/**
		 * 包含的属性值有：
		 * 1、CONSTRUCTOR：用户描述构造器
		 * 2、FIELD：用于描述成员变量，
		 * 3、LOCAL_VARIABLE：用于描述局部变量
		 * 4、METHOD：用于描述方法
		 * 5、PACKAGE：用于描述包
		 * 6、PARAMETER：用来描述参数
		 * 7、TYPE：用于描述类，接口(包括注解类型)或enum类型说明
		 */
	}
	@Target(ElementType.FIELD)//NoDBColumn只能作用于成员变量
	@interface NoDBColumn{
		
	}
	
	
	/*
	 * 测试@Retention元注解，用于说明该注解生命周期(对JVM来说)
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@interface Column{
		/**
		 * 包含的属性值有：
		 * 1、SOURCE：在源文件中有效(即原文件保留)
		 * 2、CLASS：在class文件中有效
		 * 3、RUNTIME：在运行期有效
		 */
	}
	
	/*
	 * 测试@Documented：对外形成API的说明，就是可以文档化，当使用javadoc是可以将使用该注解的对象文档化
	 */
	@Documented
	@interface TestDoc{
		
	}
	
	/*
	 * 测试@Inherited注解，用于说明某个被标注的类型是被继承的,例如，使用了该注解后，在子类中就能直接使用父类的所有注解了
	 * 以下面的例子来说例如A类使用了可继承的注解，那么在B类中就可以直接使用A中的注解了
	 */
	@Target(ElementType.TYPE)
	@Retention(RetentionPolicy.RUNTIME)
	@Inherited
	@interface Greeting{
		public enum COLOR{RED, BLUE, GREEN}
	}
	
	@Greeting
	class A{
		
	}
	
	class B extends A{
		
	}
	
	
	/**
	 * 下面开始进行自定义注解的测试
	 * -----------------------------------------------------
	 */
	@Target(ElementType.FIELD)
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	public @interface FruitColor{
		/**
	     * 颜色枚举
	     * @author peida
	     *
	     */
	    public enum Color{ BULE,RED,GREEN};
	    
	    /**
	     * 颜色属性
	     * @return
	     */
	    Color fruitColor() default Color.GREEN;
	    
	    String value() default "";

	}
	
	@Target(ElementType.FIELD)
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	public @interface FruitName{
		String value() default "";
	}
	
	static class Apple {

		@FruitName("apple")
		private String appleName;
		
		@FruitColor(fruitColor=Color.RED)
		private String appleColor;

		public String getAppleName() {
			return appleName;
		}

		public void setAppleName(String appleName) {
			this.appleName = appleName;
		}

		public String getAppleColor() {
			return appleColor;
		}

		public void setAppleColor(String appleColor) {
			this.appleColor = appleColor;
		}
		
	}
	
	
	static class FruitUtil{
		
		public static void getFruitInfo(Class<?> clazz){
			String strFruitName=" 水果名称：";
	        String strFruitColor=" 水果颜色：";
	        
	        Field[] fields = clazz.getDeclaredFields();
	        
	        for (Field field : fields) {
				if(field.isAnnotationPresent(FruitName.class)){
					FruitName fruitName = field.getAnnotation(FruitName.class);
					strFruitName += fruitName.value();
					System.out.println(strFruitName);
				}
				
				else if(field.isAnnotationPresent(FruitColor.class)){
					FruitColor fruitColor = field.getAnnotation(FruitColor.class);
					strFruitColor += fruitColor.fruitColor().BULE;
					//strFruitColor += fruitColor.value();//这里不会获得RED，是因为注解的属性值在fruitColor方法上，而不是在value方法上
					System.out.println(strFruitColor);
				}
			}
		}
		
	}
	
	/**
	 * 从测试的结果也可以看出来，相应让注解进行生效，必须使用反射机制，手动的
	 * @param args
	 * 2017-12-16
	 * dell
	 */
	
	public static void main(String[] args) {
		FruitUtil.getFruitInfo(Apple.class);
	}
	
}
