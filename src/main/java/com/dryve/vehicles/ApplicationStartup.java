package com.dryve.vehicles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

	static Logger logger = LoggerFactory.getLogger(ApplicationStartup.class);
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		
		Environment env = SpringApplicationContext.getBean(Environment.class);
		
		logger.info("### INICIANDO ENV: "+env.getProperty("env")+" ###");
		
	}

	
	
}
