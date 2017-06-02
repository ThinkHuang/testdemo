package com.wescxx.app.palHd.utility;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;


import com.independentsoft.exchange.Attachment;
import com.independentsoft.exchange.FileAttachment;
import com.independentsoft.exchange.FindItemResponse;
import com.independentsoft.exchange.IsGreaterThanOrEqualTo;
import com.independentsoft.exchange.Item;
import com.independentsoft.exchange.ItemAttachment;
import com.independentsoft.exchange.ItemId;
import com.independentsoft.exchange.Message;
import com.independentsoft.exchange.MessagePropertyPath;
import com.independentsoft.exchange.Service;
import com.independentsoft.exchange.StandardFolder;

public class ReceiveMailByJWebService {
	
	private static ReceiveMailByJWebService instance = new ReceiveMailByJWebService();
	
	private static Service service = null;
	
	private ReceiveMailByJWebService(){
		
	}
	
	public static ReceiveMailByJWebService getInstance(){
		synchronized (instance) {
			if(instance == null){
				instance = new ReceiveMailByJWebService();
			}
		}
		return instance;
	}
	
	
	/**
	 * 返回使用JWebServices返回的所有未读消息列表
	 * @param param
	 * @return List<Message>
	 * @throws Exception
	 */
//	public List<Message> getMessage(List<Map<String,Object>> param,List<String> messageIds, Date period) throws Exception{
	public List<Message> getMessage(List<Map<String,Object>> param,List<String> messageIds, Integer internal) throws Exception{
		
		List<Message> messages = new ArrayList<Message>();
		
		/**
		 * 这里的三个参数host，username和password都是从邮箱列表中来的，所以这里需要做一次查询
		 */
		
		for (int index = 0; index < param.size(); index++) {
			
			Map<String,Object> map = param.get(index);
		
			String host = (String) map.get("host");
			String username = (String) map.get("username");
			String password = (String) map.get("password");
		    
			service = new Service("https://" + host + "/ews/Exchange.asmx", username, password);
			
		    Calendar localCalendar = Calendar.getInstance();
            localCalendar.add(Calendar.MINUTE, -internal);

            Date time = localCalendar.getTime();
			
	
			/**
			 * 该已读只限于物理读取邮件
			 * 需要拿到一个时间段之内的所有邮件，而不需要管该邮件客户是否已经读取，可以采用这样的方式，如果该邮件包含附件我们才需要解析，如果没有，我们将不做解析动作
			 * 但如果客户忘记发送附件，那么我们将不知道那些客户没有发送邮件，哪些发送了邮件但是却没有发送附件
			 * 具体的做法是，我们在催发邮件那里的时候，不管是否发送没有附件的邮件，还是没有发送邮件的客户都进行催发
			 */
            IsGreaterThanOrEqualTo  restriction = new IsGreaterThanOrEqualTo(MessagePropertyPath.RECEIVED_TIME, time);
	
	        FindItemResponse response = service.findItem(StandardFolder.INBOX, MessagePropertyPath.getAllPropertyPaths(), restriction);
//            IsEqualTo restriction = new IsEqualTo(MessagePropertyPath.HAS_ATTACHMENTS, true);
//
//            ItemShape itemShape = new ItemShape(ShapeType.ID);
//
//            FindItemResponse response = service.findItem(StandardFolder.INBOX, itemShape, restriction);
	        System.out.println("size:" + response.getItems().size());
	        for (int i = 0; i < response.getItems().size(); i++)
	        {
	            if (response.getItems().get(i) instanceof Message)
	            	{
	            	
	            	ItemId itemId = response.getItems().get(i).getItemId();
                    Message message = service.getMessage(itemId);
//	                Message message = (Message) response.getItems().get(i);
	                if(IsUnparse(message.getEntryId(),messageIds)){
//                		Date receiveDate = message.getReceivedTime();//只能在这里着手准备接收邮件的时间了
//                		if(receiveDate.compareTo(period) >= 0){
                			messages.add(message);
//                		}
	                }
	            }
	        }
		}
        return messages;
	}

	/**
	 * 取得所有没有解析的邮件
	 * @param entryId
	 * @return
	 */
	private boolean IsUnparse(String entryId,List<String> ids) {
		if(ids.contains(entryId)){
			return false;
		}
		return true;
	}

	/**
	 * 保存附件，并返回附件附件对象
	 * @param id
	 * @param Attachment
	 * @return
	 * @throws Exception
	 */
	public Attachment saveAttachment(String id,String path){
		
		Attachment attachment = null;
		FileOutputStream fileStream = null;
		try{
			attachment = getAttach(id);
			if (attachment instanceof FileAttachment){
				
				FileAttachment fileAttachment = (FileAttachment) attachment;
				
				byte[] buffer = fileAttachment.getContent();
				String fileName = fileAttachment.getName();
				
				System.out.println("保存的路径为：" + path + fileName);
				fileStream = new FileOutputStream(path + fileName);
				fileStream.write(buffer);
				
			} else if (attachment instanceof ItemAttachment) {
				ItemAttachment itemAttachment = (ItemAttachment) attachment;
				
				String name = itemAttachment.getName();
				Item attachedItem = itemAttachment.getItem();
			}
			
		}catch(Exception e){
			//TODO:
			throw new RuntimeException("保存附件异常");
		}finally{
			try {
				if(fileStream != null)
					fileStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        
        return attachment;
	}
	
	
	public Attachment getAttach(String id) throws Exception{
		return service.getAttachment(id);
	}
	
}
