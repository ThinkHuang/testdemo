package cn.huang.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
	private int j;
	private Lock lock = new ReentrantLock();
	public static void main(String[] args) {
		LockTest lt = new LockTest();
		for(int i = 0 ; i < 2; i++){
			new Thread(lt.new subtractor()).start();
			new Thread(lt.new adder()).start();
		}
	}


	class subtractor implements Runnable{
	
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true){
//				synchronized(LockTest.class){
//					System.out.println("j--" + j--);
//				}
				lock.lock();
				try {
					System.out.println("j--" + j--);
				} catch (Exception e) {
					// TODO: handle exception
				}finally{
					lock.unlock();
				}
			}
		}
		
	}

	class adder implements Runnable{
	
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true){
				lock.lock();
				try {
					System.out.println("j++" + j++);
				} catch (Exception e) {
					// TODO: handle exception
				}finally{
					lock.unlock();
				}
			}
		}	
	}
}