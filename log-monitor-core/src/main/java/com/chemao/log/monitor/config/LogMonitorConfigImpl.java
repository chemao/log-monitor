/**
 * Copyright 2015 chemao.com, Inc. All rights reserved.
 */
package com.chemao.log.monitor.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.chemao.log.monitor.domain.MonitorConfigDO;
import com.chemao.log.monitor.util.HttpUtil;
/**
 * 本地配置文件实现
 * @author xuhf
 * @since 2015年9月2日 上午9:47:35
 * @version V1.0 
 */
public class LogMonitorConfigImpl implements LogMonitorConfig {
	private static Logger logger = LoggerFactory.getLogger(LogMonitorConfigImpl.class);
	private String configFileName = "https://raw.githubusercontent.com/chemao/log-monitor/master/keyword-monitor-config";
	private String oldConfigCountent = "";
	private List<MonitorConfigDO> logMonitorConfigs;
	
	public String getConfigFileName() {
		return configFileName;
	}

	public void setConfigFileName(String configFileName) {
		this.configFileName = configFileName;
	}

	public void init() throws Exception {
		refreshLogMonitorConfigs();
	}
	
	public void refreshLogMonitorConfigs() throws Exception {
		try {
			String configContent = HttpUtil.get(configFileName);
			if (configContent == null || configContent.trim().isEmpty()) {
				throw new Exception("logMonitorConfigs is empty");
			} else if (oldConfigCountent.equals(configContent)) {
				return;
			}
			
			List<MonitorConfigDO> configDOs = JSON.parseObject(configContent, new TypeReference<List<MonitorConfigDO>>(){});
			if (configDOs == null || configDOs.isEmpty()) {
				throw new Exception("logMonitorConfigs is empty");
			} else {
				this.logMonitorConfigs = configDOs;		
				this.oldConfigCountent = configContent;
			}
		} catch (Throwable throwable) {
			logger.error("REFRESH_LOG_MONITOR_CONFIG_ERROR", throwable);
		}
	}
	
	@Override
	public List<MonitorConfigDO> getLogMonitorConfigs() {
		return logMonitorConfigs;
	}
	
	
}
