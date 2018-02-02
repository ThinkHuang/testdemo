package com.huang.demo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 利用Java实现文件的压缩
 * @author dell
 *
 */
public class ZipCompression {

	private int layer = 1;//定义压缩层次
	
	public ZipCompression(){
		
	}
	
	public static void main(String[] args) throws Exception {
		
		ZipCompression obj = new ZipCompression();
		
		obj.docCompress("c:\\test\\plsqldeveloper_sn.zip", new File("c:\\test\\compression"));
	}
	
	/**
     * 文件压缩 由于上报时采用的文件类型为ZIP，所以使用ZIP进行文件打包
     * 
     * @param filename 压缩后的文件名称，绝对路径
     * @param inputFile 要压缩的文件路径，绝对路径
     */
    public static void docCompress(String filename, File inputFile) {
	StopWatch watch = new StopWatch();
	watch.start("start document compress <<<<<<");
	ZipOutputStream zos = null;
	BufferedOutputStream bos = null;
	try {
	    zos = new ZipOutputStream(new FileOutputStream(filename));
	    bos = new BufferedOutputStream(zos);
	    compressToZip(zos, inputFile, inputFile.getName(), bos);
	    watch.stop();
	    log.info("stop document compress <<<<<< StopWatch\n" + watch.prettyPrint());
	} catch (Exception e) {
	    log.error("压缩文件出现异常：_" + e.getMessage(), e);
	} finally {
	    /*
	     * 关闭资源
	     */
	    try {
		if (bos != null) {
		    bos.close();
		}
	    } catch (IOException e) {
		log.error("缓冲输出流关闭失败：_" + e.getMessage(), e);
	    }
	    try {
		if (zos != null) {
		    zos.close();
		}
	    } catch (IOException e) {
		log.error("压缩输出流关闭失败：_" + e.getMessage(), e);
	    }
	}
    }

    /**
     * @param zos 压缩流
     * @param file 压缩的文件
     * @param base zip压缩进入点
     * @param bos 缓冲输出流
     * @throws Exception
     */
    private static void compressToZip(ZipOutputStream zos, File file, String base, BufferedOutputStream bos) {
	// 由于待要压缩的文件不存在目录，所以基本会进入到这里
	FileInputStream fis = null;
	BufferedInputStream bis = null;
	if (file.isFile()) {
	    try {
		zos.putNextEntry(new ZipEntry(base)); // 创建zip压缩进入点base
		fis = new FileInputStream(file);
		bis = new BufferedInputStream(fis);
		int buffer;
		while ((buffer = bis.read()) != -1) {
		    // 将字节流写入当前zip目录
		    bos.write(buffer);
		}
	    } catch (Exception e) {

	    } finally {
		if (fis != null) {
		    try {
			fis.close();
		    } catch (IOException e) {
			log.error("文件输入流关闭失败：_" + e.getMessage(), e);
		    }
		}
		if (bos != null) {
		    try {
			bos.close();
		    } catch (IOException e) {
			log.error("缓冲输出流关闭失败：_" + e.getMessage(), e);
		    }
		}
	    }
	} else {
	    // 如果是目录，那么将会创建多个压缩层次
	    File[] flieList = file.listFiles();
	    if (flieList.length == 0) {
		// 创建zip压缩进入点base
		try {
		    zos.putNextEntry(new ZipEntry(base + "/"));
		} catch (IOException e) {
		    log.error("写入压缩输出流失败：_" + e.getMessage(), e);
		    // throw new BusinessException("");TODO 抛出业务异常
		}
		log.debug(base + "/");
	    }
	    for (int i = 0; i < flieList.length; i++) {
		compressToZip(zos, flieList[i], base + "/" + flieList[i].getName(), bos); // 递归遍历子文件夹
	    }
	    log.debug("第" + layer + "次递归");
	    layer++;
	}
    }
}
