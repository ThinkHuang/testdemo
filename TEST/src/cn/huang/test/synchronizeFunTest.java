package cn.huang.test;

public class synchronizeFunTest {
	JManager j = new JManager();
	public static void main(String[] args) {
		new synchronizeFunTest().call();
	}
	public void call(){
		for(int i = 0 ;i < 2 ;i++){
			new Thread(new Runnable(){
				@Override
				public void run() {
					while(true){
						j.accumulate();
					}
				}
			}).start();
			new Thread(new Runnable(){
				@Override
				public void run() {
					while(true){
						j.subtract();
					}
				}
			}).start();
		}
	}
}

class JManager {
	private int x;
	public synchronized void subtract(){
		x--;
		System.out.println("sub x=" + x);
	}
	public synchronized void accumulate(){
		x++;
		System.out.println("acc x=" + x);
	}

	
}
