package cn.huang.other;

public class quickSortTest {

	/**
	 * ���������˼�룺����һ�����֣�����������ָ�롱�ֱ�ָ��������������ߡ�
	 * ����һ���м�����Ϊ�Ƚϵı�׼�������׼��ֵ���ܻ�����仯��һ���������м���Ǹ�����
	 * ��1�������->�м䣬�Ƚϴ����м�ֵ���������±�x��
	 * ��2��ͬʱ���ұ�->�м䣬�Ƚ�С���м�ֵ���������±�y��
	 * ��3���ڡ��󡱲����ڡ��ҡ�������£������±�x��y��ֵ��ѭ���Ƚϡ�---->���ǵ�һ�ֱȽϣ�ֱ������������
	 * ���������ɺ�Ľ��������ߵĶ�С���м�ֵ���ұߵĶ������м�ֵ��
	 * ��4���ֱ���������ߵ�ֵ���п�������ִ�в������������һ����
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
		} while (i<j);//ע�����������һ���ǡ���С�ڡ��ҡ������ܵ��ڣ���Ȼ������ܲ��ԣ��ر�������Ԫ�ظ������ڵ�����˫����ʱ��
		if(i<right){
			quickSort(strDate,i,right);
		}
		if(j>left){
			quickSort(strDate,left,j);
		}
	}
	
}
