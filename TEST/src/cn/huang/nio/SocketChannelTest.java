package cn.huang.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketChannelTest
{
    public void test() throws IOException
    {
        // ��һ��SocketChannel����
        SocketChannel channel = SocketChannel.open();
        // �Է�����ģʽ����
        channel.configureBlocking(false);
        channel.connect(new InetSocketAddress("http://jenkov.com", 80));
        
        // �ر�һ��SocketChannel����
        channel.close();
        
        // ��һ��SocketChannel�����ж�ȡ����
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer);
        
        // ��SocketChannel��д������
        String newData = "New String to write to file..." + System.currentTimeMillis();
        
        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.clear();// ��position��Ϊ0
        buf.put(newData.getBytes());
        
        // �л���дģʽ
        buf.flip();
        
        //���첽ģʽ�£��������Connectʱ����Ҫʹ�������finishConnect����У�������Ƿ����
        channel.finishConnect();
        
        while (buf.hasRemaining())
        {
            channel.write(buf);
        }
    }
}
