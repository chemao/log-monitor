/**
 * Copyright 2015 chemao.com, Inc. All rights reserved.
 */
package com.chemao.log.monitor.config;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.chemao.log.monitor.domain.MonitorConfigDO;

/**
 * TODO
 * @author xuhf
 * @since 2015年9月6日 上午9:31:03
 * @version V1.0 
 */
public class LogMonitorConfigImplTest {
	
	@Test
	public void test() throws Exception {
		LogMonitorConfigImpl logMoni = new LogMonitorConfigImpl();
		logMoni.init();
		List<MonitorConfigDO> monitorConfigDOs = logMoni.getLogMonitorConfigs();
		System.out.println(JSON.toJSONString(monitorConfigDOs));
		Assert.assertNotNull(monitorConfigDOs);
		Assert.assertTrue(!monitorConfigDOs.isEmpty());
	}

}
