package cn.huang.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketChannelTest
{
    public void test() throws IOException
    {
        // 打开一个SocketChannel连接
        SocketChannel channel = SocketChannel.open();
        // 以非阻塞模式运行
        channel.configureBlocking(false);
        channel.connect(new InetSocketAddress("http://jenkov.com", 80));
        
        // 关闭一个SocketChannel连接
        channel.close();
        
        // 从一个SocketChannel连接中读取数据
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer);
        
        // 向SocketChannel中写入数据
        String newData = "New String to write to file..." + System.currentTimeMillis();
        
        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.clear();// 将position置为0
        buf.put(newData.getBytes());
        
        // 切换读写模式
        buf.flip();
        
        //在异步模式下，如果进行Connect时，需要使用下面的finishConnect方法校验连接是否完成
        channel.finishConnect();
        
        while (buf.hasRemaining())
        {
            channel.write(buf);
        }
    }
}
