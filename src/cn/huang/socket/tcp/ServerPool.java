package socket.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 基于线程池的服务器
 * @author huangyejun
 *
 */
public class ServerPool
{
    private static final int THREADPOOLSIZE = 2;
    
    public static void main(String[] args) throws IOException
    {
        final ServerSocket server = new ServerSocket(20006);
        
        for (int i = 0; i < THREADPOOLSIZE; i++)
        {
            Thread thread = new Thread() {
                public void run() {
                    while(true) {
                        try
                        {
                            //等待客户端连接
                            Socket client = server.accept();
                            System.out.println("与客户端连接成功");
                            //开始调用任务方法
                            ServerThread.execute(client);
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
            };
            thread.start();
        }
    }
}
