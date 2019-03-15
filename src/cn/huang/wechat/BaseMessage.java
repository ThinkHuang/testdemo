package wechat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 回复用户消息基础类
 * @author huangyejun
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement()
@XmlType(propOrder = {
        "ToUserName",
        "FromUserName",
        "CreateTime",
        "MsgType"
})
public class BaseMessage
{
    private String ToUserName;
    
    private String FromUserName;
    
    private long CreateTime;
    
    private String MsgType;
    
    public String getToUserName()
    {
        return ToUserName;
    }
    
    public void setToUserName(String toUserName)
    {
        ToUserName = toUserName;
    }
    
    public String getFromUserName()
    {
        return FromUserName;
    }
    
    public void setFromUserName(String fromUserName)
    {
        FromUserName = fromUserName;
    }
    
    public long getCreateTime()
    {
        return CreateTime;
    }
    
    public void setCreateTime(long createTime)
    {
        CreateTime = createTime;
    }
    
    public String getMsgType()
    {
        return MsgType;
    }
    
    public void setMsgType(String msgType)
    {
        MsgType = msgType;
    }
}
