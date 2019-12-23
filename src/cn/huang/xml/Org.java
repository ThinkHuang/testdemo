package xml;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("ORG")
public class Org implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 2126825442101444662L;
    
    @XStreamAlias("ORG_NAME")
    private String orgName;
    
    @XStreamAlias("ORG_SIMPLENAME")
    private String orgSimpleName;
    
    @XStreamAlias("ORG_CODE")
    private String orgCode;
    
    @XStreamAlias("ORG_PARENT_CODE")
    private String orgParentCode;
    
    @XStreamAlias("ORG_DLEVEL")
    private Integer orgDLevel;
    
    @XStreamAlias("ORG_SORT")
    private Integer orgSort;
    
    public String getOrgName() {
        return orgName;
    }
    
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
    
    public String getOrgSimpleName() {
        return orgSimpleName;
    }
    
    public void setOrgSimpleName(String orgSimpleName) {
        this.orgSimpleName = orgSimpleName;
    }
    
    public String getOrgCode() {
        return orgCode;
    }
    
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }
    
    public String getOrgParentCode() {
        return orgParentCode;
    }
    
    public void setOrgParentCode(String orgParentCode) {
        this.orgParentCode = orgParentCode;
    }
    
    public Integer getOrgDLevel() {
        return orgDLevel;
    }
    
    public void setOrgDLevel(Integer orgDLevel) {
        this.orgDLevel = orgDLevel;
    }
    
    public Integer getOrgSort() {
        return orgSort;
    }
    
    public void setOrgSort(Integer orgSort) {
        this.orgSort = orgSort;
    }
    
}
