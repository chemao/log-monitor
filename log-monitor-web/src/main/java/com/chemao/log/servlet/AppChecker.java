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

import com.chemao.log.monitor.schedule.MonitorTaskGenerator;

/**
 * 配置文件修改通知
 * @author xuhf
 * @since 2015年9月2日 下午6:54:06
 * @version V1.0 
 */
public class AppChecker extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource
	MonitorTaskGenerator monitorTaskGenerator;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (monitorTaskGenerator.getThreadPoolExecutor() != null && !monitorTaskGenerator.getThreadPoolExecutor().isShutdown()) {
			resp.getWriter().write("TRUE");
		} else {
			resp.sendError(401);
		}
	}
}
