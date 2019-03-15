/**
 * 
 */
package leetcode;

import org.junit.Test;

/**
 * @author dell
 *
 */
public class TwoSum {
	
	@Test
	public void test(){
		int[] numbers = {-1, 0};
		int target = -1;
		twoSum(numbers, target);
	}
	
	/**
	 * first style
	 * @param numbers
	 * @param target
	 * @return
	 */
	 public int[] twoSum(int[] numbers, int target) {
		    int[] result = new int[2];
	        int length = numbers.length;
//	        int middle = length / 2;
//	        if((length - middle < 2) || target > numbers[length-1]){
//	        	throw new IllegalArgumentException(" No two sum solution");
//	        }
	        first:for(int i = 0; i < length; i++){
	        	int temp = target - numbers[i];
	        	for (int j = i+1; j < length; j++) {
	        		if(target > 0 && numbers[j]==0) continue;
					if(i != j && temp == numbers[j]){
						result[0] = i+1;
						result[1] = j+1;
						break first;
					}
				}
	        }
//	        if(result[0] <= 0 || result[1] <= 0){
//	        	throw new IllegalArgumentException(" No two sum solution");
//	        }
	        System.out.println("result[0]:" + result[0] + " result[1]:" + result[1]);
	        return result;
     }
	 
	 /**
	  * second style
	  * @param numbers
	  * @param target
	  * @return
	  */
	 public int[] twoSum1(int[] numbers, int target) {
		 int[] result = new int[2];
	        int length = numbers.length;
	        int middle = length / 2;
	        if((length - middle < 2) || target > numbers[length-1]){
	        	throw new IllegalArgumentException(" No two sum solution");
	        }
	        int left = 0; int right = length - 1;
	        while(left < right){
	        	int v = numbers[left] + numbers[right];
	        	if(target == v){
	        		result[0] = left + 1;
	        		result[1] = right + 1;
	        		break;
	        	}else if(target > v){
	        		left++;
	        	}else{
	        		right--;
	        	}
	        }
	        System.out.println("result[0]:" + result[0] + " result[1]:" + result[1]);
	        return result;
	 }
	 
}
