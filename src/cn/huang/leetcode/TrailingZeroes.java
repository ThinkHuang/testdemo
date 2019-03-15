/**
 * 
 */
package leetcode;

import org.junit.Test;

/**
 * @author dell
 *
 */
public class TrailingZeroes {
	
	@Test
	public void test(){
		System.out.println(trailingZeroes(100));
	}
	
	public int trailingZeroes(int n) {
		int count = 0;
		while(n > 1){
			int temp = n / 5;
			count += temp;
			n = temp;
		}
		return count;
    }
}
