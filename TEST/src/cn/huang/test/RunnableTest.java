package cn.huang.test;

public class RunnableTest implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("this is a Runnable interface Test");
	}
	public static void main(String[] args) {
		
		test1();
		test2();
	}
	private static void test2() {
		// TODO Auto-generated method stub
		//new Thread(new Thread1()).start();
		
		try {
			Thread.currentThread().sleep(200);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//new Thread(new Thread2()).start();
	}
	private static void test1() {
		// TODO Auto-generated method stub
		RunnableTest ra = new RunnableTest();
		new Thread(ra).start();
		new Thread(ra).start();
	}
	
	

}

/*class Thread1 implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		synchronized(RunnableTest.class){
			System.out.println("Thread1 Starting");
			try {
				RunnableTest.class.wait();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			System.out.println("Thread1 is going to over");
		}
	}
	
}*/
/**
 * 同步方法和同步代码块用的不是不是同一个锁
 * @author huangyejun
 *
 */

/*class Thread2 implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		synchronized(RunnableTest.class){
			System.out.println("Thread2 Starting");
			//notify方法只有出了同步方法或者同步代码块以后才会释放锁。
			RunnableTest.class.notify();
			System.out.println("Thread2 is going to waiting");
			try {
				Thread.sleep(2000);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			System.out.println("Thread2 is going to over");
		}
		System.out.println("I am just for fun");
	}
	
}*/
