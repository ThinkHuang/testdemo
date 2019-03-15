package socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * 模拟UDP的服务端
 * @author huangyejun
 *
 */
public class UDPServer
{
    public static void main(String[] args) throws IOException
    {
        String strSend =  "Hello UDPclient";
        byte[] buf = new byte[1024];
        //服务端在3000端口监听接收到的数据
        DatagramSocket ds = new DatagramSocket(3000);
        //接收从客户端发送过来的数据
        DatagramPacket dpReceive = new DatagramPacket(buf, 1024);
        System.out.println("server is on, waiting for client to send data...");
        boolean flag = true;
        while(flag) {
            //服务器接收来自客户端的数据
            ds.receive(dpReceive);
            String strReceive = new String(dpReceive.getData(),0,dpReceive.getLength()) +   
                    " from " + dpReceive.getAddress().getHostAddress() + ":" + dpReceive.getPort();  
            System.out.println(strReceive); 
            
            //发送数据到客户端的3000端口
            DatagramPacket dpSend = new DatagramPacket(strSend.getBytes(), 
                    strSend.length(), dpReceive.getAddress(), 9000);
            ds.send(dpSend);
            /*
             * 由于dpReceive在接收数据之后，其内部消息长度值会变为实际接收的消息的字节数，
             * 所以这里要将dpReceive的内部消息长度重新设置为1024
             */
        }
        ds.close();
    }
}
