package cn.huang.other;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeMessage.RecipientType;

import org.junit.Test;

import cn.itcast.mail.AttachBean;
import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;

public class MailTest {
	
	/*
	 * �����ʼ��ķ���
	 */
	@Test
	public void fun1() throws MessagingException, IOException{
		/*
		 * �ô�session����
		 */
		Session session = MailUtils.createSession("smtp.163.com", "m17817557192", "huangbian123");
		
		/*
		 * �����ʼ�����
		 */
		Mail mail = new Mail("m17817557192@163.com",
				"huang.yejun@sz-excel.com",
				"���������ʼ���ʲô�أ�",
				"���ǻ�Ҷ���������Ҵ�163���㷢������");
		
		/*
		 * �����ݲ�����
		 */
		
		/*
		 * �����ʼ�
		 */
		MailUtils.send(session, mail);
	}
	
	@Test
	public void fun2() throws AddressException, MessagingException{
		/*
		 * �����ù�������з��ͣ�д��ԭʼ�������
		 */
		
		/*
		 * 1������session��ǰ����������������������Ȩ��֤
		 */
		Properties props = new Properties();
		props.setProperty("mail.host", "smtp.126.com");
		props.setProperty("mail.smtp.auth", "true");
		
		/*
		 * ������Ȩ����
		 */
		Authenticator auth = new Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication("mail126_hyj","huangbian123");//�����Ƕ��˺�������Ȩ
			}
		};
		
		/*
		 * ���session����
		 */
		Session session = Session.getInstance(props, auth);
		
		/*
		 * ����MimeMessage
		 */
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("itcast_cxf@126.com"));//���÷�����
		msg.setRecipients(RecipientType.TO, "mail126_hyj@126.com");//�����ռ���
		msg.setRecipients(RecipientType.CC, "m17817557192@163.com");//���ó����˵�ַ
		//msg.setRecipients(RecipientType.BCC, "itcast_cxf@sina.com");//�������ܳ��͵�ַ
		
		msg.setSubject("����һ�ݲ����ʼ�");
		msg.setContent("����һ�ݲ����ʼ�����ע�����","text/html;charset=utf-8");
		
		/*
		 * ����
		 */
		Transport.send(msg);
		
	}
	@Test
	public void fun3() throws AddressException, MessagingException, IOException{
		/*
		 * �����и������ʼ�����
		 */
		Properties props = new Properties();
		props.setProperty("mail.host", "smtp.126.com");
		props.setProperty("mail.smtp.auth", "true");
		
		Authenticator auth = new Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication("itcast_cxf","itcast");
			}
		};
		Session session = Session.getInstance(props, auth);
		
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("itcast_cxf@126.com"));
		msg.setRecipients(RecipientType.TO, "mail126_hyj@126.com");
		
		
		///////////////////////////////////////////////
		/*
		 * �����Ͱ����������ʼ�ʱ���ʼ����Ϊ�಻����ʽ��
		 * 1.����һ���಻���Ĳ������ݣ�MimeMultipart
		 *  MimeMultipart����һ�����ϣ�����װ�ض�����岿����
		 * 2.������Ҫ�����������岿����һ�����ı����ݵģ���һ���Ǹ�����
		 * 	���岿����MimeBodyPart
		 * 3.��MimeMultipart���ø�MimeMessage�����ݡ�
		 */
		MimeMultipart list = new MimeMultipart();
		
		//����MimeBodyPart
		MimeBodyPart part1 = new MimeBodyPart();
		//�������岿��������
		part1.setContent("���ǰ��������������ʼ�","text/html;charset=utf-8");
		//�����岿����ӵ�������
		list.addBodyPart(part1);
		
		//����MimeBodyPart
		MimeBodyPart part2 = new MimeBodyPart();
		part2.attachFile(new File("G:/apple1.jpg"));
		part2.attachFile(new File("G:/apple2.png"));
		
		list.addBodyPart(part2);
		msg.setContent(list);
		
		///////////////////////////////////////////////
		/*
		 * ����
		 */
		Transport.send(msg);
		
	}
	@Test
	public void fun4() throws IOException, MessagingException{
		Date curTime = new Date();
		//AttachBean[] beans;
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		String strCurTime1 = sdf1.format(curTime);
		String strCurTime2 = sdf2.format(curTime);
//		String count;
//		System.out.println("Is it necessary to increase attachfile?");
//		System.out.println("[0:No]:");
//		System.out.println("[1:Yes]:");
//		System.out.print("input you choice:");
//		Scanner scan1 = new Scanner(System.in);
//		String isIncAtt = scan1.next();
//		System.out.print("input the number of attach files:");
//		Scanner scan2 = new Scanner(System.in);
//		count = scan2.next();
//		if("".equals(scan2)){
//			if("17:00:00".equals(strCurTime1)){
//				sendEmail(strCurTime2);
//			}
//		}
		
	}
	
	public void sendEmail(String suffixpath,AttachBean ...beans) throws  IOException, MessagingException{
		Properties props = new Properties();
		InputStream input = this.getClass()
		.getClassLoader().getResourceAsStream("emaiproperties.properties");
			props.load(input);
			String mail_host = props.getProperty("mail_host");
			String username = props.getProperty("username");
			String password = props.getProperty("password");
			String from = props.getProperty("from");
			String to = props.getProperty("to");
			String prepath = props.getProperty("prepath") ;
			String path = prepath + suffixpath;//�õ�������·����
			String subject = props.getProperty("subject");
			File attachFile = new File(path);
			/*
			 * ���session
			 */
			Session session = MailUtils.createSession(mail_host, username, password);
			/*
			 * �����ʼ�����
			 */
			Mail mail = new Mail(from, to, subject, "Please being informed,There are attach in following!");
			
			/*
			 * �����ʼ��ĸ�������
			 * ���ڵ���չΪ��Ը��ֲ�ͬ���ļ���������Ӧ�Ĵ���
			 */
			AttachBean attach = new AttachBean(attachFile, suffixpath + "txt");
			/*
			 * ����
			 */
			
			MailUtils.send(session, mail);
			
		
	}
}
