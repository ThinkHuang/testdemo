package cn.huang.test;

public class tryFinallyTest {
	public static void main(String[] args) {
		//System.out.println(new tryFinallyTest().test());
		System.out.println(new tryFinallyTest().test1());
	}
	/**
	 * 说明：如果try和finally中都有return语句，针对基本数据 类型来说，finally中return前的语句会执行，
	 * 但是，return不会对try中的返回值造成影响。
	 * @return
	 */
	private int test1() {
		// TODO Auto-generated method stub
		try {
			return func1();
		} finally{
			return func2();
		}
	}
	private int func2() {
		// TODO Auto-generated method stub
		System.out.println("func1 test");
		return 1;
	}
	private int func1() {
		// TODO Auto-generated method stub
		System.out.println("func2 test");
		return 2;
	}
/**
 * 说明：try中的返回值会首先填充进去，至于finally中的影响，针对于“基本变量”不会有影响。
 * @return
 */
	private int test() {
		// TODO Auto-generated method stub
		int i = 1;
		try {
			return i;
		} finally {
			// TODO: handle exception
			++i;
		}
	}
}

