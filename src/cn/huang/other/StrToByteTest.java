package other;

import java.io.IOException;

public class StrToByteTest {
	public static void main(String[] args) throws IOException {
		String str="滚犊子";
		//要想要拿到“滚犊子”的字节码，必须将该字符串转换成字节才行。
		byte[] buf = new byte[1024];
		buf = str.getBytes();
		System.out.print("【");
		for(int i = 0; i < buf.length; i++){
			if(i == buf.length - 1){
				System.out.print(buf[i]);
			}else{
			System.out.print(buf[i]+" ");
		}
		}
		System.out.print("】");
	}
}
