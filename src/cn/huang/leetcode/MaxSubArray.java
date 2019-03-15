/**
 * 
 */
package leetcode;

import org.junit.Test;

/**
 * @author dell
 *
 */
public class MaxSubArray {
	
	@Test
	public void test(){
		int[] nums = {-2,1,-3,4,-6,2,1,-5,-3,4};
		maxSubArray(nums);
	}
	
	/**
	 * 这个算法只能算出最大的子数组的相邻项的最大和，可能不能得到这个子数组
	 * @param nums
	 * @return
	 */
	public int maxSubArray(int[] nums) {
		int maxSoFar=nums[0], maxEndingHere=nums[0];
	    for (int i=1;i<nums.length;++i){
	    	maxEndingHere= Math.max(maxEndingHere+nums[i],nums[i]);
	    	maxSoFar=Math.max(maxSoFar, maxEndingHere);	
	    }
	    System.out.println(maxSoFar);
	    return maxSoFar;
    }
	
	/**
	 * second style
	 */
//	public int maxSubArray(int[] nums) {
//        int max = Integer.MIN_VALUE, sum = 0;
//    for (int i = 0; i < nums.length; i++) {
//        if (sum < 0) 
//            sum = nums[i];
//        else 
//            sum += nums[i];
//        if (sum > max)
//            max = sum;
//    }
//    return max;
//    }
}
