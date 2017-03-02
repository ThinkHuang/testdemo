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
	 * 测试邮件的发送
	 */
	@Test
	public void fun1() throws MessagingException, IOException{
		/*
		 * 得带session对象
		 */
		Session session = MailUtils.createSession("smtp.163.com", "m17817557192", "huangbian123");
		
		/*
		 * 创建邮件对象
		 */
		Mail mail = new Mail("m17817557192@163.com",
				"huang.yejun@sz-excel.com",
				"不是垃圾邮件是什么呢？",
				"我是黄叶军，这是我从163给你发的邮箱");
		
		/*
		 * 附件暂不创建
		 */
		
		/*
		 * 发送邮件
		 */
		MailUtils.send(session, mail);
	}
	
	@Test
	public void fun2() throws AddressException, MessagingException{
		/*
		 * 不利用工具类进行发送，写最原始的裸代码
		 */
		
		/*
		 * 1、创建session的前提条件，主机服务器和授权认证
		 */
		Properties props = new Properties();
		props.setProperty("mail.host", "smtp.126.com");
		props.setProperty("mail.smtp.auth", "true");
		
		/*
		 * 创建授权对象
		 */
		Authenticator auth = new Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication("mail126_hyj","huangbian123");//这里是对账号名称授权
			}
		};
		
		/*
		 * 获得session对象
		 */
		Session session = Session.getInstance(props, auth);
		
		/*
		 * 创建MimeMessage
		 */
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("itcast_cxf@126.com"));//设置发件人
		msg.setRecipients(RecipientType.TO, "mail126_hyj@126.com");//设置收件人
		msg.setRecipients(RecipientType.CC, "m17817557192@163.com");//设置抄送人地址
		//msg.setRecipients(RecipientType.BCC, "itcast_cxf@sina.com");//设置秘密抄送地址
		
		msg.setSubject("这是一份测试邮件");
		msg.setContent("这是一份测试邮件，请注意查收","text/html;charset=utf-8");
		
		/*
		 * 发送
		 */
		Transport.send(msg);
		
	}
	@Test
	public void fun3() throws AddressException, MessagingException, IOException{
		/*
		 * 测试有附件的邮件发送
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
		 * 当发送包含附件的邮件时，邮件体就为多不见形式！
		 * 1.创建一个多不见的不见内容，MimeMultipart
		 *  MimeMultipart就是一个集合，用来装载多个主体部件！
		 * 2.我们需要创建两个主体部件，一个是文本内容的，另一个是附件的
		 * 	主体部件叫MimeBodyPart
		 * 3.把MimeMultipart设置给MimeMessage的内容。
		 */
		MimeMultipart list = new MimeMultipart();
		
		//创建MimeBodyPart
		MimeBodyPart part1 = new MimeBodyPart();
		//设置主体部件的内容
		part1.setContent("这是包含附件的垃圾邮件","text/html;charset=utf-8");
		//把主体部件添加到集合中
		list.addBodyPart(part1);
		
		//创建MimeBodyPart
		MimeBodyPart part2 = new MimeBodyPart();
		part2.attachFile(new File("G:/apple1.jpg"));
		part2.attachFile(new File("G:/apple2.png"));
		
		list.addBodyPart(part2);
		msg.setContent(list);
		
		///////////////////////////////////////////////
		/*
		 * 发送
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
			String path = prepath + suffixpath;//得到完整的路径名
			String subject = props.getProperty("subject");
			File attachFile = new File(path);
			/*
			 * 获得session
			 */
			Session session = MailUtils.createSession(mail_host, username, password);
			/*
			 * 创建邮件对象
			 */
			Mail mail = new Mail(from, to, subject, "Please being informed,There are attach in following!");
			
			/*
			 * 创建邮件的附件对象
			 * 后期的扩展为针对各种不同的文件类型做相应的处理
			 */
			AttachBean attach = new AttachBean(attachFile, suffixpath + "txt");
			/*
			 * 发送
			 */
			
			MailUtils.send(session, mail);
			
		
	}
}
