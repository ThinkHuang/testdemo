package cn.huang.socket.tcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

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
        execute(client);
    }
    
    public static void execute(Socket client)
    {
        try
        {
            // 获取socket的输出流，用来向客户端发送数据
            PrintStream out = new PrintStream(client.getOutputStream());
            // 获取Socket的输入流，用来接收从客户端发送过来的数据
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
                    // 将接收到的数据前面加上echo，发送到对应的客户端
                    out.println("echo:" + str);
                }
            }
            out.close();
            buf.close();
            client.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
}
