package cn.huang.test;

public class tryFinallyTest {
	public static void main(String[] args) {
		//System.out.println(new tryFinallyTest().test());
		System.out.println(new tryFinallyTest().test1());
	}
	/**
	 * ˵�������try��finally�ж���return��䣬��Ի������� ������˵��finally��returnǰ������ִ�У�
	 * ���ǣ�return�����try�еķ���ֵ���Ӱ�졣
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
 * ˵����try�еķ���ֵ����������ȥ������finally�е�Ӱ�죬����ڡ�����������������Ӱ�졣
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

