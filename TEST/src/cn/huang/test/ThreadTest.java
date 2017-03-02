package cn.huang.test;

import org.junit.Test;

public class ThreadTest extends Thread{
	public static void main(String[] args) {
//		test1();
//		test2();
	}

	
	

	private static void test2() {
		// TODO Auto-generated method stub
		/**
		 * ע�⣺����ֻ�ǲ����������̣߳������߳���ʱ���ƣ���һ�����̣߳�һ�����̡߳�
		 * ��һ�������ĵط����ǣ����߳�Ҫ��ɵ������װ���ˡ���Դ�ࡱ��Business���ķ����У���ͨ���̵߳�run������ʵ������
		 */
		final Business business = new Business();
			new Thread(new Runnable(){
				@Override
				public void run() {
					for(int a = 0 ;a < 50 ; a++){
						business.SubThread(a);
					}
				}
			}).start();
			for(int b = 0 ; b < 50 ; b++){
					business.MainThread(b);
			}
		
	}

	private static void test1() {
		// TODO Auto-generated method stub
		ThreadTest t1 = new ThreadTest();
		ThreadTest t2 = new ThreadTest();
		t1.start();
		t2.start();
		
		}
	public void run(){
		System.out.println("I am a Thread!");
	}
	
	@Test
	public void test(){
		new Thread(new Runnable(){

			@Override
			public void run() {
				A a = new A();
				
				a.method();
			}
			
		}).start();
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				A a = new A();
				a.method();
			}
			
		}).start();
	}
}

class Business {
//	boolean bShouldSub = true;//�����൱�ڶ����˿��Ƹ�˭ִ�е�һ���źŵ� 
//	public synchronized void MainThread(int i) 
//	{ 
//		if(bShouldSub) 
//			try { 
//				this.wait(); 
//			} catch (InterruptedException e) { 
//				e.printStackTrace(); 
//			}
//		for(int j=0;j<5;j++) 
//		{ 
//			System.out.println(Thread.currentThread().getName() + ":i=" + i +",j=" + j); 
//		} 
//		bShouldSub = true; 
//		this.notify(); 
//	} 
//	public synchronized void SubThread(int i) 
//	{ 
//		if(!bShouldSub) 
//			try { 
//				this.wait(); 
//			} catch (InterruptedException e) { 
//				e.printStackTrace(); 
//			} 
//		for(int j=0;j<10;j++) 
//		{ 
//			System.out.println(Thread.currentThread().getName() + ":i=" + i +",j=" + j); 
//		} 
//		bShouldSub = false;			 
//		this.notify();		 
//	} 

	private boolean b = true;
	public synchronized void MainThread(int x){
		if(b){
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int i = 0 ; i < 100 ; i++){
				System.out.println(Thread.currentThread() + "MainClass i = " + i + "x=" + x);
			}
		}
		b = true;
		this.notify();
	}
	
	public synchronized void SubThread(int x){
		if(!b){
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int j = 0 ; j < 10 ; j++){
				System.out.println(Thread.currentThread() + "SubClass j = " + j + "x=" + x);
			}
		}
		b = false;
		this.notify();
	}
	
	
}

class A{
	public int x;
	
	public synchronized void method(){
		x++;
		System.out.println("x= " + x);
	}
}