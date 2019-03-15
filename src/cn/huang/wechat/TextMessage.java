package wechat;

/**
 * 文本消息
 * @author huangyejun
 *
 */
public class TextMessage extends BaseMessage
{
    /**
     * 回复内容
     */
    private String Content;
    
    public String getContent()
    {
        return Content;
    }
    
    public void setContent(String content)
    {
        Content = content;
    }
    
}
