package cn.huang.test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class MainTest {

	/**
	 * @param args
	 * @throws IntrospectionException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//test1();
		test2();
	}

	private static void test2() throws Exception {
		// TODO Auto-generated method stub
		//Class clazz = Class.forName("cn.huang.test.Person");
		Class<Person> clazz = Person.class;
		Constructor<Person> constructor = clazz.getConstructor();
		Person person = constructor.newInstance();
		person.setPassword("123");
		person.setUsername("zhangsan");
		System.out.println(person.getPassword());
		System.out.println();
		
		
	}

	private static void test1() throws IntrospectionException {
		// TODO Auto-generated method stub
		//通过Class对象获取BeanInfo对象
		BeanInfo info = Introspector.getBeanInfo(Person.class);
		//通过BeanInfo获取所有属性描述符对象
		PropertyDescriptor[] pd = info.getPropertyDescriptors();
		String username = null;
		Method method;
		for(int i = 0; i < pd.length; i++){
			System.out.println(pd[i].getName());
			System.out.println(pd[i].getReadMethod());
		}
		
	}

}
