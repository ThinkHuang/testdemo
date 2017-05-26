package com.huang.demo;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class DateDemo {
	
	@Test
	public void test() throws ParseException{
		String remainDate = "2017-03-21 23:59:59";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		Date d2 = df.parse(remainDate);
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(d2);
		
		System.out.println(cal2.compareTo(cal1));
		System.out.println((d2.getTime() - date.getTime())/(1000 * 60 * 60 * 24));
	}
	
	@Test
	public void createDir(){
		DateFormat df = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		Date date = new Date();
		
		StringBuilder path = new StringBuilder("c:\\test\\");
		String datePath = df.format(date);
		path.append(datePath.substring(0, 8)).append("\\");
		
		System.out.println(datePath);
		
		String timePath = datePath.substring(9).replace(":", "");
		
		String content = "mail";
		
		path.append(content).append("_").append(timePath);
		
		
		File file = new File(path.toString());
		if(!file.exists()){
			file.mkdirs();
			System.out.println("创建目录" + path.toString() + "成功！");
		}
	}
	
	@Test
	public void comprareTime(){
		Calendar c = Calendar.getInstance();
		System.out.println(c.get(Calendar.HOUR_OF_DAY));
		
		
	}
	
	private Date getLastBizDate(Date date) throws ParseException{
		/**
		 * 从假期表中取小于发送日的前15条记录
		 * 不想重复查询数据库，又想通过递归来执行校验
		 */
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String sql = "select * from (select fdate from HSV_CSHOLIDAY where fdate < ?  order by fdate desc) T where rownum < 15";
//		List<Map<String,Object>> list = this.findForJdbc(sql, date);//该语句的作用是查出假期列表
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Calendar cal = Calendar.getInstance();
		if(list.size() > 0){
			for (Map<String,Object> map : list) {
				Date fdate = (Date) map.get("fdate");
				if(fdate.compareTo(date) != 0){
					break;
				}else{
					cal.setTime(date);
					cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 1);
					date = df.parse(cal.getTime().toString());
					continue;
				}
			}
		}
		return date;
	}
	
}	
