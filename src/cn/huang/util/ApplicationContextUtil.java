package com.huang.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


public class ApplicationContextUtil implements ApplicationContextAware {

	private static ApplicationContext context;

	
	public void setApplicationContext(ApplicationContext context) {
		this.context = context;
	}

	public static ApplicationContext getContext() {
		return context;
	}
}
