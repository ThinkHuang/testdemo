package cn.huang.filetest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Test;

public class PrepareDictatationTest {
	@Test
	public void test(){
		try {
		
		String filename = "directory.txt";
//		FileInputStream fis = new FileInputStream(filename);
		InputStream is = this.getClass().getResourceAsStream(filename);
		InputStreamReader isr = new InputStreamReader(is);
		
		
		
			byte[] buf = new byte[is.available()];//�õ�Ԥ����������ݵĴ�С
			is.read(buf);//��ʱ�����е��ֽ�ȫ����ȡ����buf����ֽ���������
			
			System.out.println(buf.length);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
