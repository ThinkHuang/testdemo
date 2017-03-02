package cn.huang.other;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.junit.Test;


public class AnnotationTest {
	@Test
	public void fun1(){
		new B();
	}
}

/**
 * 由于编译器会对编译后的Class文件进行优化，进行反编译后可以看到实际上，所有的泛型都被实际类型代替了。
 * @author huangyejun
 *
 * @param <T>
 */
class A<T>{
	public void fun(){
		ParameterizedType pType = (ParameterizedType) this.getClass().getGenericSuperclass();
		Type[] types = pType.getActualTypeArguments();//得到传递给父类的具体类型。至于这里为什么是数组类型，是因为可能有多个泛型变量
		System.out.println("A...");
	}
}

class B extends A<String>{//在继承泛型类时，必须要给其直接父类传递确认的类型。
	
}

class C extends A<Integer>{
	
}


@Retention(RetentionPolicy.RUNTIME)
@interface MyAnn01{
	//以下是注解的固定格式，不需要写成这样，虽然这里的name是属性，但是，也必须写成这样
	public String name();
	public int age();
	public String sex();
}