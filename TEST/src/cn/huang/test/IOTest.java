package cn.huang.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class IOTest {
	public static void main(String[] args) throws IOException {
		String str = "�й���";
		FileOutputStream fos = new FileOutputStream("f:\\1.txt");
		fos.write(str.getBytes("UTF-8"));
		fos.close();
		
		FileInputStream fis = new FileInputStream("f:\\1.txt");
		byte[] b = new byte[1024];
		int len = fis.read(b);
		String myStr = new String(b,0,len,"utf-8");
		System.out.println(myStr);
		
//		FileWriter fw = new FileWriter("f:\\2.txt");
//		fw.write(str);
//		fw.close();
//		
//		FileReader fr = new FileReader("f:\\2.txt");
//		char[] ch = new char[1024];
//		int len = fr.read(ch);
//		for(int i = 0 ; i < len ; i++){
//			System.out.print(ch[i]);
//		}
		
//		PrintWriter pw = new PrintWriter("f:\\3.txt");
//		pw.write(str);
//		pw.append("abelc,��������");
//		pw.close();
		
		
//		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("f:\\3.txt")));
		/*����ʹ�õ����ַ�������ַ���������ַ����������Ҫ��ȡ���Ǻ�������ַ�������ʹ���ַ�������Ϊ�ַ���������˶������ģ�
	                   ���⣬Ϊ�˸�Ч�ԣ������һ�������������ַ�������������readline()�������Ҫע��ʹ�ã���������ʽ�ķ�����������������ݵĻ�������һֱ���������᷵��null��
		     ֱ�����ĹرղŻ����null��Ҳ����˵��������еõ��Ľ�����ܱ��������Ļ��������������������ݽ��������ڡ����⣬���������ȡ������Դ��read()����������Դ�ǲ�ͬ��
		  ���Ǵ��ڴ��ж�ȡ�����ݣ������Ǵ�Ӳ���ж�ȡ�����ݡ�
		*/
//		char[] ch = new char[1024];
//		int len = br.read(ch);
//		for(int i = 0 ; i < len ; i++){
//			System.out.print(ch[i]);
//		}
		//StringBuilder myStr = null;
//		String newStr = null;
//		while((newStr = br.readLine()) != null){
//			System.out.println(newStr);
//		}
	}
}
