package cn.huang.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

import org.junit.Test;

public class PipeTest
{
    /**
     * 从线程A到线程B的单向数据流向
     * ThreadA --> Sink Channel --> Source Channel --> ThreadB
     * @throws IOException
     */
    @Test
    public void test() throws IOException{
        //创建管道
        Pipe pipe = Pipe.open();
        
        //向管道中写入数据
        Pipe.SinkChannel sinkChannel = pipe.sink();
        //调用write方法写入数据
        String newData = "New String to write to file..." + System.currentTimeMillis();
        //创建缓冲区
        ByteBuffer wBuffer = ByteBuffer.allocate(1024);
        //设置缓冲区的position为0
        wBuffer.clear();
        //将待发送的数据发送到缓冲区中
        wBuffer.put(newData.getBytes());
        /*
         * 切换读写模式，不需要管flip方法是从读模式切换到写模式，还是从写模式切换到读模式
         * 只需要记住一点的是，调用flip方法将会是position归0，并且将limit置于position未归0的位置
         * 所以在下面的管道写入和读取中可以使用同一个buffer来做缓冲区
         */
        wBuffer.flip();
        //读取缓冲区中的数据到管道中
        while(wBuffer.hasRemaining()){
            int byteWritten = sinkChannel.write(wBuffer);
            System.out.println("已写字节数：" + byteWritten);
        }
        
        //从管道中读取数据
        Pipe.SourceChannel sourceChannel = pipe.source();
        //ByteBuffer rBuffer = ByteBuffer.allocate(1024);
        wBuffer.flip();
        //read代表实际的读取到的字节数
        int byteRead = sourceChannel.read(wBuffer);
        System.out.println("已读字节数：" + byteRead);
    }
}
