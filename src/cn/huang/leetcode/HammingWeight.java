/**
 * 
 */
package leetcode;

import org.junit.Test;

/**
 * @author huangyejun
 * 记住一点：一旦采用无符号二进制表示，每个数字的表示法只有一种且唯一
 */
public class HammingWeight {

	@Test
	public void test(){
		System.out.println(hammingWeight(Integer.MIN_VALUE));
	}
	
	 public int hammingWeight(int n) {
	     int num = 0;
	     Long m = new Long(n);
	     int count = 32;
		 while (--count >= 0){
			 if((m&0x1) == 1)
				 ++num; 
			 m = m >>> 1;
		 }
		 return num;
	 }
}
