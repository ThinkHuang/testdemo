package nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

public class AsynchronousFileChannelTest
{
    public static void main(String[] args)
    {
        
    }
    
    /**
     * 读
     */
    public void read(){
        Path path = Paths.get("nio.txt");
        AsynchronousFileChannel channel = null;
        try
        {
            channel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            long position = 0;
            //通过Future来读取数据
            Future<Integer> operation = channel.read(buffer, position);
            while(!operation.isDone());//当操作没有完成前，继续
            buffer.flip();
            byte[] bytes = new byte[buffer.limit()];
            buffer.get();
            System.out.println(new String(bytes));
            buffer.clear();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        /**
         * 通过CompletionHandler读取数据
         */
        try
        {
            channel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            channel.read(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {

                //读取完成将触发该方法
                @Override
                public void completed(Integer result, ByteBuffer attachment)
                {
                    attachment.flip();
                    byte[] bytes = new byte[attachment.limit()];
                    attachment.get(bytes);
                    System.out.println(new String(bytes));
                    attachment.clear();
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment)
                {
                    
                }
                
            });
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * 写
     */
    public void write(){
        Path path = Paths.get("data/test-write.txt");
        if(Files.exists(path))
            try
            {
                Files.createFile(path);
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
        AsynchronousFileChannel channel = null;
        try
        {
            channel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            long position = 0;
            buffer.put("test data".getBytes());
            buffer.flip();
            Future<Integer> operation = channel.write(buffer, position);
            buffer.clear();
            
            while(!operation.isDone());
            
            System.out.println("Write done");
            
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        /**
         * 采用CompletionHandler进行数据写入
         */
        try
        {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            long position = 0;
            
            buffer.put("test data".getBytes());
            buffer.flip();
            
            channel.write(buffer, position, buffer, new CompletionHandler<Integer, ByteBuffer>() {

                @Override
                public void completed(Integer result, ByteBuffer attachment)
                {
                    System.out.println("bytes written: " + result);
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment)
                {
                    
                }
                
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
}
