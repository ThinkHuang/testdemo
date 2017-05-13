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
		
		
		
			byte[] buf = new byte[is.available()];//得到预测的流中数据的大小
			is.read(buf);//这时候所有的字节全部读取到了buf这个字节数组中了
			
			System.out.println(buf.length);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
