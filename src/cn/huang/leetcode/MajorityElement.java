/**
 * 
 */
package com.huang.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;


public class MajorityElement {
	
	@Test
	public void test(){
		int[] nums = {12,15,23,12,35,96,56,87,12,12,12};
		removeDuplicateNums(nums);
	}
	
	/**
	 * 难点在于只能使用一层循环
	 * @param nums
	 * @return
	 */
	public int majorityElement(int[] nums) {
		Map<String,Integer> map = new HashMap<String,Integer>();
		for (int i = 0; i < nums.length; i++) {
			String akey = String.valueOf(nums[i]);
			if(map.containsKey(akey)){
				int count = map.get(akey);
				map.put(akey, ++count);
			}else{
				map.put(akey, 1);
			}
		}
		
		Iterator<String> it = map.keySet().iterator();
		int tmp = 0;
		String value = "";
		while(it.hasNext()){
			String key = it.next();
			int count = map.get(key);
			if(tmp < count){
				tmp = count;
				value = key;
			}
		}
		return Integer.valueOf(value);
    }
	
	/**
	 * 去除数组中重复元素
	 * @param nums
	 * @return
	 */
	public Object[] removeDuplicateNums(int[] nums){
		/**
		 * 难点在于怎么记录已经遍历过的元素
		 */
		Map<String,Integer> map = new HashMap<String,Integer>();
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < nums.length; i++) {
			String akey = String.valueOf(nums[i]);
			if(map.containsKey(akey)){
				int count = map.get(akey);
				map.put(akey, ++count);
			}else{
				map.put(akey, 1);
				list.add(nums[i]);
			}
		}
		return list.toArray();
	}
}
