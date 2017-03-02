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
		 * ���л��Ĳ����������ģ�
		 * 1������һ�������������Ϊʲô��������أ���Ϊ�ǽ�������ڴ��ж�ȡ����������ָ��Ҫ���л��Ķ���ľ���λ�ã���������FileInputStream����һ���ļ���f:\\Person.txt
		 * 2�����ö����������writeObject��������person����д���ļ��С���
		 * 3���ر�������
		 */
		SerializeCustom();
		/*
		 * �����л��Ĳ��裺
		 * 1������һ����������������Ӳ�̽������õ��ڴ��У�����ָ��Ҫ���ĸ��ļ����ö�������Ҳ����FileInputStream����һ���ļ���
		 * 2�����ö�������readObject�����õ����󣬲����ظö����ʵ����
		 * 3��ʹ�øö���
		 */
		Custom p = DeserializeCustom();
		System.out.println(p.toString());
	}

	private static Custom DeserializeCustom() throws FileNotFoundException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("f:\\Custom.txt"));
		
		Custom c = (Custom) ois.readObject();
		System.out.println("Custom�������л��ɹ���");
		return c;
	}

	private static void SerializeCustom() throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		Custom c = new Custom();
		c.setAge(19);
		c.setName("����");
		c.setStation("�й�");
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("f:\\Custom.txt"));
		oos.writeObject(c);
		System.out.println("Custom�������л��ɹ���");
		oos.close();
	}

}
