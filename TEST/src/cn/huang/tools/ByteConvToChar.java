package cn.huang.tools;

public class ByteConvToChar {

	final static String[] dates = new String[]{
			"��","Ҽ","��","��","��","��","½","��","��","��"
	};
	final static String[] units = new String[]{
			"Ԫ","ʮ","��","ǧ","��","ʮ","��","ǧ","��","ʮ"
	};
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int money = 60002050;
		convert(money);
	}
	private static void convert(int money) {
		int num = 0;
		StringBuffer sb = new StringBuffer();
		while(money != 0){
			sb.insert(0, units[num++]);
			sb.insert(0, dates[money % 10]);
			money /= 10;
		}
		
		System.out.println(removeZero(sb)); 
		//System.out.println(sb.toString());
	}
	private static String removeZero(StringBuffer sb) {
		return sb.toString().replaceAll("���","��").replaceAll("����","��").replaceAll("��Ԫ","Ԫ ").replaceAll("��ʮ","��").replaceAll("��ǧ", "��").replaceAll("����", "��"); 
	}

}
