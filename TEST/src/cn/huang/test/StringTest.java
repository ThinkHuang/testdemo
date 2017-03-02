package cn.huang.test;

public class StringTest {

	 /**
	  * @param args
	  */
	 public static void main(String[] args) {
	  test1();
	  //test2();
	  //test3();
	  //test4();
		//test5();
	  
	  
	 }

	 private static void test5() {
		// TODO Auto-generated method stub
		System.out.println(new String("abc").equals(new String("abc")));
		System.out.println(new StringBuilder("abc").equals(new StringBuilder("abc")));
		/*
		 * ˵����String��ʵ����Object��equals��������StringBuilderû��ʵ�ָ÷�����
		 */
	}

	private static void test4() {
		// TODO Auto-generated method stub
		String s = "hello";
		System.out.println(s.hashCode());
		s = s + "world";
		System.out.println(s.hashCode());
		/*
		 * �������˵����String����immutable�ģ����������ı�s��ֵ��ʱ����ʵ�������½���һ��String����
		 * Ȼ����sָ������µĶ���JVM���������"��"�Ķ���
		 * ���ԣ��Ժ�����Կɱ䳤�ȵ��ַ���ʱ��һ����StringBuilder
		 */
	}

	private static void test3() {
		// TODO Auto-generated method stub
		String s1 = "abc";
		System.out.println(s1.equals( new String("abc")));
	}

	private static void test2() {
	  // TODO Auto-generated method stub
	  String s1 = "kvill";
	  String s2=s1.intern();
	  System.out.println( s1==s1.intern() ); 
	  System.out.println( s1+" "+s2 ); 
	  System.out.println( s2==s1.intern() );
	 }

	//result��

	//true
	//kvill kvill
	//true

	 private static void test1() {
	  // TODO Auto-generated method stub
	  String s1 = new String("kvill");
	  String s2 = s1.intern();
	  String s3 = "kvill";
	  System.out.println( s1==s1.intern() ); 
	  System.out.println( s2==s3 ); 
	  System.out.println( s1+" "+s2 ); 
	  System.out.println( s2==s1.intern() );
	 }

	//result��

	//false
	//kvill kvill
	//true
	 /**
	  * ˵����������Ľ�����Կ�����������string str = "abc"���ԣ����ڱ����ڵ�ʱ��ͻ��鳣���أ��Ƿ��С�abc������ַ����Ƿ���ڣ�
	  * �����ھ���ӽ�ȥ�����ھͲ�������������ã�����1�������ַ�ʽ�������ַ��������ڵײ����Ҳ�����ࡰString���ķ�ʽ����������
	  * ��������String str = new String("abc")��˵���ڱ������ǲ��ᴴ������ģ����ǵ��������ڣ���ִ����s2 = s1.intern()
	  * ������s1!=s2˵����new String()�������洢����ʵ���ǡ�abc��������������ǡ�abc��������������ã�����2��
	  * 
	  * 
	  * intern���������س����ַ��������ã�����1�������Կ�������ġ��������ַ������ú�string�����ã�����2���ǲ�ͬ��
	  * 
	  * 
	  * ���룺����Ϊʲô���������������������Ϊnew string�д洢�Ŀ����ǡ�abc����������ġ�����,���������ڵ�ĳ��ʱ�ڣ�JVM�ͻὫ
	  * ����������ơ������ܲ��Ǹ��ƣ����������У�Ȼ���������������������ʱ������ָ��ľ���һ�����������ˡ�
	  */

	 

	//�����ܾ����뵽�ˣ�ȴ�����ʲô��ϣ�������ǿ���������һЩ�µ���ʾ��


}
