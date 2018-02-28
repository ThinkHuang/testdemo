package cn.huang.socket.messagevote;

import java.io.IOException;

/**
 * 消息操作接口
 * @author huangyejun
 *
 */
public interface VoteMsgCoder
{
    /**
     * 根据特定的协议，将消息转换为一个字节序列
     * @param msg
     * @return
     * @throws IOException
     */
    byte[] toWire(VoteMsg msg) throws IOException;
    
    /**
     * 根据相同的协议，解析给定的字节序列，并封装成一个消息实例返回
     * @param input
     * @return
     * @throws IOException
     */
    VoteMsg fromWire(byte[] input) throws IOException;
}
