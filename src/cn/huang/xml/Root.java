package xml;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("ROOT")
public class Root implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 8365879165904326749L;
    
    // 去除外部重复节点
    @XStreamImplicit()
    @XStreamAlias("ORG")
    private List<Org> orgs;
    
    @XStreamAlias("ROOTORGCODE")
    private String rootOrgCode;
    
    public List<Org> getOrgs() {
        return orgs;
    }
    
    public void setOrgs(List<Org> orgs) {
        this.orgs = orgs;
    }
    
    public String getRootOrgCode() {
        return rootOrgCode;
    }
    
    public void setRootOrgCode(String rootOrgCode) {
        this.rootOrgCode = rootOrgCode;
    }
    
}
