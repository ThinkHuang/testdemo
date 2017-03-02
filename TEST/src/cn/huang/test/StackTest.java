package cn.huang.test;

public class StackTest {
	public static Stack st = new Stack();
	static{ 
		st.push(new Object());
		st.pop();//这里的对象为什么会出现内存溢出的问题呢？是因为栈对象调用pop()方法，仅仅只是将栈顶指针的前一个元素对象返回了，并没有将
		//改对象从容器elements中除掉，这个对象没有被引用，也不能被回收！--处于游离态
		st.push(new Object());
		//System.out.println("hehe");
	}
	
	public static void main(String[] args) {
	}
}
