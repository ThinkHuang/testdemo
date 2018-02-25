package cn.huang.socket.udp;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 模拟UDP的客户端
 * @author huangyejun
 *
 */
public class UDPClient
{
    private static final int TIMEOUT = 5000;//设置超时时间
    private static final int MAXNUM = 5;//设置重发数据的最多次数
    
    public static void main(String[] args) throws IOException
    {
        String strSend = "Hello UDPServer";
        byte[] buf = new byte[1024];
        //客户端在9000端口监听接收到的数据
        DatagramSocket ds = new DatagramSocket(9000);
        //确定发送的主机
        InetAddress loc = InetAddress.getLocalHost();
        //定义用来发送数据的DatagramPacket实例
        //下列参数的意义为：发送的数据(字节形式)，发送的数据长度，接收的主机IP，接收主机的端口
        DatagramPacket dpSend = new DatagramPacket(strSend.getBytes(), strSend.length(), loc, 3000);
        //定义用来接收数据的DatagramPacket实例,接收的DatagramPacket实例不需要指定主机和端口
        DatagramPacket dpReceive = new DatagramPacket(buf, 1024);
        //设置超时
        ds.setSoTimeout(TIMEOUT);
        //监控重发次数
        int tries = 0;
        //是否接收到了数据标识位
        boolean receiveResponse = false;
        
        while(!receiveResponse && tries < MAXNUM) {
            //发送数据
            ds.send(dpSend);
            //接收从服务端发送过来的数据
            try
            {
                ds.receive(dpReceive);
                //如果接收到数据不是来自于目标主机，抛出异常
                if(!dpReceive.getAddress().equals(loc)) {
                    throw new IOException("Received packet from an unknown source");
                }
                //如果接收到数据，重设标识位，退出循环
                receiveResponse = true;
            }
            catch (InterruptedIOException e)
            {
                //如果接收数据时阻塞超时，重发并减少一次重发的次数
                tries++;
                System.out.println("timeout, " + (MAXNUM - tries) + " more tries... !");
            }
        }
        if(receiveResponse) {
            // 打印出接收到的数据
            System.out.println("client received data from server: ");
            /*
             *  DatagramPacket的getData方法总是返回缓冲区的原始大小，
             *  所以实际接收的数据需要我们经过内部偏移量和实际数据大小进行截取
             */
            String strReceive = new String(dpReceive.getData(), 0, dpReceive.getLength()) + 
                    " from " + dpReceive.getAddress().getHostAddress() + ":" + dpReceive.getPort();
            System.out.println(strReceive);
            /*
             * 由于dpReceive在接收了数据之后，其内部消息长度值会变为实际接收的消息的字节数，
             * 所以这里要将dpReceive的内部消息长度重设为1024
             */
            dpReceive.setLength(1024);
        } else {
            //如果重发了MAXNUM次之后，仍然没有接收到服务器的数据，则打印如下数据
            System.out.println("No Response -- give up");
        }
        ds.close();
    }
}
