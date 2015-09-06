package com.chemao.log.monitor.alarm;

import java.util.Date;

public interface MonitorDataPushService {

	public void pushKeywordCount(String userId, String namespace, String logName, String keyword, int value, Date date, String ip) throws Exception;
}
