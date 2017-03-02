package cn.huang.filetest;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

/**
 * ��������������Properties������ʹ�õ�
 * @author huangyejun
 *
 */
public class PropertiestTest {
	public static void main(String[] args) throws IOException {
		//��ȡϵͳ�ļ�����Ϣ
//		Properties pro = System.getProperties();
//		pro.list(System.out);
		//��ȡtest.properties�ļ��е����ݣ�
		
		/*
		 * 1���õ�һ����������������Ҫ��properties���С���������
		 * ˵�����ڵõ�һ������������Ĺ����У�������ʹ������һ�ַ�ʽ��
		 * InputStream in = new BufferedInputStream(new FileInputStream(���ļ�����));
		 * ע�⣺������ļ���λ�ú���Ҫ��ʹ�����ַ�ʽ��ʱ���ļ���·�����������Ŀ·����
		 */
		//InputStream in = PropertiestTest.class.getResourceAsStream("test.properties");
		//����getResourceAsStream��ʱ���ļ�ʱ�������·���ġ�
		//InputStream in = new BufferedInputStream(new FileInputStream("src/cn/huang/filetest/test.properties"));
		/*
		 * 2.����һ��Properties���󣬲�����load���������еļ�ֵ�Ե������б���
		 */
//		Properties pro = new Properties();
//		pro.load(in);
		
		/*
		 * ����Properties�еĸ���getXXX()���õ�test.properties�����ļ��еļ�ֵ�ԡ�
		 */
//		String name = pro.getProperty("name");
//		String wight = pro.getProperty("Wight");
//		String height = pro.getProperty("Height");
		
//		System.out.println("name:" + name + " wight:" + wight + " height:" + "height");
		
		/*
		 * �������ǿ��Խ������ļ��е����м�ֵ�Էŵ�һ��Enumeration�У��÷���ΪpropertyNames(),�÷��������������б��е�����ö��
		 */
//		Enumeration en = pro.propertyNames();
		
		//ʹ��ö�ٵĵ��������е�����ѯ
//		while(en.hasMoreElements()){
//			String strKey = (String) en.nextElement();
//			String strValue = pro.getProperty(strKey);
//			System.out.println("Key:" + strKey + " Value:" + strValue);
//		}
		
		/*
		 * ����������test.properties�����ļ��м�������
		 * 1����������������󣬲��ҹ������ļ�
		 */
		OutputStream out = new BufferedOutputStream(new FileOutputStream("src/cn/huang/filetest/test.properties"));
		
		/*
		 * 2.����һ��Properties���󣬲�����load���������еļ�ֵ�Զ��������б���
		 */
		Properties pro = new Properties();
		
		pro.setProperty("password", "123");
		pro.store(out, "password");
		
		
	}
}
