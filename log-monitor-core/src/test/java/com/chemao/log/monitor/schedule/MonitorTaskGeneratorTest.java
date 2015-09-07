/**
 * Copyright 2015 chemao.com, Inc. All rights reserved.
 */
package com.chemao.log.monitor.schedule;

import org.junit.Test;

import com.chemao.log.monitor.alarm.MonitorDataPushService;
import com.chemao.log.monitor.alarm.MonitorDataPushServiceImpl;
import com.chemao.log.monitor.collector.ALiyunSlsLogQueryServiceImpl;
import com.chemao.log.monitor.config.LogMonitorConfigImpl;

/**
 * TODO
 * @author xuhf
 * @since 2015年9月7日 上午11:51:27
 * @version V1.0 
 */
public class MonitorTaskGeneratorTest {

	@Test
	public void test() throws Exception {
		MonitorTaskGenerator monitorTaskGenerator = new MonitorTaskGenerator();
		LogMonitorConfigImpl logMonitorConfig = new LogMonitorConfigImpl();
		logMonitorConfig.init();
		monitorTaskGenerator.setLogMonitorConfig(logMonitorConfig );
		ALiyunSlsLogQueryServiceImpl aLiyunSlsLogQueryServiceImpl = new ALiyunSlsLogQueryServiceImpl();
		aLiyunSlsLogQueryServiceImpl.setAccessId("L5ASLE8ue7wTNBxc");
		aLiyunSlsLogQueryServiceImpl.setAccessKey("Y5Axn5jAZxPCTFY1sXwZcDxx6g3SqS");
		aLiyunSlsLogQueryServiceImpl.setProject("log-monitor");
		aLiyunSlsLogQueryServiceImpl.init();
		monitorTaskGenerator.setLogQueryService(aLiyunSlsLogQueryServiceImpl);
		MonitorDataPushService monitorDataPushService = new MonitorDataPushServiceImpl();
		monitorTaskGenerator.setMonitorDataPushService(monitorDataPushService);
		monitorTaskGenerator.init();
		Thread.sleep(2000000L);
	}

}
