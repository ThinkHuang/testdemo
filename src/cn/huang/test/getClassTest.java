package cn.huang.test;

import java.util.Date;



public class getClassTest extends Date{
	public static void main(String[] args) {
		new getClassTest().test();
	}
	public void test(){
		System.out.println(this.getClass().getSuperclass().getName());
	}
}
