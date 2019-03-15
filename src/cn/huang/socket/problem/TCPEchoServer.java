package socket.problem;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class TCPEchoServer
{
    private static final int BUFFERSIZE = 32;
    
    public static void main(String[] args) throws IOException
    {
        if (args.length != 1)  {// Test for correct # of args  
            throw new IllegalArgumentException("Parameter(s): <Port>");
        }
        
        int servPort = Integer.parseInt(args[0]); 
        ServerSocket server = new ServerSocket(servPort);
        
        int recvMsgSize; //Size of received message
        byte[] buf = new byte[BUFFERSIZE];
        
        while(true) {
            Socket client = server.accept();
            SocketAddress clientAddress = client.getRemoteSocketAddress();  
            System.out.println("Handling client at " + clientAddress);  
            
            InputStream in = client.getInputStream();
            OutputStream out = client.getOutputStream();
            
            while((recvMsgSize = in.read(buf)) != -1) {
                out.write(buf, 0, buf.length);
            }
            client.close();
        }
    }
}
