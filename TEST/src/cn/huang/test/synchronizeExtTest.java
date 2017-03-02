package cn.huang.test;


/**
 * 改程序的中Thread1和Thread2的执行顺序是随机的。可以参考ThreadTest，该类中对线程的执行顺序做了控制。
 * @author huangyejun
 *
 */

public class synchronizeExtTest {
	public static void main(String[] args) {
		//这样不能做顺序控制，因为这里的控制没有用，只有在任务中做逻辑控制，而不是对线程顺序做做控制
		for(int x = 0; x < 50 ; x++){
			new Thread(new Thread1()).start();
			new Thread(new Thread2()).start();
		}
	}
}

class Thread1 implements Runnable{
	@Override
	public void run() {
		synchronized(synchronizeExtTest.class){
				try {
					synchronizeExtTest.class.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				for(int j = 0 ; j < 100 ; j++){
					System.out.println(Thread.currentThread() + "i am mainclass" + j);
				}
			}
			synchronizeExtTest.class.notify();
		}
	}
	

class Thread2 implements Runnable{
	@Override
	public void run() {
		synchronized(synchronizeExtTest.class){
				try {
					synchronizeExtTest.class.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				for(int i = 0 ; i < 10 ; i++){
					System.out.println(Thread.currentThread() + "I am subclass" + i);
				}
			synchronizeExtTest.class.notify();
		}
	}
}	


