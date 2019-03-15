package cn.huang.leetcode;

import org.junit.Test;

/**
 * description:
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * 
 * Example:
 * Given nums = [2, 7, 11, 15], target = 9,
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 * @author huangbing
 *
 */
public class TwoSumTest {
	@Test
	public void test(){
		int[] nums = {3, 2, 4};
		int target = 6;
		twoSum(nums,target);
	}
	 public int[] twoSum(int[] nums, int target) {
	        int[] result = new int[2];
	        int[] vacariousness = nums;
	        first:for(int i=0; i<nums.length; i++){
	        	int temp = target - nums[i];
	            //if(temp <= 0) continue;//排除掉大于或等于目标的数组元素,排除了负数的可能
	            for(int j=0; j<vacariousness.length; j++){
	                if(i != j && temp == vacariousness[j]){
	                    result[0] = j;
	                    result[1] = i;
	                    break first;
	                }
	            }
	        }
	        System.out.println("first:" + result[0] + " second:" + result[1]);
	        return result;
	    }
}
