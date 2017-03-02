package cn.huang.other;

public class TestDemo {
	private static int NUMBER = 8;
	public static void main(String[] args) {
		fun1();
	}
	
	private static void fun1() {
		int[] selectIndex = new int[]{1,3,5,7,9,11,13,15};
		for(int i = 0; i < NUMBER; i++){
			for(int j = 0; j < NUMBER; j++){
				if(selectIndex[i] + selectIndex[j] >= 15){
					for(int k = 0; k < NUMBER; k++){
						if(30 == selectIndex[i]+selectIndex[j]+selectIndex[k]){
							System.out.println("i:" + "j:" + j + "k:" +k);
						}
						System.out.println("X:" + selectIndex[i] + " Y:" + selectIndex[j] + " Z:" + selectIndex[k] +
								" Result:" + (selectIndex[i]+selectIndex[j]+selectIndex[k]));
					}
				}
			}
		}
		System.out.println("There is no result to answer the question!");
	}
}
