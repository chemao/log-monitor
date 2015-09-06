/**
 * Copyright 2015 chemao.com, Inc. All rights reserved.
 */
package com.chemao.log.monitor.schedule;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chemao.log.monitor.alarm.MonitorDataPushService;
import com.chemao.log.monitor.collector.LogQueryService;
import com.chemao.log.monitor.domain.MonitorConfigDO;

/**
 * 执行具体的监控
 * @author xuhf
 * @since 2015年9月2日 上午10:10:17
 * @version V1.0 
 */
public class LogMonitorTask implements Runnable {
	private Logger logger = LoggerFactory.getLogger(LogMonitorTask.class);
	private LogQueryService logQueryService;
	private MonitorDataPushService monitorDataPushService;
	private MonitorConfigDO monitorConfigDO;
	private Date startTime;
	private Date endTime;
	
	public LogMonitorTask(LogQueryService logQueryService, MonitorDataPushService monitorDataPushService,
			MonitorConfigDO monitorConfigDO, Date startTime, Date endTime) {
		this.logQueryService = logQueryService;
		this.monitorDataPushService = monitorDataPushService;
		this.monitorConfigDO = monitorConfigDO;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	public void run() {
		Integer count;
		try {
			count = logQueryService.getKeywordCount(monitorConfigDO.getLogName(), monitorConfigDO.getKeyword(), startTime, endTime);
			if (count == null) {
				return;	// 当前时间段的日志未分析完成
			}
			monitorDataPushService.pushKeywordCount(monitorConfigDO.getUserId(), monitorConfigDO.getNamespace(), monitorConfigDO.getLogName(), monitorConfigDO.getKeyword(), count, endTime, null);
		} catch (Exception e) {
			logger.error(String.format("MONITOR_TASK_ERROR:logName=%s,keyword=%s,userId=%,namespace=%,start=%,end=%. msg=", monitorConfigDO.getLogName(), monitorConfigDO.getKeyword(), monitorConfigDO.getUserId(), monitorConfigDO.getNamespace(), startTime, endTime,e.getMessage()), e);
		}
	}
}