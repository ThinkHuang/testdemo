/**
 * 
 */
package com.huang.leetcode;

import org.junit.Test;

/**
 * 这是加一需要考虑的特殊情况是结尾是9的情况
 * @author dell
 *
 */
public class PlusOne {
	
	@Test
	public void test(){
		int[] nums = {9,9,9,9};
		plusOne(nums);
	}
	
	 public int[] plusOne(int[] digits) {
	        int len = digits.length;
	        for(int i = len - 1; i >= 0; i--){
	            if(digits[i] < 9){
	                digits[i] += 1;
	                return digits;
	            }else{
	                digits[i] = 0;
	            }
	             
	        }
	        int[] result = new int[len + 1];
	        result[0] = 1;
	        for (int i = 0; i < result.length; i++) {
				System.out.println(result[i]);
			}
	        return result;
	    }
}
