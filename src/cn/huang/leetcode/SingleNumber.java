/**
 * 
 */
package leetcode;

import org.junit.Test;

/**
 * @author dell
 * Given an array of integers, every element appears twice except for one. Find that single one.
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory? 
 */
public class SingleNumber {
	
	@Test
	public void test(){
		int[] nums = {2,8,6,9,8,6,2};
		System.out.println(singleNumber(nums));
	}
	
	/**
	 * 使用xor的特性，相同的数据xor后得到的数值就是0
	 * @param nums
	 * @return
	 */
	public int singleNumber(int[] nums) {
		int result = 0;
        for(int i : nums) {
            result ^= i;
            System.out.println("result:" + result);
        }
        return result;
    }
}
