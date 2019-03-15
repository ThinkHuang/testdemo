package cn.huang.socket.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 引用java自带的线程池
 * @author huangyejun
 *
 */
public class ServerExecutor
{
    public static void main(String[] args) throws IOException
    {
        ServerSocket server = new ServerSocket(20006);
        Socket client = null;
        Executor service = Executors.newCachedThreadPool();
        
        boolean flag = true;
        while(flag) {
            client = server.accept();
            System.out.println("与服务器连接成功");
            
            service.execute(new ServerThread(client));
        }
    }
}
