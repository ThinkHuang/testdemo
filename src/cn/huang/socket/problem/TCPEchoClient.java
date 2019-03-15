package socket.problem;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

public class TCPEchoClient
{
    public static void main(String[] args) throws IOException
    {
        if(args.length < 2 || args.length > 3) {
            throw new IllegalArgumentException("Parameter(s): <Server> <Word> [<Port>]");
        }
        
        String server = args[0]; //server name or IP address
        byte[] data = args[1].getBytes();
        int serverPort = (args.length == 3) ? Integer.valueOf(args[2]) : 7;
        
        Socket client = new Socket(server, serverPort);
        System.out.println("connect to server... sending echo string");
        
        InputStream in = client.getInputStream();
        OutputStream out = client.getOutputStream();
        
        //在客户端发送完数据后，调用 shutdownOutput()方法将套接字的输出流关闭，这样，服务端的 read()方法便会返回 -1
        out.write(data);
        client.shutdownOutput();//验证死锁时，请屏蔽掉该方法
        
        int totalBytesRcvd = 0; //total bytes received so far
        int bytesRcvd; //bytes received in last read
        //现在的情况是已知服务端发送的字节数
//        while(totalBytesRcvd < data.length) {
//            if((bytesRcvd = in.read(data, totalBytesRcvd, data.length - totalBytesRcvd)) == -1) {
//                throw new SocketException("Connection close prematurely");
//            }
//            totalBytesRcvd += bytesRcvd;
//        }
        // 在不知道服务端发送的字节数的情况下，采用如下方式会导致read()方法的死锁
        /*
         * 原因在于：只有在socket关闭的情况下，read方法才会返回-1。
         * 也就是说如果在client和Server两方都使用了read方法的情况下，只有二者中的一方关闭了Socket，另一方的read方法才会返回-1
         */
        while((bytesRcvd = in.read())!= -1){  
            data[totalBytesRcvd] = (byte)bytesRcvd;  
            System.out.println((char)data[totalBytesRcvd]);  
            totalBytesRcvd++;  
        } 
        
        // data array is full
        System.out.println("Received:" + new String(data));
        
        client.close();
    }
}
