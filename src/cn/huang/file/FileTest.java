package cn.huang.file;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileTest {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileManager fm1 = new FileManager("f:\\a.txt",new char[]{'\n'});
		FileManager fm2 = new FileManager("f:\\b.txt",new char[]{'\n',' '});
		FileWriter fw = new FileWriter("f:\\c.txt");
		String aWord = null;
		String bWord = null;
		while((aWord = fm1.nextWord())!= null){
			fw.write(aWord + "\n");
			bWord = fm2.nextWord();
			if((bWord = fm2.nextWord())!= null){
				fw.write(bWord);
			}
		}
		while((bWord = fm2.nextWord())!= null){
			fw.write(bWord);
		}
	}

}
class FileManager{
	String[] words = null;//用于存储分割文本后的单词，
	int pos = 0;//数组下标标示
	
	public FileManager(String filename,char[] seperator) throws IOException{
		File f = new File(filename);
		FileReader fis = new FileReader(f);
		char[] buf = new char[1024];
		fis.read(buf);
		String result = new String(buf, 0, buf.length);
		String regex = null;
		if(seperator.length > 1){
			regex = " " + seperator[0] + "|" + seperator[1];
		}else{
			regex = " " + seperator[0];
		}
		words = result.split(regex);
	}
	public String nextWord(){
		if(pos == words.length)
			return null;
		System.out.println(words[pos]);
		return words[pos++];
	}
	
}
