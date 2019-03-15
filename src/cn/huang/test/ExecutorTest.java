package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ExecutorTest {
	private static Lock lock = new ReentrantLock();
	private static Condition subThreadCondition = lock.newCondition();
	private static boolean b = true;
	public static void main(String[] args) {
		ExecutorService threadPool = Executors.newFixedThreadPool(3);
		threadPool.execute(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int x = 0 ; x < 50; x++){
					lock.lock();
						try {
							if(!b)
							subThreadCondition.await();
							for(int j=0;j<10;j++) 
							{ 
								System.out.println(Thread.currentThread().getName() + ",j=" + j); 
							} 
							b = false;
							subThreadCondition.signal();
						} catch (Exception e) {
							// TODO: handle exception
						}finally{
							lock.unlock();
						}
				}
			}
		});
		threadPool.shutdown();
		for(int i=0;i<50;i++) 
		{ 
				lock.lock();				 
				try 
				{
					if(b) 
						subThreadCondition.await();														for(int j=0;j<10;j++) 
						for(int a=0;a<10;a++) 
						{ 
							System.out.println(Thread.currentThread().getName() + ",a=" + a); 
						}
					b = true; 
					subThreadCondition.signal();												}catch(Exception e) {							
					} 
				finally 
				{ 
					lock.unlock(); 
				}				
		} 
	} 
}
	
