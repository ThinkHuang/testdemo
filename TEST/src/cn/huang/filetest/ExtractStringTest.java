package cn.huang.filetest;

import java.io.UnsupportedEncodingException;

/**
 * �����������������ĳ���������һ��bug����Щ�ܹ����������Щ�ֲ��ܼ��������������������ӾͲ��ܼ������
 * @author huangyejun
 *
 */

public class ExtractStringTest {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String str = "��e��b�����л�r��aa��a";
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
