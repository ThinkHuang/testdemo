package cn.huang.wechat.automessagereply;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;

public class WeChatMessageUtil
{
    // 素材管理-新增临时素材的官方接口
    private static String apiUrl = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token={0}&type={1}";
    
    // 公众号APPID
    private static final String APPID = "wx0cf376d6d0f01ad5";
    
    // 公众号AppSecret
    private static final String APP_SECRET = "26a02487f4d9b9792fc1b60f76c72da0";
    
    // 公众号access_token
    private static final String WECHAT_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APPID + "&secret=" + APP_SECRET;
    
    // 消息类型
    public final static String USERRESP_MESSAGE_TYPE_IMAGE = "image";// 常量
    
    public final static String USERRESP_MESSAGE_TYPE_TEXT = "text";// 常量
    
    /**
     * 获取access_token
     * @return
     */
    public static AccessToken getAccessToken()
    {
        AccessToken token = new AccessToken();
        JSONObject jsonObject = doGet(WECHAT_TOKEN_URL);// 调用doGet方法发送get请求到url
        if (jsonObject != null)
        {
            token.setAccess_token(jsonObject.getString("access_token"));
            token.setExpires_in(jsonObject.getInt("expires_in"));
        }
        return token;
    }
    
    public static JSONObject doGet(String url)
    {
        JSONObject jsonObject = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();// 替代DefaultHttpClient
        HttpGet httpGet = new HttpGet(url);
        try
        {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null)
            {
                String result = EntityUtils.toString(entity);
                jsonObject = JSONObject.fromObject(result);
            }
        }
        catch (ClientProtocolException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return jsonObject;
    }
    
    /**
     * 上传临时文件到微信服务器
     * @param filePath
     * @param accessToken
     * @param type
     * @return
     * @throws IOException
     */
    public static String upload(String filePath, String accessToken, String type) throws IOException
    {
        File file = new File(filePath);
        if (!file.isFile() || !file.exists())
        {
            throw new IOException("文件不存在");
        }
        String url = getPlaceHolder(apiUrl, new Object[] {accessToken, type});
        System.out.println(url);
        URL urlObj = new URL(url);
        
        HttpURLConnection connection = (HttpURLConnection)urlObj.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setUseCaches(false);
        
        // 设置请求头
        connection.setRequestProperty("Connection", "Keep-Alive");
        connection.setRequestProperty("Charset", "utf-8");
        
        // 设置边界
        String BOUNDARY = "----------" + System.currentTimeMillis();
        connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + BOUNDARY);
        
        StringBuilder sb = new StringBuilder();
        sb.append("--");
        sb.append(BOUNDARY);
        sb.append("\r\n");
        sb.append("Content-Disposition;form-data;name=\"flie\";filename=\"" + file.getName() + "\"\r\n");
        sb.append("Content-Type:application/octet-stream\r\n\r\n");
        byte[] head = sb.toString().getBytes("utf-8");
        
        // 获取输出流
        OutputStream out = new DataOutputStream(connection.getOutputStream());
        out.write(head);
        
        // 文件正文部分，把文件以流的方式push到url中
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        int bytes = 0;
        byte[] bufferOut = new byte[1024];
        while ((bytes = in.read(bufferOut)) != -1)
        {
            out.write(bufferOut, 0, bytes);
        }
        in.close();
        
        // 结尾部分
        byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");
        out.write(foot);
        out.close();
        
        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = null;
        String result = null;
        try
        {
            // 读取url响应结果
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null)
            {
                buffer.append(line);
            }
            if (result == null)
            {
                result = buffer.toString();
            }
        }
        catch (Exception e)
        {
            System.out.println("公众号上传临时文件失败:" + e.getMessage());
        }
        finally
        {
            if (reader != null)
            {
                reader.close();
            }
        }
        JSONObject jsonObject = JSONObject.fromObject(result);
        return jsonObject.getString("media_id");
    }
    
    public static String getPlaceHolder(String msg, Object[] args)
    {
        return MessageFormat.format(msg, args);
    }
    
    public static String sendImageMessage(String toUserName, String fromUserName, String mediaId)
    {
        ImageMessage image = new ImageMessage();
        image.setFromUserName(toUserName);
        image.setToUserName(fromUserName);
        image.setCreateTime(new Date().getTime());
        image.setMsgType(USERRESP_MESSAGE_TYPE_IMAGE);
        image.setMediaId(mediaId);
        
        return XMLUtil.mapToXml(image);
    }
    
    public static String sendImageMessage(String toUserName, String fromUserName) throws Exception
    {
        AccessToken token = getAccessToken();
        String path = "D:/123.jpg";
        // 上传多媒体文件到素材管理的接口中，微信服务器会返回mediaId
        String mediaId = upload(path, token.getAccess_token(), USERRESP_MESSAGE_TYPE_IMAGE);
        return sendImageMessage(toUserName, fromUserName, mediaId);
    }
    
    public static String sendTextMessage(String toUserName, String fromUserName) throws Exception
    {
        TextMessage text = new TextMessage();
        text.setToUserName(toUserName);
        text.setFromUserName(fromUserName);
        text.setCreateTime(new Date().getTime());
        text.setMsgType(USERRESP_MESSAGE_TYPE_TEXT);
        text.setContent("hello");
        return XMLUtil.mapToXml(text);
    }
    
    public static void main(String[] args) throws Exception
    {
        System.out.println(sendImageMessage("gh_f3ba9ad92c04","oMDjs0nBzU9Y1R7Kdu3V7ZzkdofQ"));
    }
}
