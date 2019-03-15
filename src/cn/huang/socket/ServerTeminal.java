package cn.huang.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import org.junit.Test;

/**
 * 服务端socket，用来响应客户端请求
 * @author huangbing
 *
 */
public class ServerTeminal {

	private static int port = 8899;
	@Test
	public void response(){
		/**
		 * 建立服务端socket，以监听端口为实现
		 */
		ServerSocket sSocket = null;
		Socket socket = null;
		Reader reader = null;
		Writer writer = null;
		try {
			sSocket = new ServerSocket(port);

			// 建立套接字用于在监听的端口出现请求时，进行请求的接受及后续操作。
			// 接受请求的过程中便建立了连接，其中的“三次握手”被封装在了接受动作中,需要注意的是，accept方法是阻塞式的。
			socket = sSocket.accept();

			// 建立好Socket之后，便可以通过socket流来获取客户端发送过来的信息了
			reader = new InputStreamReader(socket.getInputStream());

			/**
			 * 下面开始解析socket流中的信息
			 */
			// 建立缓冲区
			char chars[] = new char[1024];
			int len;
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
			System.out.println("client information:" + sb.toString());
			
			//读到了从客户端回来的数据后，发送相应的反馈给客户端
			writer = new OutputStreamWriter(socket.getOutputStream());
			writer.write("message got");
			writer.write("eof");
			writer.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {// 关闭资源
			if (sSocket != null) {
				try {
					sSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (socket != null) {
				try {
					socket.close();
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
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 上面的方式，服务器只能响应一个客户端请求，接下来将做服务器相应对个客户端的情况，方法在于将服务器端的accept包含到一个while(true)中。但是，这种方式是阻塞式的，
	 * 服务器的接受请求的方式只能是响应完一个客户端后在继续响应下一个客户端请求，这样的情况在高并发的时候是不允许的。所以，接下来，我直接采用异步方式进行多客户端请求，采用多线程的方式。
	 * 并且将采用字符缓冲流的方式来一次获取一行数据，这里要注意的是需要手动添加换行符。
	 */
	@Test
	public void multiResponses(){
		ServerSocket sSocket = null;
		Socket socket = null;
		try {
			sSocket = new ServerSocket(port);//这里要记住，ServerSocket永远只有一个
			while (true) {
				socket = sSocket.accept();
				// 采用多线程的方式控制异步操作
				new Thread(new Task(socket)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {// 关闭资源
			if (sSocket != null) {
				try {
					sSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
	
	private class Task implements Runnable {

		private Socket socket;
		
		public Task(Socket socket){
			this.socket = socket;
		}
		
		@Override
		public void run() {
			handleSocket();
		}
		
		public void handleSocket() {
			BufferedReader bReader = null;
			BufferedWriter bWriter = null;
			try {
				// 建立好Socket之后，便可以通过socket流来获取客户端发送过来的信息了
				bReader = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));

				//设置超时时间,单位是毫秒
				socket.setSoTimeout(10*1000);
				// 建立缓冲区
				String temp;
				int index;
				StringBuffer sb = new StringBuffer();
				while ((temp = (bReader.readLine())) != null) {// 为什么采用字符流，并且每次只读取一个字符？？？？

					if ((index = temp.indexOf("eof")) != -1) {
						sb.append(temp.substring(0, index));
						break;
					}

					sb.append(temp);
				}
				System.out.println("client information:" + sb.toString() + " receive time:" + new Date().getTime());

				// 读到了从客户端回来的数据后，发送相应的反馈给客户端
				bWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"utf-8"));
				bWriter.write("message 收到");
				bWriter.write("eof\n");
				bWriter.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (bReader != null) {
					try {
						bReader.close();
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
			}
		}
	}
}
