/**
 * 
 */
package com.huang.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author dell
 * 场景：20个人去银行存款，但是该银行只有两个办公柜台，有空位则上去存钱，没有空位则只能去排队等待
 */
public class SemaphoreDemo {
	
	int a = 0;
	/**
	 * 资源类
	 */
	
	class Bank{
		public int money = 100;
		
		public int getMoney(){
			return money;
		}
		
		public void save(int num){
			money += num; 
		}
	}
	
	/**
	 * 线程任务执行类
	 */
	class MyThread implements Runnable{

		private Semaphore semaphore;//信号量，限制只能有两个线程获取信号量
		
		private Bank bank;
		
		public MyThread(Bank bank, Semaphore semaphore){
			this.bank = bank;
			this.semaphore = semaphore;
		}
		
		
		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			if(semaphore.availablePermits() > 0){//如果信号量的数量大于0，则获取
				System.out.println("当前线程即将获取资源去存钱:");
			}else{
				System.out.println("信号量不足，不能存钱");
			}
			try {
				int b = a++;
				semaphore.acquire();
				System.out.println(b + "线程开始存钱：");
				bank.save(10);
				System.out.println("存钱后的金额为：" + bank.getMoney());
				Thread.sleep(1000);
			} catch (Exception e) {
			}finally{
				semaphore.release();
			}
		}
		
	}
	
	/**
	 * 完成执行实体的封装
	 */
	public void runTask(){
		Bank bank = new Bank();
		
		Semaphore semaphore = new Semaphore(2);
		/**
		 * 创建线程池管理线程
		 */
		ExecutorService service = Executors.newCachedThreadPool();
		for (int i = 0; i < 20; i++) {//创建20个线程
			service.submit(new Thread(new MyThread(bank, semaphore)));
		}
		
		service.shutdown();//关闭线程池
	}
	
	
	public static void main(String[] args) {
		SemaphoreDemo demo = new SemaphoreDemo();
		demo.runTask();
	}
}
