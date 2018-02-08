package cn.huang.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class PipeTest
{
    /**
     * 从线程A到线程B的单向数据流向
     * ThreadA --> Sink Channel --> Source Channel --> ThreadB
     * @throws IOException
     */
    public void test() throws IOException{
        //创建管道
        Pipe pipe = Pipe.open();
        
        //向管道中写入数据
        Pipe.SinkChannel sinkChannel = pipe.sink();
        //调用write方法写入数据
        String newData = "New String to write to file..." + System.currentTimeMillis();
        //创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //设置缓冲区的position为0
        buffer.clear();
        //将待发送的数据发送到缓冲区中
        buffer.put(newData.getBytes());
        //切换读写模式
        buffer.flip();
        //读取缓冲区中的数据到管道中
        while(buffer.hasRemaining()){
            sinkChannel.write(buffer);
        }
        
        //从管道中读取数据
        Pipe.SourceChannel sourceChannel = pipe.source();
        //read代表实际的读取到的字节数
        int byteRead = sourceChannel.read(buffer);
    }
}
