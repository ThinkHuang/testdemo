package design;


//˵�������ڵ���ģʽ����Ҫ�в��������Ĺ�����
public class SingleTon {
	/**
	 * �������������˵������ģʽ�ı���ʽ
	 */
	public SingleTon(){}
//	private static final SingleTon single = new SingleTon();
//	public static SingleTon getInstance(){
//		return single;
//	}
	
	/**
	 * ���������˵������ģʽ�Ķ���ʽ
	 * 
	 * ˵�������ڶ���ʽ��˵���ڶ��̵߳Ļ����´���ʵ�������ܻᵼ���̲߳���ȫ�����⣬��������ʵ����������ͬ���ġ�
	 */
	private static SingleTon single = null;
	public static synchronized  SingleTon getInstance(){
		if(single==null){
			single = new SingleTon();
		}
		return single;
	}
}
