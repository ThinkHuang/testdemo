/**
 * 
 */
package leetcode;

import org.junit.Test;

/**
 * @author dell
 * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2. 
 * The number of elements initialized in nums1 and nums2 are m and n respectively.
 */
public class Merge {
	
	@Test
	public void test(){
		int len1 = 1;
		int len2 = 1;
		int[] arr1 = new int[len1 + len2];
		int[] arr2 = new int[len2];
		initArray(arr1,len1,arr2,len2);
		merge(arr1, len1, arr2, len2);
	}
	
	/**
	 * @param arr1
	 * @param len1
	 * @param arr2
	 * @param len2
	 */
	private void initArray(int[] arr1, int len1, int[] arr2, int len2) {
		arr1[0] = 2;
//		arr1[1] = 3;
//		arr1[2] = 10;
//		arr1[3] = 12;
//		arr1[4] = 14;
		
		arr2[0] = 1;
//		arr2[1] = 16;
//		arr2[2] = 17;
//		arr2[3] = 18;
//		arr2[4] = 19;
		
	}

	public void merge(int[] nums1, int m, int[] nums2, int n) {
        if(n == 0) return;
        for (int i = 0; i < n; i++) {//将所有的元素都放入到数组1中，这样只需要对数组1进行排序
			nums1[m+i] = nums2[i];
		}
        if(m == 0 ){
        	for (int i = 0; i < nums1.length; i++) {
    			System.out.println(nums1[i]);
    		}
        	return;
        }
        int f_index = m-1;
        int s_index = n-1;
        int t_index = m+n-1;
        while(s_index > -1 && f_index > -1)
        	nums1[t_index--] = (nums1[f_index] >= nums2[s_index]) ? nums1[f_index--] : nums2[s_index--];
        while(s_index > -1) nums1[t_index--] = nums2[s_index--];
        for (int i = 0; i < nums1.length; i++) {
			System.out.println(nums1[i]);
		}
    }
}
