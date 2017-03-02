package cn.huang.test;


/**
 * �ĳ������Thread1��Thread2��ִ��˳��������ġ����Բο�ThreadTest�������ж��̵߳�ִ��˳�����˿��ơ�
 * @author huangyejun
 *
 */

public class synchronizeExtTest {
	public static void main(String[] args) {
		//����������˳����ƣ���Ϊ����Ŀ���û���ã�ֻ�������������߼����ƣ������Ƕ��߳�˳����������
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


