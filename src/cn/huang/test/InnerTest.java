package test;

public class InnerTest {
	private Inner in;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		InnerTest d5 = new InnerTest();
		InnerTest.Inner inner = new InnerTest().new Inner();
		d5.show(inner);
		
	}

	class Inner{
		
	}
	
	private Inner show(Inner in){
		this.in = in;
		return in;
	}
}
