package netty.example;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.oio.OioServerSocketChannel;

@SuppressWarnings("deprecation")
public class NettyOioServer
{
    
    public void serve(int port) throws InterruptedException
    {
        final ByteBuf buffer = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("Hi!\r\n".getBytes(Charset.forName("UTF-8"))));
        
        // create EventLoopGroup
        EventLoopGroup group = new OioEventLoopGroup();
        // create ServerBootstrap
        ServerBootstrap server = new ServerBootstrap();
        try
        {
            server.group(group)
                // use OioServerSocketChannel to allow blocking mode(old I/O)
                .channel(OioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(port))
                // specifies ChannelInitializer that will be called for each accepted connetion
                .childHandler(new ChannelInitializer<SocketChannel>()
                {
    
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception
                    {
                        // add a ChannelInboundHandlerAdapter to intercept and handle events
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter()
                        {
                            
                            @Override
                            public void channelActive(ChannelHandlerContext context)
                            {
                                // write a message to client and adds ChannelFutureListener to close connection once message is written
                                context.writeAndFlush(buffer.duplicate()).addListener(ChannelFutureListener.CLOSE);
                            }
                        });
                    }
                });
                // bind server to accept connections
                ChannelFuture future = server.bind().sync();
                future.channel().closeFuture().sync();
        }
        finally
        {
            // release all resources
            group.shutdownGracefully().sync();
        }
    }
}
