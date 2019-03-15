package socket;

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
 * �����socket��������Ӧ�ͻ�������
 * @author huangbing
 *
 */
public class ServerTeminal {

	private static int port = 8899;
	@Test
	public void response(){
		/**
		 * ���������socket���Լ����˿�Ϊʵ��
		 */
		ServerSocket sSocket = null;
		Socket socket = null;
		Reader reader = null;
		Writer writer = null;
		try {
			sSocket = new ServerSocket(port);

			// �����׽��������ڼ����Ķ˿ڳ�������ʱ����������Ľ��ܼ�����������
			// ��������Ĺ����б㽨�������ӣ����еġ��������֡�����װ���˽��ܶ�����,��Ҫע����ǣ�accept����������ʽ�ġ�
			socket = sSocket.accept();

			// ������Socket֮�󣬱����ͨ��socket������ȡ�ͻ��˷��͹�������Ϣ��
			reader = new InputStreamReader(socket.getInputStream());

			/**
			 * ���濪ʼ����socket���е���Ϣ
			 */
			// ����������
			char chars[] = new char[1024];
			int len;
			String temp;
			int index;
			StringBuffer sb = new StringBuffer();
			while ((len = (reader.read(chars))) != -1) {// Ϊʲô�����ַ���������ÿ��ֻ��ȡһ���ַ���������
				temp = new String(chars, 0, len);
				if((index=temp.indexOf("eof"))!=-1){
					sb.append(temp.substring(0, index));
					break;
				}
				sb.append(new String(chars, 0, len)); 
			}
			System.out.println("client information:" + sb.toString());
			
			//�����˴ӿͻ��˻��������ݺ󣬷�����Ӧ�ķ������ͻ���
			writer = new OutputStreamWriter(socket.getOutputStream());
			writer.write("message got");
			writer.write("eof");
			writer.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {// �ر���Դ
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
	 * ����ķ�ʽ��������ֻ����Ӧһ���ͻ������󣬽�����������������Ӧ�Ը��ͻ��˵�������������ڽ��������˵�accept������һ��while(true)�С����ǣ����ַ�ʽ������ʽ�ģ�
	 * �������Ľ�������ķ�ʽֻ������Ӧ��һ���ͻ��˺��ڼ�����Ӧ��һ���ͻ�����������������ڸ߲�����ʱ���ǲ�����ġ����ԣ�����������ֱ�Ӳ����첽��ʽ���ж�ͻ������󣬲��ö��̵߳ķ�ʽ��
	 * ���ҽ������ַ��������ķ�ʽ��һ�λ�ȡһ�����ݣ�����Ҫע�������Ҫ�ֶ���ӻ��з���
	 */
	@Test
	public void multiResponses(){
		ServerSocket sSocket = null;
		Socket socket = null;
		try {
			sSocket = new ServerSocket(port);//����Ҫ��ס��ServerSocket��Զֻ��һ��
			while (true) {
				socket = sSocket.accept();
				// ���ö��̵߳ķ�ʽ�����첽����
				new Thread(new Task(socket)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {// �ر���Դ
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
				// ������Socket֮�󣬱����ͨ��socket������ȡ�ͻ��˷��͹�������Ϣ��
				bReader = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));

				//���ó�ʱʱ��,��λ�Ǻ���
				socket.setSoTimeout(10*1000);
				// ����������
				String temp;
				int index;
				StringBuffer sb = new StringBuffer();
				while ((temp = (bReader.readLine())) != null) {// Ϊʲô�����ַ���������ÿ��ֻ��ȡһ���ַ���������

					if ((index = temp.indexOf("eof")) != -1) {
						sb.append(temp.substring(0, index));
						break;
					}

					sb.append(temp);
				}
				System.out.println("client information:" + sb.toString() + " receive time:" + new Date().getTime());

				// �����˴ӿͻ��˻��������ݺ󣬷�����Ӧ�ķ������ͻ���
				bWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"utf-8"));
				bWriter.write("message �յ�");
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
