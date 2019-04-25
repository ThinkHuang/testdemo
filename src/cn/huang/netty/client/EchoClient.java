package netty.client;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class EchoClient
{
    
    private final String host;
    
    private final Integer port;
    
    public EchoClient(String host, Integer port)
    {
        this.host = host;
        this.port = port;
    }
    
    public static void main(String[] args) throws Exception
    {
//        if (args.length != 2)
//        {
//            System.err.println("Usage: " + EchoClient.class.getSimpleName() + " <host> <port>");
//            return;
//        }
        
        String host = "127.0.0.1";// args[0];
        int port = 8088;// Integer.parseInt(args[1]);
        new EchoClient(host, port).start();
    }
    
    private void start() throws Exception
    {
        final EchoClientHandler clientHandler = new EchoClientHandler();
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap boot = new Bootstrap();
        try
        {
            boot.group(group).channel(NioSocketChannel.class).remoteAddress(new InetSocketAddress(host, port)).handler(new ChannelInitializer<Channel>()
            {
                
                @Override
                protected void initChannel(Channel ch) throws Exception
                {
                    ch.pipeline().addLast(clientHandler);
                }
                
            });
            
            // note that: the following Bootstrap is connect to server,not bind to the server
            ChannelFuture future = boot.connect().sync();
            future.channel().closeFuture().sync();
        }
        finally
        {
            group.shutdownGracefully().sync();
        }
        
    }
}
