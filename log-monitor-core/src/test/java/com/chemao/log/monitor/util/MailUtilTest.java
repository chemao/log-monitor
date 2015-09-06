/**
 * Copyright 2015 chemao.com, Inc. All rights reserved.
 */
package com.chemao.log.monitor.util;

import org.junit.Test;

import com.chemao.log.monitor.util.MailUtil;

/**
 * TODO
 * @author xuhf
 * @since 2015年9月2日 下午4:14:08
 * @version V1.0 
 */
public class MailUtilTest {

	@Test
	public void test() throws Exception {
		MailUtil.sendMail("xuhf@chemao.com.cn", "test", "configdate");
	}

}
