/**
 * Copyright 2015 chemao.com, Inc. All rights reserved.
 */
package com.chemao.log.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chemao.log.monitor.config.LogMonitorConfig;

public class ConfigModifyNotify extends BaseServlet {

	private static final long serialVersionUID = 1L;

	private static Logger logger = LoggerFactory.getLogger(ConfigModifyNotify.class);
	
	private LogMonitorConfig logMonitorConfig;
	
	public LogMonitorConfig getLogMonitorConfig() {
		if (logMonitorConfig == null)
			logMonitorConfig = (LogMonitorConfig) getBean("logMonitorConfig");
		return logMonitorConfig;
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			getLogMonitorConfig().refreshLogMonitorConfigs();
			resp.getWriter().write("SUCCESS");
		} catch (Throwable throwable) {
			logger.error("REFRESH_LOG_MONITOR_CONFIG_ERROR", throwable);
			resp.getWriter().write("ERROR");
		}
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
