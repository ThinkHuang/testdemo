package cn.huang.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.security.SecureRandom;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

/**
 * 针对FileChannel的测试
 * @author huangyejun
 *
 */
public class FileChannelTest
{
    @Test
    public void transferNio()
    {
        RandomAccessFile fromFile = null;
        RandomAccessFile toFile = null;
        try
        {
            fromFile = new RandomAccessFile("src/cn/huang/nio/nio.txt", "rw");
            // 获取文件关联的通道
            FileChannel fromChannel = fromFile.getChannel();
            toFile = new RandomAccessFile("src/toFile.txt", "rw");
            FileChannel toChannel = toFile.getChannel();
            System.out.println(toChannel.size());
            
            long position = toChannel.size();
            long count = fromChannel.size();
            // 从from传输到to的两种做法
            fromChannel.transferTo(position, count, toChannel);
//            toChannel.transferFrom(fromChannel, position, count);
            System.out.println(toChannel.size());
        }
        catch (IOException e)
        {
        }
        finally
        {
            /**
             * 关闭资源
             */
            if (null != fromFile)
            {
                try
                {
                    fromFile.close();
                }
                catch (IOException e)
                {
                }
            }
            if (null != toFile)
            {
                try
                {
                    toFile.close();
                }
                catch (IOException e)
                {
                }
            }
        }
    }
    
    /**
     * 随机生成10个整数，然后写到缓存区，然后通过flip切换读写模式，最后从buffer中读出数据
     * 缓冲区没有关闭操作
     */
    @Test
    public void NIOTest1()
    {
        IntBuffer buffer = IntBuffer.allocate(10);
        for (int i = 0; i < 10; i++)
        {
            int random = new SecureRandom().nextInt(20);
            buffer.put(random);
        }
        
        buffer.flip();// 切换为读模式
        
        while (buffer.hasRemaining())
        {
            System.out.println(buffer.get());
        }
    }
    
    /**
     * 读取文件中的数据到buffer中，并从buffer读取数据
     */
    @Test
    public void NIOTest2()
    {
        FileInputStream fis = null;
        try
        {
            fis = new FileInputStream("src/cn/huang/nio/nio.txt");
            FileChannel channel = fis.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(512);
            // 将通道数据读取到缓冲区
            channel.read(buffer);
            
            buffer.flip();// 切换读写模式
            
            byte[] bytes = new byte[buffer.capacity()];
            int count = 0;
            while (buffer.hasRemaining())
            {
                bytes[count] = buffer.get();
                count++;
            }
            System.out.println(new String(bytes, "utf-8"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (null != fis)
                {
                    fis.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 写文件
     */
    @Test
    public void NIOTest3()
    {
        FileOutputStream fos = null;
        try
        {
            fos = new FileOutputStream("src/cn/huang/nio/toFile.txt");
            FileChannel channel = fos.getChannel();
            
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            String str = "hello world";
            
            buffer.put(str.getBytes("utf-8"));
            
            // 切换读写模式
            buffer.flip();
            
            channel.write(buffer);
            System.out.println("文件写入完成");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (null != fos)
                {
                    fos.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
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
            Set<SelectionKey> selectedKey = selector.selectedKeys();
            Iterator<SelectionKey> it = selectedKey.iterator();
            while (it.hasNext())
            {
                SelectionKey key = it.next();
                if ((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT)
                {//服务端的事件监听
                    ServerSocketChannel ssc = (ServerSocketChannel)key.channel();
                    SocketChannel sc = ssc.accept();// 接受服务端的请求
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ);
                    it.remove();
                }
                else if ((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ)
                {//客户端的事件处理
                    SocketChannel sc = (SocketChannel)key.channel();
                    while (true)
                    {
                        buffer.clear();
                        int n = sc.read(buffer);// 读取数据
                        if (n <= 0)
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
