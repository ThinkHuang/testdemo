package leetcode;

import org.junit.Test;

/**
 * 判断一个整数是不是回文数
 * 注意，不能使用额外的空间
 * 此题的一个解法为，一个数的最大后端与一个数的最大前端相等，那么这个数就是回文数
 * 例如：123321
 * 最大前端为:12332
 * 最大后端为:23321
 * @author dell
 *
 */
public class Palindrome {

	@Test
	public void test(){
		int num = 1001;
		System.out.println(isPalindrome(num));
	}
	
	 public boolean isPalindrome(int x) {
		if(x < 0) return false;
		if(x == 0) return true;
		int y = 0;
		int z = x;
		while(z >= 10){
			y *= 10;
			y += (z % 10);
			System.out.println(y);
			z = z / 10;
		}
		return y == x / 10;
		/**
		 * 下面的方式由于使用的额外的空间，不经采用
		 */
//		String temp = String.valueOf(x);
//		String source = temp;
//		return new StringBuffer(temp).reverse().toString().equalsIgnoreCase(source);
	 }
}
