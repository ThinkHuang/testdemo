package tools;

public class ByteConvToChar {

	final static String[] dates = new String[]{
			"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"
	};
	final static String[] units = new String[]{
			"元","十","百","千","万","十","百","千","亿","十"
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
		return sb.toString().replaceAll("零百","零").replaceAll("零万","万").replaceAll("零元","元 ").replaceAll("零十","零").replaceAll("零千", "零").replaceAll("零零", "零"); 
	}

}
