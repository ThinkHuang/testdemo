package cn.huang.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

import org.junit.Test;

/**
 * 客户端
 * @author huangbing
 *
 */
public class ClientTeminal {
	
	@Test
	public void request(){
		/**
		 * 初始化条件是：确认要连接的主机和服务器监听的端口
		 */
		String host = "127.0.0.1";

		int port = 8899;
		Socket client = null;
		Writer writer = null;
		Reader reader = null;
		try {
			// 与服务端进行连接
			client = new Socket(host, port);
			// 开始往服务器的输出流中写入数据
			writer = new OutputStreamWriter(client.getOutputStream());
			writer.write("hello server.");
			writer.write("eof");
			writer.flush();// 这句话千万不能忘记，如果缺失了可能在服务器端永远也接受不了客户端的数据
			
			//开始读取服务器返回的信息
			reader = new InputStreamReader(client.getInputStream());
			char[] chars = new char[1024];
			int len = 0;
			String temp;
			int index;
			StringBuffer sb = new StringBuffer();
			while ((len = (reader.read(chars))) != -1) {// 为什么采用字符流，并且每次只读取一个字符？？？？
				temp = new String(chars, 0, len);
				if((index=temp.indexOf("eof"))!=-1){
					sb.append(temp.substring(0, index));
					break;
				}
				sb.append(new String(chars, 0, len)); 
			}
			System.out.println("server information:" + sb.toString());
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (client != null) {
				try {
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Test
	public void multiRequests(){
		/**
		 * 初始化条件是：确认要连接的主机和服务器监听的端口
		 */
		String host = "127.0.0.1";

		int port = 8899;
		Socket client = null;
		BufferedWriter bWriter = null;
		BufferedReader bReader = null;
		try {
			// 与服务端进行连接
			client = new Socket(host, port);
			// 开始往服务器的输出流中写入数据
			bWriter = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			bWriter.write("你好 server.");
			bWriter.write("eof\n");
			bWriter.flush();// 这句话千万不能忘记，如果缺失了可能在服务器端永远也接受不了客户端的数据
			//设置超时时间,单位是毫秒
			client.setSoTimeout(10*1000);
			//开始读取服务器返回的信息
			bReader = new BufferedReader(new InputStreamReader(client.getInputStream(),"utf-8"));
			//乱码问题主要常见于不同主机上，在一台机器上，无法测试出乱码，当然可以直接设置接受用utf-8,发送用gbk
			//
			String temp;
			int index;
			StringBuffer sb = new StringBuffer();
			while ((temp = (bReader.readLine())) != null) {// 为什么采用字符流，并且每次只读取一个字符？？？？问题得到解决
				
				if((index=temp.indexOf("eof"))!=-1){
					sb.append(temp.substring(0, index));
					break;
				}
				
				sb.append(temp); 
			}
			System.out.println("server information:" + sb.toString() + " receiver time:" + new Date().getTime());
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (client != null) {
				try {
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bWriter != null) {
				try {
					bWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bReader != null) {
				try {
					bReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
