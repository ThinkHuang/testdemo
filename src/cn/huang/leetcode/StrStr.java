/**
 * 
 */
package leetcode;

import org.junit.Test;

/**
 * @author dell
 *
 */
public class StrStr {
	
//	@Test
//	public void test(){
//		String haystack = "sadedddddabcdcdesdsaaae";
//		String needle = "abcabd";
//		System.out.println(strStr(haystack, needle));
//	}
	
	/**
	 * 下面这种方法采用的是String自带的方法进行的比较
	 * String自带的方法就是正常的思维，找到第一个字符后，依次比较后面的字符
	 * @param haystack
	 * @param needle
	 * @return
	 */
//	public int strStr(String haystack, String needle) {
//		if(haystack.contains(needle))
//			return haystack.indexOf(needle);
//		
//		return -1;
//    }
	
	/**
	 * 接下来采用KMP算法进行解读
	 * @param haystack
	 * @param needle
	 * @return
	 */
//	public String strStr(String haystack, String needle) {
//		//KMP algorithms
//		if(needle.equals("")) return haystack;
//		if(haystack.equals("")) return null;
//		char[] arr = needle.toCharArray();
//		int[] next = makeNext(arr);
//
//		for(int i = 0, j = 0, end = haystack.length(); i < end;){
//			if(j == -1 || haystack.charAt(i) == arr[j]){
//				j++;
//				i++;
//				if(j == arr.length) return haystack.substring(i - arr.length);
//			}
//			if(i < end && haystack.charAt(i) != arr[j]) j = next[j];
//		}
//	    return null;
//	}
//
	private int[] makeNext(char[] arr){
		int len = arr.length;
		int[] next = new int[len];

		next[0] = -1;
		for(int i = 0, j = -1; i + 1 < len;){
			if(j == -1 || arr[i] == arr[j]){//左边的j == -1是为了排除第一种情况，右边的arr[i] == arr[j]适用于后面的情况
				next[i+1] = j+1;
				if(arr[i+1] == arr[j+1]) next[i+1] = next[j+1];//让相邻的两个相同字符的权重相等
				i++;
				j++;
			}
			if(arr[i] != arr[j]) j = next[j];
		}
		for (int i = 0; i < next.length; i++) {
			System.out.println(next[i]);
		}
		return next;
	}
	
	
	
	/**
	 * 改进“部分匹配表”
	 * @param arr
	 * @return
	 */
//	private int[] genNext(char[] arr){//ABCDABEABCD
//		int len = arr.length;
//		int[] next = new int[len];
//		next[0] = 0;
//		int  j = 0;
//		for(int i = 0; i < len - 1; i++){
//			//让arr[j]记录首位相同的字符，而arr[i]记录整个列表
//			if(arr[i] != arr[i+1]) {
//				next[i+1] = next[j];//第一次匹配成功后,再次遇到不匹配的值后,需要重新开始匹配,此时i不等于0,j不等于0,arr[i] != arr[i+1]
//				if(arr[i] != arr[j])j=next[j];
//			}
//			if(arr[i] == arr[i+1] && i > 0) {next[i] = 1; continue;}//解决相邻数据相同的问题
//			if((i > 0 && arr[i] == arr[j])) {
//				if(arr[i] == arr[i+1] && j == 0){
//					next[i+1] = next[i];
//				}else{
//					j++;
//					next[i] = j;
//				}
//			}
//		}
//		if(arr[len -1] == arr[j]) next[len - 1] = ++j;//解决最后下标没有赋值的问题
//		for (int i = 0; i < next.length; i++) {
//			System.out.println(next[i]);
//		}
//		return next;
//	}
	
	@Test
	public void test1(){
		String str1 = "abcdeabcdeabcdf";
		String str2 = "cdf";
		search(str1, str2, getNext(str2));
	}

	
	
    public int[] getNext(String b)  
    {  
        int len=b.length();  
        int j=0;  
              
        int next[]=new int[len+1];//next表示长度为i的字符串前缀和后缀的最长公共部分，从1开始  
        next[0]=next[1]=0;  
              
        for(int i=1;i<len;i++)//i表示字符串的下标，从0开始  
        {//j在每次循环开始都表示next[i]的值，同时也表示需要比较的下一个位置  
            while(j>0&&b.charAt(i)!=b.charAt(j))j=next[j];  
            if(b.charAt(i)==b.charAt(j))j++;  
            next[i+1]=j;  
        }  
        for (int i = 0; i < next.length; i++) {
			System.out.println(next[i]);
		}      
        return next;  
    }  
    
    
    public void search(String original, String find, int next[]) {  
        int j = 0;  
        for (int i = 0; i < original.length(); i++) {  
            while (j > 0 && original.charAt(i) != find.charAt(j))  
                j = next[j];  
            if (original.charAt(i) == find.charAt(j))  
                j++;  
            if (j == find.length()) {  
                System.out.println("find at position " + (i - j));  
                System.out.println(original.subSequence(i - j + 1, i + 1));  
                j = next[j];  
            }  
        }  
    }
}
