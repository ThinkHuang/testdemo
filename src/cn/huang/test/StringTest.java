package test;

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
		 * 说明，String类实现了Object的equals方法，而StringBuilder没有实现该方法。
		 */
	}

	private static void test4() {
		// TODO Auto-generated method stub
		String s = "hello";
		System.out.println(s.hashCode());
		s = s + "world";
		System.out.println(s.hashCode());
		/*
		 * 这个例子说明，String类是immutable的，这样，当改变s的值的时候，其实是重新新建了一个String对象，
		 * 然后让s指向这个新的对象，JVM再销毁这个"旧"的对象。
		 * 所以，以后在针对可变长度的字符串时，一般用StringBuilder
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

	//result：

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

	//result：

	//false
	//kvill kvill
	//true
	 /**
	  * 说明：从上面的结果可以看出，针对语句string str = "abc"而言，它在编译期的时候就会检查常量池，是否有“abc”这个字符串是否存在，
	  * 不存在就添加进去，存在就不创建对象的引用（引用1），这种方式创建的字符串对象在底层可能也是以类“String”的方式来创建对象；
	  * 针对于语句String str = new String("abc")来说，在编译期是不会创建对象的，而是到了运行期，当执行了s2 = s1.intern()
	  * 方法后，s1!=s2说明，new String()这个对象存储的其实不是“abc”这个常量，而是“abc”这个常量的引用（引用2）
	  * 
	  * 
	  * intern方法将返回常量字符串的引用（引用1），可以看出这里的“常量”字符串引用和string的引用（引用2）是不同的
	  * 
	  * 
	  * 猜想：我想为什么会出现上面的情况，那是因为new string中存储的可能是“abc”这个常量的“真身”,到了运行期的某个时期，JVM就会将
	  * 这个真身“复制”（可能不是复制）到常量池中，然后再销毁这个“真身”，这时候，他们指向的就是一个对象引用了。
	  */

	 

	//不过总觉得想到了，却还差点什么，希望大神们看看，给我一些新的提示！


}
