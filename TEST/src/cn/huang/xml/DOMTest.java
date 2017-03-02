package cn.huang.xml;

import java.io.File;
import java.security.KeyStore.Builder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * ������Ҫ������w3c������XML�Ľڵ㴴����XMLԪ�صĶ�ȡ��
 * @author huangyejun
 *
 */
public class DOMTest {
	private static String infile = "f:\\people.xml";
	private static String outfile = "f:\\people.xml";
	private static Document doc = null;
	private static DocumentBuilder builder = null;
	
	static{
		try {
			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args) {
		//DOMTest1();//��person.xml�������Ժͽڵ㡣
		readXMLByDom("f:/person.xml");//��ȡperson.xml�е�Ԫ��
	}
	

	
	
	private static void readXMLByDom(String xmlPath) {
		//��ʼ��DocumentBuilderFactory�ĵ��������������ҽ�ָ��Ŀ¼�µ��ļ�����Ϊһ��XML,�õ�һ��Document����
		init(xmlPath);
		//�õ�XML�ĵ��еĸ�Ԫ��
		Element element = doc.getDocumentElement();
		System.out.println("Root element:" + element.getTagName());
		//�õ��ڵ����ĳ��ȣ�����Ĵ���Ͷ�ȡû�й�ϵ��������Ϊ��Ϣ�����
		NodeList nodeList = doc.getElementsByTagName("person");
		System.out.println("book�ڵ����ĳ��ȣ�" + nodeList.getLength());
		//���ｫ�õ����ڵ�person,book�����кܶ��person�ڵ㣬person�ڵ��ҵ��ķ�ʽ���ɸ��ڵ����getElementsByTagName�����õ���
		Node fatherNode = nodeList.item(0);
		//����õ����ǵ�һ���ӽڵ�Ϊperson��Node��
		System.out.println("���ڵ�Ϊ��" + fatherNode.getNodeName());
		
		//���ΪElement���õ�fatherNode�ڵ������ֵ������Ϊ�ա�-->��ʵ����˵����fatherNode����ڵ��Ƿ���Ԫ�ؽڵ㣬����ǵĻ����ͷ���һ��NamedNodeMap����ʵ�ʾ���һ��Map������ȴ��NodeMap
		NamedNodeMap attributes = fatherNode.getAttributes();
		System.out.println("attribute:" + attributes.getLength());
		for(int i = 0; i < attributes.getLength(); i++){
			Node attribute = attributes.item(i);
			System.out.println("book��������Ϊ��" + attribute.getNodeName()
					+ " ���Ӧ������ֵΪ��" + attribute.getNodeName());
		}
		
		//�õ���һ���ӽڵ�Ϊperson��Node��ͨ����Node���õ���ڵ��µ����ԡ�
		NodeList childNodes = fatherNode.getChildNodes();
		System.out.println("childNode:" + childNodes.getLength());
		//ѭ��������person�ڵ��µ��ӽڵ�����Ƽ����Ӧ��ֵ��
		for(int j = 0; j < childNodes.getLength(); j++){
			Node childNode = childNodes.item(j);
			if(childNode instanceof Element){
				System.out.println("�ӽڵ���Ϊ��" + childNode.getNodeName()
						+ " ���Ӧ��ֵΪ��" + childNode.getNodeName());
			}
		}
	}




	private static void init(String xmlPath) {
		try {
			//��ȡDocumentBuilderFactory����ʵ��
			//DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			//ʹ�õ�ǰ���õĲ�������һ���µ�DocumentBuilder��ʵ��
			//DocumentBuilder db = dbf.newDocumentBuilder();
			//����������Դ�����ݽ���Ϊһ��XML�ĵ������ҷ���һ���µ�DOM Document����
			doc = builder.parse(new File(xmlPath));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}




	public static void DOMTest1(){
		try {
			doc= builder.newDocument();
			//�������doc�õ���Document���󣬴������ڵ�
			Element root = doc.createElement("��ʦ");
			//�����������ӽڵ㡣
			Element wang = doc.createElement("��");
			Element liu = doc.createElement("��");
			Element row = doc.createElement("row");
			//��ÿ���ӽڵ���������ĩ�ڵ�Ԫ�ز�����ֵ
			wang.appendChild(doc.createTextNode("��������ʦ"));
			liu.appendChild(doc.createTextNode("��������ʦ"));
			//����ڵ�Ƚ����⣬��һ���ڵ�����������ֵ�ԡ�
			row.appendChild(doc.createTextNode("queryDTO.enterpriseId=\"gdf\" "));
			row.appendChild(doc.createTextNode("queryDTO.loginName=\"gdfs\" "));
			row.appendChild(doc.createTextNode("queryDTO.state=\"0\""));
			
			//���������ӽڵ㡱�ҡ������ڵ�root�¡�
			root.appendChild(wang);
			root.appendChild(liu);
			root.appendChild(row);
			
			doc.appendChild(root);//��root�µ��ӽڵ���뵽root���棬��root���ڵ��Document����󶨣����ڲ������еĽڵ����
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "gb2312");//���ô����ı����ʽ
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");//�����Ƿ�������
			transformer.transform(new DOMSource(doc), 
					new StreamResult(outfile));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
