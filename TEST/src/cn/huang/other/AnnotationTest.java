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
 * ���ڱ�������Ա�����Class�ļ������Ż������з��������Կ���ʵ���ϣ����еķ��Ͷ���ʵ�����ʹ����ˡ�
 * @author huangyejun
 *
 * @param <T>
 */
class A<T>{
	public void fun(){
		ParameterizedType pType = (ParameterizedType) this.getClass().getGenericSuperclass();
		Type[] types = pType.getActualTypeArguments();//�õ����ݸ�����ľ������͡���������Ϊʲô���������ͣ�����Ϊ�����ж�����ͱ���
		System.out.println("A...");
	}
}

class B extends A<String>{//�ڼ̳з�����ʱ������Ҫ����ֱ�Ӹ��ഫ��ȷ�ϵ����͡�
	
}

class C extends A<Integer>{
	
}


@Retention(RetentionPolicy.RUNTIME)
@interface MyAnn01{
	//������ע��Ĺ̶���ʽ������Ҫд����������Ȼ�����name�����ԣ����ǣ�Ҳ����д������
	public String name();
	public int age();
	public String sex();
}