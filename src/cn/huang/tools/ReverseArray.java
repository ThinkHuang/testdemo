package tools;

import java.util.Arrays;

public class ReverseArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int arr[] = new int[]{
				(int) (Math.random()*1000),
				(int) (Math.random()*1000),
				(int) (Math.random()*1000),
				(int) (Math.random()*1000),
				(int) (Math.random()*1000)
		};
		//System.out.println(arr);
		System.out.println(Arrays.toString(arr));
		SwapArray(arr);
		System.out.println(Arrays.toString(arr));
	}
	/*
	 * 说明：下面这个方法首尾互换不用判断数组的长度是单数还是双数，因为，/2操作很好滴解决了这个问题。
	 */
	private static void SwapArray(int[] arr) {
		int len = arr.length;
		int temp;
		for(int i = 0; i < len / 2; i++){
				temp = arr[i];
				arr[i] = arr[len - i - 1];
				arr[len - i - 1] = temp;
		}
	}
}
