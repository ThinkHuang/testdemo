package cn.huang.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.security.SecureRandom;

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
        FileChannel channel = null;
        try
        {
            fis = new FileInputStream("src/cn/huang/nio/nio.txt");
            channel = fis.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(512);
            
            System.out.println("通道数据大小:" + channel.size());
            channel.position(20);
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
                fis.close();
                channel.close();
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
        FileChannel channel = null;
        try
        {
            fos = new FileOutputStream("src/cn/huang/nio/toFile.txt");
            channel = fos.getChannel();
            
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            String str = "hello world";
            
            buffer.put(str.getBytes("utf-8"));
            
            // 切换读写模式
            buffer.flip();
            
            channel.write(buffer);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                fos.close();
                channel.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    @Test
    public void NIOTest4()
    {
        RandomAccessFile aFile = null;
        FileChannel channel = null;
        try
        {
            aFile = new RandomAccessFile("src/cn/huang/nio/nio.txt", "rw");
            channel = aFile.getChannel();
           //截取文件
            channel.truncate(20);//会删除掉源文件的内容
            // 读取channel中的文件大小
            System.out.println(channel.size());
            // 从指定的位置读取文件
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            //读模式时，position设置为文件末尾以后
            channel.position(40);
            int bytesRead = channel.read(buffer);
            System.out.println(bytesRead);
            // 切换读写模式
            buffer.flip();
            
            byte[] bytes = new byte[buffer.capacity()];
            int count = 0;
            while (buffer.hasRemaining())
            {
                bytes[count] = buffer.get();
                count++;
            }
            System.out.println(new String(bytes, "utf-8"));
            
            buffer.compact();//清除掉已经读取的数据
            
            //当为写模式时，设置position在文件末尾以后，如果要读入的话，需要将channel的position复原
            String str = "只是为了测试position的写模式";
            buffer.put(str.getBytes("utf-8"));
            
            buffer.flip();
            //channel.position();
            channel.write(buffer);//往源文件中附加数据，相当于append
            
            channel.force(true);//每次都强制让缓冲区的内容刷新到磁盘文件中
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                channel.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
