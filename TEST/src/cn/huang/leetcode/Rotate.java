/**
 * 
 */
package com.huang.leetcode;

import org.junit.Test;

/**
 * @author dell
 *
 */
public class Rotate {
	
	@Test
	public void test(){
		int[] nums = {1,2,3,4,5,6,7};
		rotate(nums, 4);
	}
	
	 public void rotate(int[] nums, int k) {
	        if(k > nums.length || k < 0)
	        	throw new RuntimeException("illegal step");
	 }
}
