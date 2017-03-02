package cn.huang.test;

public class ExtendTest {
	public static void main(String[] args) {
		System.out.println(Sub.B);
	}
}

class parent{
	public static int A = 1;
	static{
		A = 2;
	}
}
class Sub extends parent{
	public static int B = A;
}