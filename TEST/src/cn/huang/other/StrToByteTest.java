package cn.huang.other;

import java.io.IOException;

public class StrToByteTest {
	public static void main(String[] args) throws IOException {
		String str="������";
		//Ҫ��Ҫ�õ��������ӡ����ֽ��룬���뽫���ַ���ת�����ֽڲ��С�
		byte[] buf = new byte[1024];
		buf = str.getBytes();
		System.out.print("��");
		for(int i = 0; i < buf.length; i++){
			if(i == buf.length - 1){
				System.out.print(buf[i]);
			}else{
			System.out.print(buf[i]+" ");
		}
		}
		System.out.print("��");
	}
}
