package cn.huang.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
	public static void main(String[] args) {
		//Executors.newSingleThreadExecutor();
	
		ThreadPoolExecutor executor = new ThreadPoolExecutor(5,10,200,TimeUnit.MILLISECONDS,
				new ArrayBlockingQueue<Runnable>(5));
		
		for(int i = 0;i < 15; i++){
			myTask task = new myTask(i);
			executor.execute(task);
			System.out.println("�̳߳��е��߳���Ŀ:" + executor.getPoolSize() + ",�����еȴ�������������" + executor.getQueue().size() + 
					",��ִ����ϵ�����������" + executor.getCompletedTaskCount());
		}
		executor.shutdown();
	}
}
class myTask implements Runnable{
	private int taskNum;
	
	public myTask(int taskNum){
		this.taskNum = taskNum;
	}

	@Override
	public void run() {
		System.out.println("����ִ�е�task" + taskNum);
		try {
			Thread.currentThread().sleep(4000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("task" + taskNum + "ִ�����");
	}
	
}
