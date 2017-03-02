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
 * 总结：利用SAXReader读取器读取的步骤顺序是这样的：
 * 1、首先利用文件流或者文件字节流关联一个文件。-->其中会抛出一些异常，
 * 2、利用SAXReader读取文件流/文件字节流读取文件中的最下子节点-->这里的最下子节点指的是和元素最接近的子节点，
 * 		如下例中的row，可能有多个row子节点，这里返回的是一个Element元素对象。多个row存在List列表中。
 * 3、通过最下子节点获取每个元素-->这里面要用到iterator迭代器进行迭代读取最下子节点下的元素。如：queryDTO.enterpriseId
 * 		这里的属性值也是多个，所以同样是装在一个List容器中，然后将元素转化为抽象属性，这样可以得到属性名和属性值。
 * 4、也可以直接通过最下子节点的attributeValue方法得到对应属性的属性值。
 * 
 * 注意：这样就有一个不方便的地方了，如果说我的data下面有多个子节点，row stuInfo sysInfo...etc那么我岂不是每个最下子节点都需要创建一个
 * List集合来装载各自下面的元素了？有没有什么解决办法呢？
 * 暂时没有什么办法能够做到，我想这也是SAXReader的不便之处，因为，SAXReader的特性本身就是读取最下子节点加载进内存中，然后读取里面的元素，而针对于我上面的
 * 描述，这是跨了两个子节点或多个子节点了，那么SAXReader本身是做不到的，读取了这一个最下子节点就不能知道上一个子节点的数据了。
 * 
 * 说明：现在能够做到的就是在data节点下可以有多个row子节点，并且，每个row子节点中的元素内容可以不一致。
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
			//生成文档对应实体
			Document doc = saxReader.read(fis);
			//获取指定路径下的元素列表，这里指获取所有的data下的row元素
			rowList = doc.selectNodes("//data/row");
			} catch (DocumentException e) {
			e.printStackTrace();
		}
			for(Iterator iter = rowList.iterator();iter.hasNext();){
				//获得具体的row元素
				Element element = (Element) iter.next();
				//获得row元素的所有属性列表
				List elementList = element.attributes();
				for(Iterator iter1 = elementList.iterator();iter1.hasNext();){
					//将每个属性转化为一个抽象属性，然后获取其名字和值
					AbstractAttribute aa = (AbstractAttribute) iter1.next();
					System.out.println("Name:" + aa.getName() + ";Value:" +aa.getValue());
				}
				System.out.println(element.getName());
				
				System.out.println(element.attributeValue("queryDTO.enterpriseId"));
				
				System.out.println(element.elementText("width"));
			}
	}
	
}
