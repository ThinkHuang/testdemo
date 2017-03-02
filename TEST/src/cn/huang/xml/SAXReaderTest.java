package cn.huang.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.AbstractAttribute;


/**
 * �ܽ᣺����SAXReader��ȡ����ȡ�Ĳ���˳���������ģ�
 * 1�����������ļ��������ļ��ֽ�������һ���ļ���-->���л��׳�һЩ�쳣��
 * 2������SAXReader��ȡ�ļ���/�ļ��ֽ�����ȡ�ļ��е������ӽڵ�-->����������ӽڵ�ָ���Ǻ�Ԫ����ӽ����ӽڵ㣬
 * 		�������е�row�������ж��row�ӽڵ㣬���ﷵ�ص���һ��ElementԪ�ض��󡣶��row����List�б��С�
 * 3��ͨ�������ӽڵ��ȡÿ��Ԫ��-->������Ҫ�õ�iterator���������е�����ȡ�����ӽڵ��µ�Ԫ�ء��磺queryDTO.enterpriseId
 * 		���������ֵҲ�Ƕ��������ͬ����װ��һ��List�����У�Ȼ��Ԫ��ת��Ϊ�������ԣ��������Եõ�������������ֵ��
 * 4��Ҳ����ֱ��ͨ�������ӽڵ��attributeValue�����õ���Ӧ���Ե�����ֵ��
 * 
 * ע�⣺��������һ��������ĵط��ˣ����˵�ҵ�data�����ж���ӽڵ㣬row stuInfo sysInfo...etc��ô������ÿ�������ӽڵ㶼��Ҫ����һ��
 * List������װ�ظ��������Ԫ���ˣ���û��ʲô����취�أ�
 * ��ʱû��ʲô�취�ܹ�������������Ҳ��SAXReader�Ĳ���֮������Ϊ��SAXReader�����Ա�����Ƕ�ȡ�����ӽڵ���ؽ��ڴ��У�Ȼ���ȡ�����Ԫ�أ���������������
 * ���������ǿ��������ӽڵ�����ӽڵ��ˣ���ôSAXReader�������������ģ���ȡ����һ�������ӽڵ�Ͳ���֪����һ���ӽڵ�������ˡ�
 * 
 * ˵���������ܹ������ľ�����data�ڵ��¿����ж��row�ӽڵ㣬���ң�ÿ��row�ӽڵ��е�Ԫ�����ݿ��Բ�һ�¡�
 * @author huangyejun
 *
 */
public class SAXReaderTest{
	public static void main(String[] args){
		File xmlFile = new File("g:s.xml");
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(xmlFile);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("file is not exists!");
		}
		SAXReader saxReader = new SAXReader();
		List rowList = null;
		Node node = null;
		try {
			//�����ĵ���Ӧʵ��
			Document doc = saxReader.read(fis);
			//��ȡָ��·���µ�Ԫ���б�����ָ��ȡ���е�data�µ�rowԪ��
			rowList = doc.selectNodes("//data/row");
			} catch (DocumentException e) {
			e.printStackTrace();
		}
			for(Iterator iter = rowList.iterator();iter.hasNext();){
				//��þ����rowԪ��
				Element element = (Element) iter.next();
				//���rowԪ�ص����������б�
				List elementList = element.attributes();
				for(Iterator iter1 = elementList.iterator();iter1.hasNext();){
					//��ÿ������ת��Ϊһ���������ԣ�Ȼ���ȡ�����ֺ�ֵ
					AbstractAttribute aa = (AbstractAttribute) iter1.next();
					System.out.println("Name:" + aa.getName() + ";Value:" +aa.getValue());
				}
				System.out.println(element.getName());
				
				System.out.println(element.attributeValue("queryDTO.enterpriseId"));
				
				System.out.println(element.elementText("width"));
			}
	}
	
}
