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
	        // 定义监视器
	//StopWatch watch = new StopWatch();
	//watch.start("start document decompress >>>>>>");
	// 输出文件定义
	File out = null;
	ZipEntry entry;
	ZipInputStream zis = null;
	BufferedInputStream bis = null;
	FileOutputStream fos = null;
	BufferedOutputStream bos = null;
	try {
	    zis = new ZipInputStream(new FileInputStream(inputPath));
	    bis = new BufferedInputStream(zis);
	    while ((entry = zis.getNextEntry()) != null) {
		if (entry.isDirectory()) {
		    continue;
		}
		out = new File(outputPath, entry.getName());
		if (!out.exists()) {
		    new File(out.getParent()).mkdirs();
		}
		fos = new FileOutputStream(out);
		bos = new BufferedOutputStream(fos);
		int buffer;
		while ((buffer = bis.read()) != -1) {
		    bos.write(buffer);
		    // 刷新缓冲区
		    bos.flush();
		}
		log.info(out + "解压成功");
		watch.stop();
		//log.info("stop document decompress >>>>>> StopWatch\n" + watch.prettyPrint());
	    }
	} catch (IOException e) {
	    log.error("文件压缩失败：_" + e.getMessage(), e);
	} finally {
	    try {
		if (bis != null) {
		    bis.close();
		}
	    } catch (IOException e) {
		log.error("缓冲输入流关闭失败：_" + e.getMessage(), e);
	    }
	    try {
		if (zis != null) {
		    zis.close();
		}
	    } catch (IOException e) {
		log.error("压缩输入流关闭失败：_" + e.getMessage(), e);
	    }
	    try {
		if (fos != null) {
		    fos.close();
		}
	    } catch (IOException e) {
		log.error("文件输出流关闭失败：_" + e.getMessage(), e);
	    }
	    try {
		if (bos != null) {
		    bos.close();
		}
	    } catch (IOException e) {
		log.error("缓冲输出流关闭失败：_" + e.getMessage(), e);
	    }
	}  
	    }  
	 
	/**
     * 获取指定压缩文件中的所有文件名称
     * @param filePath 文件路径
     * @param tempPath 临时解压路径
     * @throws FileNotFoundException
     */
    public List<String> getFileNames(String filePath, String tempPath) throws FileNotFoundException {
	if(StringUtil.isEmpty(filePath)) {
	    //throw new BusinessException() TODO 抛出业务异常
	}
	ZipInputStream zis = null;
	File fOut = null;
	ZipEntry entry;
	List<String> result = null;
	try {
	    zis = new ZipInputStream(new FileInputStream(filePath));
	    result = new ArrayList<String>();
	    while ((entry = zis.getNextEntry()) != null) {
		fOut = new File(StringUtil.isEmpty(tempPath) ? System.getProperty("java.io.tmpdir") : tempPath, entry.getName());
		String fileName = fOut.getName();
		log.debug("文件名称为：_" + fileName);
		result.add(fileName);
	    }
	    return result;
	} catch (Exception e) {
	    log.error("获取压缩文件名失败：_" + e.getMessage(), e);
	} finally {
	    if (zis != null) {
		try {
		    zis.close();
		} catch (IOException e) {
		    log.error("关闭资源流失败：_" + e.getMessage(), e);
		}
	    }
	}
	return null;
    }
	 
	
}
