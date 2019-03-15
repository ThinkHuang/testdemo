package socket;

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
 * �ͻ���
 * @author huangbing
 *
 */
public class ClientTeminal {
	
	@Test
	public void request(){
		/**
		 * ��ʼ�������ǣ�ȷ��Ҫ���ӵ������ͷ����������Ķ˿�
		 */
		String host = "127.0.0.1";

		int port = 8899;
		Socket client = null;
		Writer writer = null;
		Reader reader = null;
		try {
			// �����˽�������
			client = new Socket(host, port);
			// ��ʼ�����������������д������
			writer = new OutputStreamWriter(client.getOutputStream());
			writer.write("hello server.");
			writer.write("eof");
			writer.flush();// ��仰ǧ�������ǣ����ȱʧ�˿����ڷ���������ԶҲ���ܲ��˿ͻ��˵�����
			
			//��ʼ��ȡ���������ص���Ϣ
			reader = new InputStreamReader(client.getInputStream());
			char[] chars = new char[1024];
			int len = 0;
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
		 * ��ʼ�������ǣ�ȷ��Ҫ���ӵ������ͷ����������Ķ˿�
		 */
		String host = "127.0.0.1";

		int port = 8899;
		Socket client = null;
		BufferedWriter bWriter = null;
		BufferedReader bReader = null;
		try {
			// �����˽�������
			client = new Socket(host, port);
			// ��ʼ�����������������д������
			bWriter = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			bWriter.write("��� server.");
			bWriter.write("eof\n");
			bWriter.flush();// ��仰ǧ�������ǣ����ȱʧ�˿����ڷ���������ԶҲ���ܲ��˿ͻ��˵�����
			//���ó�ʱʱ��,��λ�Ǻ���
			client.setSoTimeout(10*1000);
			//��ʼ��ȡ���������ص���Ϣ
			bReader = new BufferedReader(new InputStreamReader(client.getInputStream(),"utf-8"));
			//����������Ҫ�����ڲ�ͬ�����ϣ���һ̨�����ϣ��޷����Գ����룬��Ȼ����ֱ�����ý�����utf-8,������gbk
			//
			String temp;
			int index;
			StringBuffer sb = new StringBuffer();
			while ((temp = (bReader.readLine())) != null) {// Ϊʲô�����ַ���������ÿ��ֻ��ȡһ���ַ�������������õ����
				
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
