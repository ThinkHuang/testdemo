/**
 * 
 */
package leetcode;

import org.junit.Test;

/**
 * 减一需要考虑的特殊情况是结尾是0的情况
 * @author dell
 *
 */
public class ReduceOne {
	
	@Test
	public void test(){
		int[] nums = {1,1,1,0};
		int[] result = reduceOne(nums);
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
	}
	
	 public int[] reduceOne(int[] digits) {
	        int len = digits.length;
	        for(int i = len - 1; i >= 0; i--){
	            if(digits[i] > 0){
	                digits[i] -= 1;
	                return digits;
	            }else{
	                digits[i] = 9;
	            }
	        }
	        int[] result = new int[len -1];
	        for (int i = 0; i < result.length; i++) {
				result[i] = 9;
			}
	        return result;
	    }
}
