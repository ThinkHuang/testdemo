package netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

// marks this class as one whose instances can be shared among channels
@Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf>
{
    /**
     * logs a dump of the received message
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception
    {
        System.out.println("Client received: " + msg.toString(CharsetUtil.UTF_8));
    }
    
    /**
     * when notified that the channel is active,sends a message
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception
    {
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
        
    }
    
    /**
     * on exception,logs the error and close channel
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
    {
        cause.printStackTrace();
        ctx.close();
    }
    
}
