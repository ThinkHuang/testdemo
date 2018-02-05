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
     * 场景：适合将指定目录下的所有文件压缩成一个ZIP文件，目录中不包含其他目录
     * 
     * @param sourceFilePath 待压缩的文件路径
     * @param zipFilePath 压缩后存放路径
     * @param fileName 压缩后文件的名称
     * @return
     */
    public static boolean docCompressInDirctory(String sourceFilePath, String zipFilePath, String fileName) {
	boolean flag = false;
	StopWatch watch = new StopWatch();
	watch.start("document compress <<<<<<");
	File sourceFile = new File(sourceFilePath);
	FileInputStream fis = null;
	BufferedInputStream bis = null;
	FileOutputStream fos = null;
	ZipOutputStream zos = null;

	if (sourceFile.exists() == false) {
	    log.warn("待压缩的文件目录：" + sourceFilePath + "不存在.");
	    return flag;
	}
	try {
	    File zipFile = new File(zipFilePath + File.separator + fileName + XBRL_ZIP_SUFFIX);
	    if (zipFile.exists()) {
		log.warn(zipFilePath + "目录下存在名字为:" + fileName + XBRL_ZIP_SUFFIX + "打包文件.");
		return flag;
	    }
	    File[] sourceFiles = sourceFile.listFiles();
	    if (null == sourceFiles || sourceFiles.length < 1) {
		log.warn("待压缩的文件目录：" + sourceFilePath + "里面不存在文件，无需压缩.");
		return flag;
	    }
	    fos = new FileOutputStream(zipFile);
	    zos = new ZipOutputStream(new BufferedOutputStream(fos));
	    byte[] bufs = new byte[BUFFER_SIZE];
	    for (int i = 0; i < sourceFiles.length; i++) {
		// 创建ZIP实体，并添加进压缩包
		ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());
		zos.putNextEntry(zipEntry);
		// 读取待压缩的文件并写进压缩包里
		fis = new FileInputStream(sourceFiles[i]);
		bis = new BufferedInputStream(fis, BUFFER_SIZE);
		int read = 0;
		while ((read = bis.read(bufs, 0, BUFFER_SIZE)) != -1) {
		    zos.write(bufs, 0, read);
		}
	    }
	    flag = true;
	    watch.stop();
	    log.info("stop document compress <<<<<< StopWatch\n" + watch.prettyPrint());
	} catch (Exception e) {
	    log.error("压缩文件出现异常：_" + e.getMessage(), e);
	} finally {
	    // 关闭流
	    try {
		if (null != bis) {
		    bis.close();
		}
	    } catch (IOException e) {
		log.error("缓冲输入流关闭失败：_" + e.getMessage(), e);
	    }
	    try {
		if (null != zos) {
		    zos.close();
		}
	    } catch (IOException e) {
		log.error("压缩输出流关闭失败：_" + e.getMessage(), e);
	    }
	}
	return flag;
    }

    /**
     * 场景：不管是压缩单个文件还是文件夹，都能压缩成指定的名称的ZIP文件 
     * 存在bug，会覆盖掉之前的文件
     * 
     * @param sourceFilePath 压缩后的文件路径，绝对路径
     * @param zipFilePath 压缩文件路径，绝对路径
     * @param fName 压缩文件名称
     */
    public static void docCompress(String sourceFilePath, String zipFilePath, String fName) {
	StopWatch watch = new StopWatch();
	watch.start("document compress <<<<<<");
	File zipFile = new File(zipFilePath);
	ZipOutputStream zos = null;
	BufferedOutputStream bos = null;
	try {
	    String fileName = zipFilePath + File.separator + fName + XBRL_ZIP_SUFFIX;
	    zos = new ZipOutputStream(new FileOutputStream(fileName));
	    bos = new BufferedOutputStream(zos);
	    compressToZip(zos, zipFile, fName, bos);
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
	if (file.isFile()) {
	    compressFile(zos, file, base, bos);
	} else {
	    compressDirectory(zos, file, base, bos);
	}
    }
    
    /**
     * 压缩目录
     * @param zos
     * @param file
     * @param base
     * @param bos
     */
    private static void compressDirectory(ZipOutputStream zos, File file, String base, BufferedOutputStream bos) {
	File[] flieList = file.listFiles();
	    if (flieList.length == 0) {
		// 创建zip压缩进入点base
		try {
		    zos.putNextEntry(new ZipEntry(base + File.separator));
		} catch (IOException e) {
		    log.error("写入压缩输出流失败：_" + e.getMessage(), e);
		    // throw new BusinessException("");TODO 抛出业务异常
		}
		log.debug(base + File.separator);
	    }
	    for (int i = 0; i < flieList.length; i++) {
		compressToZip(zos, flieList[i], flieList[i].getPath(), bos); // 递归遍历子文件夹
	    }
	    log.debug("第" + layer + "次递归");
	    layer++;
    }

    /**
     * 压缩单一文件
     * @param zos
     * @param file
     * @param base
     * @param bos
     */
    private static void compressFile(ZipOutputStream zos, File file, String base, BufferedOutputStream bos) {
	FileInputStream fis = null;
	BufferedInputStream bis = null;
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
	    log.error("压缩文件出现异常：_" + e.getMessage(), e);
	} finally {
	    /**
	     * 关闭资源
	     */
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
    }
}
