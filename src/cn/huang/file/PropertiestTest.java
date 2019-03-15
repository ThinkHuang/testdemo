package file;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

/**
 * 该类是用来测试Properties这个类的使用的
 * @author huangyejun
 *
 */
public class PropertiestTest {
	public static void main(String[] args) throws IOException {
		//读取系统文件的信息
//		Properties pro = System.getProperties();
//		pro.list(System.out);
		//读取test.properties文件中的内容，
		
		/*
		 * 1、得到一个输入流，并将需要的properties进行“流化”。
		 * 说明，在得到一个输入流对象的过程中，还可以使用另外一种方式。
		 * InputStream in = new BufferedInputStream(new FileInputStream(”文件名“));
		 * 注意：这里的文件的位置很重要，使用这种方式的时候，文件的路径是相对于项目路径，
		 */
		//InputStream in = PropertiestTest.class.getResourceAsStream("test.properties");
		//采用getResourceAsStream这时的文件时相对于类路径的。
		//InputStream in = new BufferedInputStream(new FileInputStream("src/cn/huang/filetest/test.properties"));
		/*
		 * 2.创建一个Properties对象，并利用load方法将流中的键值对到属性列表中
		 */
//		Properties pro = new Properties();
//		pro.load(in);
		
		/*
		 * 利用Properties中的各种getXXX()来得到test.properties配置文件中的键值对。
		 */
//		String name = pro.getProperty("name");
//		String wight = pro.getProperty("Wight");
//		String height = pro.getProperty("Height");
		
//		System.out.println("name:" + name + " wight:" + wight + " height:" + "height");
		
		/*
		 * 这里我们可以将配置文件中的所有键值对放到一个Enumeration中，该方法为propertyNames(),该方法将返回属性列表中的所有枚举
		 */
//		Enumeration en = pro.propertyNames();
		
		//使用枚举的迭代器进行迭代查询
//		while(en.hasMoreElements()){
//			String strKey = (String) en.nextElement();
//			String strValue = pro.getProperty(strKey);
//			System.out.println("Key:" + strKey + " Value:" + strValue);
//		}
		
		/*
		 * 接下来将往test.properties配置文件中加入数据
		 * 1、将创建输出流对象，并且关联到文件
		 */
		OutputStream out = new BufferedOutputStream(new FileOutputStream("src/cn/huang/filetest/test.properties"));
		
		/*
		 * 2.创建一个Properties对象，并利用load方法将流中的键值对读到属性列表中
		 */
		Properties pro = new Properties();
		
		pro.setProperty("password", "123");
		pro.store(out, "password");
		
		
	}
}
