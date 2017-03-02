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
		 * ���л��Ĳ����������ģ�
		 * 1������һ�������������Ϊʲô��������أ���Ϊ�ǽ�������ڴ���д��Ӳ���ϣ�����ָ��Ҫ���л��Ķ���ľ���λ�ã���������FileInputStream����һ���ļ���f:\\Person.txt
		 * 2�����ö����������writeObject��������person����д���ļ��С�
		 * 3���ر�������
		 */
		SerializePerson();
		/*
		 * �����л��Ĳ��裺
		 * 1������һ����������������Ӳ�̽���������ڴ��У�����ָ��Ҫ���ĸ��ļ����ö�������Ҳ����FileInputStream����һ���ļ���
		 * 2�����ö�������readObject�����õ����󣬲����ظö����ʵ����
		 * 3��ʹ�øö���
		 */
		Person person = DeserializePerson();
		System.out.println("name="+person.getName() + "age=" + person.getAge() + "sex=" + person.getSex());
	}

	private static Person DeserializePerson() throws FileNotFoundException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("f:\\Person.txt"));
		
		Person p = (Person) ois.readObject();
		System.out.println("Person�������л��ɹ���");
		return p;
	}

	private static void SerializePerson() throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		Person p = new Person();
		p.setAge(19);
		p.setName("����");
		p.setSex("��");
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("f:\\Person.txt"));
		oos.writeObject(p);
		System.out.println("Person�������л��ɹ���");
		oos.close();
	}

}
