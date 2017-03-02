package cn.huang.filetest;

import java.io.UnsupportedEncodingException;

/**
 * 这个程序属于有问题的程序，其中有一个bug，有些能够计算出来有些又不能计算出来，例如下面的例子就不能计算出来
 * @author huangyejun
 *
 */

public class ExtractStringTest {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String str = "我e爱b景秀中华r，aa在a";
		int num = trimGBK(str.getBytes("utf-8"),5);
		System.out.println(num);
		System.out.println(str.substring(0, num));
	}

	private static int trimGBK(byte[] bytes, int j) {
		// TODO Auto-generated method stub
		boolean isChinese = false;
		int num = 0;
		for(int i = 0 ; i < j ; i++){
			if(bytes[i] < 0 && !isChinese){
					isChinese = true;
			}
			else{
					num++;
					isChinese = false;
			}
		}
		return num;
	}
}
