package cn.huang.socket.framing;

import java.io.IOException;
import java.io.OutputStream;

public interface Framer
{
    /**
     * 添加成帧信息并将指定消息输出到指定流
     * @param message
     * @param out
     * @throws IOException
     */
    void frameMsg(byte[] message, OutputStream out) throws IOException;
    
    /**
     * 获取下一条消息
     * @return
     * @throws IOException
     */
    byte[] nextMsg() throws IOException;
}
