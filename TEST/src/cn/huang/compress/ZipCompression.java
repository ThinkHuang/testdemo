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
 * @author huangyejun
 *
 */
public class ZipCompression {
	
	/*
     * 初始化临时目录
     */
    static {
	try {
	    XBRL_DECOMPRESS_TEMPORARY_DIR = System.getProperty("java.io.tmpdir");
	} catch (Exception e) {
	    log.error("获取操作系统临时目录失败：_" + e.getMessage(), e);
	}
    }
	
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
	    try {
		if (null != fis) {
		    fis.close();
		}
	    } catch (IOException e) {
		log.error("文件输入流关闭失败：_" + e.getMessage(), e);
	    }
	    try {
		if (null != fos) {
		    fos.close();
		}
	    } catch (IOException e) {
		log.error("文件输出流关闭失败：_" + e.getMessage(), e);
	    }
	}
	return flag;
    }

    /**
     * 压缩文件
     * 
     * @param srcfile 压缩源文件
     * @param targetFile 压缩目标文件
     * @param baseDir 压缩基点
     */
    public static void zipFiles(File srcfile, File targetFile, String baseDir) {
	// 定义监视器
	StopWatch watch = new StopWatch();
	watch.start("zip file");
	// 变量定义
	ZipOutputStream zos = null;
	try {
	    zos = new ZipOutputStream(new FileOutputStream(targetFile));
	    if (srcfile.isFile()) {
		zipFile(srcfile, zos, baseDir, false);
	    } else {
		File[] fileList = srcfile.listFiles();
		for (File file : fileList) {
		    compress(file, zos, baseDir);
		}
	    }
	    watch.stop();
	    log.info("文件压缩完毕  StopWatch:\n" + watch.prettyPrint());
	} catch (Exception e) {
	    log.error("文件压缩失败：_" + e.getMessage(), e);
	} finally {
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
     * 压缩文件夹里的文件
     * 
     * @param file 源文件
     * @param zos 压缩输出流
     * @param basedir 压缩基点，如果不存在默认以文件名称为压缩基点
     */
    private static void compress(File file, ZipOutputStream zos, String basedir) {
	if (file.isDirectory()) {
	    zipDirectory(file, zos, basedir);
	} else {
	    zipFile(file, zos, basedir, false);
	}
    }

    /**
     * 压缩单个文件
     * 
     * @param srcFile 源文件
     * @param zos 压缩输出流
     * @param baseDir 压缩基点
     * @param multiDirectory 是否需要多层级目录
     */
    public static void zipFile(File srcFile, ZipOutputStream zos, String baseDir, boolean multiDirectory) {
	// 判断文件是否存在
	if (!srcFile.exists())
	    return;

	byte[] buffer = new byte[BUFFER_SIZE];
	FileInputStream fis = null;
	String baseEntry = EMPTY_STR;
	try {
	    int length;
	    fis = new FileInputStream(srcFile);
	    // 是否启用多层目录
	    if (multiDirectory) {
		baseEntry = baseDir + File.separator + srcFile.getName();
	    } else {
		baseEntry = srcFile.getName();
	    }
	    zos.putNextEntry(new ZipEntry(baseEntry));
	    while ((length = fis.read(buffer)) > 0) {
		zos.write(buffer, 0, length);
	    }
	} catch (Exception e) {
	    log.error("压缩单个文件失败：_" + e.getMessage(), e);
	} finally {
	    try {
		if (fis != null) {
		    fis.close();
		}
	    } catch (IOException e) {
		log.error("文件输入流关闭失败：_" + e.getMessage(), e);
	    }
	}
    }

    /**
     * 压缩文件夹
     * 
     * @param directory 文件夹
     * @param zos
     * @param baseDir
     */
    public static void zipDirectory(File directory, ZipOutputStream zos, String baseDir) {
	// 判断文件是否存在
	if (!directory.exists())
	    return;

	File[] files = directory.listFiles();
	for (File file : files) {
	    /* 递归 */
	    compress(file, zos, baseDir + directory.getName() + File.separator);
	}
    }
}
