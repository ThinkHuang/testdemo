package cn.huang.json;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Demo1 {
	public static void main(String[] args) {
		/*
		 * ��JSONArray����List��ʹ��
		 */
		JSONArray jsonArr = new JSONArray();
		jsonArr.add("liSi");
		jsonArr.add(23);
		jsonArr.add("female");
		System.out.println(jsonArr);
		
		/*
		 * ��JSONObject����Map��ʹ�ã��������Ǻܳ��õ�һ����������fromObject(Map)����һ��Map����ȥ�����ǽ��õ�һ��JSON��
		 */
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("name", "zhangSan");
		jsonObj.put("age", 18);
		jsonObj.put("sex", "male");
		
		System.out.println(jsonObj);
	}
}
