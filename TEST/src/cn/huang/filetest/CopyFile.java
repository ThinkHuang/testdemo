/**
 * 
 */
package com.huang.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

/**
 * @author dell
 *
 */
public class CopyFile {

	@Test
	public void test() throws Exception{
		String src = "C:\\PrivateMicroService\\ResearchReport";
		String target1 = "W:\\workspace\\sc-kbs_branch\\src\\main\\webapp\\upload\\tserveInfomation";
		String target2 = "C:\\PrivateMicroService\\ResearchReport\\done";
		readfile(src, target1, target2);
	}
	
	public boolean copyFileToDir(String src, String target){
		boolean flag = false;
		InputStream inStream = null;
		FileOutputStream fs = null;
		try {
			//int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(src);
			if (oldfile.exists()) { // 文件存在时
				inStream = new FileInputStream(src); // 读入原文件
				fs = new FileOutputStream(target);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					//bytesum += byteread; // 字节数 文件大小
					fs.write(buffer, 0, byteread);
				}
				flag = true;
			}
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错");
			e.printStackTrace();
		}finally{
			if(inStream != null){
				try {
					inStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fs != null){
				try {
					fs.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}
	
	
	public boolean readfile(String filepath, String target1, String target2) throws Exception {
		try {
			File file = new File(filepath);
			if (!file.isDirectory()) {
				System.out.println("输入的不是路径，请重新输入路径");
			} else if (file.isDirectory()) {
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					File readfile = new File(filepath + "\\" + filelist[i]);
					if (!readfile.isDirectory()) {
						String sourcePath = readfile.getAbsolutePath();
						String targetPath1 = target1 + "\\" + readfile.getName();
						String targetPath2 = target2 + "\\" + readfile.getName();
						boolean flag1 = copyFileToDir(sourcePath, targetPath1);
						boolean flag2 = copyFileToDir(sourcePath, targetPath2);
						if(flag1 && flag2){
							deletefile(sourcePath);
						}
					} else if (readfile.isDirectory()) {
						// readfile(filepath + "\\" + filelist[i]);
						//跳过目录，只读取文件
						continue;
					}
				}
			}

		} catch (Exception e) {
			System.out.println("readfile()   Exception:" + e.getMessage());
		}
		return true;
	}

	public boolean deletefile(String delpath)
			throws FileNotFoundException, IOException {
		try {

			File file = new File(delpath);
			if (!file.isDirectory()) {
				file.delete();
//			} else if (file.isDirectory()) {
//				String[] filelist = file.list();
//				for (int i = 0; i < filelist.length; i++) {
//					File delfile = new File(delpath + "\\" + filelist[i]);
//					if (!delfile.isDirectory()) {
//						System.out.println("path=" + delfile.getPath());
//						System.out.println("absolutepath="
//								+ delfile.getAbsolutePath());
//						System.out.println("name=" + delfile.getName());
//						delfile.delete();
//						System.out.println("删除文件成功");
//					} else if (delfile.isDirectory()) {
//						deletefile(delpath + "\\" + filelist[i]);
//					}
//				}
//				file.delete();
			}
		} catch (Exception e) {
			System.out.println("deletefile()   Exception:" + e.getMessage());
		}
		return true;
	}
	
}
