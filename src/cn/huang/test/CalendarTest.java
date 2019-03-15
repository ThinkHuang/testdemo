package test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarTest {
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date smdate=sdf.parse("2014-09-08");
		//Date bdate=sdf.parse("2013-09-15"); 
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		cal.add(Calendar.DATE, -1);
		System.out.println(new Timestamp(cal.getTime().getTime()));
//		long time1 = cal.getTimeInMillis();
//		cal.setTime(bdate);
//		long time2 = cal.getTimeInMillis();
//		long between_days=(time2-time1)/(1000*3600*24);
//		System.out.println(between_days);
	}
}
