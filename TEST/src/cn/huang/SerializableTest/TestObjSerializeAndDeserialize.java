package cn.huang.SerializableTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TestObjSerializeAndDeserialize {

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
		 * 1、建立一个对象输出流（为什么是输出流呢？因为是将对象从内存中写到硬盘上），并指定要序列化的对象的具体位置，这里利用FileInputStream关联一个文件，f:\\Person.txt
		 * 2、利用对象输出流的writeObject方法，将person对象写到文件中。
		 * 3、关闭流对象。
		 */
		SerializePerson();
		/*
		 * 反序列化的步骤：
		 * 1、建立一个对象输入流（从硬盘将对象读到内存中），并指定要从哪个文件中拿对象，这里也是用FileInputStream关联一个文件。
		 * 2、利用对象流的readObject方法得到对象，并返回该对象的实例。
		 * 3、使用该对象。
		 */
		Person person = DeserializePerson();
		System.out.println("name="+person.getName() + "age=" + person.getAge() + "sex=" + person.getSex());
	}

	private static Person DeserializePerson() throws FileNotFoundException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("f:\\Person.txt"));
		
		Person p = (Person) ois.readObject();
		System.out.println("Person对象反序列化成功！");
		return p;
	}

	private static void SerializePerson() throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		Person p = new Person();
		p.setAge(19);
		p.setName("张三");
		p.setSex("男");
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("f:\\Person.txt"));
		oos.writeObject(p);
		System.out.println("Person对象序列化成功！");
		oos.close();
	}

}
