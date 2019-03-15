package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class DatagramChannelTest
{
    public void test() throws IOException{
        //打开一个DatagramChannel
        DatagramChannel channel = DatagramChannel.open();
        //绑定到指定端口上
        channel.socket().bind(new InetSocketAddress(9999));
        
        //接收数据receive
        ByteBuffer buffer = ByteBuffer.allocate(1024);
      //设置position为0
        buffer.clear();
        //超出buffer的数据将会被丢弃
        channel.receive(buffer);
        
        //发送数据send
        String newData = "new String to write to file..." + System.currentTimeMillis();
        buffer.put(newData.getBytes());
        
        //切换读写模式
        buffer.flip();
        
        int bytesend = channel.send(buffer, new InetSocketAddress("jenkov.com", 80));
        
        //链接特定的机器地址
        channel.connect(new InetSocketAddress("jenkov.com", 80));
        //当链接成功了以后，就可以使用传统通道那样使用read和write了
        int bytesRead = channel.read(buffer);
        int bytesWrite = channel.write(buffer);
    }
    
}
