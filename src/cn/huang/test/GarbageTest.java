package cn.huang.test;

public class GarbageTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GCTest();
	}

	private static void GCTest() {
		// TODO Auto-generated method stub
		Person p1 = new Person();
		Person p2 = new Person();
		
//		p1.getPerson(p2);
//		p2.getPerson(p1);
		System.gc();
		System.out.println("GCTest is over");
	}

}

//class Person{
//	private Person p;
//	
//	public Person getPerson(Person person){
//		this.p = person;
//		return p;
//	}
//}
