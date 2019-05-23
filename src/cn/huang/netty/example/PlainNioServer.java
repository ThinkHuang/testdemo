package netty.example;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class PlainNioServer
{
    public void serve(int port) throws Exception
    {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        // config the asynchronous model
        serverChannel.configureBlocking(false);
        // related the channel with ServerSocket
        ServerSocket ssocket = serverChannel.socket();
        InetSocketAddress address = new InetSocketAddress(port);
        // binds the server to the selected port
        ssocket.bind(address);
        // open the selector for handling channel
        Selector selector = Selector.open();
        // register serverChannel to selector with accept operation
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        final ByteBuffer msg = ByteBuffer.wrap("Hi!\r\n".getBytes());
        for(;;)
        {
            // wait for new events to process;blocks until the next incoming event
            selector.select();
            
            // handle various I/O operation with SelectionKey
            Set<SelectionKey> readyKeys = selector.selectedKeys();
            Iterator<SelectionKey> it = readyKeys.iterator();
            while(it.hasNext())
            {
                SelectionKey key = it.next();
                it.remove();
                if(key.isAcceptable()) //checks if the event is new connection ready to be accepted
                {
                    ServerSocketChannel server = (ServerSocketChannel)key.channel();
                    SocketChannel client = server.accept();
                    client.configureBlocking(false);
                    client.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ, msg.duplicate());
                    System.out.printf("accepted connection from " + client);
                }
                if(key.isWritable()) // write datas to connected client
                {
                    SocketChannel client = (SocketChannel)key.channel();
                    ByteBuffer buffer = (ByteBuffer)key.attachment();
                    while(buffer.hasRemaining())
                    {
                        if(client.write(buffer) == 0)
                        {
                            break;
                        }
                    }
                    client.close();
                }
                key.channel().close();
            }
        }
    }
}
