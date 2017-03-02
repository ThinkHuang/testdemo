package cn.huang.filetest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ReaderFromFileTest {

	/**
	 * ˵�����ó��򲻹����죬��Ϊjava������ĳ����ǲ��ɸ��ĵģ����ǣ������õ������ֵ��дһ�������õ�ȥ����ֵ������鳤��
	 * �ù�������ѵ�֮һ���Ƕ�̬�Ļ������ĳ��ȡ���Ϊ����ĳ������������ͻ��С
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
				//�ҹ����������JIT�������Ż���Ľ���������Ͼ��print�в���Ϊchips[i]��names[i]�Ľ��һ����
				//�����Ȼ�Ǳ������Ż���Ľ��
				j++;
			}
		}
//		System.out.println("Delete before " + names.length);
//		DelSameStr("����" ,names);
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
					//�������ҵ�����ͬ���ַ����󣬾ͽ��ַ��������е�������ͬ���ַ�����ɾ����
				}
			}
			System.out.println("name:" + str1 + "count:" + count);
			dynamiclen = DelSameStr(str1, names);
			count = 1;
		}
	}
	
	/*
	 * ɾ����ͬ�ַ�����ĳ���
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
	 * ȥ�������еĿ��ַ�����ĳ���
	 */
	private static int trimArray(String[] names) {
		int i = 0; 
		for(i = 0; i < names.length; i++){
			if(names[i] == null){
				//System.out.println("i:"+i);
				return i;
			}
		}
		//System.out.println("ȥ���մ�������鳤��" + i);
		return i;
	}

	/*
	 * �ж�����ַ����Ƿ���name����Ȼ������жϻ��Ƚϵľ����ԣ��������Ҫ��Χ����Ļ�������Ҫ�и�����߼�������
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
