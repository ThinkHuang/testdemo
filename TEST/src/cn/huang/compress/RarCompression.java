package com.huang.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.innosystec.unrar.Archive;
import de.innosystec.unrar.rarfile.FileHeader;

/**
 * 该工具只能解压rar的文件
 * @author dell
 *
 */
public class RarCompression {

	
	public static void main(String[] args) throws Exception {
		new RarCompression().unrar(new File("c:\\test\\webservices-api.rar"), new File("c:\\test"));
	}
	
	 public void unrar(File sourceRar, File destDir) throws Exception {  
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
		                	System.out.println((++count) + ") " + compressFileName);
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
		                archive.extractFile(fh, fos);  
		                fos.close();  
		                fos = null;  
		                fh = archive.nextFileHeader();  
		            }  
		  
		            archive.close();  
		            archive = null;  
		            System.out.println("Finished !");  
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

}
