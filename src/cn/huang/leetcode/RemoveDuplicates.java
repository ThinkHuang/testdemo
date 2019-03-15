/**
 * 
 */
package leetcode;

import org.junit.Test;

/**
 * @author dell
 * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 * For example,
 * Given input array nums = [1,1,2],
 * Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
 * It doesn't matter what you leave beyond the new length. 
 */
public class RemoveDuplicates {
	
	/**
	 * 这里有一个巧妙的地方在于，如果是相等的元素的话，那么附近的几个元素都是相同的
	 */
	@Test
	public void test(){
		int[] nums = {1,2,5,5,5,12,32,45,65};
		System.out.println(removeDuplicates(nums));
	}
	
	
	/**
	 * 
	 * @param nums
	 * @return
	 */
	public int removeDuplicates(int[] nums) {
		int j = 0;
        for (int i = 0; i < nums.length; i++) 
    		if(nums[i] != nums[j])
    			nums[++j] = nums[i];
		return ++j;
    }
}
