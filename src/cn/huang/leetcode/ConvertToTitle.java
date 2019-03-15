/**
 * 
 */
package leetcode;

import org.junit.Test;

/**
 * @author dell
 *
 */
public class ConvertToTitle {

	@Test
	public void test(){
		System.out.println(convertToTitle(353));
	}
	
	public String convertToTitle(int n) {
        final int EN_LENGTH = 26;
        StringBuilder sb = new StringBuilder();
        char[] baseChar = {' ','A','B','C','D','E','F','G','H','I',
        					'J','K','L','M','N','O','P','Q','R',
        					'S','T','U','V','W','X','Y','Z'
                           };
        if(n < EN_LENGTH){
            return String.valueOf(baseChar[n]);
        } else {
        	while(n / EN_LENGTH > 0){
        		int remaining = n % EN_LENGTH;
        		sb.append((char)('A' + remaining));
        		n = n / EN_LENGTH;
        	}
        }
        return sb.toString();
    }
	
	 public String convertToTitle1(int n) {
	        StringBuilder result = new StringBuilder();

	        while(n>0){
	            n--;
	            result.insert(0, (char)('A' + n % 26));
	            n /= 26;
	        }

	        return result.toString();
	    }
}
