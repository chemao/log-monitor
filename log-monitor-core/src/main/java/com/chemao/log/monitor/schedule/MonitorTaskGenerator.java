/*
 * Copyright (c) 2002-2012 Alibaba Group Holding Limited.
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.chemao.log.monitor.schedule;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chemao.log.monitor.alarm.MonitorDataPushService;
import com.chemao.log.monitor.collector.LogQueryService;
import com.chemao.log.monitor.config.LogMonitorConfig;
import com.chemao.log.monitor.domain.MonitorConfigDO;

/**
 * 提交监控任务。
 * 由于每个监控点的当前时间是增加的，每一类任务目前只允许单线程执行。在提交任务时发现此类任务还在排队和执行钟，这跳过提交
 * @author xuhf
 * @since 2015年9月2日 上午10:10:17
 * @version V1.0 
 */
public class MonitorTaskGenerator {
	private Logger logger = LoggerFactory.getLogger(MonitorTaskGenerator.class);
	private final long submitTaskInterval = 60000L;	// 任务提交时间
	private final long monitorInterval = 60000L;	// 监控采样间隔
	private final long monitorDelayTime = 10000L;	// 监控采样延迟时间
	private Timer timer;
	private TimerTask timerTask;
	private ThreadPoolExecutor threadPoolExecutor;
	private LogMonitorConfig logMonitorConfig;
	private LogQueryService logQueryService;
	private MonitorDataPushService monitorDataPushService;
	private long endTime = System.currentTimeMillis();
	public ThreadPoolExecutor getThreadPoolExecutor() {
		return threadPoolExecutor;
	}

	public void setLogMonitorConfig(LogMonitorConfig logMonitorConfig) {
		this.logMonitorConfig = logMonitorConfig;
	}

	public void setLogQueryService(LogQueryService logQueryService) {
		this.logQueryService = logQueryService;
	}

	public void setMonitorDataPushService(MonitorDataPushService monitorDataPushService) {
		this.monitorDataPushService = monitorDataPushService;
	}

	public void init() {
		threadPoolExecutor = new ThreadPoolExecutor(40, 40, 300000L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), new ThreadPoolExecutor.CallerRunsPolicy());
		timerTask = new TimerTask() {
			@Override
			public void run() {
				submitTask();				
			}
		};
		timer = new Timer(true);
		timer.scheduleAtFixedRate(timerTask, 0, submitTaskInterval);
		logger.info("MonitorTaskGenerator init,submitTaskInterval="+submitTaskInterval+", monitorInterval="+monitorInterval+", monitorDelayTime=" + monitorDelayTime);
	}

	public void submitTask() {
		long currentTime = System.currentTimeMillis();
		long maxEndTime = currentTime - monitorDelayTime;
		for (;endTime <= maxEndTime; endTime += monitorInterval) {
			Date start = new Date(endTime - monitorInterval);
			Date end = new Date(endTime);
			List<MonitorConfigDO> logMonitorConfigs = logMonitorConfig.getLogMonitorConfigs();
			for (MonitorConfigDO monitorConfigDO : logMonitorConfigs) {
				if (monitorConfigDO.getRun() == null || !monitorConfigDO.getRun()) {
					continue;
				}
				LogMonitorTask logMonitorTask = new LogMonitorTask(logQueryService, monitorDataPushService, 
						monitorConfigDO, start, end);
				threadPoolExecutor.submit(logMonitorTask);
			}
		}
	}
	
}
