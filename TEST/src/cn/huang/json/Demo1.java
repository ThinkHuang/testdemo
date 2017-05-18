package cn.huang.json;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Demo1 {
	public static void main(String[] args) {
		/*
		 * 将JSONArray当做List来使用
		 */
		JSONArray jsonArr = new JSONArray();
		jsonArr.add("liSi");
		jsonArr.add(23);
		jsonArr.add("female");
		System.out.println(jsonArr);
		
		/*
		 * 将JSONObject当做Map来使用，其中我们很常用的一个方法就是fromObject(Map)，讲一个Map传进去，我们将得到一个JSON串
		 */
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("name", "zhangSan");
		jsonObj.put("age", 18);
		jsonObj.put("sex", "male");
		
		System.out.println(jsonObj);
	}
}
