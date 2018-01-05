/**
 * 
 */
package com.huang.demo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;

/**
 * <p>
 * Project:test
 * <p>
 * Package:com.huang.demo
 * <p>
 * Title:JSONArrayJSONObject
 * <p>
 * Description:
 * <p>
 * Company:ShangCheng Tech
 * 
 * @author huangyejun Create Date:2018-1-5 上午11:12:52
 * @version
 */
public class JSONArrayJSONObject {

	/**
	 * 解析JSONObject 2018-1-5 huangyejun
	 */
	@Test
	public void test1() {
		String jsonString = "{\"UserName\":\"ZHULI\",\"age\":\"30\",\"workIn\":\"ALI\",\"Array\":[\"ZHULI\",\"30\",\"ALI\"]}";
		// 将Json字符串转为java对象
		JSONObject obj = JSONObject.fromObject(jsonString);
		// 获取Object中的UserName
		if (obj.has("UserName")) {
			System.out.println("UserName:" + obj.getString("UserName"));
		}
		// 获取ArrayObject
		if (obj.has("Array")) {
			JSONArray transitListArray = obj.getJSONArray("Array");
			for (int i = 0; i < transitListArray.size(); i++) {
				System.out.print("Array:" + transitListArray.getString(i) + " ");
			}
		}
	}

	/**
	 * 解析JSONArray 2018-1-5 huangyejun
	 */
	@Test
	public void test() {
		String str = "[{name:'a',value:'aa'},{name:'b',value:'bb'},{name:'c',value:'cc'},{name:'d',value:'dd'}]"; // 一个未转化的字符串
		JSONArray json = JSONArray.fromObject(str); // 首先把字符串转成 JSONArray 对象
		if (json.size() > 0) {
			for (int i = 0; i < json.size(); i++) {
				JSONObject job = json.getJSONObject(i); // 遍历 jsonarray 数组，把每一个对象转成 json 对象
				System.out.println(job.get("name") + "="); // 得到 每个对象中的属性值
			}
		}
	}

	/**
	 * 复制JSON数据解析
	 * 
	 * 2018-1-5 huangyejun
	 */
	@Test
	public void test2() {
		String s = "[{'matched':'false'," +
					 "'orgin_str':{'certid':'916501000802265430','country':'CHINA'," +
					 	"'dates':'2013-11-12','depcode':'','depname':'','gender':'','id':1," +
					 	"'name':'乌鲁木齐轩盛股权投资管理有限公司','originCountry':'CHN','pflag':'0'}," +
					 "'matchedStatus':'0'}]";
		JSONArray arr = JSONArray.fromObject(s);
		Object obj = arr.get(0);
		JSONObject msJson = JSONObject.fromObject(obj);
		String matchedStatus = msJson.getString("matchedStatus");
		System.out.println(matchedStatus);
		String orginStr = msJson.getString("orgin_str");
		JSONObject orgObj = JSONObject.fromObject(orginStr);
		String certid = orgObj.getString("certid");
		System.out.println(certid);
		
	}
}
