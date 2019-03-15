/**
 * 
 */
package com.huang.leetcode;

import org.junit.Test;

/**
 * @author dell
 *
 */
public class AddBinary {
	
	@Test
	public void test(){
		String a = "101";
		String b = "111111";
		System.out.println(addBinary(a,b));
	}
	
	/**
	 * first style
	 * @param a
	 * @param b
	 * @return
	 */
//	public String addBinary(String a, String b) {
//        //二进制的运算法则
//        boolean flag = false;//用来标记进位标识
//        StringBuilder sb = new StringBuilder();
//        if(a.length() < b.length()){
//            String temp = "";
//            temp = b;
//            b = a;
//            a = temp;
//        }
//            int a_len = a.length() - 1;
//            int b_len = b.length() - 1;
//           while(b_len >= 0){
//            	if(flag){
//            		if((b.charAt(b_len) - '0' + a.charAt(a_len) - '0') > 1){
//            			sb.append("1");
//                        flag = true;
//            		}else if((b.charAt(b_len) - '0' + a.charAt(a_len) - '0') == 1){
//            			sb.append("0");
//            			flag = true;
//            		}else{
//            			sb.append("1");
//            			flag = false;
//            		}
//            	}else if((b.charAt(b_len) - '0' + a.charAt(a_len) - '0') > 1){
//                    sb.append("0");
//                    flag = true;
//                }else if((b.charAt(b_len) - '0' + a.charAt(a_len) - '0') == 1){
//                	sb.append("1");
//                	flag = false;
//                }else{
//                    sb.append("0");
//                    flag = false;
//                } 
//            	a_len--;
//            	b_len--;
//            }
//            for(int j = a.length() - b.length() - 1; j >= 0; j--){
//                if(flag){
//                   if(a.charAt(j) - '0' + 1 > 1){
//                      sb.append("0");
//                        flag = true;  
//                    } else{
//                        sb.append("1");
//                        flag = false;
//                    } 
//                }else{
//                    sb.append(a.charAt(j) - '0');
//                }    
//            } 
//            if(flag) sb.append("1");
//        return sb.reverse().toString();
//    }
	
	/**
	 * second style
	 * @param a
	 * @param b
	 * @return
	 */
	public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() -1, carry = 0;
        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (j >= 0) sum += b.charAt(j--) - '0';
            if (i >= 0) sum += a.charAt(i--) - '0';
            sb.append(sum % 2);
            carry = sum / 2;
        }
        if (carry != 0) sb.append(carry);
        return sb.reverse().toString();
    }
}
