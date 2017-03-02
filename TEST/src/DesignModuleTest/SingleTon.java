package DesignModuleTest;


//说明，对于单例模式必须要有不含参数的构造器
public class SingleTon {
	/**
	 * 下面的例子用于说明单例模式的饱汉式
	 */
	public SingleTon(){}
//	private static final SingleTon single = new SingleTon();
//	public static SingleTon getInstance(){
//		return single;
//	}
	
	/**
	 * 下面的例子说明单例模式的饿汉式
	 * 
	 * 说明：对于饿汉式来说，在多线程的环境下创建实例，可能会导致线程不安全的问题，所以它的实例化方法是同步的。
	 */
	private static SingleTon single = null;
	public static synchronized  SingleTon getInstance(){
		if(single==null){
			single = new SingleTon();
		}
		return single;
	}
}
