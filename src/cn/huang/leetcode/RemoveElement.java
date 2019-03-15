/**
 * 
 */
package com.huang.leetcode;

import org.junit.Test;

/**
 * @author dell
 * Given an array and a value, remove all instances of that value in place and return the new length.
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 * Example:
 * Given input array nums = [3,2,2,3], val = 3
 * Your function should return length = 2, with the first two elements of nums being 2.
 */
public class RemoveElement {
	
	@Test
	public void test(){
		int[] nums = {3,2,5,5,2,3};
		int val = 5;
		System.out.println(removeElement(nums,val));
	}
	
	public int removeElement(int[] nums, int val) {
		int j = 0;
		for (int i = 0; i < nums.length; i++) {
			if(val != nums[i])
				nums[j++] = nums[i];
		}
		for (int i = 0; i < nums.length; i++) {
			System.out.println(nums[i]);
		}
		return ++j;
    }
}
