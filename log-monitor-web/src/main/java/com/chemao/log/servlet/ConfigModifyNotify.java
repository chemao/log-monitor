/**
 * Copyright 2015 chemao.com, Inc. All rights reserved.
 */
package com.chemao.log.servlet;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chemao.log.monitor.config.LogMonitorConfig;

/**
 * 配置文件修改通知
 * @author xuhf
 * @since 2015年9月2日 下午6:54:06
 * @version V1.0 
 */
public class ConfigModifyNotify extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger logger = LoggerFactory.getLogger(ConfigModifyNotify.class);
	
	@Resource
	LogMonitorConfig logMonitorConfig;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			logMonitorConfig.refreshLogMonitorConfigs();
		} catch (Exception e) {
			logger.error("REFRESH_LOG_MONITOR_CONFIG_ERROR", e);
			throw new ServletException("refreshLogMonitorConfigs error", e);
		}
		resp.getWriter().write("TRUE");
	}

}
