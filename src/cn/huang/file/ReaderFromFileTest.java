package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ReaderFromFileTest {

	/**
	 * 说明：该程序不够成熟，因为java中数组的长度是不可更改的，但是，可以拿到里面的值，写一个方法得到去除空值后的数组长度
	 * 该工具类的难点之一就是动态的获得数组的长度。因为数组的长度满足条件就会减小
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File file = new File("f:\\test.txt");
		
		FileInputStream isr = new FileInputStream(file);
		
		byte[] buf = new byte[1024];
		int len = isr.read(buf);
		//boolean isChinese = false;
	    String str = new String(buf, 0 ,len, "GB2312");
			   
		String[] chips =  str.split(",");
		
		String[] names = new String[chips.length] ;
		int j = 0;
		for(int i = 0; i < chips.length; i++){
			//System.out.println(chips[i]);
			if(isName(chips[i])){
				names[j] = chips[i];
				System.out.println(names[j]);
				//我估计这里就有JIT编译器优化后的结果，例如上句的print中参数为chips[i]和names[i]的结果一样，
				//这很显然是编译器优化后的结果
				j++;
			}
		}
//		System.out.println("Delete before " + names.length);
//		DelSameStr("张三" ,names);
//		System.out.println("After Delete" + names.length);
		String str1 = names[0];
		int count = 1;
		int dynamiclen = DelSameStr(str1 ,names);
		for(int i = 0; i <  dynamiclen; i++){
			//System.out.println("dynamiclen:" +  DelSameStr(str1 ,names));
			str1 = names[i];
			for(int n = i+1; n < dynamiclen; n++){
				if(str1.equals(names[n])){
					count++;
					//这里在找到了相同的字符串后，就将字符串数组中的所有相同的字符串都删除掉
				}
			}
			System.out.println("name:" + str1 + "count:" + count);
			dynamiclen = DelSameStr(str1, names);
			count = 1;
		}
	}
	
	/*
	 * 删除相同字符串后的长度
	 */
	private static int DelSameStr(String str1, String[] names) {
		String strtmp;
		int length = trimArray(names);
		System.out.println("Acctual length" + length);
		System.out.println("names:" + str1);
		for(int i = 1; i < length; i++){
			if(str1.equals(names[i])){
				strtmp = names[i+1];
				names[i] = strtmp;
				names[i+1]="";
			}
		}
		return length;
	}

	/*
	 * 去除数组中的空字符串后的长度
	 */
	private static int trimArray(String[] names) {
		int i = 0; 
		for(i = 0; i < names.length; i++){
			if(names[i] == null){
				//System.out.println("i:"+i);
				return i;
			}
		}
		//System.out.println("去掉空串后的数组长度" + i);
		return i;
	}

	/*
	 * 判断这个字符串是否是name，当然这里的判断还比较的局限性，如果，想要范围更广的话，必须要有更多的逻辑操作。
	 */
	private static boolean isName(String string) {
		char temp;
		for(int i = 0; i < string.length(); i++){
			temp = string.charAt(i);
			if(temp >= '0' && temp <= '9'){
				return false;
			}
		}
		return true;
	}

}
