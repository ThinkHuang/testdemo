package cn.huang.test;

public class synchronizeBlockTest {
	private int i;
	public static void main(String[] args) {
		synchronizeBlockTest d2 = new synchronizeBlockTest();
		Inc increse = d2.new Inc();
		Des decrease = d2.new Des();
		for(int x = 0; x < 2; x++){
			Thread t1 = new Thread(increse);
			Thread t2 = new Thread(decrease);
			t1.start();
			t2.start();
		}
	}
	
	synchronized void inc(){
		while(true){
			i++;
			System.out.println(Thread.currentThread() + "increase i=" + i);
		}
	}
	
	synchronized void des(){
		while(true){
			i--;
			System.out.println(Thread.currentThread() + "decrease i=" + i);
		}
	}
	
	class Inc implements Runnable{

		@Override
		public void run() {
			inc();
		} 
	}
	
	class Des implements Runnable{

		@Override
		public void run() {
			des();
		}
	}
}
