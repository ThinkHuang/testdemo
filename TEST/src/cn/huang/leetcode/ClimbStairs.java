/**
 * 
 */
package com.huang.leetcode;

import org.junit.Test;

/**
 * You are climbing a stair case. It takes n steps to reach to the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * Note: Given n will be a positive integer. 
 * @author dell
 *
 */
public class ClimbStairs {
	
	@Test
	public void test(){
		int steps = 50;
		System.out.println(climbStairs(steps));
	}
	
	
	/**
	 * 穷举法，采用穷举法可以打印出所有排列
	 * 排列组合 ，动态规划，
	 * @param n
	 * @return
	 */
	public int climbStairs(int n) {
		if(n == 1) return 1;
		if(n == 2) return 2;
		int[] foo = new int[n];
		foo[0] = 1;
		foo[1] = 2;
		for(int i = 2; i < n; i++){
			foo[i] = foo[i-1] + foo[i-2];
		}       
		//采用递归的话只能计算30以内的数据，一旦数据过大，将会十分消耗内存
		if(n >= 3) return climbStairs(n-1) + climbStairs(n-2);
		return foo[foo.length - 1];
//		return 0;
    }
}
