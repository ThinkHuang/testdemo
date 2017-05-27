package com.huang.demo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.junit.Test;

/**
 * java实现文件的解压
 * @author dell
 *
 */
public class ZipDecompression {

	 public static void main(String[] args) {  
	        long startTime=System.currentTimeMillis();  
	        try {  
	            ZipInputStream Zin=new ZipInputStream(new FileInputStream(  
	                    "C:\\test\\Hibernate中文API大全.rar"));//输入源zip路径  
	            BufferedInputStream Bin=new BufferedInputStream(Zin);  
	            String Parent="C:\\test\\decompression"; //输出路径（文件夹目录）  
	            File Fout=null;  
	            ZipEntry entry;  
	            try {  
	                while((entry = Zin.getNextEntry())!=null){ 
	                	if(entry.isDirectory())continue;  
	                    Fout=new File(Parent,entry.getName());  
	                    if(!Fout.exists()){  
	                        (new File(Fout.getParent())).mkdirs();  
	                    }  
	                    FileOutputStream out=new FileOutputStream(Fout);  
	                    BufferedOutputStream Bout=new BufferedOutputStream(out);  
	                    int b;  
	                    while((b=Bin.read())!=-1){  
	                        Bout.write(b);
	                        Bout.flush();//刷新缓冲区
	                    }  
	                    Bout.close();  
	                    out.close();  
	                    System.out.println(Fout+"解压成功");      
	                }  
	                Bin.close();  
	                Zin.close();  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            }  
	        } catch (FileNotFoundException e) {  
	            e.printStackTrace();  
	        }  
	        long endTime=System.currentTimeMillis();  
	        System.out.println("耗费时间： "+(endTime-startTime)+" ms");  
	    }  
	 
	/**
	 * 获取压缩文件的所有文件名称
	 * @throws FileNotFoundException 
	 */
	 @Test
	 public void getAllFileNames() throws FileNotFoundException{
		 ZipInputStream Zin=new ZipInputStream(new FileInputStream("C:\\test\\plsqldeveloper_sn.zip"));
		 String Parent="D:\\test\\decompression"; //输出路径（文件夹目录）  
         File Fout=null;  
         ZipEntry entry;  
         try {  
             while((entry = Zin.getNextEntry())!=null){ 
             	//if(entry.isDirectory())continue;  
             	
                 Fout=new File(Parent,entry.getName());
                 String fileName = Fout.getName().trim().toLowerCase();
                 System.out.println("filename:" + entry.getName());
             }
         }catch(Exception e){
        	 //TODO: Ignore
         }
	 }
	 
	
}
