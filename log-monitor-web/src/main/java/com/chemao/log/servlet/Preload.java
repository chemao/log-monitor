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

import com.chemao.log.monitor.schedule.MonitorTaskGenerator;

/**
 * 配置文件修改通知
 * @author xuhf
 * @since 2015年9月2日 下午6:54:06
 * @version V1.0 
 */
public class Preload extends BaseServlet {
	private static Logger logger = LoggerFactory.getLogger(Preload.class);
	private static final long serialVersionUID = 1L;
	
	private static MonitorTaskGenerator monitorTaskGenerator;

	public MonitorTaskGenerator getMonitorTaskGenerator() {
		if (monitorTaskGenerator == null)
			monitorTaskGenerator = (MonitorTaskGenerator) getBean("monitorTaskGenerator");
		return monitorTaskGenerator;
	}
	
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	try {
			MonitorTaskGenerator monitorTaskGenerator = getMonitorTaskGenerator();
			if (monitorTaskGenerator.getThreadPoolExecutor() != null && !monitorTaskGenerator.getThreadPoolExecutor().isShutdown()) {
				resp.getWriter().write("TRUE");
			} else {
				resp.sendError(500);
			}
		} catch (Throwable throwable) {
			logger.error("PRELOAD_CHECK_ERROR:", throwable);
			resp.sendError(500);
		}
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
