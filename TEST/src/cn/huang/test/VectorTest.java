package cn.huang.test;

import java.util.Vector;

public class VectorTest {
	public static void main(String[] args) {
		Vector<String> v = new Vector<String>();
		for(int i = 0;i < v.size(); i++){
			String str = v.get(i);
			if(!v.contains(str)){
				v.add(str);
			}
		}
	}
}
