package leetcode;

import org.junit.Test;


public class LongestCommonPrefix {

	@Test
	public void test(){
		String[] strs = {
				"aaaaabbbb",
				"aaccccccsss",
				"aaaaades",
				"aabbbb",
				"abbbss"
		};
		System.out.println(longestCommonPrefix(strs));
	}
	
	/**
	 * 既然是前缀，那么最高位元素必定相同，否则就返回空
	 * @param strs
	 * @return
	 */
	public String longestCommonPrefix(String[] strs) {
        /**
         * 参考Arrays.sort(strs);
         * 在给数组排序的时候就确定字符大小
         */
		if(strs.length == 0) return "";
		int index = 0;
		String shortStr = strs[0];
		for (int i = 1; i < strs.length; i++) {
			if(strs[i].length() < shortStr.length()){
				shortStr = strs[i];//得到最小的字符串
				index = i;//得到最小字符串的下标
			}
		}
		StringBuilder sb = new StringBuilder("");
		boolean flag = false;
		first:for (int i = 0; i < shortStr.length(); i++) {
			for (int j = 0; j < strs.length; j++) {
				if(j == index){
					continue;
				}
				flag = compareWithIndex(strs[j],shortStr.charAt(i),i);
				if(!flag)
					break first;
			}
				sb.append(shortStr.charAt(i));
		}
		
		return sb.toString();
    }
	
	public boolean compareWithIndex(String str, char targetChar,int index){
		if(str.charAt(index) == targetChar)
			return true;
		return false;
	}
}
