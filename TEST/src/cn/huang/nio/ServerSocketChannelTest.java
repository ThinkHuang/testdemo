package cn.huang.nio;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ServerSocketChannelTest
{
    
    public void test() throws IOException{
        
        //打开一个ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        
        //关闭ServerSocketChannel
        serverSocketChannel.close();
        
        //以阻塞模式监听连接
        while(true)
        {
            serverSocketChannel.accept();
            break;
        }
        
        //以非阻塞模式监听连接
        while(true)
        {
            SocketChannel socketChannel = serverSocketChannel.accept();
            if(null == socketChannel){
                break;
            }
        }
    }
}
