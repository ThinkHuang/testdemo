package cn.huang.socket.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * 服务器需要使用多线程处理客户端连接，所以定义一个任务类
 * @author huangyejun
 *
 */
public class ServerThread implements Runnable
{
    private Socket client = null;
    
    public ServerThread(Socket client)
    {
        this.client = client;
    }
    
    @Override
    public void run()
    {
        try
        {
            // 获取Socket的输出流，用来向客户端发送数据
            PrintStream out = new PrintStream(client.getOutputStream());
            // 获取Socket的输入流，获取socket的输入信息
            BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
            
            boolean flag = true;
            while (flag)
            {
                String str = buf.readLine();
                if (str == null || "".equals(str))
                {
                    flag = false;
                }
                else
                {
                    // 将接收到的Socket数据加上echo，发送给client
                    out.print("echo:" + str);
                }
            }
            out.close();
            buf.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
}
