/**
 * Copyright 2015 chemao.com, Inc. All rights reserved.
 */
package com.chemao.log.monitor.util;

import org.junit.Assert;
import org.junit.Test;

import com.chemao.log.monitor.util.TextFileUtil;

/**
 * @author xuhf
 * @since 2015年9月2日 下午1:48:09
 * @version V1.0 
 */
public class TextFileUtilTest {

	@Test
	public void test() throws Exception {
		String content = "{\"fileA\":1234, \"fieldB\":\"fileBValue\"}";
		String fileName = "/Users/xuhf/Desktop/testFile.dat";
		TextFileUtil.saveStringToFile(fileName, content);
		String fileContent = TextFileUtil.loadFileContentAsString(fileName);
		Assert.assertEquals(content, fileContent);
	}
}
