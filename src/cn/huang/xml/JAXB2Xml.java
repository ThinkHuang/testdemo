package xml;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

public class JAXB2Xml {
    
    public static void main(String[] args) {
        
        Root root = new Root();
        List<Org> orgs = new ArrayList<>();
        Org org = new Org();
        org.setOrgName("大数据研发中心");
        org.setOrgCode("yfzx");
        org.setOrgParentCode("parentCode");
        org.setOrgDLevel(1);
        org.setOrgSort(1);
        orgs.add(org);
        root.setOrgs(orgs);
        
        Org org1 = new Org();
        org1.setOrgName("大数据研发中心1");
        org1.setOrgCode("yfzx1");
        org1.setOrgParentCode("parentCode1");
        org1.setOrgDLevel(2);
        org1.setOrgSort(2);
        orgs.add(org1);
        
        root.setRootOrgCode("rootOrgCode");
        // 解决双下划线的问题
        XStream xstream = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
        xstream.ignoreUnknownElements();
        xstream.processAnnotations(Root.class);

       //XML序列化
       String xml = xstream.toXML(root);
       System.out.println(xml);
    }
    
}
