package test;

public class StackTest {
	public static Stack st = new Stack();
	static{ 
		st.push(new Object());
		st.pop();//����Ķ���Ϊʲô������ڴ�����������أ�����Ϊջ�������pop()����������ֻ�ǽ�ջ��ָ���ǰһ��Ԫ�ض��󷵻��ˣ���û�н�
		//�Ķ��������elements�г������������û�б����ã�Ҳ���ܱ����գ�--��������̬
		st.push(new Object());
		//System.out.println("hehe");
	}
	
	public static void main(String[] args) {
	}
}
