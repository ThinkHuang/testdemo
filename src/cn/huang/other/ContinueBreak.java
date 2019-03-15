/**
 * 
 */
package other;

/**
 * <p>Project:test
 * <p>Package:com.huang.demo
 * <p>Title:ContinueBreak
 * <p>Description:
 * <p>Company:ShangCheng Tech
 * @author huangyejun
 * Create Date:2018-1-4 下午03:13:21
 * @version
 */
public class ContinueBreak {
	
	public static void main(String[] args) {
		test();
	}
	
	public static void test(){
		int sum = 0;
		retry:
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 5; j++) {
					if(i == 5)
						continue retry;
					//从这里看出，continue也能直接跳出整个循环，或者多个循环,但是依然会进入循环中
					sum += j;
				}
			}
		System.out.println(sum);
	}
	
	
	public static void test1(){
		con:
			for (int i = 0; i < 5; i++) {
				
			}
	   con:
		for (int i = 0; i < 10; i++) {
			if(i == 5) 
				//从这个例子看出，continue只能在一个块中进行跳转，而不能在多个执行块中执行
				continue con;
		}
		
	}
}
