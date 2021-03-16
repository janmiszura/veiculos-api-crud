package com.dryve.vehicles;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class SpringApplicationContext implements ApplicationContextAware {

	private static ApplicationContext CONTEXT;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		
		CONTEXT = applicationContext;
		
	}
	
	public static Object getBean(String beanName) {
		return CONTEXT.getBean(beanName);
	}
	
	public static <T> T getBean(Class<T> clazz) {
		return CONTEXT.getBean(clazz);
	}
	
	public static Boolean isTestEnv() {
		Environment env = SpringApplicationContext.getBean(Environment.class);
		return "test".equals(env.getProperty("env"));
	}
	
	public static Boolean isDevelopEnv() {
		Environment env = SpringApplicationContext.getBean(Environment.class);
		return "develop".equals(env.getProperty("env"));
	}
	
	public static Boolean isProductionEnv() {
		Environment env = SpringApplicationContext.getBean(Environment.class);
		return "production".equals(env.getProperty("env"));
	}
}