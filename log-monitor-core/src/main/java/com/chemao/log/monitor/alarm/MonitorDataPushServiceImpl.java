package com.chemao.log.monitor.alarm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.chemao.log.monitor.util.HttpUtil;

public class MonitorDataPushServiceImpl implements MonitorDataPushService {
	private String aliyunMetricsUrl ="http://open.cms.aliyun.com/metrics/put";
	
	public String getAliyunMetricsUrl() {
		return aliyunMetricsUrl;
	}

	public void setAliyunMetricsUrl(String aliyunMetricsUrl) {
		this.aliyunMetricsUrl = aliyunMetricsUrl;
	}

	@Override
	public void pushKeywordCount(String userId, String namespace, String logName, String keyword, int value, Date date, String ip) throws Exception {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("[{\"metricName\":\"").append(logName)
			.append("\",\"timestamp\":\"").append(date.getTime())
			.append("\",\"value\":").append(value)
			.append("\",\"unit\":\"个\",\"dimensions\":{\"keyword\":\"").append(keyword)
			.append("\",\"ip\":\"").append(ip)
			.append("\"}}]");
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("namespace", namespace);
		params.put("metrics", stringBuilder.toString());
		HttpUtil.post(aliyunMetricsUrl, params);
	} 
	
}
