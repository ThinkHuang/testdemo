package cn.huang.other;

public class quickSortTest {

	/**
	 * 快速排序的思想：给定一个数字，定义两个“指针”分别指向数组的左右两边。
	 * 定义一个中间量作为比较的标准，这个标准的值可能会产生变化（一般是数组中间的那个数）
	 * （1）在左边->中间，比较大于中间值的数记下下标x，
	 * （2）同时在右边->中间，比较小于中间值的数记下下标y，
	 * （3）在“左”不大于“右”的情况下，互换下标x和y的值，循环比较。---->这是第一轮比较，直到数组遍历完成
	 * 数组遍历完成后的结果就是左边的都小于中间值，右边的都大于中间值。
	 * （4）分别对左右两边的值进行快速排序，执行步骤和上面三部一样。
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] strVoid=new String[]{"11","66","22","0","44","55","22","0","32"}; 
		quickSort(strVoid,0,strVoid.length-1); 
		for(int i=0;i<strVoid.length;i++){ 
			System.out.println(strVoid[i]+" ");
		}

	}
	
	public static void quickSort(String[] strDate,int left,int right){
		int i,j;
		String tempStr,middle;
		i = left;
		j = right;
		do {
			middle = strDate[(i+j)/2];
			while((middle.compareTo(strDate[i])>0) && i < right){
				i++;
			}
			while((middle.compareTo(strDate[j])<0) && j > left){
				j--;
			}
			if(i<=j){
				tempStr = strDate[i];
				strDate[i] = strDate[j];
				strDate[j] = tempStr;
				i++;
				j--;
			}
		} while (i<j);//注意这里的条件一定是“左”小于“右”，不能等于，不然结果可能不对，特别是数组元素个数存在单数和双数的时候
		if(i<right){
			quickSort(strDate,i,right);
		}
		if(j>left){
			quickSort(strDate,left,j);
		}
	}
	
}
