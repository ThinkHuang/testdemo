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
 * 该类主要是利用w3c来进行XML的节点创建和XML元素的读取。
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
		//DOMTest1();//给person.xml设置属性和节点。
		readXMLByDom("f:/person.xml");//读取person.xml中的元素
	}
	

	
	
	private static void readXMLByDom(String xmlPath) {
		//初始化DocumentBuilderFactory文档创建工厂，并且将指定目录下的文件解析为一个XML,得到一个Document对象
		init(xmlPath);
		//得到XML文档中的根元素
		Element element = doc.getDocumentElement();
		System.out.println("Root element:" + element.getTagName());
		//得到节点链的长度，这里的代码和读取没有关系，仅仅作为信息输出。
		NodeList nodeList = doc.getElementsByTagName("person");
		System.out.println("book节点链的长度：" + nodeList.getLength());
		//这里将得到父节点person,book下面有很多的person节点，person节点找到的方式是由父节点调用getElementsByTagName方法得到的
		Node fatherNode = nodeList.item(0);
		//这里得到的是第一个子节点为person的Node。
		System.out.println("父节点为：" + fatherNode.getNodeName());
		
		//如果为Element，得到fatherNode节点的属性值，否则为空。-->其实就是说，看fatherNode这个节点是否是元素节点，如果是的话，就返回一个NamedNodeMap，其实质就是一个Map，不过却是NodeMap
		NamedNodeMap attributes = fatherNode.getAttributes();
		System.out.println("attribute:" + attributes.getLength());
		for(int i = 0; i < attributes.getLength(); i++){
			Node attribute = attributes.item(i);
			System.out.println("book的属性名为：" + attribute.getNodeName()
					+ " 相对应的属性值为：" + attribute.getNodeName());
		}
		
		//得到第一个子节点为person的Node后，通过该Node将得到其节点下的属性。
		NodeList childNodes = fatherNode.getChildNodes();
		System.out.println("childNode:" + childNodes.getLength());
		//循环遍历出person节点下的子节点的名称及其对应的值。
		for(int j = 0; j < childNodes.getLength(); j++){
			Node childNode = childNodes.item(j);
			if(childNode instanceof Element){
				System.out.println("子节点名为：" + childNode.getNodeName()
						+ " 相对应的值为：" + childNode.getNodeName());
			}
		}
	}




	private static void init(String xmlPath) {
		try {
			//获取DocumentBuilderFactory的新实例
			//DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			//使用当前配置的参数创建一个新的DocumentBuilder新实例
			//DocumentBuilder db = dbf.newDocumentBuilder();
			//将给定输入源的内容解析为一个XML文档，并且返回一个新的DOM Document对象。
			doc = builder.parse(new File(xmlPath));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}




	public static void DOMTest1(){
		try {
			doc= builder.newDocument();
			//由上面的doc得到了Document对象，创建根节点
			Element root = doc.createElement("老师");
			//下面是三个子节点。
			Element wang = doc.createElement("王");
			Element liu = doc.createElement("刘");
			Element row = doc.createElement("row");
			//在每个子节点下面增加末节点元素并设置值
			wang.appendChild(doc.createTextNode("我是王老师"));
			liu.appendChild(doc.createTextNode("我是刘老师"));
			//这个节点比较特殊，在一个节点中有三个键值对。
			row.appendChild(doc.createTextNode("queryDTO.enterpriseId=\"gdf\" "));
			row.appendChild(doc.createTextNode("queryDTO.loginName=\"gdfs\" "));
			row.appendChild(doc.createTextNode("queryDTO.state=\"0\""));
			
			//将创建的子节点”挂“到根节点root下。
			root.appendChild(wang);
			root.appendChild(liu);
			root.appendChild(row);
			
			doc.appendChild(root);//将root下的子节点加入到root下面，将root更节点和Document对象绑定，便于操作其中的节点对象。
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "gb2312");//设置创建的编码格式
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");//设置是否缩进。
			transformer.transform(new DOMSource(doc), 
					new StreamResult(outfile));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
