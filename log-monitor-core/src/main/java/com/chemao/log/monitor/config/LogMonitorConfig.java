/**
 * Copyright 2015 chemao.com, Inc. All rights reserved.
 */
package com.chemao.log.monitor.config;

import java.util.List;

import com.chemao.log.monitor.domain.MonitorConfigDO;
/**
 * 日志监控配置接口定义
 * @author xuhf
 * @since 2015年9月2日 上午9:47:35
 * @version V1.0 
 */
public interface LogMonitorConfig {
	
	/**
	 * 查询配置项
	 * */
	public List<MonitorConfigDO> getLogMonitorConfigs();
	
	/**
	 * 刷新配置
	 * */
	public void refreshLogMonitorConfigs() throws Exception;
	
}
