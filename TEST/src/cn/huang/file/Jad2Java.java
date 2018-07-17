package cn.huang.file;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Jad2Java {
	public static void main(String[] args) throws Exception {
		File src = new File("f:");
		if(!(src.exists() && src.isDirectory())){
			throw new Exception("File is not exist");
		}
		File[] files = src.listFiles(new FileFilter(){
			public boolean accept(File pathname) {
				// TODO Auto-generated method stub
				return pathname.getName().endsWith(".txt");
			}
		});
		File des = new File("e:");
		if(!des.isDirectory())des.mkdir();
		for(File file : files){
			FileInputStream fis = new FileInputStream(file);
			String desname = file.getName().replace(".txt", ".jad");
			FileOutputStream fos =new FileOutputStream(new File(des,desname));
			copy(fis,fos);
			fis.close();
			fos.close();
		}
	}

	private static void copy(InputStream fis, OutputStream fos) throws IOException {
		// TODO Auto-generated method stub
		int len = 0 ;
		byte[] buf = new byte[1024];
		while((len = fis.read(buf))!=-1){
			fos.write(buf, 0, len);
		}
	}
}

