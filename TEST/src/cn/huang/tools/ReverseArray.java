package cn.huang.tools;

import java.util.Arrays;

public class ReverseArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
	 * ˵�����������������β���������ж�����ĳ����ǵ�������˫������Ϊ��/2�����ܺõν����������⡣
	 */
	private static void SwapArray(int[] arr) {
		// TODO Auto-generated method stub
		int len = arr.length;
		int temp;
		for(int i = 0; i < len / 2; i++){
				temp = arr[i];
				arr[i] = arr[len - i - 1];
				arr[len - i - 1] = temp;
		}
	}
}
