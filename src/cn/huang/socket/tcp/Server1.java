package socket.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 模拟TCP的服务端
 * @author huangyejun
 *
 */
public class Server1
{
    public static void main(String[] args) throws IOException
    {
        ServerSocket server = new ServerSocket(20006);
        Socket client = null;
        boolean flag = true;
        while(flag) {
            client = server.accept();
            System.out.println("与客户端连接成功");
            //为每个客户端开启一个线程任务
            new Thread(new ServerThread(client)).start();
        }
        server.close();
    }
}
