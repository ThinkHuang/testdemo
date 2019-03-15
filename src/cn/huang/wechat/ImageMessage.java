package wechat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement()
@XmlType(propOrder = {
        "MediaId"
})
public class ImageMessage extends BaseMessage
{
    /**
     * 上传成功后返回的mediaId
     */
    private String MediaId;
    
    public String getMediaId()
    {
        return MediaId;
    }
    
    public void setMediaId(String mediaId)
    {
        MediaId = mediaId;
    }
}
