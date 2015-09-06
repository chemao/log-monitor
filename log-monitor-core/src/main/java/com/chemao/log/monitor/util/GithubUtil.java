/**
 * Copyright 2015 chemao.com, Inc. All rights reserved.
 */
package com.chemao.log.monitor.util;

import org.junit.Test;

/**
 * github源码中的数据获取
 * @author xuhf
 * @since 2015年9月2日 下午2:58:37
 * @version V1.0 
 */
public class GithubUtil {
	public static void sendMail(String toEmail, String subject, String content) throws Exception {
			
    }
	
	@Test
	public void test() throws Exception {
		long startTime = System.currentTimeMillis();
		System.err.println(HttpUtil.get("https://raw.githubusercontent.com/chemao/log-monitor/master/keyword-monitor-config"));
		System.err.println(System.currentTimeMillis() - startTime);
		startTime = System.currentTimeMillis();
		System.err.println(HttpUtil.get("https://api.github.com/repos/chemao/log-monitor/contents/keyword-monitor-config"));
		System.err.println(System.currentTimeMillis() - startTime);

	}

}
