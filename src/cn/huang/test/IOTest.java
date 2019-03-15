package test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class IOTest {
	public static void main(String[] args) throws IOException {
		String str = "中国人";
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
//		pw.append("abelc,哈哈哈，");
//		pw.close();
		
		
//		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("f:\\3.txt")));
		/*这里使用的是字符流输出字符，针对于字符流，如果想要读取的是汉字类的字符，建议使用字符流，因为字符流就是因此而产生的，
	                   另外，为了高效性，下面的一个方法采用了字符缓冲流，其中readline()这个方法要注意使用，它是阻塞式的方法，如果读不到数据的话，他会一直阻塞，不会返回null，
		     直到流的关闭才会输出null。也就是说如果从流中得到的结果不能保存下来的话，出了流的生命域，数据将不复存在。另外，这个方法读取的数据源和read()方法的数据源是不同的
		  它是从内存中读取的数据，而不是从硬盘中读取的数据。
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
