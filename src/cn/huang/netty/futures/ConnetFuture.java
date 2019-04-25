package netty.futures;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ConnetFuture
{
    
    public void test()
    {
        Channel channel = new NioSocketChannel();
        ChannelFuture future = channel.connect(new InetSocketAddress("192.168.0.1", 25));
        future.addListener(new ChannelFutureListener()
        {
            
            @Override
            public void operationComplete(ChannelFuture future) throws Exception
            {
                if(future.isSuccess())
                {
                    ByteBuf buffer = Unpooled.copiedBuffer("Hello", Charset.defaultCharset());
                    ChannelFuture wf = future.channel().writeAndFlush(buffer);
                }
                else
                {
                    Throwable cause = future.cause();
                    cause.printStackTrace();
                }
            }
        });
    }
}
