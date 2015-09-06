package com.chemao.log.monitor.collector;

import java.util.Date;

public interface LogQueryService {
	
	/**
	 * 统计指定时间段内，关键字出现的数量
	 * @param appName 应用名称
	 * @param keyword 日志钟统计的关键字
	 * @param startTime 开始时间,精确到秒
	 * @param endTime 结束时间，精确到秒
	 * @return 返回统计到的数量。如果当前时间段的日志还未同步或未分析完成返回null；否则返回统计数据
	 * 
	 * */
	public Integer getKeywordCount(String appName, String keyword, Date startTime, Date endTime) throws Exception;
}
