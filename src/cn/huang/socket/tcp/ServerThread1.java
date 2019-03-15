package cn.huang.socket.tcp;

import java.io.BufferedReader;
<<<<<<< HEAD
import java.io.IOException;
=======
>>>>>>> f85375e6b7e0ffb737d36c5201ddc714bcb614f9
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

<<<<<<< HEAD
/**
 * 服务器需要使用多线程处理客户端连接，所以定义一个任务类
 * @author huangyejun
 *
 */
public class ServerThread implements Runnable
{
=======
public class ServerThread implements Runnable
{
    
>>>>>>> f85375e6b7e0ffb737d36c5201ddc714bcb614f9
    private Socket client = null;
    
    public ServerThread(Socket client)
    {
        this.client = client;
    }
    
    @Override
    public void run()
    {
<<<<<<< HEAD
        try
        {
            // 获取Socket的输出流，用来向客户端发送数据
            PrintStream out = new PrintStream(client.getOutputStream());
            // 获取Socket的输入流，获取socket的输入信息
=======
        execute(client);
    }
    
    public static void execute(Socket client)
    {
        try
        {
            // 获取socket的输出流，用来向客户端发送数据
            PrintStream out = new PrintStream(client.getOutputStream());
            // 获取Socket的输入流，用来接收从客户端发送过来的数据
>>>>>>> f85375e6b7e0ffb737d36c5201ddc714bcb614f9
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
<<<<<<< HEAD
                    // 将接收到的Socket数据加上echo，发送给client
                    out.print("echo:" + str);
=======
                    // 将接收到的数据前面加上echo，发送到对应的客户端
                    out.println("echo:" + str);
>>>>>>> f85375e6b7e0ffb737d36c5201ddc714bcb614f9
                }
            }
            out.close();
            buf.close();
<<<<<<< HEAD
        }
        catch (IOException e)
=======
            client.close();
        }
        catch (Exception e)
>>>>>>> f85375e6b7e0ffb737d36c5201ddc714bcb614f9
        {
            e.printStackTrace();
        }
    }
    
}
