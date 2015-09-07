/**
 * Copyright 2015 chemao.com, Inc. All rights reserved.
 */
package com.chemao.log.monitor.collector;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

/**
 * TODO
 * @author xuhf
 * @since 2015年9月6日 上午11:39:50
 * @version V1.0 
 */
public class ALiyunSlsLogQueryServiceImplTest {

	@Test
	public void test() throws Exception {
		ALiyunSlsLogQueryServiceImpl aLiyunSlsLogQueryServiceImpl = new ALiyunSlsLogQueryServiceImpl();
		aLiyunSlsLogQueryServiceImpl.setAccessId("L5ASLE8ue7wTNBxc");
		aLiyunSlsLogQueryServiceImpl.setAccessKey("Y5Axn5jAZxPCTFY1sXwZcDxx6g3SqS");
		aLiyunSlsLogQueryServiceImpl.setProject("log-monitor");
		aLiyunSlsLogQueryServiceImpl.init();
		String logName = "yunfu-kw-counter";
		String keyword = "Json";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startTime = (Date) sdf.parseObject("2015-09-07 11:49:00");
		Date endTime = new Date(startTime.getTime()+59000L);
		Integer count = aLiyunSlsLogQueryServiceImpl.getKeywordCount(logName, keyword, startTime, endTime);
		System.err.println("start:" + startTime);
		System.err.println("end:  " + endTime);
		System.err.println("count:" + count);
	}

}
