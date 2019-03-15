/**
 * 
 */
package thread;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @author dell
 * 该demo用于实现CyclicBarrier的测试，
 */
public class CyclicBarrierDemo {
	
	static int N = 4;
	
	static CyclicBarrier barrier = new CyclicBarrier(N, new Runnable(){//这里的任务有最后到达的线程进入执行

		@Override
		public void run() {
			System.out.println("进入状态后的线程为:" + Thread.currentThread().getName());
		}
	});
	
	public static void main(String[] args) throws InterruptedException {
		
		for (int i = 0; i < N; i++) {
			if(i < N - 1){
				Worker worker = new Worker(barrier);
				Thread thead = new Thread(worker);
				thead.start();
			}else{
				Thread.sleep(5000);
				
				Worker lworker = new Worker(barrier);
				Thread lThread = new Thread(lworker);
				lThread.start();
			}
		}
		
		
//		System.out.println("开始重用CyclicBarrier...........");
//		
//		for (int i = 0; i < N; i++) {
//			Worker worker = new Worker(barrier);
//			Thread thead = new Thread(worker);
//			thead.start();
//		}
	}
	
	
	
	private static class Worker implements Runnable{
		
		CyclicBarrier barrier;
		
		public Worker(CyclicBarrier barrier){
			this.barrier = barrier;
		}
		
		@Override
		public void run() {
			
			try {
				System.out.println("线程:" + Thread.currentThread().getName() + "开始准备向数据库进行数据拷贝!");
				Thread.sleep(3000);
				barrier.await(2000,TimeUnit.MILLISECONDS);
				System.out.println("线程:" + Thread.currentThread().getName() + "正在进行数据拷贝");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			System.out.println("线程:" + Thread.currentThread().getName() + "拷贝数据完成!");
		}
		
	}
}
