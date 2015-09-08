/**
 * Copyright 2015 chemao.com, Inc. All rights reserved.
 */
package com.chemao.log.monitor.alarm;

import java.util.Date;

import org.junit.Test;

/**
 * TODO
 * @author xuhf
 * @since 2015年9月6日 上午9:37:21
 * @version V1.0 
 */
public class MonitorDataPushServiceImplTest {
	
	@Test
	public void test() throws Exception {
		MonitorDataPushService monitorDataPushService = new MonitorDataPushServiceImpl();
		String userId = "1434797818170073";
		String namespace = "acs/custom/1434797818170073";
		String logName= "cloudpay_kw_counter";
		String keyword = "ERROR";
		int value = 800;
		Date date = new Date();
		monitorDataPushService.pushKeywordCount(userId, namespace, logName, keyword, value, date, null);
	}

}
