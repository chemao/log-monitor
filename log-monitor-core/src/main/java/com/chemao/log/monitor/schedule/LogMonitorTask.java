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
	private Logger monitorLog = LoggerFactory.getLogger("monitorLog");
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
		Integer count = null;
		String processResult = null;
		long nowTimeStamp = System.currentTimeMillis();
		try {
			count = logQueryService.getKeywordCount(monitorConfigDO.getLogName(), monitorConfigDO.getKeyword(), startTime, endTime);
			if (count == null) {
				return;	// 当前时间段的日志未分析完成
			}
			monitorDataPushService.pushKeywordCount(monitorConfigDO.getUserId(), monitorConfigDO.getNamespace(), monitorConfigDO.getLogName(), monitorConfigDO.getKeyword(), count, endTime, null);
			processResult = "SUCCESS";
		} catch (Exception e) {
			processResult = "ERROR";
			logger.error(String.format("MONITOR_TASK_ERROR:logName=%s,keyword=%s,userId=%s,namespace=%s,start=%s,end=%s. msg=%s", monitorConfigDO.getLogName(), monitorConfigDO.getKeyword(), monitorConfigDO.getUserId(), monitorConfigDO.getNamespace(), startTime, endTime,e.getMessage()), e);
		} finally {
			recordMonitorLog(monitorConfigDO, startTime, endTime, count, processResult, System.currentTimeMillis() - nowTimeStamp);
		}
	}
	
	private void recordMonitorLog(MonitorConfigDO monitorConfigDO, Date startTime, Date endTime, Integer count, String result, long costTime) {
//		log format:logType(M),timestamp,logName,keyword,result,cost
		StringBuilder stringBuilder = new StringBuilder("M,"); 
		stringBuilder.append(System.currentTimeMillis()).append(",");
		stringBuilder.append(monitorConfigDO.getLogName()).append(",");
		stringBuilder.append(monitorConfigDO.getKeyword()).append(",");
		stringBuilder.append(startTime.getTime()).append(",");
		stringBuilder.append(endTime.getTime()).append(",");
		stringBuilder.append(count).append(",");
		stringBuilder.append(result).append(",");
		stringBuilder.append(costTime);
		monitorLog.info(stringBuilder.toString());
	}
}
