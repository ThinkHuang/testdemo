package leetcode;

import org.junit.Test;

/**
 * Reverse digits of an integer.
 * Example1: x = 123, return 321
 * Example2: x = -123, return -321 
 * If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.
 * The input is assumed to be a 32-bit signed integer. Your function should return 0 when the reversed integer overflows. 
 * @author dell
 *
 */
public class ReverseDigits {
	
	@Test
	public void test(){
		int num = 0;
		System.out.println(reverse(num));
	}
	
	public int reverse(int x) {
		boolean flag = false;
		Integer classic = x;
		String temp = classic.toString();
		if(temp.startsWith("-")){
			temp = temp.substring(1);
			flag = true;
		}
		String result = reverseStr(temp);
		if(flag){
			result = "-" + result;
		}
		int y;
		try{
			y = new Integer(result);
		}catch(NumberFormatException e){
			y = 0;
		}
		return y;
    }

	private String reverseStr(String temp) {
		byte[] bytes = temp.getBytes();
		byte middle;
		int firstIndex = 0;
		int lastIndex = bytes.length - 1;
		while(firstIndex <= lastIndex){
			middle = bytes[firstIndex];
			bytes[firstIndex] = bytes[lastIndex];
			bytes[lastIndex] = middle;
			firstIndex++;
			lastIndex--;
		}
		
		return new String(bytes);
	}
}
