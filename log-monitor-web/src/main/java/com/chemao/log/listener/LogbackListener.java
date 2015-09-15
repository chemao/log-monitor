/**
 * Copyright 2015 chemao.com, Inc. All rights reserved.
 */
package com.chemao.log.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;

/**
 * logback配置文件
 * @author xuhf
 * @since 2015年9月15日 下午5:18:47
 * @version V1.0 
 */
public class LogbackListener implements ServletContextListener {
	private static final String CONFIG_FILE = "logbackConfigLocation";
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		String config = sce.getServletContext().getInitParameter(CONFIG_FILE);
		String file = sce.getServletContext().getRealPath(config);
		try {
			LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
			loggerContext.reset();
			JoranConfigurator configurator = new JoranConfigurator();
			configurator.setContext(loggerContext);
			configurator.doConfigure(file);
			System.out.print("Logback configurations from " + file);
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
