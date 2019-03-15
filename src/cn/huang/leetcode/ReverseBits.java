/**
 * 
 */
package leetcode;

import org.junit.Test;

/**
 * Reverse bits of a given 32 bits unsigned integer.
 * For example
 * given input 43261596 (represented in binary as 00000010100101000001111010011100), 
 * return 964176192 (represented in binary as 00111001011110000010100101000000).
 * @author dell
 *
 */
public class ReverseBits {
	
	@Test
	public void test(){
		int n = 43261596;
		reverseBits(n);
	}
	
	/**
	 * Integer的自带方法
	 * @param n
	 * @return
	 */
	public int reverseBits(int n) {
		return reverse(n);
    }
	
	public static int reverse(int i) {
		i = (i & 0x55555555) << 1 | (i >>> 1) & 0x55555555;
		i = (i & 0x33333333) << 2 | (i >>> 2) & 0x33333333;
		i = (i & 0x0f0f0f0f) << 4 | (i >>> 4) & 0x0f0f0f0f;
		i = (i << 24) | ((i & 0xff00) << 8) |
		    ((i >>> 8) & 0xff00) | (i >>> 24);
	return i;
    }
}
