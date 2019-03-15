package cn.huang.socket.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * 模拟TCP链接的客户端
 * @author huangyejun
 *
 */
public class Client1
{
    public static void main(String[] args) throws IOException
    {
        //客户端请求与本机在20006端口上建立链接
        Socket client = new Socket("127.0.0.1", 20006);
        //设置超时时间为100s
        //client.setSoTimeout(100000);
        //接受键盘输入
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        //获取Socket的输出流，用来发送数据到服务端
        PrintStream out = new PrintStream(client.getOutputStream());
        //获取Socket的输入流，用来接受从服务器发送过来的数据
        BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
        
        boolean flag = true;
        while(flag) {
            System.out.println("输入信息：");
            String str = input.readLine();
            //发送数据到服务端
            out.print(str);
            if("bye".equals(str)) {
                flag = false;
            } else {
                //接受服务端返回的数据
                String echo = buf.readLine();
                System.out.println(echo);
            }
        }
        input.close();
        if(null != client) {
            client.close();//当Socket被关闭时，其关联的输入流和输出流也会被关闭
        }
    }
}
