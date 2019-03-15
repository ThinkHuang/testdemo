package com.wescxx.app.palHd.biz.impl;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.independentsoft.exchange.Attachment;
import com.independentsoft.exchange.AttachmentInfo;
import com.independentsoft.exchange.Mailbox;
import com.independentsoft.exchange.Message;
import com.wescxx.app.core.common.pageModel.DataGrid;
import com.wescxx.app.core.common.pageModel.PageHelper;
import com.wescxx.app.core.utility.lang.DateUtil;
import com.wescxx.app.core.utility.lang.StringUtil;
import com.wescxx.app.core.utility.net.FileUtil;
import com.wescxx.app.palBase.biz.impl.PalBaseServiceImpl;
import com.wescxx.app.palHd.biz.HdAutoMailServiceI;
import com.wescxx.app.palHd.model.THdBrokerHead;
import com.wescxx.app.palHd.model.THdImportConfig;
import com.wescxx.app.palHd.model.TMailAccessory;
import com.wescxx.app.palHd.model.TMailManager;
import com.wescxx.app.palHd.model.TMailSchedule;
import com.wescxx.app.palHd.pageModel.ProductMailList;
import com.wescxx.app.palHd.utility.ReceiveMailByJWebService;

import de.innosystec.unrar.Archive;
import de.innosystec.unrar.rarfile.FileHeader;

/**   
 * @Title: ServiceImpl
 * @Description: 券商对账_自动处理邮件
 * @author codegenerate
 * @date 2017-05-25 
 * @version V1.0 
 *  1.占位符 
	2.流关闭  --
	3.文件删除  --
	4.后缀  --
	5.检查 是否为节假日  
	6.路径 动态    --  
 *
 */
@Service
public class HdAutoMailServiceImpl extends PalBaseServiceImpl implements HdAutoMailServiceI {

	private static Logger logger = Logger.getLogger(HdAutoMailServiceImpl.class);

	private static final String RECEIVE_TYPE = "接收";
	
	private static final String DATE_FORMATE = "yyyyMMdd HH:mm:ss";
	
	private static final String DATE_FORMATE1 = "yyyy-MM-dd HH:mm:ss";
	
	private static final String TIME_SEPERATOR = ":";
	
	//设定的业务小时边界
	private static final int BISNESS_HOUR = 18;
	
	//第一次接收前24小时的数据
	private static final int ONE_DAY = 24 * 60 * 60;
	
	//以毫秒为单位的一分钟
	private static final int ONE_MINUTE = 60 * 1000;
	
	@Override
	@Transactional
	public void autoReadMail(HashMap<String, String> param) {
		try {
			
			/**
			 * 如果当前时间是节假日，直接返回
			 */
			if(isHoliday()){
				return;
			}
			
			logger.info("开始查找邮箱服务器    -----------  01");
			String sql = "SELECT T.MAIL_NO AS username,T.MAIL_PWD AS password,T.SERVER_ADDRESS AS host FROM HD_MAILBOX_SERVER T WHERE T.SERVER_TYPE = ? and T.SERVER_STATE = ?" ;
			List<String> params = new ArrayList<String>();
			params.add(RECEIVE_TYPE);
			params.add("00086001");
			List<Map<String,Object>> list = this.findForJdbc(sql, params.toArray());
			Date date = new Date();//由于其时间的特殊性，为了保证获取的时间都是统一的，这里将调度时间定于在外面，确保不变性
			logger.info("已找到服务器邮箱    -----------  02");
			/*
			 * 获取一段时间内所有的邮件，这个时间点需要确定
			 * 还要判断是不是第一次进行解析
			 */
			int Internal = 0;
			logger.info("获取上次调度时间   -----------  03");
			if(firstReceive()){
				logger.info("上次没有调度时间 默认取前一天   -----------  04");
				TMailSchedule t = new TMailSchedule();
				t.setLastScheTime(date);
				t.setRecordType("0");
				this.save(t);
				//period = getLastDay(date);
				Internal = ONE_DAY;
			}else{
				/*
				 * 获取间隔时间
				 */
				Internal = getInternalPeriod(date);
				// period = getLastPeriod(date);
				 /**
				  * 同时更新该表的上次调度时间
				  */
	           	 String hql = "update TMailSchedule t set t.lastScheTime =:lastScheTime where t.recordType =:recordType";
	           	 Map<String,Object> pMap = new HashMap<String,Object>();
	           	 pMap.put("lastScheTime", date);
	           	 pMap.put("recordType", "0");
	           	 this.executeHql(hql, pMap);
			}
			logger.info("开始接收邮件  -----------  05");
			ReceiveMailByJWebService util = ReceiveMailByJWebService.getInstance();
			List<String> messageIds = getMessageIds();
//			List<Message> resultLst = util.getMessage(list, messageIds, period);
			List<Message> resultLst = util.getMessage(list, messageIds, 60);
			logger.info("接收邮件完毕， "+resultLst.size()+"封邮件 -----------  06");
			for (int i = 0; i < resultLst.size(); i++) {
				 logger.info("开始解析第"+i+"封邮件  -----------  06_1");
				 Message message = resultLst.get(i);
				 List<AttachmentInfo> attachmentsInfo = message.getAttachments();
				 /**
				  * 如果附件中不包含HTML,EXCEL和TXT的文件，那么将不会保存该附件
				  * 但是需要保存一条记录到调度记录表中
				  */
				 if(unnecessary(message,date)){
					 TMailSchedule schedule = new TMailSchedule();
	            	 String messageId = message.getEntryId();
	            	 String recordType = "1";
	            	 schedule.setMessageId(messageId);
	            	 schedule.setRecordType(recordType);
	            	 this.save(schedule);
	            	 logger.info(i+"封邮件,无附件或附件没有需要的格式,放弃  -----------  06_2");
            		 continue;
            	 }
				 logger.info(i+"封邮件,解析附件中  -----------  06_2");
                 for (int j = 0; j < attachmentsInfo.size(); j++){
                	 
                	 String path = createDir(getBizDate(date));
                	 Attachment attachment = util.saveAttachment(attachmentsInfo.get(j).getId(),path);//传入要保存的路径
                	 
                     /**
                      * 接下来要保存必要的数据到数据库中
                      */
                	 TMailManager manager = new TMailManager();
                	 //邮件主题
                	 String subject = message.getSubject();
                	 //发件人
                	 String from = message.getFrom().getName();
                	 //收件人
                	 List<Mailbox> toLst = message.getToRecipients();
                	 //抄送人
                	 List<Mailbox> ccLst = message.getCcRecipients();
                	 //邮件内容
                	 String content = message.getBody().getText();
                	 //邮件日期
                	 Date mailDate = message.getSentTime();
                	 //业务日期
                	 Date bizDate = getMarketDay(mailDate, -1);
                	 manager.setMailContent(content);
                	 manager.setMailTitle(subject);
                	 manager.setBizDate(bizDate);
                	 manager.setMailDate(mailDate);
                	 manager.setSendPerson(from);
                	 this.save(manager);//完成保存后即得到ID
                	 logger.info(i+"封邮件,保存邮件管理,"
                	 		+ "<发件人："+from+"><邮件主题："+subject+">"
        	 				+ "<邮件日期："+mailDate+"><业务日期："+bizDate+">"
        	 				+ " -----------  06_3");
                	 TMailAccessory accessory = new TMailAccessory();
                	 //附件名称
                	 String attachName = attachment.getName();
                	 //附件路径
                	 String attachPath = path; 
                	 //是否是压缩文件
                	 Integer isCompressed = 0;
                	 if(attachName.trim().toLowerCase().endsWith("rar") || attachName.trim().toLowerCase().endsWith("zip")){
                		 isCompressed = 1;
                	 }
                	 accessory.setAbsolutePath(attachPath + "\\" + attachName);
                	 accessory.setAccessoryName(attachName);
                	 accessory.setAccessoryPath(attachPath);
                	 accessory.setRarChild(isCompressed);
                	 accessory.setMailId(manager.getId());
                	 
                	 logger.info(i+"封邮件,保存附件,"
                	 		+ "<附件名称："+attachName+">  -----------  06_4");
                	 /**
                	  * 解析并保存附件
                	  */
                	 parseAttach(accessory);
                	 
                	 this.save(accessory);
                	 
                	 TMailSchedule schedule = new TMailSchedule();
                	//消息ID
                	 String messageId = message.getEntryId();
                	 //上次调度时间，当进入到了调度任务后，该表所有的记录都会更新时间为当前时间
                	 String recordType = "1";//这里都是保存经过解析的邮件
                	 schedule.setMessageId(messageId);
                	 schedule.setRecordType(recordType);
                	 this.save(schedule);
                	 logger.info(i+"封邮件,处理完毕, -----------  06_5");
                 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("自动接收邮件异常");
		}
		
		
		
	}
	
	private Date getLastDay(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 1);
		return cal.getTime();
	}
	
	/**
	 * 用于过滤不必要的文件类型的邮件
	 * (判断该邮件附件是否包含指定的格式  html excel txt)
	 * @param attachmentsInfo
	 * @return  包含 返回 false    不包含返回true
	 * @throws Exception 
	 */
	private boolean unnecessary(Message message,Date date) throws Exception {
		 List<AttachmentInfo> attachmentsInfo = message.getAttachments();
		 String path = "";
		 boolean flag = false;
		 for (int j = 0; j < attachmentsInfo.size(); j++)
         {
			 path = createDir(getBizDate(date));
        	 Attachment attachment = ReceiveMailByJWebService.getInstance().saveAttachment(attachmentsInfo.get(j).getId(),path);
			 String attachName = attachment.getName().trim().toLowerCase();
			 logger.info("附件名称：" + attachName);
			 logger.info("附件完整路径：" + path + "\\" + attachment.getName());
			 if(attachName.endsWith("html") || attachName.endsWith("htm") || attachName.endsWith("xlsx") || attachName.endsWith("xls") || attachName.endsWith("txt")){
				 return false;
			 }
			 if(attachName.endsWith("rar")){
				 flag = isRarRequired(path + "\\" + attachment.getName());
				 
			 }
			 if(attachName.endsWith("zip")){
				flag = isZipRequired(path + "\\" + attachment.getName(),path+"\\");
			 }
         }
		 if(flag){
			 logger.info("要删除的文件的路径为：" + path);//删除文件存在隐患，如果文件过多，处理时间超出时间范围，将无法正确删除文件
	         FileUtil.delAllFile(path);//删除掉该目录下的所有文件
		 }
		return flag;
	}
	
	
	/**
	 * 判断Zip压缩文件是否包含HTML,EXCEL和TXT格式的文件
	 * @param sourcePath
	 * @param targetPath:该解压路径必须以"\"结尾
	 * @return
	 * @throws Exception
	 */
	private boolean isZipRequired(String sourcePath,String targetPath) {
		ZipFile zip = null;
		try{
			File zipFile = new File(sourcePath);
			String descDir = targetPath;
			File pathFile = new File(descDir);
			if (!pathFile.exists()) {
				pathFile.mkdirs();
			}
			zip = new ZipFile(zipFile);
			for (Enumeration entries = zip.getEntries(); entries.hasMoreElements();) {
				ZipEntry entry = (ZipEntry) entries.nextElement();
				String zipEntryName = entry.getName();
				String outPath = (descDir + zipEntryName).replaceAll("\\*", "/");
				logger.info("输出路径：" + outPath);
				String dirPath = outPath.substring(0, outPath.lastIndexOf('/'));
				String fileName = outPath.substring(outPath.lastIndexOf('/')).trim().toLowerCase();
				// 判断路径是否存在,不存在则创建文件路径
				File file = new File(dirPath);
				if (!file.exists()) {
					file.mkdirs();
				}
				// 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
				if (new File(outPath).isDirectory()) {
					continue;
				}
				// 输出文件路径信息
				logger.info("Zip压缩文件中的文件名称:" + fileName);
				if (fileName.endsWith("html") || fileName.endsWith("htm") || fileName.endsWith("xlsx")
						|| fileName.endsWith("xls") || fileName.endsWith("txt")) {// 还有考虑不同版本的Excel格式
					return false;
				}

		        }
			}catch(Exception e){
				
			}finally{
					try {
						if(zip != null)
							zip.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
	        
	        return true;
	}
	
	/**
	 * 判断rar压缩文件是否包含HTML,EXCEL和TXT格式的文件
	 * @param attachment
	 * @return
	 */
	private boolean isRarRequired(String sourcePath) {
		  File sourceRar = new File(sourcePath);
		  Archive archive = null;  
	        try {  
	            archive = new Archive(sourceRar);  
	            FileHeader fh = archive.nextFileHeader();  
	            while (fh != null) {  
	                String compressFileName = "";
	                if(existZH(fh.getFileNameW())){//改名称中如果包含了中文，则使用该名称
	                	compressFileName = fh.getFileNameW().trim();
	                }else{
	                	compressFileName = fh.getFileNameString().trim();  
	                }
	                if(compressFileName.endsWith("html") || compressFileName.endsWith("htm") || compressFileName.endsWith("xlsx") || compressFileName.endsWith("xls") || compressFileName.endsWith("txt")){//还有考虑不同版本的Excel格式
	                	 return false;
	                }
	                fh = archive.nextFileHeader();
	            }
 	        }catch(Exception e){
	        	logger.error("获取Rar压缩文件中文件名称失败");
	        }finally{
					try {
						if(archive != null)
						archive.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
	        }
		return true;
	}
	
	
	/**
	 * 是否是第一次接收邮件，如果是第一次需要插入一条数据进行调度标识
	 * @return
	 */
	private boolean firstReceive(){
		String hql = " from TMailSchedule schedule where schedule.recordType = '0'";
		List<TMailSchedule> list = this.find(hql);
		if(list.size() > 0){
			return false;
		}
		return true;
	}
	
	/**
	 * 得到调度时间的间隔
	 */
	private int getInternalPeriod(Date date){
		String hql = " from TMailSchedule schedule where schedule.recordType = '0'";
		List<TMailSchedule> list = this.find(hql);
		if(list.size() > 0){//正常情况下，到达这一步说明一定存在上一次调度时间
			TMailSchedule t = list.get(0);
			//将两个时间进行相减
			Long time1 = date.getTime();
			Long time2 = t.getLastScheTime().getTime();
			int period = (int) ((time1 - time2) / ONE_MINUTE);
			logger.info("以获取上次调度时间  "+t.getLastScheTime()+" -----------  04");
//			if(period >= ONE_DAY){
//				return ONE_DAY;
//			} else{
				return period;
//			}
		}
		return ONE_DAY;
	}
	
	
	private Date getLastPeriod(Date date){
		String hql = " from TMailSchedule schedule where schedule.recordType = '0'";
		List<TMailSchedule> list = this.find(hql);
		if(list.size() > 0){//正常情况下，到达这一步说明一定存在上一次调度时间
			TMailSchedule t = list.get(0);
			return t.getLastScheTime();
		}
		return getLastDay(date);
	}
	
	/**
	 * 解析附件，并将解析后的文件的信息保存到数据库中
	 * @param attach
	 * @throws Exception 
	 */
	public void parseAttach(TMailAccessory accessory) throws Exception{
		String attachName = accessory.getAccessoryName();
		
		/**
		 * 解析rar的文件
		 */
		if(attachName.trim().toLowerCase().endsWith("rar")){//解压文件的时候，如果压缩文件中还有压缩文件？？？
			unrar(new File(accessory.getAbsolutePath()),new File(accessory.getAccessoryPath()),accessory.getMailId());
		}
		
		/**
		 * 解析zip的文件
		 */
		if(attachName.trim().toLowerCase().endsWith("zip")){
			zipDecompress(accessory.getAbsolutePath(),accessory.getAccessoryPath(),accessory.getMailId());
		}
	}
	
	/**
	 * 解压zip的文件
	 * @throws IOException 
	 */
	private void zipDecompress(String sourcePath,String targetPath,String messageId) throws IOException{
		File zipFile = new File(sourcePath);
		 File pathFile = new File(targetPath);  
	        if(!pathFile.exists()){  
	            pathFile.mkdirs();  
	        }  
	        ZipFile zip = new ZipFile(zipFile); 
	        for(Enumeration entries = zip.getEntries();entries.hasMoreElements();){  
	            ZipEntry entry = (ZipEntry)entries.nextElement();  
	            String zipEntryName = entry.getName();
	            logger.info("解压zip文件,"
            	 		+ "<文件名称："+zipEntryName+">  -----------  06_5");
	            InputStream in = zip.getInputStream(entry);  
	            String outPath = (targetPath+zipEntryName).replace("/", "\\");  
	            //判断路径是否存在,不存在则创建文件路径  
	            String dirPath = outPath.substring(0, outPath.lastIndexOf('\\'));
	            String fileName = outPath.substring(outPath.lastIndexOf('\\')+1).trim();//去除文件分隔符
	            File file = new File(dirPath);  
	            if(!file.exists()){  
	                file.mkdirs();  
	            }  
	            //判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压  
	            if(new File(outPath).isDirectory()){  
	                continue;  
	            }  
	            //输出文件路径信息  
	            logger.info("解压zip文件,"
            	 		+ "<输出文件路径信息 ："+outPath+">  -----------  06_5");
	            //建立压缩附件对象
                TMailAccessory accessory = new TMailAccessory();
                accessory.setMailId(messageId);
                accessory.setAbsolutePath(outPath);
                accessory.setAccessoryName(fileName);
                accessory.setAccessoryPath(dirPath);
                accessory.setRarChild(1);//这时为解压文件
                this.save(accessory);
	              
	            OutputStream out = new FileOutputStream(outPath);  
	            byte[] buf1 = new byte[1024];  
	            int len;  
	            while((len=in.read(buf1))>0){  
	                out.write(buf1,0,len);  
	            } 
	            if(in != null)
	            	in.close();  
	            if(out != null)
	            	out.close();  
	            } 
	}
	
	/**
	 * 解析rar的文件
	 * @param sourceRar
	 * @param destDir
	 * @throws Exception
	 */
	public void unrar(File sourceRar, File destDir,String messageId) throws Exception {  
        Archive archive = null;  
        FileOutputStream fos = null;  
        System.out.println("Starting...");  
        try {  
            archive = new Archive(sourceRar);  
            FileHeader fh = archive.nextFileHeader();  
            int count = 0;  
            File destFileName = null;  
            while (fh != null) {  
                String compressFileName = "";
                if(existZH(fh.getFileNameW())){//改名称中如果包含了中文，则使用该名称
                	compressFileName = fh.getFileNameW().trim();
                }else{
                	compressFileName = fh.getFileNameString().trim();  
                }
                logger.info("解压rar附件,"
            	 		+ "<附件名称："+compressFileName+">  -----------  06_5");
                destFileName = new File(destDir.getAbsolutePath() + "/" + compressFileName);  
                if (fh.isDirectory()) {  
                    if (!destFileName.exists()) {  
                        destFileName.mkdirs();  
                    }  
                    fh = archive.nextFileHeader();  
                    continue;  
                }   
                if (!destFileName.getParentFile().exists()) {  
                    destFileName.getParentFile().mkdirs();  
                }  
                fos = new FileOutputStream(destFileName);  
                
                String absolutePath = destFileName.getAbsolutePath();
              //建立压缩附件对象
                TMailAccessory accessory = new TMailAccessory();
	                accessory.setMailId(messageId);
	                accessory.setAbsolutePath(absolutePath);
	                accessory.setAccessoryName(destFileName.getName());
	                accessory.setAccessoryPath(absolutePath.substring(0,absolutePath.lastIndexOf("\\")));
	                accessory.setRarChild(1);//这时为解压文件
	                this.save(accessory);
                
                /**
                 * 提取下一个压缩文件
                 */
                archive.extractFile(fh, fos);  
                fos.close();  
                fos = null;  
                fh = archive.nextFileHeader();  
            }  
  
            archive.close();  
            archive = null;  
            logger.info("解压rar附件,解压rar完成  -----------  06_5");
        } catch (Exception e) {  
            throw e;  
        } finally {  
            if (fos != null) {  
                try {  
                    fos.close();  
                    fos = null;  
                } catch (Exception e) {  
                    //ignore  
                }  
            }  
            if (archive != null) {  
                try {  
                    archive.close();  
                    archive = null;  
                } catch (Exception e) {  
                    //ignore  
                }  
            }  
        }  
    }  


	/**
	 * 判断是否包含有中文
	 * @param str
	 * @return
	 */
	public static boolean existZH(String str) {  
	    String regEx = "[\\u4e00-\\u9fa5]";  
	    Pattern p = Pattern.compile(regEx);  
	    Matcher m = p.matcher(str);  
	    while (m.find()) {  
	        return true;  
	    }  
	    return false;  
	}  
	
	/**
	 * 得到业务日期，规则如下：
	 * 如果T日 18:00 ~ T+1日 18:00,那么业务日期即为T日;否则即为T+1日
	 * 排除节假日
	 * @param mailDate 根据邮件发送日期进行算起
	 * @return
	 * @throws ParseException
	 */
	private Date getBizDate(Date mailDate) throws ParseException {
		DateFormat df = new SimpleDateFormat(DATE_FORMATE1);
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		if(hour > BISNESS_HOUR){
			return mailDate;
		}else{
			/**
			 * 需要排除掉节假日
			 */
			String dateStr = df.format(getLastDay(cal.getTime()));
			Date date = df.parse(dateStr);
			
			return getLastBizDate(date);
		}
	}
	
	
	/**
	 * 根据当前时间，获取它的上一个业务日期
	 * @param date
	 * @return
	 * @throws ParseException 
	 */
	private Date getLastBizDate(Date date) throws ParseException{
		/**
		 * 从假期表中取小于发送日的前15条记录
		 * 不重复查询数据库，通过递归来执行校验
		 */
		Date result = date;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String sql = "select * from (select fdate from HSV_CSHOLIDAY where fdate < ?  order by fdate desc) T where rownum < 15";
		Date newDate = DateUtil.str2Date(DateUtil.formatDate(date), df);
		List<Map<String,Object>> list = this.findForJdbc(sql, newDate);
		if(list.size() > 0){
			for (Map<String,Object> map : list) {
				Date fdate = (Date) map.get("fdate");
				if(fdate.compareTo(result) != 0){
					break;
				}else{
					String dateStr = df.format(getLastDay(result));
					result = df.parse(dateStr);
					continue;
				}
			}
		}
		return result;
	}
	
	private boolean isHoliday(){
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String sql = "select fdate from HSV_CSHOLIDAY where fdate = ?  order by fdate desc";
		Date newDate = DateUtil.str2Date(DateUtil.formatDate(date), df);
		List<Map<String,Object>> list = this.findForJdbc(sql, newDate);
		if(list.size() > 0){
			return true;
		}
		return false;
	}

	/**
	 * 创建要保存的路径
	 * @param name
	 * @return
	 */
	private String createDir(Date date) {
		
		DateFormat df = new SimpleDateFormat(DATE_FORMATE);
		
//		List<THdImportConfig> list = this.find(" from THdImportConfig t");//该表中只存在一条数据
//		THdImportConfig config = list.get(0);
//		String path = config.getHdImportPath();
		String datePath = df.format(date);
//		if(path.contains("yyyyMMdd")){
//			path.replace("yyyyMMdd", datePath);
//		}
		
		StringBuilder path = new StringBuilder("E:\\mailtest\\");//这里的绝对路径需要进行配置
		System.out.println(datePath);
		String timePath = datePath.substring(9,15).replace(TIME_SEPERATOR, "");
		path.append("_").append(timePath);
		
		File file = new File(path.toString());
		if(!file.exists()){
			file.mkdirs();
			System.out.println("创建目录" + path.toString() + "成功！");
		}
		
		return path.append("\\").toString();
	}

	/**
	 * 邮件调度记录表每天会做一个定时清除，确保一定程度上的有效数据
	 * 返回所有已经解析过的邮件的ID
	 * @return
	 */
	private List<String> getMessageIds(){
//		List<String> result = new ArrayList<String>();
//		List<TMailSchedule> list = this.getList(TMailSchedule.class);
//		for (TMailSchedule tMailSchedule : list) {
//			result.add(tMailSchedule.getMessageId());
//		}
//		return result;
		return this.find("select T.messageId from TMailSchedule T where T.recordType = '1'");
	}
	
	
	public Date getMarketDay(Date date, int addDays) {
		int oneDay = 0;
		if (addDays > 0) {
			oneDay = 1;
		} else if (addDays < 0) {
			oneDay = -1;
		} else {
			return date;
		}

		List<Map<String, Object>> holidayList = null;
		do {
			date = DateUtil.addAndMinusDate(date, oneDay);
			holidayList = this.findForJdbc("SELECT FDATE AS holiday FROM HSV_CSHOLIDAY WHERE FDATE=?", date);
		} while (holidayList != null && holidayList.size() > 0);

		return getMarketDay(date, addDays - oneDay);
	}

	@Override
	public DataGrid dataGrid(ProductMailList pmList, PageHelper pageHelper) {
		DataGrid dataGrid = new DataGrid();
		StringBuffer sb = new StringBuffer(512);
		List<Object> params = new ArrayList<Object>();
		sb.append("select mm.id as id,mm.mail_title as mailTitle,mm.send_person as sendPerson,mm.cc_person as ccPerson,il.biz_date as bizDate,mm.mail_date as mailDate, ");
		sb.append(" il.security_company as securityCompany,pp.product_name as productName,il.account as account ");
		sb.append(" from hd_mail_manager mm ");
		sb.append(" left join HD_IMPORT_LOG il on il.mail_id = mm.id ");	
		sb.append(" left join pr_product pp on pp.id = il.pro_id ");
		try{
			
			sb.append(getWhereSql(pmList, params, pageHelper.getQSearch()));
			
			sb.append(getOrderSql(pageHelper));
			
			dataGrid = this.getDataGridBySql(sb.toString(), pageHelper.getPage(), pageHelper.getRows(), ProductMailList.class,
					params.toArray());
			return dataGrid;
		}catch (Exception e){
			logger.error("查询产品邮件列表失败");
			return null;
		}
	}
	
	private String getWhereSql(ProductMailList pmList, List<Object> params, String qSearch){
		StringBuilder sb = new StringBuilder(" where 1 = 1 ");
		if(StringUtil.isNotEmpty(pmList.getMailTitle())){
			sb.append(" and mm.mail_title like ? ");
			params.add("%" + pmList.getMailTitle() + "%");
		}
		if(StringUtil.isNotEmpty(pmList.getAccount())){
			sb.append(" and il.account like ? ");
			params.add("%" + pmList.getAccount() + "%");
		}
		if(StringUtil.isNotEmpty(pmList.getProductName())){
			sb.append(" and pp.product_name like ? ");
			params.add("%" + pmList.getProductName() + "%");
		}
		if(StringUtil.isNotEmpty(pmList.getSecurityCompany())){
			sb.append(" and il.security_company like ? ");
			params.add("%" + pmList.getSecurityCompany() + "%");
		}
		if(StringUtil.isNotEmpty(pmList.getBizDate())){
			sb.append(" and il.biz_date like ? ");
			params.add("%" + pmList.getBizDate() + "%");
		}
		
		if (StringUtil.isNotEmpty(qSearch)) {
			sb.append(" and ( il.security_company like ? or pp.product_name like ?  or il.account like ? or mm.mail_title like ?) ");
			params.add("%" + qSearch + "%");
			params.add("%" + qSearch + "%");
			params.add("%" + qSearch + "%");
			params.add("%" + qSearch + "%");
		}
		
		return sb.toString();
	}
	
	private String getOrderSql(PageHelper pageHelper){
		String hql = "";
		if (StringUtil.isNotEmpty(pageHelper.getSort()) && StringUtil.isNotEmpty(pageHelper.getOrder())) {
			hql += " order by ";
			if(pageHelper.getSort().equals("bizDate")){
				hql += "il.biz_date";
				hql += " " + pageHelper.getOrder();
			}else if(pageHelper.getSort().equals("mailTitle")){
				hql += "mm.mail_title";
				hql += " " + pageHelper.getOrder();
			}else if(pageHelper.getSort().equals("sendPerson")){
				hql += "mm.send_person";
				hql += " " + pageHelper.getOrder();
			}else if(pageHelper.getSort().equals("ccPerson")){
				hql += "mm.cc_person";
				hql += " " + pageHelper.getOrder();
			}else if(pageHelper.getSort().equals("mailDate")){
				hql += "mm.mail_date";
				hql += " " + pageHelper.getOrder();
			}else if(pageHelper.getSort().equals("securityCompany")){
				hql += "il.security_company";
				hql += " " + pageHelper.getOrder();
			}else if(pageHelper.getSort().equals("account")){
				hql += "il.account";
				hql += " " + pageHelper.getOrder();
			}else if(pageHelper.getSort().equals("productName")){
				hql += "pp.product_name";
				hql += " " + pageHelper.getOrder();
			}
		}
		return hql;
	}
	
	@Override
	public DataGrid getStatement(String id) {
		DataGrid dataGrid = new DataGrid();
		
		StringBuffer sb = new StringBuffer(512);
		sb.append("select t.biz_date as bizDate,");
		sb.append("t.security_company as securityCompany,");
		sb.append("t.capital_account as account,");
		sb.append("t.capital_balance as capitalBalance,");
		sb.append("t.account_name as accountName ");
		sb.append(" from hd_broker_head t ");
		sb.append(" where t.mail_id = ? ");
		
		dataGrid = this.getDataGridBySql(sb.toString(), 1, 100, THdBrokerHead.class, id);
//		int count = this.countBySql("select count(*) from hd_broker_head t where t.mail_id = ? ", id);
//		dataGrid.setTotal(count);
//		if(count > 0){
//			List<Map<String, Object>> modelList = this.findForJdbc(sb.toString(), id);
//			dataGrid.setRows(modelList);
//		}
//		this.getDataGridBySql(sb.toString(), 1, 100, id);
		
		return dataGrid;
	}

	@Override
	public DataGrid getAccesory(String id) {//这里为什么不能正常给界面赋值的原因在于，Oracle会自动将字段名称全部变成大写所以，无法进行赋值
		DataGrid dataGrid = new DataGrid();
		String sql = "select t.id as id,t.accessory_name as accessoryName,t.accessory_path as accessoryPath "
				+ "from hd_mail_accessory t where t.rar_child = 0 and t.mail_id = ?";
		
		//String hql = " from TMailAccessory t where t.rar_child = 0 and t.mail_id = ?";
		dataGrid = this.getDataGridBySql(sql, 1, 100, TMailAccessory.class, id);
//		int count = this.countBySql("select count(*) from hd_mail_accessory t where t.rar_child = 0 and t.mail_id = ? ", id);
//		dataGrid.setTotal(count);
//		if(count > 0){
//			List<Map<String, Object>> modelList = this.findForJdbc(sql, id);
//			dataGrid.setRows(modelList);
//		}
		
		return dataGrid;
	}
}
