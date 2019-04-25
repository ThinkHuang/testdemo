package netty.server;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class EchoServer
{
    
    /**
     * 定义端口
     */
    private final Integer port;
    
    public EchoServer(Integer port)
    {
        this.port = port;
    }
    
    public static void main(String[] args) throws Exception
    {
//        if(args.length != 1)
//        {
//            System.err.println("Usage:" + EchoServer.class.getSimpleName() + "<port>");
//        }
        
        int port = 8088;//Integer.parseInt(args[0]);
        new EchoServer(port).start();
    }

    private void start() throws Exception
    {
        // creates server handler instance
        final EchoServerHandler serverHandler = new EchoServerHandler();
        // creates EventLoopGroup
        EventLoopGroup group = new NioEventLoopGroup();
        // creates ServerBootstrap
        ServerBootstrap boot = new ServerBootstrap();
        try
        {
            boot.group(group)
            // specifies the use of an NIO transport Channel
            .channel(NioServerSocketChannel.class)
            // set the socket address using the specified port
            .localAddress(new InetSocketAddress(port))
            // add the EchoServerHandler to Channel's ChannelPipeline
            .childHandler(new ChannelInitializer<Channel>()
            {
    
                @Override
                protected void initChannel(Channel ch) throws Exception
                {
                    // EchoServerHandler is @Sharable so we can always use the same one
                    ch.pipeline().addLast(serverHandler);
                }
                
            });
            //bind the server synchronously,sync() waits for the bind to complete
            ChannelFuture future = boot.bind().sync();
            // gets the CloseFuture of channel and blocks the current thread until it's completed
            future.channel().closeFuture().sync();
        }
        finally
        {
            // shutdown the EventLoopGroup,release all resources
            group.shutdownGracefully().sync();
        }
        
    }
}
