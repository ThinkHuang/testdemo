package cn.huang.socket.framing;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 基于定界符的成帧方式
 * @author huangyejun
 *
 */
public class DelimFramer implements Framer
{
    
    private InputStream in = null; // 定义数据来源
    
    private static final byte DELIMITER = '\n'; // 以换行符为定界符
    
    public DelimFramer(InputStream in)
    {
        this.in = in;
    }
    
    @Override
    public void frameMsg(byte[] message, OutputStream out) throws IOException
    {
        for (byte b : message)
        {
            if (b == DELIMITER)
            {
                throw new IOException("message contains Delimiter");
            }
        }
        out.write(message);
        out.write(DELIMITER);// 该方法虽然接收的是int型的数据，但是，它只会保留int的低八位比特数据，高24位比特数据将被忽略
        out.flush();
    }
    
    @Override
    public byte[] nextMsg() throws IOException
    {
        ByteArrayOutputStream messageBuffer = new ByteArrayOutputStream();
        int nextByte;
        
        while ((nextByte = in.read()) != DELIMITER)
        {
            // 数据流读取完成
            if (nextByte == -1)
            {
                // 如果消息不为空，则说明消息没有定界符，属于不完整的消息
                if (messageBuffer.size() > 0)
                {
                    throw new IOException("Non-empty message without delimiter");
                }
                else
                {
                    return null;
                }
            }
            else
            {
                messageBuffer.write(nextByte);
            }
        }
        return messageBuffer.toByteArray();
    }
    
}
