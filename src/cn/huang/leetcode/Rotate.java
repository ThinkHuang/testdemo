/**
 * 
 */
package leetcode;

import org.junit.Test;

/**
 * @author dell
 *
 */
public class Rotate {
	
	@Test
	public void test(){
		int[] nums = {1,2,3,4,5,6,7};
		rotate1(nums, 4);
	}
	
	/**
	 * 采用新数组
	 * @param nums
	 * @param k
	 */
	 public void rotate(int[] nums, int k) {
	        if(k > nums.length || k < 0)
	        	throw new RuntimeException("illegal step");
	        
	        int[] newNum = new int[nums.length];
	        int index = 0;
	        for (int i = nums.length - 1; i >= k; i--) {
				newNum[index++] = nums[i];
			}
	        for (int i = 0; i < k; i++) {
				newNum[index++] = nums[i];
			}
	        for (int i = 0; i < newNum.length; i++) {
				System.out.println("index " + i + ": " +newNum[i]);
			}
	 }
	 
	 public void rotate1(int[] nums, int k) {
		 
	 }
}
