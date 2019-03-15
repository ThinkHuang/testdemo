package cn.huang.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

public class SelectorTest
{
    
    public void test() throws IOException
    {
        Selector selector = Selector.open();
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.configureBlocking(false);// 设置非阻塞模式
        channel.register(selector, SelectionKey.OP_ACCEPT);
        while (true)
        {
            int readyChannels = selector.select();// 以阻塞模式等待通道状态可用
            if (readyChannels == 0)
                continue;// 没有通道状态准备好了
                
            // 说明有通道的状态准备好了，获取已经准备就绪的所有通道
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> it = selectedKeys.iterator();
            while (it.hasNext())
            {
                SelectionKey selectionkey = it.next();
                if (selectionkey.isAcceptable())
                {
                    // a connection was accpeted by ServerSocketChannel
                }
                else if (selectionkey.isConnectable())
                {
                    // a connection was established with a remote server
                }
                else if (selectionkey.isReadable())
                {
                    // a channel is ready for read
                }
                else if (selectionkey.isWritable())
                {
                    // a channel is ready for write
                }
                // 处理完事件后需要将该通道移除准备就绪队列，下次该通道就绪后Selector就将该动刀加入到就绪队列
                it.remove();
            }
        }
    }
    
    /**
     * socket nio
     * 服务端socket和客户端socket都在同一个线程中
     * @throws IOException
     */
    @Test
    public void NIOTest4() throws IOException
    {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Selector selector = Selector.open();
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.configureBlocking(false);// 设置为非阻塞模式
        channel.bind(new InetSocketAddress(8080));// 绑定服务端口到8080上
        channel.register(selector, SelectionKey.OP_ACCEPT);// 注册监听accept监听事件
        while (true)
        {
            Set<SelectionKey> selectionKey = selector.selectedKeys();
            Iterator<SelectionKey> it = selectionKey.iterator();
            while (it.hasNext())
            {
                SelectionKey key = it.next();
                if ((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT)
                {// 服务端的事件监听
                    ServerSocketChannel ssc = (ServerSocketChannel)key.channel();
                    SocketChannel sc = ssc.accept();// 接受服务端的请求
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ);
                    it.remove();
                }
                else if ((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ)
                {// 客户端的事件处理
                    SocketChannel sc = (SocketChannel)key.channel();
                    while (true)
                    {
                        buffer.clear();
                        int n = sc.read(buffer);// 读取数据
                        if (n <= 0)//读到了文件末尾
                        {
                            break;
                        }
                        buffer.flip();
                    }
                    it.remove();
                }
            }
        }
        
    }
}
