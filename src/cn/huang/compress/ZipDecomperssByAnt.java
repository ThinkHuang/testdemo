package compress;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.junit.Test;

/**
 * 这里使用了ant.jar中的ZipFile完成中文乱码的解决
 * 另外一种方式是使用JDK 1.7自带的ZipFile(File,Charset)
 * @author dell
 *
 */
public class ZipDecomperssByAnt {

	
	@Test
	 public void unZipFiles()throws IOException{ 
		 File zipFile = new File("C:/test/plsqldeveloper_sn.zip");
		 String descDir = "D:/test/decompression/";
		 File pathFile = new File(descDir);  
	        if(!pathFile.exists()){  
	            pathFile.mkdirs();  
	        }  
	        ZipFile zip = new ZipFile(zipFile); 
	        for(Enumeration entries = zip.getEntries();entries.hasMoreElements();){  
	            ZipEntry entry = (ZipEntry)entries.nextElement();  
	            String zipEntryName = entry.getName();  
	            InputStream in = zip.getInputStream(entry);  
	            String outPath = (descDir+zipEntryName).replaceAll("\\*", "/");;  
	            //判断路径是否存在,不存在则创建文件路径  
	            File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));  
	            if(!file.exists()){  
	                file.mkdirs();  
	            }  
	            //判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压  
	            if(new File(outPath).isDirectory()){  
	                continue;  
	            }  
	            //输出文件路径信息  
	            System.out.println(outPath);  
	              
	            OutputStream out = new FileOutputStream(outPath);  
	            byte[] buf1 = new byte[1024];  
	            int len;  
	            while((len=in.read(buf1))>0){  
	                out.write(buf1,0,len);  
	            }  
	            in.close();  
	            out.close();  
	            }  
	        System.out.println("******************解压完毕********************");  
	    }  
	
		/**
		 * 获取压缩文件中所有的文件名称
		 * @throws IOException
		 */
		@Test
		public void getAllFileNames() throws IOException{
			File zipFile = new File("C:/test/plsqldeveloper_sn.zip");
			 String descDir = "D:/test/decompression/";
			 File pathFile = new File(descDir);  
		        if(!pathFile.exists()){  
		            pathFile.mkdirs();  
		        }  
		        ZipFile zip = new ZipFile(zipFile); 
		        for(Enumeration entries = zip.getEntries();entries.hasMoreElements();){  
		            ZipEntry entry = (ZipEntry)entries.nextElement();  
		            String zipEntryName = entry.getName();  
		            String outPath = (descDir+zipEntryName).replaceAll("\\*", "/");;  
		            //判断路径是否存在,不存在则创建文件路径  
		            File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));  
		            if(!file.exists()){  
		                file.mkdirs();  
		            }  
		            //判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压  
		            if(new File(outPath).isDirectory()){  
		                continue;  
		            }  
		            //输出文件路径信息  
		            System.out.println(outPath);  
		        }
		}
}
