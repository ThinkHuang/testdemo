/**
 * 
 */
package util;

/**
 * <p>Project:test
 * <p>Package:com.huang
 * <p>Title:EncodeTypeUtil
 * <p>Description:
 * <p>Company:ShangCheng Tech
 * @author huangyejun
 * Create Date:2017-11-30 上午11:40:35
 * @version
 */
public abstract class EncodeTypeUtil {
	
	public static String getcode(String str) {
		String[] encodelist = { "GBK", "UTF-8", "GB2312", "ISO-8859-1", "gb 18030", "Big5", "UTF-16LE", "Shift_JIS",
				"EUC-JP", "ISO-2002-JP" };
		for (int i = 0; i < encodelist.length; i++) {
			try {
				String s = new String(str.getBytes(encodelist[i]), encodelist[i]);
				System.out.println(s);
				if (str.equals(s)) {
					return encodelist[i];
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				
			}
		}
		return "";
	}
	
	
	public static void main(String[] args) {
		String str = "å Œæ˜Œä¿ éç» çºª";
		System.out.println(getcode(str));
	}
	
}
