package com.huang.demo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class DatepathTest {
	@Test
	public void getDatepath(){
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		String datepath = df.format(date);
		System.out.println("/upload/image/" + datepath);
//		return datepath;
	}
}
