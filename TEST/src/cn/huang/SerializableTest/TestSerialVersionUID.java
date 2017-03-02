package cn.huang.SerializableTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TestSerialVersionUID {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		/*
		 * 序列化的步骤是这样的：
		 * 1、建立一个对象输出流（为什么是输出流呢？因为是将对象从内存中读取出来），并指定要序列化的对象的具体位置，这里利用FileInputStream关联一个文件，f:\\Person.txt
		 * 2、利用对象输出流的writeObject方法，将person对象写到文件中。、
		 * 3、关闭流对象。
		 */
		SerializeCustom();
		/*
		 * 反序列化的步骤：
		 * 1、建立一个对象输入流（从硬盘将对象拿到内存中），并指定要从哪个文件中拿对象，这里也是用FileInputStream关联一个文件。
		 * 2、利用对象流的readObject方法得到对象，并返回该对象的实例。
		 * 3、使用该对象。
		 */
		Custom p = DeserializeCustom();
		System.out.println(p.toString());
	}

	private static Custom DeserializeCustom() throws FileNotFoundException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("f:\\Custom.txt"));
		
		Custom c = (Custom) ois.readObject();
		System.out.println("Custom对象反序列化成功！");
		return c;
	}

	private static void SerializeCustom() throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		Custom c = new Custom();
		c.setAge(19);
		c.setName("张三");
		c.setStation("中国");
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("f:\\Custom.txt"));
		oos.writeObject(c);
		System.out.println("Custom对象序列化成功！");
		oos.close();
	}

}
